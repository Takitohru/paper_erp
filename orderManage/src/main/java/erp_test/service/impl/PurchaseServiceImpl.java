package erp_test.service.impl;

import erp_test.bean.Purchase;
import erp_test.bean.PurchaseDetail;
import erp_test.bean.PurchaseEmpolyee;
import erp_test.bean.PurchaseSupplier;
import erp_test.dao.PurchaseDao;
import erp_test.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public List<PurchaseEmpolyee> getEmployee() {
        return purchaseDao.getEmployee();
    }

    @Override
    public List<PurchaseSupplier> getSupplier() {
        return purchaseDao.getSupplier();
    }

    @Override
    public List<String> getCname() {
        return purchaseDao.getCname();
    }

    @Override
    public List<Purchase> getPurchaseHistory() {
        return purchaseDao.getPurchaseHistory();
    }

    @Override
    public double selectTotalPrice(Integer purchaseId) {
        return purchaseDao.selectTotalPrice(purchaseId);
    }

    @Override
    public int getCid(String commodityName, String brand, String paperClass, String paperSize, String Crank) {
        return purchaseDao.getCid(commodityName,brand,paperClass,paperSize,Crank);
    }

    @Override
    public List<Purchase> getAllPurchase() {
        return purchaseDao.getAllPurchase();
    }

    @Override
    public int updateState(Integer purchaseId) {
        return purchaseDao.updateState(purchaseId);
    }

    @Override
    public List<PurchaseDetail> selectDetailById(Integer purchaseId) {
        return purchaseDao.selectDetailById(purchaseId);
    }

    @Override
    public int insertPurchaseDetail(Integer purchaseId, Integer commodityId, double purchasePrice, Integer quantity) {
        purchaseDao.insertPurchaseDetail(purchaseId,commodityId,purchasePrice,quantity);
        return 0;
    }

    @Override
    public int insertPurchase(Integer supplierId, Integer employeeId, double totalPrice) {
        purchaseDao.insertPurchase(supplierId,employeeId,totalPrice);
        return purchaseDao.getPurchaseId();
    }

    @Override
    public List<String> getCrank(String commodityName, String brand, String paperClass, String paperSize) {
        return purchaseDao.getCrank(commodityName,brand,paperClass,paperSize);
    }

    @Override
    public List<String> getCsize(String commodityName, String brand, String paperClass) {
        return purchaseDao.getCsize(commodityName,brand,paperClass);
    }

    @Override
    public List<String> getCclass(String commodityName, String brand) {
        return purchaseDao.getCclass(commodityName,brand);
    }

    @Override
    public List<String> getCbrand(String commodityName) {
        return purchaseDao.getCbrand(commodityName);
    }
}
