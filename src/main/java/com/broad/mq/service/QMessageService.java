package com.broad.mq.service;

import com.broad.mq.domain.QMessage;

import java.util.List;

public interface QMessageService {

    /**
     * 获取QMessage
     *
     * @param messageId
     * @return
     */
    QMessage getMessage(String messageId);

    /**
     * 添加消息
     *
     * @param qMessage
     */
    void addQMessage(QMessage qMessage);

    /**
     * 更新消息
     *
     * @param qMessage
     */
    void updateQMessage(QMessage qMessage);

    /**
     * 删除消息
     *
     * @param messageId
     */
    void deleteQMessage(String messageId);

    /**
     * 获取所有消息
     *
     * @return
     */
    List<QMessage> selectAllQMessage(Long currentTime);
}
