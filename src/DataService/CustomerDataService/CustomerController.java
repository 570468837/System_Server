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

import PO.CollectionOrPaymentPO;
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
				customers.remove(p);
				save();
				return ResultMessage.delete_success;
				}
			}
		if(!exist){
			System.out.println("查无此人");
			return ResultMessage.delete_success;
		}
		else
			return ResultMessage.delete_failure;
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
	public ArrayList<Object> findCustomer(String keyword)
			throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<Object> customerList=new ArrayList<Object>();
		ArrayList<Object> allCustomer=new ArrayList<Object>();
		allCustomer=this.show();
		//暂时只针对序列号和名字
		for (Iterator iterator = allCustomer.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			CustomerPO temp=(CustomerPO)object;
			if(temp.getNumber().contains(keyword)||temp.getName().contains(keyword)){
				customerList.add(temp);
			}
		}
		return customerList;
	}

	//
	@Override
	public CustomerPO getCustomerPOById(String serialnumber)
			throws RemoteException {
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			CustomerPO customerPO = (CustomerPO) iterator.next();
			//by name or id
			
			if(customerPO.getName().equals(serialnumber)||customerPO.getNumber().equals(serialnumber)){
				return customerPO;
			}			
		}
		return null;
	}
	public CustomerPO getCustomerPOByName(String name)throws RemoteException{
		ArrayList<Object> customers=this.show();
		CustomerPO customer=null;
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			CustomerPO customerPO = (CustomerPO) iterator.next();
			if(customerPO.getNumber().equals(name)){
				customer=customerPO;
			}			
		}
		return customer;
	}
	
	//收付款单修改应收应付
	public ResultMessage collectionOrPaymentChangePayOrGetting(CollectionOrPaymentPO collectionOrPaymentPO){
		//如果是收款单修改应付
		CustomerPO customer;
		try {
			customer = this.getCustomerPOByName(collectionOrPaymentPO.getCustomer());
			if(collectionOrPaymentPO.getNumber().substring(0, 3).equals("SKD")){
				customer.setGetting(customer.getGetting()+collectionOrPaymentPO.getTotal());
			}
			else{
				customer.setPay(customer.getPay()+collectionOrPaymentPO.getTotal());
			}
			
			this.updateCustomer(customer);
			return ResultMessage.update_success;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.update_failure;
		}
		
		
		
		
	}
	
	
	
	//通过审批的进货单修改应付
	public ResultMessage purchaseChangePay(PurchaseReceiptPO receipt){
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
			customer.setGetting(customer.getPay()+receipt.getTotalPrice());
		}
		//进货退货单
		else{
			customer.setGetting(customer.getPay()-receipt.getTotalPrice());
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
	
	public ResultMessage salesChangeGetting(SalesReceiptPO receipt){
		CustomerPO customer;
		try {
			customer = getCustomerPOById(receipt.getCustomerPO().getNumber());
		
		//TODO 判断权限
//		if(userPO.getSelf().getLevel()!=3){
//			System.out.println("权限过低!");
//			return ResultMessage.update_failure;
//		}
		//判断进货单还是进货退货单
		//销售单
		if(receipt.getSerialNumber().substring(0, 3).equals("XSD")){
			customer.setGetting(customer.getGetting()+receipt.getFinalprice());
		}
		//销售退货单
		else{
			customer.setGetting(customer.getGetting()-receipt.getFinalprice());
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
