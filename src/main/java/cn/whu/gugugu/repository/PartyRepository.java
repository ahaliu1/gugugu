package cn.whu.gugugu.repository;

import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.model.Party;
import org.springframework.beans.factory.annotation.Autowired;

public class PartyRepository {
    @Autowired
    PartyMapper mapper;

    public void createParty(Party party) {
        mapper.insertSelective(party);
    }

    public void updateParty(Party party) {
        mapper.updateByPrimaryKeySelective(party);
    }
}
