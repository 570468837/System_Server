package DataService.UserDataService;

import PO.UserPO;
import ResultMessage.ResultMessage;

public class UserDataService_Stub implements UserDataService{

	@Override
	public ResultMessage check(UserPO po) {
		// TODO Auto-generated method stub
		System.out.println("check right!");
		return ResultMessage.login_success;
	}

	@Override
	public ResultMessage add(UserPO po) {
		// TODO Auto-generated method stub
	    System.out.println("add success!");
	    return ResultMessage.add_success;
	}

	@Override
	public ResultMessage delete(UserPO po) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ResultMessage update(UserPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPO find(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
