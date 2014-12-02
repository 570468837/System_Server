package DataService.GoodsDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import PO.GoodsClassPO;
import PO.GoodsPO;
import PO.PurchaseReceiptPO;
import PO.SalesReceiptPO;
import ResultMessage.ResultMessage;;

/**
 * 
 * @author hutao
 *
 */
public class GoodsController implements GoodsDataService{
	ArrayList<GoodsPO> goodsList;
	ArrayList<GoodsClassPO> goodsClassList;
	
	public GoodsController() {
		goodsList = new ArrayList<GoodsPO>();
		goodsClassList = new ArrayList<GoodsClassPO>();
		readFile();
	}
	
	
	
	@Override
	public GoodsPO getGoodsByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoodsPO getGoodsByInfo(String name, String model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GoodsPO> getGoodsVOList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoodsClassPO getGoodsClassByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoodsClassPO getGoodsClassByInfo(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GoodsClassPO> getGoodsClassVOList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addGoods(GoodsPO goodsVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delGoods(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
    /**
     * 用于任何商品信息的更新
     * 更新的内容依据传入的goodsVO属性而定，double和int若为0则不修改，否则修改，String若为"/"则不修改，否则修改
     * id必须为正确的
     */
	@Override
	public ResultMessage updGoods(GoodsPO goodsVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GoodsPO> searchGoods(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addGoodsClass(GoodsClassPO goodsClassVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delGoodsClass(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updGoodsClass(GoodsClassPO goodsClassVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purchaseChangeGoods(ArrayList<PurchaseReceiptPO> receipt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void salesChangeGoods(ArrayList<SalesReceiptPO> receipt) {
		// TODO Auto-generated method stub
		
	}

	private void readFile() {
		FileInputStream gfis, gcfis;
		try {
			gfis = new FileInputStream(goodsURL);
			gcfis = new FileInputStream(goodsClassURL);
		} catch (FileNotFoundException e) {
			System.out.println("goodsPO.out or goodsClassPO.out not found");
		}
		
		
		
		
	}
	
	private static final String goodsURL = "Datas/GoodsPO.out";
	private static final String goodsClassURL = "Datas/GoodsClassPO.out";
}
