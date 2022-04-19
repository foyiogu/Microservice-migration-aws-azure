package com.francisMS.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public record FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {

    public boolean isFraudulentCustomer(Long customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false).createdAt(LocalDateTime.now()).build()
        );
        return false;
    }

    public void deleteEntry(Long customerId){
        FraudCheckHistory fraudCheckHistory = fraudCheckHistoryRepository.findByCustomerId(customerId);
        fraudCheckHistoryRepository.delete(fraudCheckHistory);
        log.info("fraud del");
    }

}
