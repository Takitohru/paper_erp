package erp_test.vo;

import java.util.List;

public class IncompleteOrdersVo {
    private int code;
    private int count;
    private String msg;
    private List<InCompleteOrder> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<InCompleteOrder> getData() {
        return data;
    }

    public void setData(List<InCompleteOrder> data) {
        this.data = data;
    }
}
