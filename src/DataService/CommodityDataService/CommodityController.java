package DataService.CommodityDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	 * @return 待审批的赠送单
	 */
	public ArrayList<Object> showUncheckedSend() {
		ArrayList<Object> unchecked = new ArrayList<Object>();
		Iterator<SendCommodityPO> iter = sendList.iterator();
		SendCommodityPO s;
		while(iter.hasNext()) {
			s = iter.next();
			if(s.checked == SendCommodityPO.UNCHECKED)
				unchecked.add(new SendCommodityPO(s));
		}
		return unchecked;
	}
	/**
	 * 审批赠送单
	 * @param poList 待审批的赠送单
	 */
	public ResultMessage updUncheckedSend(ArrayList<SendCommodityPO> poList) {
		SendCommodityPO po;
		for(int i = 0; i < poList.size(); i ++) {
			po = poList.get(i);
			Iterator<SendCommodityPO> iter = sendList.iterator();
			SendCommodityPO s;
			while(iter.hasNext()) {
				s = iter.next();
				if(po.date == s.date) {
					s.checked = po.checked;
					break;
				}
			}
		}
		writeSendFile();
		return ResultMessage.update_success;
	}
	
	/**
	 * 库存赠送单的show方法
	 * @return
	 */
	public ArrayList<Object> showSendCommodity() {
		Iterator<SendCommodityPO> iter = sendList.iterator();
		ArrayList<Object> show = new ArrayList<Object>();
		SendCommodityPO po;
		while(iter.hasNext()) {
			po = iter.next();
			if(po.checked == SendCommodityPO.PASS)
				show.add(new SendCommodityPO(po));
		}
		return show;
	}
	
	/**
	 * 库存报单show方法
	 * @return
	 */
	public ArrayList<Object> showReportCommodity() {
		Iterator<ReportCommodityPO> iter = reportList.iterator();
		ArrayList<Object> show = new ArrayList<Object>();
		ReportCommodityPO po;
		while(iter.hasNext()) {
			po = iter.next();
			show.add(new ReportCommodityPO(po));
		}
		return show;
	}
	
	/**
	 * 
	 * @return 一段时间的报溢收入，默认正值
	 */
	public Object reportIncome(String d1, String d2) {
		Date date1 = changeToDate(d1);
		Date date2 = changeToDate(d2);
		double sum = 0;
		Iterator<ReportCommodityPO> iter = reportList.iterator();
		ReportCommodityPO po;
		while(iter.hasNext()) {
			po = iter.next();
			if(po.date.getTime() > date1.getTime() && po.date.getTime() < date2.getTime() && po.num > 0)
				sum += po.num * po.price;
		}
		return new Double(sum);
	}
	/**
	 * 
	 * @return 一段时间的报损支出，默认负值
	 */
	public Object reportOutcome(String d1, String d2) {
		Date date1 = changeToDate(d1);
		Date date2 = changeToDate(d2);
		double sum = 0;
		Iterator<ReportCommodityPO> iter = reportList.iterator();
		ReportCommodityPO po;
		while(iter.hasNext()) {
			po = iter.next();
			if(po.date.getTime() > date1.getTime() && po.date.getTime() < date2.getTime() && po.num < 0)
				sum += po.num * po.price;
		}
		return new Double(sum);
	}
	
	/**
	 * 
	 * @return 一段时间的赠送支出，默认正值
	 */
	public Object sendOutcome(String d1, String d2) {
		Date date1 = changeToDate(d1);
		Date date2 = changeToDate(d2);
		double sum = 0;
		Iterator<SendCommodityPO> iter = sendList.iterator();
		SendCommodityPO po;
		while(iter.hasNext()) {
			po = iter.next();
			if(po.date.getTime() > date1.getTime() && po.date.getTime() < date2.getTime())
				sum += po.price * po.num;
		}
		return new Double(sum);
	}
	
	
	
	
	
	@Override
	public ResultMessage insertSendCommodity(SendCommodityPO sendCommodityPO) throws RemoteException {
		sendList.add(sendCommodityPO);
		writeSendFile();
		return ResultMessage.add_success;
	}

	@Override
	public ResultMessage insertReportCommodity(
			ReportCommodityPO reportCommodityPO) throws RemoteException {
		Iterator<GoodsPO> gIter = goodsController.goodsList.iterator();
		GoodsPO goods = null;
		while(gIter.hasNext()) {
			goods = gIter.next();
			if(goods.getSerialNumber().equals(Long.toString(reportCommodityPO.goodsPOId))) {
				break;
			}
			goods = null;
		}
		if(goods != null) {
			if(goods.commodityQuantity + reportCommodityPO.num >= 0) {
				goods.commodityQuantity += reportCommodityPO.num;
				writeReportFile();
				return ResultMessage.add_success;
			}
			else 
				return ResultMessage.add_failure;
		}
		else {
			return ResultMessage.add_failure;
		}
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
		icp.icInfo = new ArrayList<Object>();
		GoodsPO g;
		for(int i = 0; i < goodsList.size(); i ++) {
			String[] s = new String[8];
			g = (GoodsPO)goodsList.get(i);
			s[0] = (i + 1) + "";
			s[1] = g.getName();
			s[2] = g.getModel();
			s[3] = g.getCommodityQuantity() + "";
			s[4] = g.getSalePrice() + ""; //库存均价
			s[5] = "/"; //批次
			s[6] = "/"; //批号
			s[7] = "/"; //出厂日期
			icp.icInfo.add(s);
			
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
	
	@SuppressWarnings("deprecation")
	private Date changeToDate(String d) {
		String[] s = d.split("/");
		Date date = null;
		try {
			date = new Date(
					Integer.parseInt(s[0]) - 1900, 
					Integer.parseInt(s[1]) - 1,
					Integer.parseInt(s[2]));
		}catch(Exception e) {}
		return date;
		
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
