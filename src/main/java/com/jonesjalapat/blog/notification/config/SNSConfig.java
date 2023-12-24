package com.jonesjalapat.blog.notification.config;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SNSConfig {


    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonSNS amazonSNS() {
        return AmazonSNSClientBuilder.standard().withRegion(region)
                .build();
    }

}
