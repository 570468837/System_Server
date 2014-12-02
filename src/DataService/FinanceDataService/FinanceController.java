package DataService.FinanceDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

import PO.AccountPO;
import PO.CashPO;
import PO.CollectionPO;
import PO.PaymentPO;
import PO.AccountPO;
import ResultMessage.ResultMessage;

public class FinanceController implements FinanceDataService {
	ResultMessage result = null ;
	ArrayList<AccountPO> accounts = new ArrayList<AccountPO>();
	
	public FinanceController(){
		read() ;
	}
	
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas/AccountPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			accounts=(ArrayList<AccountPO>)oin.readObject();
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
			fos = new FileOutputStream("Datas/AccountPO.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(accounts);
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
	public ResultMessage insertAccount(AccountPO account) throws RemoteException {
		// TODO Auto-generated method stub
		boolean isExist = false ;
		for(AccountPO theAccount : accounts){
			if(theAccount.getName().equals(account.getName())){
				isExist = true;
				break;
			}
		}
		if(!isExist){
			accounts.add(account) ;
			result = ResultMessage.add_success ;
		}else{
			result = ResultMessage.add_failure ;
		}
		return result ;
	}

	@Override
	public ResultMessage delet(AccountPO account) throws RemoteException {
		if(account.getName().equals("0001")){
			result = ResultMessage.delete_success ;
		}else{
			result = ResultMessage.delete_failure ;
		}
		return result ;
	}

	@Override
	public ResultMessage update(AccountPO account) throws RemoteException {
		// TODO Auto-generated method stub
		if(account.getName().equals("0001")){
			result = ResultMessage.update_success ;
		}else{
			result = ResultMessage.update_failure ;
		}
		return result ;
	}

	@Override
	public ArrayList<Object> find(String keyword) throws RemoteException {
		// TODO Auto-generated method stub
		if(keyword.equals("0001")){
			ArrayList<Object> accounts = new ArrayList<>() ;
			accounts.add(new AccountPO(keyword,212)) ;
			accounts.add(new AccountPO(keyword,366)) ;
			System.out.println("find successd") ;
			return accounts ;
		}else{
			System.out.println("find fail") ;
			return null ;
		}
	}

	@Override
	public ResultMessage insertPayment(PaymentPO payment) throws RemoteException {
		// TODO Auto-generated method stub
		if(payment.getNumber().equals("0001")){
			result = ResultMessage.add_success ;
		}else{
			result = ResultMessage.add_failure ;
		}
		return result ;
	}

	@Override
	public ResultMessage insertCollection(CollectionPO collection) throws RemoteException {
		// TODO Auto-generated method stub
		if(collection.getNumber().equals("0001")){
			result = ResultMessage.add_success ;
		}else{
			result = ResultMessage.add_failure ;
		}
		return result ;
	}

	@Override
	public ResultMessage insertCash(CashPO cash) throws RemoteException {
		// TODO Auto-generated method stub
		if(cash.getNumber().equals("0001")){
			result = ResultMessage.add_success ;
		}else{
			result = ResultMessage.add_failure ;
		}
		return result ;
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}
	

}
