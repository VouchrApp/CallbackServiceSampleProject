package com.example.vouchr.payments.callbackservice.controller;

import ca.vouchr.payments.callback.dto.ClaimRequestDto;
import ca.vouchr.payments.callback.dto.ClaimResponseDto;
import ca.vouchr.payments.callback.dto.CreateRequestDto;
import ca.vouchr.payments.callback.dto.CreateResponseDto;
import ca.vouchr.payments.callback.dto.PaymentDestDto;
import ca.vouchr.payments.callback.dto.PaymentRedeemDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("service/callback/claim")
public class CallbackClaimController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ClaimResponseDto claim(@RequestBody ClaimRequestDto requestDto) {
        ClaimResponseDto responseDto = new ClaimResponseDto();
        responseDto.getPayment().setDest(requestDto.getPayment().getDest());

        PaymentRedeemDto paymentRedeemDto = new PaymentRedeemDto();
        paymentRedeemDto.setType(requestDto.getPayment().getDest().getType());
        paymentRedeemDto.setProperties(requestDto.getPayment().getDest().getProperties());

        responseDto.getPayment().setRedeem(paymentRedeemDto);

        return responseDto;
    }
}
