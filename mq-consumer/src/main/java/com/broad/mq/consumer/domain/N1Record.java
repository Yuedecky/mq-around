package com.broad.mq.consumer.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class N1Record implements Serializable {

    private Integer id;


    private String messageId;

    private long timeStamp;

}
