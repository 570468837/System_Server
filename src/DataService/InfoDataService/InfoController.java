package DataService.InfoDataService;

import java.util.ArrayList;

import PO.CashPO;
import PO.CollectionPO;
import PO.PaymentPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
import PO.ScreeningConditionPO;
import ResultMessage.ResultMessage;

public class InfoController implements InfoDataService {

	@Override
	public ArrayList<Object> findReceipt(ScreeningConditionPO PO) {
		// TODO Auto-generated method stub
		ArrayList<Object> objects = new ArrayList<Object>();
		if(PO.getTypeOfReceipt().equals("SalesReceiptPO")){
			objects.add(new SalesReceiptPO()) ;
		}
		if(PO.getTypeOfReceipt().equals("PurchaseReceiptPO")){
			objects.add(new PurchaseReceiptPO()) ;
		}
		if(PO.getTypeOfReceipt().equals("CollectionPO")){
			objects.add(new CollectionPO());
		}
		if(PO.getTypeOfReceipt().equals("PaymentPO")){
			objects.add(new PaymentPO()) ;
		}
		if(PO.getTypeOfReceipt().equals("CashPO")){
			objects.add(new CashPO());
		}
		return objects;
	}

	@Override
	public ResultMessage deletReceipt(String typeOfReceipt,String label) {
		// TODO Auto-generated method stub
		
		return null ;
	}

	@Override
	public String getSalesCondition(String time1, String time2) {
		// TODO Auto-generated method stub
		if(time1.equals("0001")){
			System.out.println("success");
		}
		return null;
	}

	@Override
	public ResultMessage deletAndUpdateReceipt(String typeOfreceipt,
			String number) {
		// TODO Auto-generated method stub
		return null;
	}

}
