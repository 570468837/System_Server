package DataService.CommodityDataService;

import java.rmi.RemoteException;

import PO.*;
import ResultMessage.ResultMessage;

/**
 * 
 * @author hutao
 *
 */
public class CommodityDataService_Driver {
	public void drive(CommodityDataService commodityController) throws RemoteException {
		ResultMessage resultMessage = commodityController.insertReportCommodity(new ReportCommodityPO());
	    if(resultMessage == ResultMessage.add_success) {
	    	System.out.println("reportCommodity add succcess");
	    }
	    else {
	    	System.out.println("reportCommodity add failure");
	    }
		
	    resultMessage = commodityController.insertSendCommodity(new SendCommodityPO());
	    if(resultMessage == ResultMessage.add_success) {
	    	System.out.println("sendCommodity add succcess");
	    }
	    else {
	    	System.out.println("sendCommodity add failure");
	    }
	    
	    
	    
	    
		
	}
	
	
	public static void main(String[] args) throws RemoteException {
		CommodityDataService_Driver c = new CommodityDataService_Driver();
		//c.drive(new CommodityController());
	}
	
	

}
