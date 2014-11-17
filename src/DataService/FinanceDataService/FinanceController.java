package DataService.FinanceDataService;

import java.rmi.RemoteException;

import PO.AccountPO;
import PO.CashPO;
import PO.CollectionPO;
import PO.PaymentPO;
import ResultMessage.ResultMessage;

public class FinanceController implements FinanceDataService {
	ResultMessage result = null ;
	@Override
	public ResultMessage insertAccount(AccountPO account) throws RemoteException {
		// TODO Auto-generated method stub
		if(account.getName().equals("0001")){
			result = ResultMessage.add_success ;
		}else{
			result = ResultMessage.add_failure ;
		}
		return result ;
	}

	@Override
	public void delet(AccountPO account) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AccountPO account) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AccountPO find(AccountPO accout) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertPayment(PaymentPO payment) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertCollection(CollectionPO payment) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertCash(CashPO cash) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}
	

}
