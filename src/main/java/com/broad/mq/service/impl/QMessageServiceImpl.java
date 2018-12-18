package com.broad.mq.service.impl;

import com.broad.mq.domain.QMessage;
import com.broad.mq.mapper.QMessageMapper;
import com.broad.mq.service.QMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QMessageServiceImpl implements QMessageService {


    @Resource(name = "QMessageMapper")
    private QMessageMapper qMessageMapper;

    /**
     * 获取QMessage
     *
     * @param messageId
     * @return
     */
    public QMessage getMessage(String messageId) {
        if (StringUtils.isBlank(messageId)) {
            return null;
        }
        return qMessageMapper.selectQMessageById(messageId);
    }

    /**
     * 添加消息
     *
     * @param qMessage
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void addQMessage(QMessage qMessage) {
        if (qMessage == null) {
            return;
        }
        qMessageMapper.addQMessage(qMessage);
    }

    /**
     * 更新消息
     *
     * @param qMessage
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void updateQMessage(QMessage qMessage) {
        if (qMessage == null) {
            return ;
        }
        qMessageMapper.updateQMessage(qMessage);
    }

    /**
     * 删除消息
     *
     * @param messageId
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void deleteQMessage(String messageId) {
        if (StringUtils.isBlank(messageId)) {
            return ;
        }
        qMessageMapper.deleteQMessageById(messageId);
    }

    /**
     * 获取所有消息
     *
     * @return
     */
    public List<QMessage> selectAllQMessage(Long currentTime) {
        return qMessageMapper.findAllQMessages(currentTime);
    }


}
