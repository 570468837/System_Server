package DataService.UserDataService;
/**
 * @author yaoMengzhou
 * 
 */
import Config.UserSort;
import PO.UserPO;
import ResultMessage.ResultMessage;

public class UserDataService_Driver {
	public void drive(UserController userdataservice){
		System.out.println(new UserController().delete(new UserPO("2333",null,null,0)));
		System.out.println(new UserController().delete(new UserPO("",null,null,0)));
		
		userdataservice=new UserController();
		userdataservice.read();
		System.out.println(((UserPO)userdataservice.show().get(0)).getLevel());
		
	}
	
	public static void main(String[] args){
		UserController userController=new UserController();
		new UserDataService_Driver().drive(userController);
	}
}
