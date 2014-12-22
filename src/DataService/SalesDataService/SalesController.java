package DataService.SalesDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
import ResultMessage.ResultMessage;

public class SalesController implements SalesDataService{
ArrayList<SalesReceiptPO> salesReceipts=new ArrayList<SalesReceiptPO>(); 
	
	public SalesController(){
		read();
	}
	
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas/SalesReceiptPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			salesReceipts=(ArrayList<SalesReceiptPO>)oin.readObject();
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
			fos = new FileOutputStream("Datas/SalesReceiptPO.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(salesReceipts);
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
	public ResultMessage addReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
//				boolean exist=false;
//				for(SalesReceiptPO p:salesReceipts){
//					if(p.getSerialNumber().equals(p.getSerialNumber())){
//						exist=true;
//						break;
//						}
//					}
//				if(!exist){
					salesReceipts.add(po);
					save();
					return ResultMessage.add_success;
//				}
//				else
//					return ResultMessage.add_failure;
	
	}

	@Override
	public ResultMessage deleteReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delete successful");
		return ResultMessage.delete_success;
	}

	@Override//只针对经理和库存管理人员的审批
	public ResultMessage updateReceipt(SalesReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(int i=0;i<salesReceipts.size();i++){
			if(salesReceipts.get(i).getSerialNumber().equals(po.getSerialNumber())){
				salesReceipts.get(i).setApprovedByManager(po.isApprovedByManager());
				salesReceipts.get(i).setApprovedByCommodity(po.isApprovedByCommodity());
			}
		}
		save();
		
		return ResultMessage.update_success;
	}

	@Override
	public ArrayList<SalesReceiptPO> findReceipt(String serialNumber, String key)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<SalesReceiptPO> polist=new ArrayList<SalesReceiptPO>();
		polist.add(new SalesReceiptPO());
		System.out.println("find successful");
		return polist;
	}
	
	public ArrayList<Object> show(){
		ArrayList<Object> salesReceipts_2=new ArrayList<Object>(salesReceipts);
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas/SalesReceiptPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			salesReceipts_2=(ArrayList<Object>)oin.readObject();
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
		return salesReceipts_2;
	}
	
	//给财务人员查看一段时间的总收入,日期的格式统一为XXXX-XX-XX
	public double getTotalIncomeInATime(String beginTime,String endTime){
		double totalIncome=0.0;
		
		ArrayList<Object> receipts=this.show();
		for (Iterator iterator = receipts.iterator(); iterator.hasNext();) {
			SalesReceiptPO object = (SalesReceiptPO) iterator.next();
			if(changeDateToInt(object.getTime())>=changeDateToInt(beginTime)&&changeDateToInt(object.getTime())<=changeDateToInt(endTime)){
				//判断是否为销售单,如果是就加入总和
				if(object.getSerialNumber().substring(0, 3).equals("XSD")){
					totalIncome+=object.getFinalprice();
				}
			}
			
		}
		
		return totalIncome;
		
	}
	//一段时间内的销售成本 TODO 未完成 因为商品的销售价格并不是price，而是salesPrice,需要改动所有和sales有关的goods的属性
	public double getTotalCostInATime(String beginTime,String endTime){
		
		double totalCost=0.0;
		ArrayList<Object> receipts=this.show();
		for (Iterator iterator = receipts.iterator(); iterator.hasNext();) {
			SalesReceiptPO object = (SalesReceiptPO) iterator.next();
			if(changeDateToInt(object.getTime())>=changeDateToInt(beginTime)&&changeDateToInt(object.getTime())<=changeDateToInt(endTime)){
				//判断单据类型
				if(object.getSerialNumber().substring(0, 3).equals("XSD")){
					for(int i=0;i<object.getSalesList().size();i++){
						totalCost+=object.getSalesList().get(i).getGoodsPO().getPrice();//获得所有进价之和
					}
				}
			}			
		}
		
		return totalCost;
	}
	//一段时间的折让总和
	public double getTotalDiscountInAtime(String beginTime,String endTime){
		double totalDiscount=0.0;
		ArrayList<Object> receipts=this.show();
		
		for (Iterator iterator = receipts.iterator(); iterator.hasNext();) {
			SalesReceiptPO object = (SalesReceiptPO) iterator.next();
			if(changeDateToInt(object.getTime())>=changeDateToInt(beginTime)&&changeDateToInt(object.getTime())<=changeDateToInt(endTime)){
				if(object.getSerialNumber().substring(0, 3).equals("XSD")){
					totalDiscount+=object.getDiscout();
				}
			}
		}
		
		return totalDiscount;
		
	}
	//一段时间进货退货差价
	public double getDiffrence(String beginTime,String endTime){
		double totalDeal=0.0;
		ArrayList<Object> receipts=this.show();
		
		for (Iterator iterator = receipts.iterator(); iterator.hasNext();) {
			SalesReceiptPO object = (SalesReceiptPO) iterator.next();
			if(changeDateToInt(object.getTime())>=changeDateToInt(beginTime)&&changeDateToInt(object.getTime())<=changeDateToInt(endTime)){
				if(object.getSerialNumber().substring(0, 3).equals("XSD")){
					totalDeal+=object.getFinalprice();
				}else{//销售退货单
					totalDeal-=object.getFinalprice();
				}
				
			}
		}
		
		return totalDeal;
		
	}
	
	//代金券与实际收款差额
	public double getDifferenceFromVocherInATime(String beginTime,String endTime){
		double totalDiff=0.0;
		
		ArrayList<Object> receipts=this.show();
		
		for (Iterator iterator = receipts.iterator(); iterator.hasNext();) {
			SalesReceiptPO object = (SalesReceiptPO) iterator.next();
			if(changeDateToInt(object.getTime())>=changeDateToInt(beginTime)&&changeDateToInt(object.getTime())<=changeDateToInt(endTime)){
				if(object.getSerialNumber().substring(0, 3).equals("XSD")&&(object.getVocher()>object.getFinalprice())){
					totalDiff+=(object.getVocher()-object.getFinalprice());
				}
			}
		}
		return totalDiff;
	}
	
	//获得一段时间的销售和销售退货单
		public ArrayList<Object> getReceiptsInATime(String beginTime,String endTime){
			ArrayList<Object> result=new ArrayList<Object>();//第一个元素是进货单的list，第二个元素是进货退货单的list
			ArrayList<SalesReceiptPO> salesReceipts=new ArrayList<SalesReceiptPO>();
			ArrayList<SalesReceiptPO> salesBackReceipts=new ArrayList<SalesReceiptPO>();
			
			result=this.show();
			
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				SalesReceiptPO salesReceiptPO = (SalesReceiptPO) iterator
						.next();
			if(changeDateToInt(salesReceiptPO.getTime())>=changeDateToInt(beginTime)&&changeDateToInt(salesReceiptPO.getTime())<=changeDateToInt(endTime)){
				if(salesReceiptPO.getSerialNumber().substring(0, 3).equals("XSD")){
					salesReceipts.add(salesReceiptPO);
				}else{
					salesBackReceipts.add(salesReceiptPO);
				}
			}
			}
			
			result.add(salesReceipts);
			result.add(salesBackReceipts);
			
			return result;
		}
	

	//将日期转换为可以比较大小的整数
	public int changeDateToInt(String date){
		int result=new Integer(0).parseInt(date.replaceAll("-", ""));
		
		return result;
		
	}
	
	public static void main(String [] args){
		SalesController salesController=new SalesController();
		for(int i=0;i<salesController.salesReceipts.size();i++){
			System.out.println(salesController.salesReceipts.get(i).getSalesList().get(0).getTotalPrice());
		}
	}
	
	
}
