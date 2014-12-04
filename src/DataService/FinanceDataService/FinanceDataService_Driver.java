package DataService.FinanceDataService;

import java.rmi.RemoteException;

import PO.AccountPO;
import PO.CashPO;
import PO.CollectionOrPaymentPO;

public class FinanceDataService_Driver {
	public void driver(FinanceDataService financeDataService) throws RemoteException{
//		financeDataService.insertAccount(new AccountPO()) ;
//		financeDataService.delet(new AccountPO());
//		financeDataService.update(new AccountPO());
//		AccountPO account = new AccountPO() ;
//		financeDataService.find("0001");
//		financeDataService.insertCollectionOrPaymentPO(new CollectionOrPaymentPO());
//		financeDataService.insertCash(new CashPO());
		
	}
	public static void main(String[] args){
//		FinanceController controller = new FinanceController();
		FinanceController f=new FinanceController();
		System.out.print(f.cpReceipts.size());
	}
}

