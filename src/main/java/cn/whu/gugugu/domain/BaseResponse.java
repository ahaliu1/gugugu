package cn.whu.gugugu.domain;

public class BaseResponse {
    public String message;
    public DataImpl data;

    public BaseResponse(String message) {
        this(message, null);
    }

    public BaseResponse(String message, DataImpl data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataImpl getData() {
        return data;
    }

    public void setData(DataImpl data) {
        this.data = data;
    }
}
