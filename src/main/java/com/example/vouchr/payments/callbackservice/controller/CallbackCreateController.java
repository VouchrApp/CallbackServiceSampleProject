package com.example.vouchr.payments.callbackservice.controller;

import ca.vouchr.payments.callback.dto.CreateRequestDto;
import ca.vouchr.payments.callback.dto.CreateResponseDto;
import ca.vouchr.payments.callback.dto.PaymentDestDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service/callback/create")
public class CallbackCreateController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateResponseDto create(@RequestBody CreateRequestDto requestDto) {
        CreateResponseDto responseDto = new CreateResponseDto();
        responseDto.getPayment().setSource(requestDto.getPayment().getSource());

        PaymentDestDto paymentDestDto = new PaymentDestDto();
        paymentDestDto.setType(requestDto.getPayment().getSource().getType());
        paymentDestDto.setProperties(requestDto.getPayment().getSource().getProperties());

        responseDto.getPayment().setDest(paymentDestDto);

        return responseDto;
    }
}
