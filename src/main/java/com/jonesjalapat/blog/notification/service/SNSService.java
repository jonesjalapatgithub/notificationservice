package com.jonesjalapat.blog.notification.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.google.gson.Gson;
import com.jonesjalapat.blog.notification.model.JobModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SNSService {

    private final AmazonSNS amazonSns;

    @Value("${topic.name}")
    private String topicArn;

    public void sendSNSEvent(JobModel jobModel) {
        try {
            String message = new Gson().toJson(jobModel);
            PublishRequest request = new PublishRequest().withMessage(message).withTopicArn(topicArn);
            amazonSns.publish(request);
        } catch (Exception e) {
            throw e;
        }
    }
}
