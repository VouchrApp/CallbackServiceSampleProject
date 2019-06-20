package ca.vouchr.payments.callback.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

@Component
public class VouchrPreAuthenticationFilter implements Filter {

    private static final Logger logger = Logger.getLogger(VouchrPreAuthenticationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.info("SS");
        if (request instanceof HttpServletRequest
                && response instanceof HttpServletResponse) {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpServletResponse servletResponse = (HttpServletResponse) response;

            ResettableStreamHttpServletRequest requestToCache = new ResettableStreamHttpServletRequest(servletRequest);

            String vouchrSignature = servletRequest.getHeader("X-Vouchr-Signature");

            String authorization = servletRequest.getHeader("Authorization");

            logger.info("GOT AUTHORIZATION HEADER: " + authorization);
            logger.info("GOT SIGNATURE: " + vouchrSignature);

            String bearerToken = null;

            byte[] signature = new byte[0];
            byte[] content = new byte[0];
            if(!StringUtils.isEmpty(vouchrSignature)) {
                signature = Base64.getUrlDecoder().decode(vouchrSignature);
                //content = requestToCache.getContentAsByteArray();
                content = toByteArray(requestToCache.getInputStream());
                logger.info("CONTENT: " + new String(content, StandardCharsets.UTF_8));
            }


            if (authorization != null && authorization.startsWith("Bearer ")) {

                bearerToken = authorization.replaceAll("^Bearer ", "").trim();

                VouchrPreAuthentication authToken = new VouchrPreAuthentication(bearerToken, content, signature);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            logger.info("chain.doFilter()");

            //chain.doFilter(request,response);

            requestToCache.resetInputStream();
            chain.doFilter(requestToCache, response);
        } else {
            chain.doFilter(request,response);
        }
    }

    private static class ResettableStreamHttpServletRequest extends
            HttpServletRequestWrapper {

        private byte[] rawData;
        private HttpServletRequest request;
        private ResettableServletInputStream servletStream;

        public ResettableStreamHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
            this.servletStream = new ResettableServletInputStream();
        }


        public void resetInputStream() {
            if(rawData != null) {
                servletStream.setSourceStream(new ByteArrayInputStream(rawData));
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            if (rawData == null) {
                rawData = toByteArray(this.request.getInputStream());
                servletStream.setSourceStream(new ByteArrayInputStream(rawData));
            }
            return servletStream;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            if (rawData == null) {
                rawData = toByteArray(this.request.getInputStream());
                servletStream.setSourceStream(new ByteArrayInputStream(rawData));
            }
            return new BufferedReader(new InputStreamReader(servletStream));
        }


        private class ResettableServletInputStream extends ServletInputStream {

            private InputStream sourceStream;

            private boolean finished = false;


            private void setSourceStream(InputStream sourceStream) {
                this.sourceStream = sourceStream;
                this.finished = false;
            }

            /**
             * Return the underlying source stream (never {@code null}).
             */
            public final InputStream getSourceStream() {
                return this.sourceStream;
            }


            @Override
            public int read() throws IOException {
                int data = this.sourceStream.read();
                if (data == -1) {
                    this.finished = true;
                }
                return data;
            }

            @Override
            public int available() throws IOException {
                return this.sourceStream.available();
            }

            @Override
            public void close() throws IOException {
                super.close();
                this.sourceStream.close();
            }

            @Override
            public boolean isFinished() {
                return this.finished;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException();
            }
        }
    }


    private static byte[] toByteArray(InputStream inputStream) throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        // read bytes from the input stream and store them in buffer
        while ((len = inputStream.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }

    @Override
    public void destroy() {

    }
}
