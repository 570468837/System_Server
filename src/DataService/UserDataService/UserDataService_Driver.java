package DataService.UserDataService;
/**
 * @author yaoMengzhou
 * 
 */
import PO.UserPO;
import ResultMessage.ResultMessage;

public class UserDataService_Driver {
	public void drive(UserController userdataservice){
		System.out.println(new UserController().add(new UserPO("nihao", null, null, 30)));
		System.out.println(new UserController().update(new UserPO("nihao", null, null, 50)));
		userdataservice=new UserController();
		userdataservice.read();
		System.out.println(((UserPO)userdataservice.show().get(0)).getLevel());
		
	}
	
	public static void main(String[] args){
		UserController userController=new UserController();
		new UserDataService_Driver().drive(userController);
	}
}
