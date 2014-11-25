package DataService.UserDataService;

import java.util.ArrayList;

import PO.UserPO;
import ResultMessage.ResultMessage;

public interface UserDataService {
	public ResultMessage check(UserPO po);
    public ResultMessage add(UserPO po);
    public ResultMessage delete(UserPO po);
    public ResultMessage update(UserPO po);
    public ArrayList<Object> find(String userName);
    public ArrayList<Object> show();
}
