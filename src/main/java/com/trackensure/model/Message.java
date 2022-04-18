package com.trackensure.model;

import lombok.Data;

@Data
public class Message {
    private Long id;
    private User user;
    private String text;
    private String timeStamp;
}
