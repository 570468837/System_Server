package PO;

import static org.junit.Assert.*;

import org.junit.Test;



public class Purchase_TotalIntergration_Tester {

	@Test
	public void test() {
		MockGoods goods1 = new MockGoods(50);
		MockGoods goods2 = new MockGoods(30);

		PurchaseListItemPO purchaseListItem1 = new PurchaseListItemPO(goods1, 2);
		PurchaseListItemPO purchaseListItem2 = new PurchaseListItemPO(goods2, 3);

		PurchaseReceiptPO purchaseReceipt = new PurchaseReceiptPO();

		purchaseReceipt.addPurchaseListItem(purchaseListItem1);
		purchaseReceipt.addPurchaseListItem(purchaseListItem2);

		assertEquals(190, (int) purchaseReceipt.getTotalPrice());
	}

}
