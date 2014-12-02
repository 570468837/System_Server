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
	 * 通过id获取新的商品对象
	 */
	@Override
	public GoodsPO getGoodsByID(long id) {
		gIter = goodsList.iterator();
		GoodsPO g;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.getSerialNumber().equals(Long.toString(id)))
				return new GoodsPO(g);
		}
		return null;
	}

	/**
	 * 通过名字和型号获取新的商品对象
	 */
	@Override
	public GoodsPO getGoodsByInfo(String name, String model) {
		gIter = goodsList.iterator();
		GoodsPO g;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.getName().equals(name) && g.getModel().equals(model))
				return new GoodsPO(g);
		}
		System.out.println("goods not found");
		return null;
	}

	/**
	 * 获取新的商品列表
	 */
	@Override
	public ArrayList<GoodsPO> getGoodsPOList() {
		ArrayList<GoodsPO> gl = new ArrayList<GoodsPO>();
		gIter = goodsList.iterator();
		while(gIter.hasNext()) {
			gl.add(new GoodsPO(gIter.next()));
		}
		return gl;
	}

	/**
	 * 通过商品分类id获取新的商品分类对象
	 */
	@Override
	public GoodsClassPO getGoodsClassByID(long id) {
		gcIter = goodsClassList.iterator();
		GoodsClassPO gc;
		while(gcIter.hasNext()) {
			gc = gcIter.next();
			if(gc.Num == id)
				return new GoodsClassPO(gc);
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
				return new GoodsClassPO(gc);
		}
		System.out.println("goodsClass not found");
		return null;
	}

	/**
	 * 获取新的商品分类列表
	 */
	@Override
	public ArrayList<GoodsClassPO> getGoodsClassPOList() {
		ArrayList<GoodsClassPO> gcl = new ArrayList<GoodsClassPO>();
		gcIter = goodsClassList.iterator();
		while(gcIter.hasNext()) {
			gcl.add(new GoodsClassPO(gcIter.next()));
		}
		return gcl;
	}

	/**
	 * 添加商品
	 * 在这个方法中商品获得编号
	 */
	@Override
	public synchronized ResultMessage addGoods(GoodsPO goodsPO) {
		gIter = goodsList.iterator();
		GoodsPO g = null;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.getName().equals(goodsPO.getName()) && g.getModel().equals(goodsPO.getModel()))
				return ResultMessage.add_failure;
		}
		if(g != null) {
			if(goodsList.size() != 0) {
				g.setSerialNumber(Long.toString(Long.parseLong(
						goodsList.get(goodsList.size() - 1).getSerialNumber()) + 1));
			}
			else {
				g.setSerialNumber("1000000000");
			}
			goodsList.add(g);
			writeFile();
			return ResultMessage.add_success;
		}
		return ResultMessage.add_failure;
		
	}

	/**
	 * 删除商品
	 */
	@Override
	public synchronized ResultMessage delGoods(long id) {
		gIter = goodsList.iterator();
		GoodsPO g = null;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.getSerialNumber().equals(Long.toString(id))) {
				goodsList.remove(g);
				writeFile();
				return ResultMessage.delete_success;
			}
		}
		return ResultMessage.delete_failure;
	}
	
    /**
     * 用于任何商品信息的更新
     * 更新的内容依据传入的goodsPO属性而定，double和int若为-1则不修改，否则修改，String若为"/"则不修改，否则修改
     * id必须为正确的
     */
	@Override
	public synchronized ResultMessage updGoods(GoodsPO goodsPO) {
		gIter = goodsList.iterator();
		GoodsPO g;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.getSerialNumber().equals(goodsPO.getSerialNumber())) {
				if(goodsPO.getName() != "/") g.setName(goodsPO.getName());
				if(goodsPO.getModel() != "/") g.setModel(goodsPO.getModel());
				if(goodsPO.getPrice() != -1) g.setPrice(goodsPO.getPrice());
				if(goodsPO.getTotalPrice() != -1) g.setTotalPrice(goodsPO.getTotalPrice());
				if(goodsPO.getComment() != "/") g.setComment(goodsPO.getComment());
				//if(goodsPO.getGoodsClassName() != "/") g.setGoodsClassName(goodsPO.getGoodsClassName());
				if(goodsPO.getSalePrice() != -1) g.setSalePrice(goodsPO.getSalePrice());
				if(goodsPO.getLatestPrice() != -1) g.setLatestPrice(goodsPO.getLatestPrice());
				if(goodsPO.getLatestSalePrice() != -1) g.setLatestSalePrice(goodsPO.getLatestSalePrice());
				if(goodsPO.getCommodityQuantity() != -1) g.setCommodityQuantity(goodsPO.getCommodityQuantity());
				writeFile();
				return ResultMessage.update_success;
			}
		}
		return ResultMessage.update_failure;
		
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
	public synchronized ResultMessage addGoodsClass(GoodsClassPO goodsClassPO) {
		boolean hasFather = false;
		gcIter = goodsClassList.iterator();
		GoodsClassPO gcp;
		while(gcIter.hasNext()) {
			gcp = gcIter.next();
			if(gcp.goodsClassName.equals(goodsClassPO.goodsClassName))
				return ResultMessage.add_failure;
			if(gcp.goodsClassName.equals(Long.toString(goodsClassPO.fatherGoodsClassNum)))
				hasFather = true;
		}
		if(hasFather) {
			goodsClassList.add(goodsClassPO);
			writeFile();
			return ResultMessage.add_success;
		}
		return ResultMessage.add_failure;
		
	}

	/**
	 * 删除商品分类
	 * 递归地删除所有子类以及商品
	 */
	@Override
	public synchronized ResultMessage delGoodsClass(long id) {
		/*
		ArrayList<GoodsClassPO> child = new ArrayList<GoodsClassPO>();
		gcIter = goodsClassList.iterator();
		GoodsClassPO g;
		while(gcIter.hasNext()) {
			g = gcIter.next();
			
		}
		*/
		return null;
		
	}

	/**
	 * 更新商品分类
	 */
	@Override
	public synchronized ResultMessage updGoodsClass(GoodsClassPO goodsClassPO) {
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
