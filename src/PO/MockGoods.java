package PO;
/**
 * 
 * @author hutao
 * 类协作的桩程序
 */
public class MockGoods extends GoodsPO{
	double price;
	public MockGoods(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	
}
