package PO;

import static org.junit.Assert.*;

import org.junit.Test;

import PO.PaymentPO;
import PO.TransferListItemPO;

public class Payment_TotalIntegration_Tester {

	@Test
	public void test() {
		TransferListItemPO transferlist1=new TransferListItemPO(null, 200, null);
		TransferListItemPO transferlist2=new TransferListItemPO(null, 300, null);
		
		PaymentPO pay=new PaymentPO();
		pay.add(transferlist1);
		pay.add(transferlist2);
		
		assertEquals(500,(int)pay.getTotal());
	}

}
