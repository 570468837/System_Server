package DataService.GoodsDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

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
	Iterator<GoodsPO> gIter;
	Iterator<GoodsClassPO> gcIter;
	
	public GoodsController() {
		goodsList = new ArrayList<GoodsPO>();
		goodsClassList = new ArrayList<GoodsClassPO>();
		readFile();
	}
	
	
	/**
	 * 通过id获取商品对象
	 */
	@Override
	public GoodsPO getGoodsByID(long id) {
		gIter = goodsList.iterator();
		GoodsPO g;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.getSerialNumber().equals(Long.toString(id)))
				return g;
		}
		return null;
	}

	/**
	 * 通过名字和型号获取商品对象
	 */
	@Override
	public GoodsPO getGoodsByInfo(String name, String model) {
		gIter = goodsList.iterator();
		GoodsPO g;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.getName().equals(name) && g.getModel().equals(model))
				return g;
		}
		System.out.println("goods not found");
		return null;
	}

	/**
	 * 获取商品列表
	 */
	@Override
	public ArrayList<GoodsPO> getGoodsPOList() {
		return goodsList;
	}

	/**
	 * 通过商品分类id获取商品分类对象
	 */
	@Override
	public GoodsClassPO getGoodsClassByID(long id) {
		gcIter = goodsClassList.iterator();
		GoodsClassPO gc;
		while(gcIter.hasNext()) {
			gc = gcIter.next();
			if(gc.Num == id)
				return gc;
		}
		System.out.println("goodsClass not found");
		return null;
	}

	/**
	 * 通过商品分类名获取商品分类对象
	 */
	@Override
	public GoodsClassPO getGoodsClassByInfo(String name) {
		gcIter = goodsClassList.iterator();
		GoodsClassPO gc;
		while(gcIter.hasNext()) {
			gc = gcIter.next();
			if(gc.goodsClassName.equals(name))
				return gc;
		}
		System.out.println("goodsClass not found");
		return null;
	}

	/**
	 * 获取商品分类列表
	 */
	@Override
	public ArrayList<GoodsClassPO> getGoodsClassPOList() {
		return goodsClassList;
	}

	/**
	 * 添加商品
	 */
	@Override
	public ResultMessage addGoods(GoodsPO goodsPO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 删除商品
	 */
	@Override
	public ResultMessage delGoods(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
    /**
     * 用于任何商品信息的更新
     * 更新的内容依据传入的goodsPO属性而定，double和int若为0则不修改，否则修改，String若为"/"则不修改，否则修改
     * id必须为正确的
     */
	@Override
	public ResultMessage updGoods(GoodsPO goodsPO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 搜索商品
	 */
	@Override
	public ArrayList<GoodsPO> searchGoods(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 添加商品分类
	 */
	@Override
	public ResultMessage addGoodsClass(GoodsClassPO goodsClassPO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 删除商品分类
	 */
	@Override
	public ResultMessage delGoodsClass(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 更新商品分类
	 */
	@Override
	public ResultMessage updGoodsClass(GoodsClassPO goodsClassPO) {
		// TODO Auto-generated method stub
		return null;
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
	
	private void writeFile() {
		
	}
	
	private static final String goodsURL = "Datas/GoodsPO.out";
	private static final String goodsClassURL = "Datas/GoodsClassPO.out";
}
