package DataService.InfoDataService;

import java.util.ArrayList;

import PO.ScreeningConditionPO;
import ResultMessage.ResultMessage;

public class InfoDataService_Stub implements InfoDataService {

	public ArrayList<Object> findReceipt(ScreeningConditionPO PO)  {
		// TODO Auto-generated method stub
		if(PO.getTime1().equals("0001")) {
			System.out.println("success") ;
			return null ;
		}else{
			System.out.println("fail") ;
			return null ;
		}
		
	}

	public ResultMessage deletReceipt(String label) {
		// TODO Auto-generated method stub
		if(label.equals("0001")) {
			System.out.println("success") ;
			return ResultMessage.delete_success ;
		}else{
			System.out.println("fail") ;
			return ResultMessage.add_failure ;
		}
		
	}

	public String getSalesCondition(String time1, String time2) {
		// TODO Auto-generated method stub
		if(time1.equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
		}
		return null ;
	}
	

}
