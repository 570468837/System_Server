package DataService.CommodityDataService;

import java.rmi.RemoteException;

import PO.CheckCommodityPO;
import PO.InventoryCommodityPO;
import PO.ReportCommodityPO;
import PO.SendCommodityPO;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public class CommodityController implements CommodityDataService {

	@Override
	public ResultMessage insertSendCommodity(SendCommodityPO sendCommodityPO) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insertReportCommodity(
			ReportCommodityPO reportCommodityPO) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckCommodityPO getCheckCommodity(String time1, String time2) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryCommodityPO getInventoryCommodity() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
