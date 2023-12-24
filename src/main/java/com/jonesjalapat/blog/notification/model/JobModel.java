package com.jonesjalapat.blog.notification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobModel {

    @NotEmpty(message = "The type is required.")
    private String type;

    @NotEmpty(message = "The tradeIds are required.")
    private String requirement;

    @NotEmpty(message = "The availability is required.")
    public String availability;

    @NotEmpty(message = "The pinCode is required.")
    private String pinCode;

    @NotEmpty(message = "The phoneNumber is required.")
    private Integer phoneNumber;
}
