package com.francisMS.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email()) //todo: check if email is valid and not taken
                .build(); // todo: store in db

        //check if fraudster
        customerRepository.saveAndFlush(customer); //to get access to id after saving
        FraudCheckResponse fraudCheckResponse = restTemplate
                .getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
    }

    public void deleteCustomer(Long customerId){
        Customer customer = customerRepository.findById(customerId).get();

        Map<String, Long> params = new HashMap<>();
        params.put("customerId", customerId);
        restTemplate.delete("http://FRAUD/api/v1/fraud-check/delete/{customerId}",params);

        customerRepository.delete(customer);
    }
}
