package erp_test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import erp_test.service.CommodityService;
import erp_test.vo.CommodityPieVo;

@Controller
@RequestMapping("/erp/home")
public class IndexController 
{
	@Autowired
	CommodityService commodityService;
	
	/**主页控制层   负责主页跳转其他页面的控制
	 * 
	 * */
	@RequestMapping("/index") //主页界面url
	public String Home() {
		return "index";  //跳转到主页
	}
	
	@RequestMapping("/order")    //主页页面tab栏跳转url 测试示例
	public String Sellorder()
	{
		return "sellorder";   //销售订单界面
	}
	
	@RequestMapping("/analisis")    //主页页面tab栏跳转url 测试示例
	public String commodityAna()
	{
		return "echarts4";   //销售订单界面
	}

	/**
     * ECharts数据展示
     * @param request
     * @param session
     * @return 数据库查询的数据
     */
	@RequestMapping(value="erp/home/commodityAnalisis")
    @ResponseBody
    public List<CommodityPieVo> groupdata(HttpServletRequest request, HttpServletResponse response)
    {
    	List<CommodityPieVo> list=commodityService.findTopFiveCommodity();
        return list;
    }
}
