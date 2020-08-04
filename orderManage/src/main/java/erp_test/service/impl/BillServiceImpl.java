package erp_test.service.impl;

import erp_test.bean.BillDetail;
import erp_test.bean.BillMaster;
import erp_test.bean.Client;
import erp_test.dao.BillDao;
import erp_test.dao.ClientDao;
import erp_test.service.BillService;
import erp_test.vo.BillVo;
import erp_test.vo.InCompleteOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    ClientDao clientDao;

    @Autowired
    BillDao billDao;

    @Autowired


    @Override
    public List<Client> getAllCilent() {
        return clientDao.findAllClientList();
    }

    @Override
    public List<InCompleteOrder> getAllIncompleteOrder(){
        return billDao.getAllIncompleteOrder();
    }

    @Override
    public List<InCompleteOrder> getIncompleteOrderByClientId(int clientId, List<Integer> ids){
        return billDao.getIncompleteOrderByClientId(clientId,ids);
    }

    @Override
    public List<BillMaster> getAllBillMasterByType() {
        return billDao.getAllBillMaster();
    }

    @Override
    public int recoredBill(BillVo billVo,int type) {
        billDao.recordBillMaster(billVo.getBillMaster(),type);
        int rows=billDao.recordBillDetail(billVo.getList(),type);
        List<BillDetail> l=billVo.getList();
        for (BillDetail bd:l
             ) {
            if(bd.getUnpay()<=0){
                billDao.updateOrderStatus(bd.getOrderId(),"交易完成");
            }
        }

        return rows;
    }

    @Override
    public BillMaster getBillMasterByBillId(String billId) {
        return billDao.getBillMasterByBillId(billId);
    }

    @Override
    public List<BillDetail> getBillDetailByBillId(String billId) {
        return billDao.getBillDetailByBillId(billId);
    }


}
