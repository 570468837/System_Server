package DataService.InfoDataService;

import java.util.ArrayList;

import PO.ScreeningConditionPO;
import ResultMessage.ResultMessage;

public interface InfoDataService {
	public ArrayList<Object> findReceipt(ScreeningConditionPO PO)  ;
	public ResultMessage deletReceipt(String number) ;
	public String getSalesCondition(String time1 ,String time2) ;
}
