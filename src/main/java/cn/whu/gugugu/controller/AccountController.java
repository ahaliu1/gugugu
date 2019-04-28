package cn.whu.gugugu.controller;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.commons.MessageResponse;
import cn.whu.gugugu.generated.mapper.UserMapper;
import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.utils.FixedPointNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

class Data {

    private String total = "";

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

class AccountResponse extends MessageResponse {

    private Data data = null;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}


/*
充值，提现接口
 */
@RestController
public class AccountController extends AuthenticatedController {

    @Autowired
    private UserMapper mapper;

    /*
    充值
    注意，金额用字符串表示，只向下取整保留到小数点后2位

    返回值

    正常情况

    {
        "message": "ok"
        "data": {
            "total": "110.00"  // 充值后的总金额
        }
    }

    输入数据不合法

    {
        "message": "amount should be positive"
    }

    交易失败（在之后如果要做真·充值接口就会使用到）

    {
        "message": "failed"
    }
     */
    @RequestMapping(value = "/account/top", method = RequestMethod.POST)
    public AccountResponse top(String amount){
        User user = this.getRequestedUser();
        Integer account = user.getAccount();
        account += FixedPointNumber.toInteger(amount);
        user.setAccount(account);
        mapper.updateByPrimaryKey(user);
        AccountResponse resp = new AccountResponse();
        Data data = new Data();
        data.setTotal(FixedPointNumber.toString(account));
        resp.setData(data);
        resp.setMessage("ok");
        return resp;
    }

    /*
    提现
    注意。金额用字符串表示，只向下取整保留到小数点后2位。

    返回值

    正常情况

    {
        "message": "ok",
        "data": {
            "total": "10.00"  // 提现后剩余总金额
        }
    }

    提现金额超过总金额或提现金额不为正数

    {
        "message": "out of range"
    }
     */
    @RequestMapping(value = "/account/withdraw", method = RequestMethod.POST)
    public AccountResponse withdraw(String amount){
        User user = getRequestedUser();
        AccountResponse resp = new AccountResponse();
        Integer account = user.getAccount();
        Integer f_amount = FixedPointNumber.toInteger(amount);
        if (f_amount > account){
            resp.setMessage("out of range");
            return resp;
        }
        int total = account + f_amount;
        user.setAccount(total);
        mapper.updateByPrimaryKey(user);
        Data data = new Data();
        data.setTotal(FixedPointNumber.toString(total));
        resp.setMessage("ok");
        return resp;
    }
}
