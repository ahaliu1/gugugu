package cn.whu.gugugu.service;

import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.PartyRecord;
import cn.whu.gugugu.generated.model.Transaction;
import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.repository.PartyRepository;
import cn.whu.gugugu.repository.UserRepository;
import cn.whu.gugugu.service.impl.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyServiceImpl implements PartyService {
    @Autowired
    PartyRepository partyRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void createParty(Party party) {
        partyRepository.createParty(party);
    }

    @Override
    public void createRecord(PartyRecord record) {
        partyRepository.createRecord(record);
    }

    @Override
    public void createTransaction(Transaction transaction) {
        partyRepository.createTransaction(transaction);
    }

    @Override
    public void updateParty(Party party) {
        partyRepository.updateParty(party);
    }

    @Override
    public void pay(String userId, String partyId, int fee) {
        //自己的钱
        User user = new User();
        user.setOpenId(userId);
        user.setAccount(user.getAccount() - fee);
        userRepository.updateUser(user);

        //聚会信息的总数
        Party temp = partyRepository.getBasicInfo(partyId);
        Party party = new Party();
        party.setTotalSum(party.getTotalSum() + 1);
        partyRepository.updateParty(party);
    }

    @Override
    public PartyRecord getRecord(String partyId, String userId) {
        return partyRepository.getRecord(partyId, userId);
    }

    @Override
    public List<PartyRecord> getRecords(String partyId) {
        return partyRepository.getRecords(partyId);
    }

    @Override
    public Party getInfo(String partyId) {
        return partyRepository.getBasicInfo(partyId);
    }
}
