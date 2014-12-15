package DataService.CustomerDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.CustomerPO;
import ResultMessage.ResultMessage;

public class CustomerDataService_Stub implements CustomerDataService {

	@Override
	public ResultMessage insertCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("add customer success");
		return null;
	}

	@Override
	public ResultMessage deleteCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delete customer success");
		return null;
	}

	@Override
	public ResultMessage updateCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("update customer success");
		return null;
	}

	@Override
	public ArrayList<Object> findCustomer(String key)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<Object> customerList=new ArrayList<Object>();
		customerList.add(new CustomerPO());
		System.out.println("find customer successfully");
		return customerList;
	}

	@Override
	public CustomerPO getCustomerPOById(String serialnumber)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
