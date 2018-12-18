package com.broad.mq.consumer.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class N2Record implements Serializable {


    private Integer id;

    private String businessMark;


    private long timeStamp;


    private String destination;
}
