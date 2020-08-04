package erp_test.vo;

import erp_test.bean.Commodity;
import erp_test.bean.OutboundDetail;

public class OutBoundDetailVo extends OutboundDetail
{
	private Commodity commodity;
	private OutBoundMasterVo outBoundMasterVo;

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public OutBoundMasterVo getOutBoundMasterVo() {
		return outBoundMasterVo;
	}

	public void setOutBoundMasterVo(OutBoundMasterVo outBoundMasterVo) {
		this.outBoundMasterVo = outBoundMasterVo;
	}


	
}
