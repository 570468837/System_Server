package DataService.CommodityDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import DataService.GoodsDataService.GoodsController;
import PO.CheckCommodityPO;
import PO.GoodsClassPO;
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
	public GoodsController goodsController;
	public ArrayList<SendCommodityPO> sendList;
	public ArrayList<ReportCommodityPO> reportList;
	
	public CommodityController(Communication_Controller communication_Controller) {
		this.goodsController = communication_Controller.goodsController;
		sendList = new ArrayList<SendCommodityPO>();
		reportList = new ArrayList<ReportCommodityPO>();
		readFile();
		
	}
	
	/**
	 * TODO client相应接口
	 * @return 待审批的赠送单
	 */
	public ArrayList<SendCommodityPO> showUncheckedSend() {
		ArrayList<SendCommodityPO> unchecked = new ArrayList<SendCommodityPO>();
		Iterator<SendCommodityPO> iter = sendList.iterator();
		SendCommodityPO s;
		while(iter.hasNext()) {
			s = iter.next();
			if(s.type == SendCommodityPO.UNCHECKED)
				unchecked.add(new SendCommodityPO(s));
		}
		return unchecked;
	}
	/**TODO client相应接口
	 * 审批赠送单
	 * @param poList 待审批的赠送单
	 */
	public void updUncheckedSend(ArrayList<SendCommodityPO> poList) {
		SendCommodityPO po;
		for(int i = 0; i < poList.size(); i ++) {
			po = poList.get(i);
			Iterator<SendCommodityPO> iter = sendList.iterator();
			SendCommodityPO s;
			while(iter.hasNext()) {
				s = iter.next();
				if(po.date == s.date) {
					s.type = po.type;
					break;
				}
			}
		}
		writeSendFile();
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

	@SuppressWarnings("unchecked")
	private void readFile() {
		FileInputStream 
	        sfis = null,
	        rfis = null;
	    ObjectInputStream
	        sois = null,
	        rois = null;
	    try {
			sfis = new FileInputStream(sendURL);
			rfis = new FileInputStream(reportURL);
			if(sfis.available() > 0) sois = new ObjectInputStream(sfis);
			if(rfis.available() > 0) rois = new ObjectInputStream(rfis);
			sendList = (ArrayList<SendCommodityPO>) sois.readObject();
			reportList = (ArrayList<ReportCommodityPO>) rois.readObject();
			
		} catch (FileNotFoundException e) {
			System.out.println("sendCommodityPO or ReportCommodityPO.out not found");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(".out读取问题");
		}
		
		
	}
	/**
	 * 写入赠送单文件
	 */
	private void writeSendFile() {
		try {
			ObjectOutputStream soos = new ObjectOutputStream(new FileOutputStream(sendURL));
			soos.writeObject(sendList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 写入报单文件
	 */
	private void writeReportFile() {
		try {
			ObjectOutputStream roos = new ObjectOutputStream(new FileOutputStream(reportURL));
			roos.writeObject(reportList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private final String sendURL = "Datas/SendCommodityPO";
	private final String reportURL = "Datas/ReportCommodityPO";
 	
}
