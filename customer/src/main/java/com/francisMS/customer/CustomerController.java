package com.francisMS.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("new cust reg {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @DeleteMapping(path = "/delete/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        log.info("del cust  {}", customerId);
        customerService.deleteCustomer(customerId);

    }
}
