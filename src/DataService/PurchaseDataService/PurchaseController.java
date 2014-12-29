package DataService.PurchaseDataService;

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
import PO.GoodsPO;
import PO.PurchaseListItemPO;
import PO.PurchaseReceiptPO;
import ResultMessage.ResultMessage;

public class PurchaseController implements PurchaseDataService{

ArrayList<PurchaseReceiptPO> purchaseReceipts=new ArrayList<PurchaseReceiptPO>(); 
	
	public PurchaseController(){
		read();
	}
	
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas/PurchaseReceiptPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			purchaseReceipts=(ArrayList<PurchaseReceiptPO>)oin.readObject();
			}
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}          
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		     catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			FileOutputStream fos;
			ObjectOutputStream oos;
			fos = new FileOutputStream("Datas/PurchaseReceiptPO.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(purchaseReceipts);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	
	@Override
	public ResultMessage addReceipt(PurchaseReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
//				boolean exist=false;
//				for(PurchaseReceiptPO p:purchaseReceipts){
//					if(p.getSerialNumber().equals(p.getSerialNumber())){
//						exist=true;
//						break;
//						}
//					}
//				if(!exist){
					purchaseReceipts.add(po);
					save();
					return ResultMessage.add_success;
//				}
//				else
//					return ResultMessage.add_failure;
	
	}

	
	
	@Override
	public ResultMessage deleteReceipt(PurchaseReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delete successful");
		return ResultMessage.add_failure;
	}

	//经过经理审批
	@Override
	public ResultMessage updateReceipt(PurchaseReceiptPO po) throws RemoteException {
		//构造器中已read
		for (Iterator iterator = purchaseReceipts.iterator(); iterator
				.hasNext();) {
			PurchaseReceiptPO purchaseReceiptPO = (PurchaseReceiptPO) iterator
					.next();
			if(purchaseReceiptPO.getSerialNumber().equals(po.getSerialNumber())){
				purchaseReceiptPO.setApprovedByManager(po.isApprovedByManager());
				purchaseReceiptPO.setApprovedByCommodity(po.isApprovedByCommodity());
			}
		}
		
		save();
		return ResultMessage.update_success;
		

	}
	

	@Override
	public ArrayList<PurchaseReceiptPO> findReceipt(String serialNumber,
			String key) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PurchaseReceiptPO> polist=new ArrayList<PurchaseReceiptPO>();
		polist.add(new PurchaseReceiptPO());
		System.out.println("find successful");
		return polist;
	}
	
	public ArrayList<Object> show(){
		ArrayList<Object> purchaseReceipts_2=new ArrayList<Object>(purchaseReceipts);
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas/PurchaseReceiptPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			purchaseReceipts_2=(ArrayList<Object>)oin.readObject();
			}
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}          
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		     catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return purchaseReceipts_2;
	}
	
	//获得一段时间的进货单和进货退货单
	public ArrayList<Object> getReceiptsInATime(String beginTime,String endTime){
		ArrayList<Object> result=new ArrayList<Object>();//第一个元素是进货单的list，第二个元素是进货退货单的list
		ArrayList<PurchaseReceiptPO> purchaseReceipts=new ArrayList<PurchaseReceiptPO>();
		ArrayList<PurchaseReceiptPO> purchaseOutReceipts=new ArrayList<PurchaseReceiptPO>();
		//TODO 下面这句是什么。。。。
		result=this.show();
		
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			PurchaseReceiptPO purchaseReceiptPO = (PurchaseReceiptPO) iterator
					.next();
		if(changeDateToInt(purchaseReceiptPO.getTime())>=changeDateToInt(beginTime)&&changeDateToInt(purchaseReceiptPO.getTime())<=changeDateToInt(endTime)){
			if(purchaseReceiptPO.getSerialNumber().substring(0, 3).equals("JHD")){
				purchaseReceipts.add(purchaseReceiptPO);
			}else{
				purchaseOutReceipts.add(purchaseReceiptPO);
			}
		}
		}
		
		result.add(purchaseReceipts);
		result.add(purchaseOutReceipts);
		
		return result;
	}
	
	//成本调价
	public double getDiffCostInATime(String beginTime,String endTime){
		ArrayList<Object> receipts=this.show();
		double result=0;
		for (Iterator iterator = receipts.iterator(); iterator.hasNext();) {
			PurchaseReceiptPO object = (PurchaseReceiptPO) iterator.next();
			if(changeDateToInt(object.getTime())>=changeDateToInt(beginTime)&&changeDateToInt(object.getTime())<=changeDateToInt(endTime)){
				ArrayList<PurchaseListItemPO> listItem=object.getPurchaseList();
				for (Iterator iterator2 = listItem.iterator(); iterator2
						.hasNext();) {
					PurchaseListItemPO purchaseListItemPO = (PurchaseListItemPO) iterator2
							.next();
					GoodsPO good=new GoodsController().getGoodsByID(new Long(0).parseLong(purchaseListItemPO.getGoodsPO().getSerialNumber()));
					
					result+=(good.getPrice()-purchaseListItemPO.getGoodsPO().getPrice())*good.getCommodityQuantity();
				}
			}			
		}		
		return result;		
	}
	
	
	
	//将日期转换为可以比较大小的整数
		public int changeDateToInt(String date){
			int result=new Integer(0).parseInt(date.replaceAll("-", ""));
			
			return result;
			
		}

}
