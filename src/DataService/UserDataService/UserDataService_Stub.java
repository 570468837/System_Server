package DataService.UserDataService;

import java.util.ArrayList;

import PO.UserPO;
import ResultMessage.ResultMessage;

public class UserDataService_Stub implements UserDataService{

	@Override
	public ResultMessage check(UserPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.login_success;
	}

	@Override
	public ResultMessage add(UserPO po) {
		// TODO Auto-generated method stub

	    return ResultMessage.add_success;
	}

	@Override
	public ResultMessage delete(UserPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.delete_success;
	}

	@Override
	public ResultMessage update(UserPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.update_success;
	}

	@Override
	public ArrayList<Object> find(String userName) {
		// TODO Auto-generated method stub
		System.out.println("Find success!");
		return null;
	}

	@Override
	public ArrayList<Object> show() {
		// TODO Auto-generated method stub
		return null;
	}

}
