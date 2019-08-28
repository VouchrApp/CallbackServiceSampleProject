package com.example.vouchr.payments.callbackservice.controller;

import ca.vouchr.payments.callback.dto.CreateRequestDto;
import ca.vouchr.payments.callback.dto.CreateResponseDto;
import ca.vouchr.payments.callback.dto.PaymentDestDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service/status")
public class StatusController {

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String create() {
       return "OK";
    }
}
