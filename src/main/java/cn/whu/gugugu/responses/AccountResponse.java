package cn.whu.gugugu.responses;

import java.text.DecimalFormat;

public class AccountResponse {

    public String msg = "ok";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data data;

    public class Data {

        public String total;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public void setTotal(double total) {
            DecimalFormat df = new DecimalFormat("#.00");
            this.total = df.format(total);
        }
    }
}
