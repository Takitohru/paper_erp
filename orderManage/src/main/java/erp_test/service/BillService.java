package erp_test.service;

import erp_test.bean.BillDetail;
import erp_test.bean.BillMaster;
import erp_test.bean.Client;
import erp_test.vo.BillVo;
import erp_test.vo.InCompleteOrder;

import java.util.List;

public interface BillService {
    List<Client> getAllCilent();
    List<InCompleteOrder> getAllIncompleteOrder();
    List<InCompleteOrder> getIncompleteOrderByClientId(int clientId, List<Integer> ids);
    List<BillMaster> getAllBillMasterByType();
    int recoredBill(BillVo billVo,int type);

    BillMaster getBillMasterByBillId(String billId);
    List<BillDetail> getBillDetailByBillId(String billId);
}
