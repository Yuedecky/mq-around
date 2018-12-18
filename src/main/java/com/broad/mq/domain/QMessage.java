package com.broad.mq.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class QMessage implements Serializable {



    private Integer id;

    /**
     * id消息
     */
    private String messageId;


    /**
     * 业务标识
     */
    private String businessMark;

    /**
     * 消息内容
     */
    private String content;


    /**
     * 重试次数
     */
    private Integer retry;


    /**
     * 消息投递地址
     */
    private String destination;


    /**
     * 消息持久化
     */
    private int persistent;


    /**
     * 消息时间戳
     */
    private long timeStamp;

}
