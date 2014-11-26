package DataService.CustomerDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.CustomerPO;
import ResultMessage.ResultMessage;

public interface CustomerDataService {

	public ResultMessage insertCustomer(CustomerPO po)throws RemoteException;
	public ResultMessage deleteCustomer(CustomerPO po)throws RemoteException;
	public ResultMessage updateCustomer(CustomerPO po)throws RemoteException;
	public ArrayList<CustomerPO> findCustomer(String key,int id)throws RemoteException;
}

