package com.example.project.model;

import java.time.Instant;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Response {

    protected Instant timeStamp;
    protected String message;
    protected Map<?, ?> data;

}
// password hashing
// java doc & comments
// go back and do direct JDBC after application is working