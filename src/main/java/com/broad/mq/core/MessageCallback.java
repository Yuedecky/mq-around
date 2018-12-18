package com.broad.mq.core;

public interface MessageCallback {

    /**
     * 事务消息处理成功
     *
     * @param messageId 消息id
     */
    void onSuccess(String messageId);

    /**
     * 事务消息处理失败
     *
     * @param e         异常
     * @param messageId 消息id
     */
    void onFail(Exception e, String messageId);
}
