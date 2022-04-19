package com.francisMS.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              CustomerPropertyConfig config) {

    //    @Value("${fraud.check.url}")
//    private static String fraudCheckUrl;
//
//    @Value("${fraud.delete.url}")
//    private static String fraudDeleteUrl;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        log.error("---------- {}", config.getCheckUrl());
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email()) //todo: check if email is valid and not taken
                .build(); // todo: store in db

        //check if fraudster
        customerRepository.saveAndFlush(customer); //to get access to id after saving
        FraudCheckResponse fraudCheckResponse = restTemplate
//                .getForObject(Objects.requireNonNull(environment.getProperty("fraud.check.url")),
                .getForObject(config.getCheckUrl(),
                        FraudCheckResponse.class,
                        customer.getId());



        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).get();

        log.error("---------- {}", config.getDeleteUrl());
        Map<String, Long> params = new HashMap<>();
        params.put("customerId", customerId);
//        restTemplate.delete(Objects.requireNonNull(environment.getProperty("fraud.delete.url")),params);
        restTemplate.delete(config.getDeleteUrl(), params);

        customerRepository.delete(customer);
    }
}
