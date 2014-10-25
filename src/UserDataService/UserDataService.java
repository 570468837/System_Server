package UserDataService;

import PO.UserPO;
import ResultMessage.ResultMessage;

public interface UserDataService {
	public ResultMessage check(UserPO po);
    public ResultMessage add(UserPO po);
    public ResultMessage delete(UserPO po);
    public ResultMessage update(UserPO po);
    public UserPO find(String userName);
}
