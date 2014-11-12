package PO;

import static org.junit.Assert.*;

import org.junit.Test;

import PO.AccountPO;
import PO.CollectionPO;
import PO.TransferListItem;

public class Collection_TotalIntegration_Tester {

	@Test
	public void test() {
		TransferListItem listItem1=new TransferListItem(null,100,null);
		TransferListItem listItem2=new TransferListItem(null,200,null);
		
		CollectionPO collection=new CollectionPO();
		collection.add(listItem1);
		collection.add(listItem2);
		
		assertEquals(300, (int)collection.getTotal());
	}

}
