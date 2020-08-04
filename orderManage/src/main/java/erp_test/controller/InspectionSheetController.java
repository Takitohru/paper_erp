package erp_test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import erp_test.bean.InspectionSheetMaster;
import erp_test.bean.PurchaseDetail;
import erp_test.bean.PurchaseEmpolyee;
import erp_test.service.ISheetService;
import erp_test.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/j")
public class InspectionSheetController {
    @Autowired
    private ISheetService iSheetService;
    @Autowired
    private PurchaseService purchaseService;
    @RequestMapping("/purchasedit")//入库页面初始化
    public String toPurchasedit(Model model, @RequestParam String parchaseId){
        int id=Integer.parseInt(parchaseId);
        List<PurchaseDetail> list=purchaseService.selectDetailById(id);
        List<PurchaseEmpolyee> empolyeeList=purchaseService.getEmployee();
        double totalPrice=purchaseService.selectTotalPrice(id);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("parchaseId",id);
        model.addAttribute("empolyeeList",empolyeeList);
        model.addAttribute("list",list);
        return "purchasedit";
    }
    @ResponseBody
    @RequestMapping("/checkNewPurchase")//添加验货单，验货明细
    public int checkNewPurchase(Integer purchaseId,Integer employeeId,double payablePrice,@RequestParam String detailList){
        System.out.println("------------验货主单信息-------------");
        System.out.println("采购单id："+purchaseId+" 验货员id："+employeeId+" 实际价值："+payablePrice);
        Integer id=iSheetService.insertCheckMaster(purchaseId,employeeId,payablePrice);//插入主表，并返回id
        System.out.println("验货主单id："+id);
        System.out.println("------------验货明细信息-------------");
        JSONArray list = JSON.parseArray(detailList);
        for(int i=0;i<list.size();i++){
            JSONObject itemJson=(JSONObject)list.get(i);
            Integer commodityId=itemJson.getInteger("commodityId");
            Integer actualAcceptance=itemJson.getInteger("actualAcceptance");
            double price=itemJson.getDouble("price");
            String inspectionSituaton=itemJson.getString("inspectionSituaton");
            System.out.println("商品id:"+commodityId+" 实际接受数量："+actualAcceptance+" 实际价值："+price+" 备注:"+inspectionSituaton);
            iSheetService.insertCheckDetail(id,commodityId,actualAcceptance,price,inspectionSituaton);//插入明细
            iSheetService.updateInventory(commodityId,actualAcceptance);
        }
        purchaseService.updateState(purchaseId);
       return 0;
    }
    @RequestMapping("/checkhistory")//跳转验货历史
    public String tocheckhistory(Model model){
        return "checkhistory";
    }
    @ResponseBody
    @RequestMapping("/getCheckHitory")//获取验货单历史记录
    public PageInfo<InspectionSheetMaster> getCheckHitory(@RequestParam(defaultValue = "1",value = "page") Integer page,
                                                          @RequestParam(defaultValue = "10",value = "limit")Integer limit){
        PageHelper.startPage(page,limit);
        List<InspectionSheetMaster> list=iSheetService.selectAllCheck();
        PageInfo<InspectionSheetMaster> pageInfo=new PageInfo<InspectionSheetMaster>(list);
        return pageInfo;
    }
}
