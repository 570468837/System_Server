package DataService.PromotionDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Config.PromotionSort;
import PO.GoodsPO;
import PO.PromotionPO;
import PO.SalesListItemPO;
import PO.SalesReceiptPO;
import PO.UserPO;
import ResultMessage.ResultMessage;

public class PromotionController implements PromotionDataService {
ArrayList<PromotionPO> promotions=new ArrayList<PromotionPO>(); 
	
	public PromotionController(){
		read();
	}
	
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas\\PromotionPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			promotions=(ArrayList<PromotionPO>)oin.readObject();
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
			fos = new FileOutputStream("Datas\\PromotionPO.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(promotions);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public ResultMessage add(PromotionPO po){   //供具体调用
		boolean exist=false;
		for(PromotionPO p:promotions){
			if(p.getPromotionId().equals(po.getPromotionId())){
				exist=true;
				break;
			}
		}
		if(!exist){
			promotions.add(po);
			save();
			return ResultMessage.add_success;
		}
		else
			return ResultMessage.add_failure;
	}
	@Override
	public ResultMessage addPackage(PromotionPO po) {  
		// TODO Auto-generated method stub
		return add(po);
	}

	@Override
	public ResultMessage addGift(PromotionPO po) {
		// TODO Auto-generated method stub
		return add(po);
	}

	@Override
	public ResultMessage addVoucher(PromotionPO po) {
		// TODO Auto-generated method stub
		return add(po);
		
	}

	@Override
	public ResultMessage delete(PromotionPO po) {
		// TODO Auto-generated method stub
		boolean exist=false;
		for(PromotionPO p:promotions){
			if(p.getPromotionId().equals(po.getPromotionId())){
				promotions.remove(p);
				exist=true;
				break;
				}
			}
		if(exist){
			save();
			return ResultMessage.delete_success;
		}
		else
			return ResultMessage.delete_failure;
	}

	@Override
	public ArrayList<Object> show() {
		// TODO Auto-generated method stub
		ArrayList<Object> promotionobj=new ArrayList<Object>(promotions);
		return promotionobj;
	}
	

	@Override
	public ArrayList<Object> ifPackage(SalesReceiptPO receipt) {
		// TODO Auto-generated method stub
		ArrayList<Object> returnPromotions=new ArrayList<Object>();
		ArrayList<SalesListItemPO> listItems=receipt.getSalesList();
		ArrayList<String> goodsIds=new ArrayList<String>();
		for(int i=0;i<listItems.size();i++){
			goodsIds.add(listItems.get(i).getGoodsPO().getSerialNumber());
		}
		for(PromotionPO p:promotions){
			if(p.getPromotionType()==PromotionSort.Package){
			GoodsPO goods1=p.getPromotionGoods().get(0);
			GoodsPO goods2=p.getPromotionGoods().get(1);
			if(goodsIds.contains(goods1.getSerialNumber())&&goodsIds.contains(goods2.getSerialNumber()))
				returnPromotions.add(p);
			}
		}
		return returnPromotions;
	}
	
	public ArrayList<Object> ifGift(SalesReceiptPO receipt){
		ArrayList<Object> returnPromotions=new ArrayList<Object>();
		for(PromotionPO p:promotions){
			if(p.getPromotionType()==PromotionSort.Gifts){
				if(receipt.getFinalprice()>=p.getLeastPrice()){
					returnPromotions.add(p);
				}
			}
		}
		return returnPromotions;
	}


	@Override
	public ArrayList<Object> ifVoucher(SalesReceiptPO receipt) {
		// TODO Auto-generated method stub
		ArrayList<Object> returnPromotions=new ArrayList<Object>();
		for(PromotionPO p:promotions){
			if(p.getPromotionType()==PromotionSort.Voucher){
				if(receipt.getFinalprice()>=p.getLeastPrice())
					returnPromotions.add(p);
			}
		}
		return returnPromotions;
	}
}
