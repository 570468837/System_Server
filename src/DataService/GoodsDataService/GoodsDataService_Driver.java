package DataService.GoodsDataService;

import java.util.*;

import PO.*;
import ResultMessage.ResultMessage;


/**
 * 
 * @author hutao
 *
 */
public class GoodsDataService_Driver {
    public void drive(GoodsDataService goodsController) {
		ResultMessage resultMessage = goodsController.addGoods(new GoodsPO());
    	if(resultMessage == ResultMessage.add_success) {
    		System.out.println("Goods add success");
    	}
    	else {
    		System.out.println("Goods add failure");
    	}
    	
    
    	
    	resultMessage = goodsController.updGoods(new GoodsPO());
    	if(resultMessage == ResultMessage.update_success) {
    		System.out.println("Goods upd success");
    	}
    	else {
    		System.out.println("Goods upd failure");
    	}
    	
    	ArrayList<Object> GoodsPOList = goodsController.searchGoods(new String());
    	if(GoodsPOList.size() != 0) {
    		System.out.println("Good shave searched");
    	}
    	else {
    		System.out.println("no match goods");
    	}
    	
    	
    	
    	
    	
	}
	
	
	public static void main(String[] args) {
		GoodsDataService_Driver g = new GoodsDataService_Driver();
		g.drive(new GoodsController());
	}
	

}
