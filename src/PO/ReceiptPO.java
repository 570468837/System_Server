package PO;

public class ReceiptPO {
//�������ģ�壬���еĵ��ݶ�Ҫ�̳������
	String time = null ;//���ݴ���ʱ��
	String number = null ;//���ݱ�ţ������������͵���Ϣ�����磺SKD-xxxxxxxxx�������۵���FKD-xxxxxxxxxx�����
	CustomerPO customer = null ;
	UserPO user = null ;
	String storage = null ;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public CustomerPO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerPO customer) {
		this.customer = customer;
	}
	public UserPO getUser() {
		return user;
	}
	public void setUser(UserPO user) {
		this.user = user;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	
}
