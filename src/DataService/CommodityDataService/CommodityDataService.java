package DataService.CommodityDataService;

import PO.*;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public interface CommodityDataService {
	public ResultMessage insertSendCommodity(SendCommodityPO sendCommodityPO);
	public ResultMessage insertReportCommodity(ReportCommodityPO reportCommodityPO);
	

}
