package com.learn.demo.ai.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class DataSummary {
    private String data;
    private String contextMessage;
}
