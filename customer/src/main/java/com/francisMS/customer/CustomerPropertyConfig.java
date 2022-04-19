package com.francisMS.customer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "fraud")
@RefreshScope
public class CustomerPropertyConfig {
    private String checkUrl;
    private String deleteUrl;
}
