package com.francisMS.customer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "fraud")
public class CustomerPropertyConfig {
    private String checkUrl;
    private String deleteUrl;
}
