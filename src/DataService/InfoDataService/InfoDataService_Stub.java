package DataService.InfoDataService;

import ResultMessage.ResultMessage;

public class InfoDataService_Stub implements InfoDataService {

	public ResultMessage findReceipt(String label)  {
		// TODO Auto-generated method stub
		if(label.equals("0001")) {
			System.out.println("success") ;
			return ResultMessage.Exist ;
		}else{
			System.out.println("fail") ;
			return ResultMessage.Not_Exist ;
		}
		
	}

	public void deletReceipt(String label) {
		// TODO Auto-generated method stub
		if(label.equals("0001")) {
			System.out.println("success") ;
		}else{
			System.out.println("fail") ;
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
