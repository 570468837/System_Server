package DataService.InfoDataService;

public class InfoDataService_Driver {
	public void driver(InfoDataService infoDataService){
		String label = "0001" ;
		infoDataService.findReceipt(label) ;
		infoDataService.deletReceipt(label); 
		infoDataService.getSalesCondition("0001", "0002") ;
	}
}
