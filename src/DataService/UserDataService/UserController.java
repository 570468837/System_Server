package DataService.UserDataService;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import PO.UserPO;
import ResultMessage.ResultMessage;

public class UserController implements UserDataService {
	ArrayList<UserPO> users=new ArrayList<UserPO>(); 
	
	public UserController(){
		read();
	}
	
	public void read(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Datas\\UserPO.out");
			if(fis.available()>0){
			ObjectInputStream oin;
			oin = new ObjectInputStream(fis);
			users=(ArrayList<UserPO>)oin.readObject();
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
			fos = new FileOutputStream("datas\\UserPO.out");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
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
	public ResultMessage check(UserPO po) {
		// TODO Auto-generated method stub
		if(po.getUserName().equals("0001"))
			return ResultMessage.login_success;
		else{
			return ResultMessage.login_failure;
		}
	}

	@Override
	public ResultMessage add(UserPO po) {
		// TODO Auto-generated method stub
		boolean exist=false;
		if(!users.isEmpty()){
		for(UserPO p:users){
			if(p.getUserName().equals(po.getUserName())){
				exist=true;
				break;
				}
			}
		}
		if(!exist){
			users.add(po);
			save();
			return ResultMessage.add_success;
		}
		else
			return ResultMessage.add_failure;
	}

	@Override
	public ResultMessage delete(UserPO po) {
		// TODO Auto-generated method stub
		if(po.getUserName().equals("0003"))
			return ResultMessage.delete_success;
		else{
			return ResultMessage.delete_failure;
		}
	}

	@Override
	public ResultMessage update(UserPO po) {
		// TODO Auto-generated method stub
		if(po.getUserName().equals("0004")){
			return ResultMessage.update_success;
		}
		else{
			return ResultMessage.update_failure;
		}
	}

	@Override
	public ArrayList<Object> find(String userName) {
		// TODO Auto-generated method stub
		if(userName.equals("0005"));
		ArrayList<Object> users=new ArrayList<Object>();
		users.add(new UserPO("00051", " ", null, 0));
		users.add(new UserPO("00052", " ", null, 0));
		return users;
	}

}
