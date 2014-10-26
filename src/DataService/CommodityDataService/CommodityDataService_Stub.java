package DataService.CommodityDataService;

import PO.ReportCommodityPO;
import PO.SendCommodityPO;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public class CommodityDataService_Stub implements CommodityDataService {
	@Override
	public ResultMessage insertSendCommodity(SendCommodityPO sendCommodityPO) {
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage insertReportCommodity(
			ReportCommodityPO reportCommodityPO) {
		return ResultMessage.add_success;
	}


}
