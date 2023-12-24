package com.jonesjalapat.blog.notification.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.google.gson.Gson;
import com.jonesjalapat.blog.notification.model.JobModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SQSListener {

    private final AmazonSQS amazonSQSClient;

    @Value("${queue.name}")
    private String queueName;

    public String retrieveSQSEvent() {
        String queueUrl = amazonSQSClient.getQueueUrl(queueName).getQueueUrl();
        log.info("Reading SQS Queue done: URL {}", queueUrl);
        ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);
        List<JobModel> jobModels = new ArrayList<>();
        for (Message message : receiveMessageResult.getMessages()) {
            JobModel jobModel = new Gson().fromJson(message.getBody(), JobModel.class);
            jobModels.add(jobModel);
        }
        return new Gson().toJson(jobModels);
    }

}
