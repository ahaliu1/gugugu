package cn.whu.gugugu.service;

import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.model.Party;
import cn.whu.gugugu.repository.PartyRepository;
import cn.whu.gugugu.service.impl.PartyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService implements PartyImpl {
    PartyRepository partyRepository = new PartyRepository();

    @Override
    public void createParty(Party party) {
        partyRepository.createParty(party);
    }

    @Override
    public void updateParty(Party party) {
        partyRepository.updateParty(party);
    }
}
