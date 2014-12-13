package DataService.CommodityDataService;

import java.rmi.RemoteException;

import PO.InventoryCommodityPO;
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


	@Override
	public InventoryCommodityPO getInventoryCommodity() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
