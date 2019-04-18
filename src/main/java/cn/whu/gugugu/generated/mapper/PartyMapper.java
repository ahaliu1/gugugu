package cn.whu.gugugu.generated.mapper;

import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.PartyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyMapper {
    long countByExample(PartyExample example);

    int deleteByExample(PartyExample example);

    int deleteByPrimaryKey(String partyId);

    int insert(Party record);

    int insertSelective(Party record);

    List<Party> selectByExample(PartyExample example);

    Party selectByPrimaryKey(String partyId);

    int updateByExampleSelective(@Param("record") Party record, @Param("example") PartyExample example);

    int updateByExample(@Param("record") Party record, @Param("example") PartyExample example);

    int updateByPrimaryKeySelective(Party record);

    int updateByPrimaryKey(Party record);
}