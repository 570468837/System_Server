package DataService.FinanceDataService;

import java.rmi.RemoteException;

import PO.AccountPO;
import PO.CashPO;
import PO.CollectionPO;
import PO.PaymentPO;
import ResultMessage.ResultMessage;

public interface FinanceDataService {
	public ResultMessage insertAccount(AccountPO account) throws RemoteException;
	public void delet(AccountPO account) throws RemoteException;
	public void update(AccountPO account) throws RemoteException;
	public AccountPO find(AccountPO accout) throws RemoteException;
	public void insertPayment(PaymentPO payment) throws RemoteException ;
	public void insertCollection(CollectionPO payment) throws RemoteException ;
	public void insertCash(CashPO cash) throws RemoteException ;
	public void finish() ;
}
