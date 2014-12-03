package DataService.FinanceDataService;

import java.rmi.RemoteException;

import PO.AccountPO;
import PO.CashPO;
import PO.CollectionOrPaymentPO;

public class FinanceDataService_Driver {
	public void driver(FinanceDataService financeDataService) throws RemoteException{
		financeDataService.insertAccount(new AccountPO()) ;
		financeDataService.delet(new AccountPO());
		financeDataService.update(new AccountPO());
		AccountPO account = new AccountPO() ;
		financeDataService.find("0001");
		financeDataService.insertCollectionOrPaymentPO(new CollectionOrPaymentPO());
		financeDataService.insertCash(new CashPO());
	}
	public static void main(String[] args){
		FinanceController controller = new FinanceController();
		try {
			controller.insertAccount(new AccountPO("s盛宇",1000));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

