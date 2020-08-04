package erp_test.service.impl;

import erp_test.bean.BillDetail;
import erp_test.bean.BillMaster;
import erp_test.bean.Supplier;
import erp_test.dao.BillDao;
import erp_test.dao.SupplierDao;
import erp_test.service.PaymentService;
import erp_test.vo.BillVo;
import erp_test.vo.InCompleteOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    BillDao billDao;

    @Autowired
    SupplierDao supplier;

    @Override
    public List<InCompleteOrder> getAllIncompletePurchaseOrder() {
        return billDao.getAllIncompletePurchaseOrder();
    }

    @Override
    public List<Supplier> getAllSupplier() {
        return supplier.findAllSupplierList();
    }

    @Override
    public List<InCompleteOrder> getIncompletePurchaseOrderBySupplierId(int supplierId, List<Integer> ids) {
        return billDao.getIncompletePurchaseOrderBySupplierId(supplierId,ids);
    }

    @Override
    public int recordPaymentBill(BillVo billVo, int type) {
        billDao.recordBillMaster(billVo.getBillMaster(),type);
        return billDao.recordBillDetail(billVo.getList(),type);
    }

    @Override
    public List<BillMaster> getAllPaymentBillMaster() {
        return billDao.getAllPaymentBillMaster();
    }

    @Override
    public BillMaster getPaymentBillMasterByBillId(String billId) {
        return billDao.getPaymentBillMasterByBillId(billId);
    }

    @Override
    public List<BillDetail> getPaymentBillDetailByBillId(String billId) {
        return billDao.getPaymentBillDetailByBillId(billId);
    }
}
