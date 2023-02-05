package com.waigo.backend_api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.waigo.backend_api.Model.Repositories")
@EntityScan(basePackages = "com.waigo.backend_api.Model.Entities")
public class TestConfig {
}