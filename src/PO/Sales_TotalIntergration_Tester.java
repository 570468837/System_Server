package PO;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Sales_TotalIntergration_Tester {

	@Test
	public void test() {
		MockGoods goods1=new MockGoods(50);
		MockGoods goods2=new MockGoods(30);
		
		SalesListItemPO salesListItem1=new SalesListItemPO(goods1,2);
		SalesListItemPO salesListItem2=new SalesListItemPO(goods2,3);
	
		SalesReceiptPO salesReceipt=new SalesReceiptPO();
		
		salesReceipt.addSalesListItem(salesListItem1);
		salesReceipt.addSalesListItem(salesListItem2);
		
		assertEquals(190, (int)salesReceipt.getPriveBefore());
	}

}
