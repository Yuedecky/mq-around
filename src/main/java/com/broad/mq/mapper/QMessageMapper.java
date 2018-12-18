package com.broad.mq.mapper;

import com.broad.mq.domain.QMessage;

import java.util.List;

public interface QMessageMapper {

    /**
     *
     * 根据messageId查找消息
     * @param messageId
     * @return
     */
    QMessage selectQMessageById(String messageId);


    /**
     * 保存qMessage消息
     * @param qMessage
     */
    void addQMessage(QMessage qMessage);


    /**
     * 更新qMessage消息
     * @param qMessage
     */
    void updateQMessage(QMessage qMessage);


    /**
     * 删除消息
     * @param messageId
     */
    void deleteQMessageById(String messageId);


    /**
     * 查询所有消息列表
     * @return
     */
    List<QMessage> findAllQMessages(Long currentTimeStamp);

}
