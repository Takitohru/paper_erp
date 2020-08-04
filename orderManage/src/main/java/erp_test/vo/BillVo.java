package erp_test.vo;

import erp_test.bean.BillDetail;
import erp_test.bean.BillMaster;

import java.util.List;

public class BillVo {
    private BillMaster billMaster;
    private List<BillDetail> list;

    public BillMaster getBillMaster() {
        return billMaster;
    }

    public void setBillMaster(BillMaster billMaster) {
        this.billMaster = billMaster;
    }

    public List<BillDetail> getList() {
        return list;
    }

    public void setList(List<BillDetail> list) {
        this.list = list;
    }
}
