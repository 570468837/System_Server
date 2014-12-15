package DataService.GoodsDataService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import PO.GoodsClassPO;
import PO.GoodsPO;
import ResultMessage.ResultMessage;;

/**
 * 
 * @author hutao
 *
 */
public class GoodsController implements GoodsDataService{
	public ArrayList<GoodsPO> goodsList;
	public ArrayList<GoodsClassPO> goodsClassList;
	Iterator<GoodsPO> gIter;
	Iterator<GoodsClassPO> gcIter;
	
	public GoodsController() {
		goodsList = new ArrayList<GoodsPO>();
		goodsClassList = new ArrayList<GoodsClassPO>();
		//!writeFile();
		readFile();
	}
	
	
	/**
	 * 通过id获取新的商品对象
	 * 在bl层也有实现
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
	 * 在bl层也有实现
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
	public ArrayList<Object> getGoodsPOList() {
		ArrayList<Object> gl = new ArrayList<Object>();
		gIter = goodsList.iterator();
		while(gIter.hasNext()) {
			gl.add((Object)new GoodsPO(gIter.next()));
		}
		return gl;
	}

	/**
	 * 通过商品分类id获取新的商品分类对象
	 * 在bl层也有实现
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
	 * 在bl层也有实现
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
	public ArrayList<Object> getGoodsClassPOList() {
		ArrayList<Object> gcl = new ArrayList<Object>();
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
		if(goodsPO.getName().equals("") || goodsPO.getModel().equals("")) return ResultMessage.add_failure;
		gIter = goodsList.iterator();
		GoodsPO g = null;
		while(gIter.hasNext()) {
			g = gIter.next();
			if(g.getName().equals(goodsPO.getName()) && g.getModel().equals(goodsPO.getModel()))
				return ResultMessage.add_failure;
		}
		if(goodsList.size() != 0) {
			goodsPO.setSerialNumber(Long.toString(Long.parseLong(
					goodsList.get(goodsList.size() - 1).getSerialNumber()) + 1));
		}
		else {
			goodsPO.setSerialNumber("1000000000");
		}
		goodsList.add(goodsPO);
		writeFile();
		return ResultMessage.add_success;
		
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
				//if(goodsPO.getGoodsClassNum() != -1) g.setGoodsClassNum(goodsPO.getGoodsClassNum());
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
	public ArrayList<Object> searchGoods(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 添加商品分类
	 */
	@Override
	public ResultMessage addGoodsClass(GoodsClassPO goodsClassPO) {
		if(goodsClassPO.goodsClassName.equals("")) return ResultMessage.add_failure;
		boolean hasFather = false;
		gcIter = goodsClassList.iterator();
		GoodsClassPO gcp;
		System.out.println(goodsClassPO.fatherGoodsClassNum);
		if(goodsClassPO.fatherGoodsClassNum == 0)
			hasFather = true;
		while(gcIter.hasNext()) {
			gcp = gcIter.next();
			if(gcp.goodsClassName.equals(goodsClassPO.goodsClassName))
				return ResultMessage.add_failure;
			hasFather = hasFather || (gcp.Num == goodsClassPO.fatherGoodsClassNum);
		}
		if(hasFather) {
			System.out.println("ca");
			if(goodsClassList.size() != 0) {
				goodsClassPO.Num = goodsClassList.get(goodsClassList.size() - 1).Num + 1;
			}
			else {
				goodsClassPO.Num = 1;
			}
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
		
		ArrayList<GoodsClassPO> child = new ArrayList<GoodsClassPO>();
		gcIter = goodsClassList.iterator();
		GoodsClassPO gc;
		GoodsClassPO thisPO = null;
		while(gcIter.hasNext()) {
			gc = gcIter.next();
			if(gc.fatherGoodsClassNum == id)
				child.add(gc);
			if(gc.Num == id) {
				thisPO = gc;
				gcIter.remove();
			}
		}
		if(thisPO == null) return ResultMessage.delete_failure;
		if(child.size() == 0) {
			gIter = goodsList.iterator();
			while(gIter.hasNext()) {
				if(gIter.next().getGoodsClassNum() == id)
					gIter.remove();
			}
		}
		else {
			Iterator<GoodsClassPO> childIter = child.iterator();
			while(childIter.hasNext()) {
				delGoodsClass(childIter.next().Num);
				childIter.remove();
			}
		}
		
		
		
		return ResultMessage.delete_success;
		
	}

	/**
	 * 更新商品分类
	 */
	@Override
	public ResultMessage updGoodsClass(GoodsClassPO goodsClassPO) {
		gcIter = goodsClassList.iterator();
		GoodsClassPO gcp;
		GoodsClassPO toBeUpd = null;
		boolean conflict = false;
		while(gcIter.hasNext()) {
			gcp = gcIter.next();
			if(goodsClassPO.Num == gcp.Num) {
				toBeUpd = gcp;
			}
			conflict = conflict || (goodsClassPO.goodsClassName.equals(gcp.goodsClassName));
			
		}
		
		if(!conflict && (toBeUpd != null)) {
			toBeUpd.goodsClassName = goodsClassPO.goodsClassName;
			writeFile();
			return ResultMessage.update_success;
		}
		else {
			return ResultMessage.update_failure;
		}
	}

	private void readFile() {
		FileInputStream 
		    gfis = null,
		    gcfis = null;
		ObjectInputStream
		    gois = null,
		    gcois = null;
		try {
			gfis = new FileInputStream(goodsURL);
			gcfis = new FileInputStream(goodsClassURL);
			if(gfis.available() > 0) gois = new ObjectInputStream(gfis);
			if(gcfis.available() > 0) gcois = new ObjectInputStream(gcfis);
			goodsList = (ArrayList<GoodsPO>) gois.readObject();
			goodsClassList = (ArrayList<GoodsClassPO>) gcois.readObject();
			
		} catch (FileNotFoundException e) {
			System.out.println("goodsPO.out or goodsClassPO.out not found");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(".out读取问题");
		}
	}
	
	public void writeFile() {
		try {
			ObjectOutputStream goos = new ObjectOutputStream(new FileOutputStream(goodsURL));
			ObjectOutputStream gcoos = new ObjectOutputStream(new FileOutputStream(goodsClassURL));
			goos.writeObject(goodsList);
			gcoos.writeObject(goodsClassList);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	private static final String goodsURL = "Datas/GoodsPO.out";
	private static final String goodsClassURL = "Datas/GoodsClassPO.out";
}
