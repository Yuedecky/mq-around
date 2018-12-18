package com.broad.mq.consumer.mapper;

import com.broad.mq.consumer.domain.N1Record;

import java.util.Date;

public interface N1RecordMapper {

    /**
     * 根据消息id获取消费记录
     *
     * @param messageId 消息id
     * @return
     */
    N1Record selectN1Record(String messageId);

    /**
     * 添加消息消费记录
     *
     * @param n1Record 消费记录
     * @return
     */
    int addN1Record(N1Record n1Record);

    /**
     * 删除消费记录
     *
     * @param timeStamp 时间戳
     * @return
     */
    int deleteN1Record(Date timeStamp);

}
