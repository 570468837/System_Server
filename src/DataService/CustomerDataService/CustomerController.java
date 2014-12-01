package DataService.CustomerDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.CustomerPO;
import ResultMessage.ResultMessage;

public class CustomerController implements CustomerDataService{

ArrayList<CustomerPO> customers=new ArrayList<CustomerPO>(); 
	
	public CustomerController(){
		read();
	}
	
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas/CustomerPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			customers=(ArrayList<CustomerPO>)oin.readObject();
			}
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}          
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		     catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			FileOutputStream fos;
			ObjectOutputStream oos;
			fos = new FileOutputStream("Datas/CustomerPO.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(customers);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	@Override
	public ResultMessage insertCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
				boolean exist=false;
				for(CustomerPO p:customers){
					if(p.getNumber().equals(po.getNumber())){
						exist=true;
						break;
						}
					}
				if(!exist){
					customers.add(po);
					save();
					System.out.println("添加客户成功");
					return ResultMessage.add_success;
				}
				else
					return ResultMessage.add_failure;
	}

	@Override
	public ResultMessage deleteCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		boolean exist=false;
		for(CustomerPO p:customers){
			if(p.getNumber().equals(po.getNumber())){
				exist=true;
				break;
				}
			}
		if(!exist){
			customers.remove(po);
			save();
			return ResultMessage.add_success;
		}
		else
			return ResultMessage.add_failure;
	}

	@Override
	public ResultMessage updateCustomer(CustomerPO po) throws RemoteException {
		// TODO Auto-generated method stub
		boolean exist=false;
		for(CustomerPO p:customers){
			if(p.getNumber().equals(po.getNumber())){
				p.setAddress(po.getAddress());
				p.setClerk(po.getClerk());
				p.setLevel(po.getLevel());
				p.setMail(po.getMail());
				p.setName(po.getName());
				p.setPhone(po.getPhone());
				p.setSort(po.getSort());
				p.setZipCode(po.getZipCode());
				exist=true;
				break;
				}
			}
		if(exist){
			save();
			return ResultMessage.update_success;
		}
		else
			return ResultMessage.update_failure;
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
