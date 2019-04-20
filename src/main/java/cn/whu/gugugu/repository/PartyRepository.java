package cn.whu.gugugu.repository;

import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.mapper.PartyRecordMapper;
import cn.whu.gugugu.generated.mapper.TransactionMapper;
import cn.whu.gugugu.generated.mapper.UserMapper;
import cn.whu.gugugu.generated.model.*;
import org.springframework.beans.factory.annotation.Autowired;

public class PartyRepository {
    @Autowired
    PartyMapper partyMapper;
    @Autowired
    PartyRecordMapper recordMapper;
    @Autowired
    TransactionMapper transactionMapper;

    public void createParty(Party party) {
        partyMapper.insertSelective(party);
    }

    public void createRecord(PartyRecord record) {
        recordMapper.insertSelective(record);
    }

    public void createTransaction(Transaction transaction) {
        transactionMapper.insertSelective(transaction);
    }

    public void updateParty(Party party) {
        partyMapper.updateByPrimaryKeySelective(party);
    }

    public PartyRecord getRecord(String partyId) {
        PartyRecordExample example = new PartyRecordExample();
        example.createCriteria().andPartyIdEqualTo(partyId);
        return recordMapper.selectByExample(example).get(0);
    }

    public Party getBasicInfo(String partyId) {
        return partyMapper.selectByPrimaryKey(partyId);
    }
}
