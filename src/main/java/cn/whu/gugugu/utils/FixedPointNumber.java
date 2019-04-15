package cn.whu.gugugu.utils;

import java.text.DecimalFormat;

public class FixedPointNumber {
    /*
        定点小数点后两位小数，用于表示账户余额以及所有的流水
        数据库中用整数存储余额和流水（包括符号，收入为正，支出为负）
        在查询出结果后，使用这个类转换成定点小数的表示法，例如：

        某个账户拥有 123.31 存款
        那么，数据库中存储的数值为 12331

        在所有接口调用中，无论是参数还是返回值，与钱相关的数值全部用字符串表示，
        相当于调用了本类的 toString() 方法。

        提示，在做计算时，可以直接调用本类的构造函数 public FixedPointNumber(String fixedPointNumber)
        来得到整数表示的定点小数，直接进行四则运算，然后再讲计算结果转换为定点小数进行输出
         */
    private int storageValue;

    public int getStorageValue() {
        return storageValue;
    }

    public void setStorageValue(int storageValue) {
        this.storageValue = storageValue;
    }

    /*
    使用小数点后两位的字符串来初始化定点小数
     */
    public FixedPointNumber(String fixedPointNumber){
            storageValue = Integer.parseInt(fixedPointNumber.replace(".", ""));
    }

    /*
    使用整数初始化定点小数
     */
    public FixedPointNumber(int fixedPointNumber){
        storageValue = fixedPointNumber;
    }

    /*
    输出为字符串
     */
    public String toString(){
        double trueValue = storageValue / 100;
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(trueValue);
    }
}
