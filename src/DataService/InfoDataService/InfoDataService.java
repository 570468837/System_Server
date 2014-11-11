package DataService.InfoDataService;

import ResultMessage.ResultMessage;

public interface InfoDataService {
	public ResultMessage findReceipt(String label)  ;
	public void deletReceipt(String label) ;
	public String getSalesCondition(String time1 ,String time2) ;
}
