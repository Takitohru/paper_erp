package erp_test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import erp_test.bean.BillDetail;
import erp_test.bean.BillMaster;
import erp_test.bean.Supplier;
import erp_test.service.PaymentService;
import erp_test.vo.BillMasterVo;
import erp_test.vo.BillVo;
import erp_test.vo.InCompleteOrder;
import erp_test.vo.IncompleteOrdersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/erp/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    /**
     * 返回未完款采购单界面
     * @return 返回界面
     */
    @RequestMapping("/paymentList/page")
    public String paymentListPage() {
        return "paymentList";
    }

    /**
     * 返回未完款采购单界面
     * @return 返回界面
     */
    @RequestMapping("/page")
    public String paymentPage() {
        return "payment";
    }

    /**
     * 展示按单付款界面
     * @return 返回界面
     */
    @RequestMapping("/selectOrder")
    public String pageSelect() {
        return "paymentSelectOrder";
    }

    /**
     * 展示历史付款界面
     * @return 返回界面
     */
    @RequestMapping("/paymentHistory/detail/page")
    public String pageReceiptHistory() {
        return "paymentHistoryDetail";
    }
    /**
     * 展示历史付款列表界面
     * @return 返回界面
     */
    @RequestMapping("/paymentHistoryList")
    public String pageReceiptHistoryList() {
        return "paymentHistory";
    }

    @RequestMapping("/paymentList/orders")
    @ResponseBody
    public IncompleteOrdersVo getIncompleteOrders(){
        IncompleteOrdersVo icoo=new IncompleteOrdersVo();
        List<InCompleteOrder> l=paymentService.getAllIncompletePurchaseOrder();

        icoo.setCode(0);
        icoo.setMsg("");
        icoo.setCount(l.size());
        icoo.setData(l);

        return icoo;
    }

    @RequestMapping("/getSuppliers")
    @ResponseBody
    public List<Supplier> getAllSupplier(){
        return paymentService.getAllSupplier();
    }

    @RequestMapping("/selectOrder/clientId")
    @ResponseBody
    public IncompleteOrdersVo getIncompleteOrderByClientId(@RequestParam("clientId") int clientId,
                                                           @RequestParam("ids[]") int[] ids){
        List<Integer> ll= Arrays.stream(ids).boxed().collect(Collectors.toList());
        IncompleteOrdersVo icoo=new IncompleteOrdersVo();
        List<InCompleteOrder> l=paymentService.getIncompletePurchaseOrderBySupplierId(clientId, ll);

        icoo.setCode(0);
        icoo.setMsg("");
        icoo.setCount(l.size());
        icoo.setData(l);

        return icoo;
    }

    @RequestMapping(value = "/recordPaymentBill",method = RequestMethod.POST)
    @ResponseBody
    public int recordBill(@RequestParam("customerId") int customerId,@RequestParam("name") String name,
                          @RequestParam("list") String list){
        JSONArray array = JSON.parseArray(list);
        List<InCompleteOrder> icol=array.toJavaList(InCompleteOrder.class);

        BillVo bv=CollectByOrderController.parseBillVo(customerId,name,icol);

        return paymentService.recordPaymentBill(bv,2);
    }

    /**
     * 获取历史付款单
     * @return 付款单列表
     */
    @RequestMapping("/paymentHistory/paymentBillMasterList")
    @ResponseBody
    public BillMasterVo getBillMaster(){
        BillMasterVo bmo=new BillMasterVo();
        List<BillMaster> l=paymentService.getAllPaymentBillMaster();
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
    @RequestMapping("paymentHistory/detail")
    @ResponseBody
    public BillVo getBillDetail(@RequestParam("billId") String billId){
        BillMaster bm=paymentService.getPaymentBillMasterByBillId(billId);
        List<BillDetail> bd=paymentService.getPaymentBillDetailByBillId(billId);

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
