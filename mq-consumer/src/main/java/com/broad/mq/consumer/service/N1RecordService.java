package com.broad.mq.consumer.service;

import com.broad.mq.consumer.domain.N1Record;

public interface N1RecordService {


    /**
     *
     * @param messageId
     * @return
     */
    N1Record selectN1RecordByMessageId(String messageId);

    /**
     *
     * @param n1Record
     */
    void addN1Record(N1Record n1Record);


    /**
     *
     * @param messageId
     */
    void deleteN1Record(String messageId);
}
