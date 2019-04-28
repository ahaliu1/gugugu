package cn.whu.gugugu.controller;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.commons.MessageResponse;
import cn.whu.gugugu.domain.BaseResponse;
import cn.whu.gugugu.domain.UserDetailResponse;
import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.mapper.TransactionMapper;
import cn.whu.gugugu.generated.model.Transaction;
import cn.whu.gugugu.generated.model.TransactionExample;
import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.service.UserService;
import cn.whu.gugugu.service.impl.UserImpl;
import cn.whu.gugugu.utils.FixedPointNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


class TransactionItem{

    private String transaction_id;

    private String party_id;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getParty_id() {
        return party_id;
    }

    public void setParty_id(String party_id) {
        this.party_id = party_id;
    }

    public String getParty_name() {
        return party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    public long getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(long payment_time) {
        this.payment_time = payment_time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    private String party_name;

    private long payment_time;

    private String money;

}


class TransactionData{

    private ArrayList<TransactionItem> transaction = new ArrayList<>();

    public ArrayList<TransactionItem> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<TransactionItem> transaction) {
        this.transaction = transaction;
    }

    public void addTransactionItem(TransactionItem item){
        transaction.add(item);
    }
}


class TransactionResponse extends MessageResponse {

    private TransactionData data;

    public TransactionData getData() {
        return data;
    }

    public void setData(TransactionData data) {
        this.data = data;
    }
}


@RestController
public class UserController extends AuthenticatedController {
    UserImpl userService = new UserService();

    @Autowired
    private TransactionMapper mapper;

    @Autowired
    private PartyMapper mapper1;

    @RequestMapping(value = "/account/detail", method = RequestMethod.GET)
    public BaseResponse userDetail() {
        User user = userService.getUser(this.getRequestedUser().getOpenId());
        return new BaseResponse("ok",
                new UserDetailResponse(user.getUserName(),
                        user.getHeader(),
                        FixedPointNumber.toString(user.getAccount())));
    }

    @RequestMapping(value = "/account/transaction", method = RequestMethod.GET)
    public TransactionResponse transaction(){
        User user = getRequestedUser();
        TransactionExample example = new TransactionExample();
        example.createCriteria().andUserIdEqualTo(user.getOpenId());
        List<Transaction> records = mapper.selectByExample(example);
        TransactionData data = new TransactionData();
        for (Transaction t: records) {
            TransactionItem i = new TransactionItem();
            i.setMoney(FixedPointNumber.toString(t.getMoney()));
            String party_id = t.getPartyId();
            i.setParty_id(party_id);
            if (party_id.equals("TOP") || party_id.equals("WITHDRAW")){
                i.setParty_name(party_id);
            }else{
                i.setParty_name(mapper1.selectByPrimaryKey(t.getPartyId()).getPartySubject());
            }
            i.setPayment_time(t.getPaymentTime().getTime());
            i.setTransaction_id(t.getTransactionId());
            data.addTransactionItem(i);
        }
        TransactionResponse resp = new TransactionResponse();
        resp.setMessage("ok");
        resp.setData(data);
        return resp;
    }
}
