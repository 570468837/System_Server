package DataService.UserDataService;
/**
 * @author yaoMengzhou
 * 
 */
import PO.UserPO;
import ResultMessage.ResultMessage;

public class UserDataService_Driver {
	public void drive(UserController userdataservice){
		userdataservice.save();
		userdataservice.read();
	}
	
	public static void main(String[] args){
		UserController userController=new UserController();
		new UserDataService_Driver().drive(userController);
	}
}
