package cn.whu.gugugu.repository;

import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.mapper.PartyRecordMapper;
import cn.whu.gugugu.generated.mapper.TransactionMapper;
import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.PartyRecord;
import cn.whu.gugugu.generated.model.PartyRecordExample;
import cn.whu.gugugu.generated.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    public PartyRecord getRecord(String partyId, String userId) {
        PartyRecordExample example = new PartyRecordExample();
        example.createCriteria().andPartyIdEqualTo(partyId).andUserIdEqualTo(userId);
        List<PartyRecord> list = recordMapper.selectByExample(example);
        return list.size() == 0 ? null : list.get(0);
    }

    public List<PartyRecord> getRecords(String partyId) {
        PartyRecordExample example = new PartyRecordExample();
        example.createCriteria().andPartyIdEqualTo(partyId);
        List<PartyRecord> list = recordMapper.selectByExample(example);
        return list;
    }

    public Party getBasicInfo(String partyId) {
        return partyMapper.selectByPrimaryKey(partyId);
    }
}
