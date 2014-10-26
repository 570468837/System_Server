package DataService.CustomerDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.CustomerPO;

public class CustomerController implements CustomerDataService{

	@Override
	public void insertCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("add customer success");
	}

	@Override
	public void deleteCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delete customer success");
	}

	@Override
	public void updateCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("update customer success");
	}

	@Override
	public ArrayList<CustomerPO> findCustomer(String key, int id)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CustomerPO> customerList=new ArrayList<CustomerPO>();
		customerList.add(new CustomerPO());
		System.out.println("find customer successfully");
		return customerList;
	}
	
}
