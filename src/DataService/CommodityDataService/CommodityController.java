package DataService.CommodityDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import DataService.GoodsDataService.GoodsController;
import PO.CheckCommodityPO;
import PO.GoodsPO;
import PO.InventoryCommodityPO;
import PO.ReportCommodityPO;
import PO.SendCommodityPO;
import RMI.Communication_Controller;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public class CommodityController implements CommodityDataService {
	GoodsController goodsController;
	
	public CommodityController(Communication_Controller communication_Controller) {
		this.goodsController = communication_Controller.goodsController;
	}
	
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
		InventoryCommodityPO icp = new InventoryCommodityPO();
		ArrayList<Object> goodsList = goodsController.getGoodsPOList();
		icp.icInfo = new String[goodsList.size()][8];
		GoodsPO g;
		for(int i = 0; i < goodsList.size(); i ++) {
			g = (GoodsPO)goodsList.get(i);
			icp.icInfo[i][0] = (i + 1) + "";
			icp.icInfo[i][1] = g.getName();
			icp.icInfo[i][2] = g.getModel();
			icp.icInfo[i][3] = g.getCommodityQuantity() + "";
			icp.icInfo[i][4] = g.getSalePrice() + ""; //库存均价
			icp.icInfo[i][5] = "/"; //批次
			icp.icInfo[i][6] = "/"; //批号
			icp.icInfo[i][7] = "/"; //出厂日期
			
		}
		
		return icp;
	}

	
	
}
