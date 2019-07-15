# Callback Service Sample Project

## Description

This is a sample service to serve as the model for your own callback payments microservice for the [VouchrSDK](https://www.vouchrsdk.com).  Using this service as a base, you should
be able to implement your own custom payment services in-house.

As configured, this sample service will log all incoming and outgoing requests, and verify the identity and validity of the incoming information

## Stack

This is a [Spring Boot](https://spring.io/projects/spring-boot) project, harnessing spring-security and web services

## Project layout

* ca.vouchr.payments.callbacks - Standard VouchrSDK callbacks components that should be useful to you.  You should not have to modify anything in here
* ca.vouchr.payments.callbacks.conf - Contains authentication and validation components to ensure the validity of data posted to you
* ca.vouchr.payments.callbacks.dto - Vouchr SDK Dto models representing the incoming and returned data
* ca.vouchr.payments.callbacks.service - Vouchr Signature service - verifies the signature on jwt and well as the signatures of the request contents

## Configuration

In application.properties, see the following:

```
logging.level.org.zalando.logbook=TRACE

vouchr.signature.public-key.url=https://api.vouchrsdk.com/api/v2/publicKey
vouchr.signature.public-key.api-key=SURPRIISE_APP
```

* logging.level.org.zalando.logbook : controls how much info is logged
* vouchr.signature.public-key.url : should reflect the api url used when communicating with your vouchrsdk server
* vouchr.signature.public-key.api-key : use an api key configured in the [VouchrSDK Dashboard](https://www.vouchrsdk.com/dashboard/) to verify your requests came from this app

## Customizing

### com.example.vouchr.payments.callbackservice.controller.CallbackCreateController.create

this receives requests from the VouchrSDK whenever a voucher is created.  It contains voucher and payment destination info and should return any payment information that should be added to the voucher on create.

### com.example.vouchr.payments.callbackservice.controller.CallbackClaimController.claim

this receives requests from the VouchrSDK whenever a voucher is claimed.  It contains voucher and payment destination info and should return any payment information that should be added to the voucher on claim.  

## Deployment

The maven pom is currently configured to work with appengine,

type:

To run locally:
```
mvn clean package appengine:run
```

To deploy and run to an appengine project:
```
mvn clean package appengine:deploy -Dapp.deploy.project=yourproject -Dapp.deploy.promote=true -Dapp.deploy.version=1.0
```


