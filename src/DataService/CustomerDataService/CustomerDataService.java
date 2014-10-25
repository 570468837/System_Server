package DataService.CustomerDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.CustomerPO;

public interface CustomerDataService {

	public void insertCustomer(CustomerPO po)throws RemoteException;
	public void deleteCustomer(CustomerPO po)throws RemoteException;
	public void updateCustomer(CustomerPO po)throws RemoteException;
	public ArrayList<CustomerPO> findCustomer(String key,int id)throws RemoteException;
}

