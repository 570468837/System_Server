package DataService.FinanceDataService;

import java.rmi.RemoteException;

import PO.AccountPO;
import PO.CashPO;
import PO.CollectionPO;
import PO.PaymentPO;

public class FinanceDataService_Driver {
	public void driver(FinanceDataService financeDataService) throws RemoteException{
		financeDataService.insertAccount(new AccountPO()) ;
		financeDataService.delet(new AccountPO());
		financeDataService.update(new AccountPO());
		AccountPO account = new AccountPO() ;
		financeDataService.find("0001");
		financeDataService.insertCollection(new CollectionPO());
		financeDataService.insertPayment(new PaymentPO());
		financeDataService.insertCash(new CashPO());
	}
}

