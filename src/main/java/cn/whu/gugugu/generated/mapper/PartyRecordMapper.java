package cn.whu.gugugu.generated.mapper;

import cn.whu.gugugu.generated.model.PartyRecord;
import cn.whu.gugugu.generated.model.PartyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRecordMapper {
    long countByExample(PartyRecordExample example);

    int deleteByExample(PartyRecordExample example);

    int deleteByPrimaryKey(String recordId);

    int insert(PartyRecord record);

    int insertSelective(PartyRecord record);

    List<PartyRecord> selectByExample(PartyRecordExample example);

    PartyRecord selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") PartyRecord record, @Param("example") PartyRecordExample example);

    int updateByExample(@Param("record") PartyRecord record, @Param("example") PartyRecordExample example);

    int updateByPrimaryKeySelective(PartyRecord record);

    int updateByPrimaryKey(PartyRecord record);
}