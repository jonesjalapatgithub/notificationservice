package com.jonesjalapat.blog.notification.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jonesjalapat.blog.notification.model.JobModel;
import com.jonesjalapat.blog.notification.service.SNSService;
import com.jonesjalapat.blog.notification.service.SQSListener;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class NotificationController {

    private final SNSService snsService;

    private final SQSListener sqsListener;

    /**
     * Creates an entry of Tradesman.
     *
     * @param jobModel the Job Request
     * @return the response entity
     */
    @PostMapping(value = "/job")
    public ResponseEntity<Long> createJob(@Valid @RequestBody JobModel jobModel)
            throws JsonProcessingException {
        snsService.sendSNSEvent(jobModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gets list of Job Requests requested for the given trade.
     *
     * @return the response entity
     */
    @GetMapping(value = "/job", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJobs()
            throws JsonProcessingException {
        String jobModels =  sqsListener.retrieveSQSEvent();
        return ResponseEntity.status(HttpStatus.OK).body(jobModels);
    }

}
