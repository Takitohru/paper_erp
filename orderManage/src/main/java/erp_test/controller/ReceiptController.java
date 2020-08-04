package erp_test.controller;

import erp_test.bean.BillDetail;
import erp_test.bean.BillMaster;
import erp_test.service.BillService;
import erp_test.vo.BillMasterVo;
import erp_test.vo.BillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/erp/receipt")
public class ReceiptController {
    @Autowired
    private BillService billService;
    /**
     * 展示按单付款界面
     * @return 返回界面
     */
    @RequestMapping("/page")
    public String page(){
        return "receipt";
    }

    /**
     * 展示历史付款界面
     * @return 返回界面
     */
    @RequestMapping("/receiptHistory/detail/page")
    public String pageReceiptHistory() {
        return "receiptBillHistory";
    }
    /**
     * 展示历史付款列表界面
     * @return 返回界面
     */
    @RequestMapping("/receiptHistoryList")
    public String pageReceiptHistoryList() {
        return "receiptBillHistoryList";
    }

    /**
     * 获取历史付款单
     * @return 付款单列表
     */
    @RequestMapping("/receiptHistory/billMasterList")
    @ResponseBody
    public BillMasterVo getBillMaster(){
        BillMasterVo bmo=new BillMasterVo();
        List<BillMaster> l=billService.getAllBillMasterByType();
        bmo.setCode(0);
        bmo.setCount(l.size());
        bmo.setMsg("");
        bmo.setData(l);
        return bmo;
    }

    /**
     * 获取历史付款单
     * @return 付款单列表
     */
    @RequestMapping("/receiptHistory/detail")
    @ResponseBody
    public BillVo getBillDetail(@RequestParam("billId") String billId){
        BillMaster bm=billService.getBillMasterByBillId(billId);
        List<BillDetail> bd=billService.getBillDetailByBillId(billId);

        double total=0;
        double received=0;
        double unpay=0;
        double thistime=0;
        for (BillDetail b:bd) {
            total+=b.getPayable();
            received+=b.getReceived();
            unpay+=b.getUnpay();
            thistime+=b.getThistime();
        }

        bm.setTotal(total);
        bm.setReceived(received);
        bm.setUnpay(unpay);
        bm.setThistime(thistime);

        BillVo billVo=new BillVo();
        billVo.setBillMaster(bm);
        billVo.setList(bd);

        return billVo;
    }
}
