package PO;

import PO.AccountPO;

public class TransferListItem {
	AccountPO account;
	double transferMoney ;
	String remark ;
	
	public TransferListItem(AccountPO account, double transferMoney,
			String remark) {
		this.account = account;
		this.transferMoney = transferMoney;
		this.remark = remark;
	}
	public AccountPO getAccount() {
		return account;
	}
	public void setAccount(AccountPO account) {
		this.account = account;
	}
	public double getTransferMoney() {
		return transferMoney;
	}
	public void setTransferMoney(double transferMoney) {
		this.transferMoney = transferMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
