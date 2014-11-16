package DataService.UserDataService;

import java.util.ArrayList;
import PO.UserPO;
import ResultMessage.ResultMessage;

public class UserController implements UserDataService {

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
		if(po.getUserName().equals("0002")){
			return ResultMessage.add_success;
		}
		else{
			return ResultMessage.add_failure;
		}
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
