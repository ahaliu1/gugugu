package cn.whu.gugugu.service.impl;

import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.generated.model.PartyRecord;
import cn.whu.gugugu.generated.model.Transaction;

public interface PartyImpl {
    public void createParty(Party party);

    public void createRecord(PartyRecord record);

    public void createTransaction(Transaction transaction);

    public void updateParty(Party party);

    public void pay(String userId, String partyId, int deposit);

    public PartyRecord getRecord(String partyId, String userId);

    public Party getInfo(String partyId);
}
