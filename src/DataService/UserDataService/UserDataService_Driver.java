package DataService.UserDataService;
/**
 * @author yaoMengzhou
 * 
 */
import PO.UserPO;
import ResultMessage.ResultMessage;

public class UserDataService_Driver {
	public void drive(UserDataService userdataservice){
		UserPO po = new UserPO(null, null, null, 0);
		ResultMessage result;
		result=userdataservice.check(po);
		if(result==ResultMessage.login_success)
			System.out.println("login success!");
		
		result=userdataservice.add(po);
		if(result==ResultMessage.add_success)
		    System.out.println("add success!");
		
		result=userdataservice.delete(po);
		if(result==ResultMessage.delete_success)
			System.out.println("delete success!");
		
		result=userdataservice.update(po);
		if(result==ResultMessage.update_success)
			System.out.println("update success!");
	}
	
	public static void main(String[] args){
		UserDataService userController=new UserController();
		new UserDataService_Driver().drive(userController);
	}
}
