package DataService.CustomerDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.CustomerPO;
import ResultMessage.ResultMessage;

public class CustomerController implements CustomerDataService{

	@Override
	public ResultMessage insertCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.add_customer_success;
	}

	@Override
	public ResultMessage deleteCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("delete customer success");
		return ResultMessage.delete_customer_success;
		
	}

	@Override
	public ResultMessage updateCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("update customer success");
		return ResultMessage.update_customer_success;
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
