package DataService.CustomerDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import PO.CustomerPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
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
	public ArrayList<CustomerPO> findCustomer(String keyword)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CustomerPO> customerList=new ArrayList<CustomerPO>();
		customerList.add(new CustomerPO());
		System.out.println("find customer successfully");
		return customerList;
	}

	@Override
	public CustomerPO getCustomerPOById(String serialnumber)
			throws RemoteException {
		ArrayList<Object> customers=this.show();
		CustomerPO customer=null;
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			CustomerPO customerPO = (CustomerPO) iterator.next();
			if(customerPO.getNumber().equals(serialnumber)){
				customer=customerPO;
			}			
		}
		return customer;
	}
	
	public ResultMessage purchaseChangeGetting(PurchaseReceiptPO receipt){
		//
		CustomerPO customer;
		try {
			customer = getCustomerPOById(receipt.getCustomerPO().getNumber());
		
		//TODO 判断权限
//		if(userPO.getSelf().getLevel()!=3){
//			System.out.println("权限过低!");
//			return ResultMessage.update_failure;
//		}
		//判断进货单还是进货退货单
		//进货单
		if(receipt.getSerialNumber().substring(0, 3).equals("JHD")){
			customer.setGetting(customer.getGetting()+receipt.getTotalPrice());
		}
		//进货退货单
		else{
			customer.setGetting(customer.getGetting()-receipt.getTotalPrice());
		}
		
			this.updateCustomer(customer);
			return ResultMessage.update_success;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("修改客户应收出错！");
			e.printStackTrace();
			return ResultMessage.update_failure;
		}
	}
	
	public ResultMessage salesChangePay(SalesReceiptPO receipt){
		CustomerPO customer;
		try {
			customer = getCustomerPOById(receipt.getCustomerPO().getNumber());
		
		//TODO 判断权限
//		if(userPO.getSelf().getLevel()!=3){
//			System.out.println("权限过低!");
//			return ResultMessage.update_failure;
//		}
		//判断进货单还是进货退货单
		//进货单
		if(receipt.getSerialNumber().substring(0, 3).equals("XSD")){
			customer.setGetting(customer.getPay()+receipt.getFinalprice());
		}
		//进货退货单
		else{
			customer.setGetting(customer.getPay()-receipt.getFinalprice());
		}
		
			this.updateCustomer(customer);
			return ResultMessage.update_success;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("修改客户应付出错！");
			e.printStackTrace();
			return ResultMessage.update_failure;
		}
	}
	
	//返回所有客户
	public ArrayList<Object> show(){
//		System.out.println("is here?");
		ArrayList<Object> customers_2=new ArrayList<Object>(customers);
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas/CustomerPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			customers_2=(ArrayList<Object>)oin.readObject();
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
		
		return customers_2;
	}
	
	
	public static void main(String []args){
		
		System.out.println(new CustomerController().show());
	}

	
	
}
