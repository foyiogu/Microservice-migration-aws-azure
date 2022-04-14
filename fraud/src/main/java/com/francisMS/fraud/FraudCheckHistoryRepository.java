package com.francisMS.fraud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Long> {

    FraudCheckHistory findByCustomerId(Long customerId);
}
