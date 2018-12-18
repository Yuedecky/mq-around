package com.broad.mq.consumer.service.impl;

import com.broad.mq.consumer.domain.N1Record;
import com.broad.mq.consumer.mapper.N1RecordMapper;
import com.broad.mq.consumer.service.N1RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class N1RecordServiceImpl implements N1RecordService {


    @Resource
    private N1RecordMapper n1RecordMapper;


    @Override
    public N1Record selectN1RecordByMessageId(String messageId) {
        return null;
    }

    @Override
    public void deleteN1Record(String messageId) {

    }

    @Override
    public void addN1Record(N1Record n1Record) {

    }
}
