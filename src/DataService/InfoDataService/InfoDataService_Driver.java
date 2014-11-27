package DataService.InfoDataService;

import PO.ScreeningConditionPO;

public class InfoDataService_Driver {
	public void driver(InfoDataService infoDataService){
		String label = "0001" ;
		infoDataService.findReceipt(new ScreeningConditionPO()) ;
		infoDataService.deletReceipt(null,label); 
		infoDataService.getSalesCondition("0001", "0002") ;
	}
}
