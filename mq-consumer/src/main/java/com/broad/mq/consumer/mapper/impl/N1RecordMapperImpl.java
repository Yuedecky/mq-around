package com.broad.mq.consumer.mapper.impl;

import com.broad.mq.consumer.domain.N1Record;
import com.broad.mq.consumer.mapper.N1RecordMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

@Repository
public class N1RecordMapperImpl implements N1RecordMapper {



    @Resource
    private SqlSession sqlSession;

    /**
     * 根据消息id获取消费记录
     *
     * @param messageId 消息id
     * @return
     */
    public N1Record selectN1Record(String messageId) {
        return sqlSession.selectOne("com.broad.mq.mapper.N1RecordMapper.selectN1Record", messageId);
    }

    /**
     * 添加消息消费记录
     *
     * @param n1Record 消费记录
     * @return
     */
    public int addN1Record(N1Record n1Record) {
        return sqlSession.insert("com.broad.mq.mapper.N1RecordMapper.addN1Record",n1Record);
    }

    /**
     * 删除消费记录
     *
     * @param timeStamp 时间戳
     * @return
     */
    public int deleteN1Record(Date timeStamp) {
        return sqlSession.delete("com.broad.mq.mapper.N1RecordMapper.deleteN1Record",timeStamp);
    }

}
