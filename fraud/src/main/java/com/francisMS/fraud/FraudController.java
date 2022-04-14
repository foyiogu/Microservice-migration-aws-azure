package com.francisMS.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public record   FraudController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId){

        boolean fraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("request for {}", customerId);
        return new FraudCheckResponse(fraudulentCustomer);
    }

    @DeleteMapping(path = "/delete/{customerId}")
    public void deleteEntry(@PathVariable("customerId") Long customerId){

//        boolean fraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("request deleted for {}", customerId);
        fraudCheckService.deleteEntry(customerId);
    }
}
