package erp_test.service;

import erp_test.bean.BillDetail;
import erp_test.bean.BillMaster;
import erp_test.bean.Supplier;
import erp_test.vo.BillVo;
import erp_test.vo.InCompleteOrder;

import java.util.List;

public interface PaymentService {
    List<InCompleteOrder> getAllIncompletePurchaseOrder();
    List<Supplier> getAllSupplier();
    List<InCompleteOrder> getIncompletePurchaseOrderBySupplierId(int supplierId,List<Integer> ids);
    int recordPaymentBill(BillVo billVo, int type);
    List<BillMaster> getAllPaymentBillMaster();

    BillMaster getPaymentBillMasterByBillId(String billId);
    List<BillDetail> getPaymentBillDetailByBillId(String billId);
}
