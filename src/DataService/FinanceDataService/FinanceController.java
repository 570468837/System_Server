package DataService.FinanceDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.print.CancelablePrintJob;

import com.mysql.fabric.xmlrpc.base.Data;

import PO.AccountPO;
import PO.CashPO;
import PO.CollectionOrPaymentPO;
import PO.AccountPO;
import ResultMessage.ResultMessage;

public class FinanceController implements FinanceDataService {
	ResultMessage result = null ;
	ArrayList<AccountPO> accounts = new ArrayList<AccountPO>();
	ArrayList<CollectionOrPaymentPO> cpReceipts = new ArrayList<CollectionOrPaymentPO>();
	ArrayList<CashPO> cashReceipts = new ArrayList<CashPO>() ;
	boolean isExist = false ;
	
	public FinanceController(){
		read() ;
	}
	
	public void read(){
		try {
			FileInputStream fis1;
			fis1 = new FileInputStream("Datas/AccountPO.out");
			FileInputStream fis2  = new FileInputStream("Datas/CollectionOrPaymentPO.out") ;
			FileInputStream fis3 = new FileInputStream("Datas/CashPO.out");
			
			if(fis1.available()>0&&fis2.available()>0&&fis3.available()>0){
			ObjectInputStream oin1 ;
			oin1 = new ObjectInputStream(fis1);
			accounts=(ArrayList<AccountPO>)oin1.readObject();
			}
			
			if(fis2.available()>0){
				ObjectInputStream oin2 = new ObjectInputStream(fis2) ;
				cpReceipts = (ArrayList<CollectionOrPaymentPO>)oin2.readObject() ;
			}
			if(fis3.available()>0){
				ObjectInputStream oin3 = new ObjectInputStream(fis3) ;
	    		cashReceipts = (ArrayList<CashPO>)oin3.readObject() ;
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
			FileOutputStream fos1,fos2,fos3;
			ObjectOutputStream oos1,oos2,oos3;
			fos1 = new FileOutputStream("Datas/AccountPO.out");
			oos1 = new ObjectOutputStream(fos1);
			oos1.writeObject(accounts);
			
			fos2 = new FileOutputStream("Datas/CollectionOrPaymentPO.out") ;
			oos2 = new ObjectOutputStream(fos2) ;
			oos2.writeObject(cpReceipts);
			
			fos3 = new FileOutputStream("Datas/CashPO.out");
			oos3 = new ObjectOutputStream(fos3) ;
			oos3.writeObject(cashReceipts);
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
		for(AccountPO theAccount : accounts){
			if(theAccount.getName().equals(account.getName())){
				isExist = true;
				break;
			}
		}
		if(!isExist){
			accounts.add(account) ;
			save() ;
			result = ResultMessage.add_success ;
		}else{
			result = ResultMessage.add_failure ;
		}
		return result ;
	}

	@Override
	public ResultMessage delet(AccountPO account) throws RemoteException {
		for(AccountPO theAccount : accounts){
			if(account.getName().equals(theAccount.getName())){
				isExist = true ;
				accounts.remove(theAccount) ;
				save();
				break ;
			}
		}
		if(isExist){
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
		ArrayList<Object> findAccounts = new ArrayList<Object>() ;
		for(AccountPO theAccount:accounts){
			if(theAccount.getName().contains(keyword)){
				findAccounts.add(theAccount) ;
			}
		}
		return findAccounts ;
	}
	

	public ArrayList<Object> show() throws RemoteException {
		ArrayList<Object> result = new ArrayList<Object>() ;
		for(AccountPO theAccount :accounts){
			result.add(theAccount) ;
		}
		return result;
	}

	@Override
	public ResultMessage insertCollectionOrPaymentPO(CollectionOrPaymentPO receipt) throws RemoteException {
		// TODO Auto-generated method stub
		cpReceipts.add(receipt) ;
		save() ;
		return ResultMessage.add_success ;
	}

	@Override
	public ResultMessage insertCash(CashPO cash) throws RemoteException {
		// TODO Auto-generated method stub
		cashReceipts.add(cash) ;
		save() ;
		return ResultMessage.add_success ;
	}

	@Override
	public String getReceiptNumber(String typeOfReceipt) {
		// TODO Auto-generated method stub
		int count = 1 ;
		
		String number = "" ;
		
		String keyWord = typeOfReceipt+"-"+getDate()+"-";
		if(typeOfReceipt.equals("SKD")||typeOfReceipt.equals("FKD")){
			for(CollectionOrPaymentPO theReceipt:cpReceipts){
				if(theReceipt.getNumber().contains(keyWord))
					count++ ;
			}
		}else{
			if(typeOfReceipt.equals("XJFYD")){
				for(CashPO theCash: cashReceipts){
					if(theCash.getNumber().contains(keyWord))
						count++ ;
				}
			}
		}
		if(count<10){
			number = "000"+count ;
		}else{
			if(count<100){
				number = "00"+count ;
			}else{
				if(count<1000){
					number = "0"+count ;
				}else{
					number = String.valueOf(count) ;
		  		}
			}
		}
		return keyWord+number ;
	}
	

	/*
	 * 获得当前日期，形式：yyyymmdd
	 */
	public String getDate(){//yyyymmdd
		Calendar cal = Calendar.getInstance() ;
		int y = cal.get(Calendar.YEAR) ;
		int m = cal.get(Calendar.MONTH) + 1 ;
		int d = cal.get(Calendar.DAY_OF_MONTH) ;
		String month = String.valueOf(m) ;
		String day = String.valueOf(d) ;
		if(m<10)
			month = "0"+month ;
		if(d<10)
			day = "0" + day ;
		String date = String.valueOf(y)+month+day ;
		return date ;
	}


@Override
public ArrayList<Object> showCollectionOrPayment() throws RemoteException {
	// TODO Auto-generated method stub
	ArrayList<Object> result = new ArrayList<>(cpReceipts) ;
	return result ;
}

@Override
public ArrayList<Object> showCash() throws RemoteException {
	// TODO Auto-generated method stub
	ArrayList<Object> result = new ArrayList<>(cashReceipts) ;
	return result ;
}

@Override
public ResultMessage updateCollectionOrPayment(CollectionOrPaymentPO po)
		throws RemoteException {
	// TODO Auto-generated method stub
	result = ResultMessage.update_failure ;
	for(CollectionOrPaymentPO theReceipt : cpReceipts){
		if(theReceipt.getNumber().equals(po.getNumber())){
			theReceipt.setCustomer(po.getCustomer());
			theReceipt.setTypeOfCustomer(po.getTypeOfCustomer());
			theReceipt.setUser(po.getUser());
			theReceipt.setTrList(po.getTrList());
			theReceipt.setTotal(po.getTotal());
			theReceipt.setApprovedByManager(po.isApprovedByManager());
			theReceipt.setApprovedByFinancer(po.isApprovedByFinancer());
			result = ResultMessage.update_success ;
		}
	}
	save() ;
	return result;
}
@Override
public void finish() {
	// TODO Auto-generated method stub
	
}
public static void main(String[] args){
	FinanceController f = new FinanceController() ;
	f.save();
}
}
