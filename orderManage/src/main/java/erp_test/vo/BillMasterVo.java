package erp_test.vo;

import erp_test.bean.BillMaster;

import java.util.List;

public class BillMasterVo {
    private int code;
    private int count;
    private String msg;
    private List<BillMaster> data;

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

    public List<BillMaster> getData() {
        return data;
    }

    public void setData(List<BillMaster> data) {
        this.data = data;
    }
}
