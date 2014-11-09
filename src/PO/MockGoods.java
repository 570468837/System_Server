package PO;
/**
 * 
 * @author hutao
 * 类协作的桩程序
 */
public class MockGoods extends GoodsPO{
	long price;
	public MockGoods(long price) {
		this.price = price;
	}
	
	public long getPrice() {
		return price;
	}
	
	
}
