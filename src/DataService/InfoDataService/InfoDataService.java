package DataService.InfoDataService;

import java.util.ArrayList;

import PO.ScreeningConditionPO;
import ResultMessage.ResultMessage;

public interface InfoDataService {
	public ArrayList<Object> findReceipt(ScreeningConditionPO PO)  ;
	public ResultMessage deletReceipt(String typeOfReceipt,String number) ;
	public ResultMessage deletAndUpdateReceipt(String typeOfreceipt,String number);
	public String getSalesCondition(String time1 ,String time2) ;
}
