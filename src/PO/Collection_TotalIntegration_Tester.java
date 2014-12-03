package PO;

import static org.junit.Assert.*;

import org.junit.Test;

import PO.AccountPO;
import PO.CollectionOrPaymentPO;
import PO.TransferListItemPO;

public class Collection_TotalIntegration_Tester {

	@Test
	public void test() {
		TransferListItemPO listItem1=new TransferListItemPO(null,100,null);
		TransferListItemPO listItem2=new TransferListItemPO(null,200,null);
		
		CollectionOrPaymentPO collection=new CollectionOrPaymentPO();
		collection.add(listItem1);
		collection.add(listItem2);
		
		assertEquals(300, (int)collection.getTotal());
	}

}
