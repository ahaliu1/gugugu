package cn.whu.gugugu.service;

import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.PartyRecord;
import cn.whu.gugugu.generated.model.Transaction;
import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.repository.PartyRepository;
import cn.whu.gugugu.repository.UserRepository;
import cn.whu.gugugu.service.impl.PartyImpl;
import org.springframework.stereotype.Service;

@Service
public class PartyService implements PartyImpl {
    PartyRepository partyRepository = new PartyRepository();
    UserRepository userRepository = new UserRepository();

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

        //聚会信息的钱
        Party temp = partyRepository.getBasicInfo(partyId);
        Party party = new Party();
        party.setTotalSum(temp.getTotalSum() + fee);
        partyRepository.updateParty(party);
    }

    @Override
    public PartyRecord getRecord(String partyId) {
        return partyRepository.getRecord(partyId);
    }

    @Override
    public Party getInfo(String partyId) {
        return partyRepository.getBasicInfo(partyId);
    }
}
