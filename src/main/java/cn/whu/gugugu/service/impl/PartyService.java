package cn.whu.gugugu.service.impl;

import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.PartyRecord;
import cn.whu.gugugu.generated.model.Transaction;
import cn.whu.gugugu.generated.model.User;

import java.util.List;

public interface PartyService {
    public void createParty(Party party);

    public void createRecord(PartyRecord record);

    public void createTransaction(Transaction transaction);

    public void updateParty(Party party);

    public void pay(User user, Party party);

    public PartyRecord getRecord(String partyId, String userId);

    public List<PartyRecord> getRecords(String partyId);

    public Party getInfo(String partyId);
}
