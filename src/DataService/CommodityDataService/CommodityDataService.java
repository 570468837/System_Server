package DataService.CommodityDataService;

import java.rmi.RemoteException;

import PO.*;
import ResultMessage.ResultMessage;


/**
 * 
 * @author hutao
 *
 */
public interface CommodityDataService {
	public ResultMessage insertSendCommodity(SendCommodityPO sendCommodityPO) throws RemoteException;
	public ResultMessage insertReportCommodity(ReportCommodityPO reportCommodityPO) throws RemoteException;
	public InventoryCommodityPO getInventoryCommodity() throws RemoteException;
	
	
	
}
