package com.smms.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.smms.dao.GoodsDao;
import com.smms.dao.GoodsDaoImpl;
import com.smms.pojo.Goods;

public class GoodsServiceImpl implements GoodsService {

	private static Scanner sc = new Scanner(System.in);
	private static GoodsDao goodsdao = new GoodsDaoImpl();
	private static Goods goods = new Goods();
	private static List<Goods> goodslist = new ArrayList<Goods>();
	//总价
	private static List<Float> pa = new ArrayList<Float>();
	//数量
	private static List<Float> na = new ArrayList<Float>();
	
	/**
	 * 库存商品管理
	 * 
	 * @throws Exception
	 */
	public static void GoodsManagement() throws Exception {
		try
		{
		while (true) {
			System.out.println("请输入您的选项：");
			System.out.println("1. 查找所有库存商品");
			System.out.println("2. 引进新的商品");
			System.out.println("3. 添加原库存商品");
			System.out.println("4. 删除库存商品");
			System.out.println("5. 修改商品的销售价格");
			System.out.println("6. 返回上一级");
			System.out.println("7. 退出系统");
			int management = sc.nextInt();
			System.out.println();
			switch (management) {
			case 1:
				// 库存商品详细信息
				allgoodsMessage();
				System.out.println();
				break;

			case 2:
				// 新货
				newGoods();
				System.out.println();
				break;

			case 3:
				// 补货
				addAmount();
				System.out.println();
				break;

			case 4:
				// 清货
				clearGoods();
				System.out.println();
				break;

			case 5:
				changePrice();
				break;
			
			case 6:
				// 上一级
				UserServiceImpl.managerJF();
				break;

			default:
				System.out.println("您已退出系统！");
				System.exit(0);
				break;
			}
		}
		}
		catch (Exception e) {
			System.err.println("非法输入："+e.getMessage());
		}
	}

	/**
	 * 引入新货
	 * 
	 * @throws Exception
	 */
	public static void newGoods() throws Exception {
		System.out.print("请输入商品编号：");
		String number = sc.next();
		System.out.print("请输入商品名称：");
		String CName = sc.next();
		System.out.print("请输入商品的供应商：");
		String supplier = sc.next();
		System.out.print("请输入商品的生产地：");
		String PArea = sc.next();
		System.out.print("请输入商品单位：");
		String unit = sc.next();
		System.out.print("请输入商品规格：");
		String shelfFife = sc.next();
		System.out.print("请输入商品价格：");
		String price = sc.next();
		System.out.print("请输入商品包装：");
		String pack = sc.next();
		System.out.print("请输入商品数量：");
		String fullNumber = sc.next();

		goods = new Goods(number, CName, supplier, PArea, unit, shelfFife, Float.valueOf(price), pack,
				Float.valueOf(fullNumber));
		int addGoods = goodsdao.addGoods(goods);
		if (addGoods > 0) {
			System.out.println("新货录入成功！" + "\n");
			System.out.println(" ================ 库存商品信息 ================ "+"\n");
			List<Goods> List = goodsdao.GoodsList();
			for (Goods goods : List) {
				System.out.println("商品编号：" + goods.getNumber() + "   商品名称：" + goods.getCName() + "   供应商："
						+ goods.getSupplier() + "   生产地：" + goods.getPArea() + "   单位：" + goods.getUnit() + "   规格："
						+ goods.getShelfFife() + "   价格：" + goods.getPrice() + "   包装：" + goods.getPack() + "   库存数量："
						+ goods.getFullNumber());
			}
			List.clear();
		} else {
			System.out.println("新货录入失败！");
		}
	}

	/**
	 * 进旧货，仅数量改变
	 * 
	 * @throws Exception
	 */
	public static void addAmount() throws Exception {
		System.out.print("请输入商品编号：");
		String id = sc.next();
		System.out.print("请输入进货数量：");
		Float amount = sc.nextFloat();
		int update = goodsdao.update(id, amount, '+');
		if (update > 0) {
			System.out.println("补货成功！");
			Goods searchAmount = goodsdao.searchGoodsById(id);
			System.out.println(searchAmount);
		} else {
			System.out.println("补货失败！");
		}
	}

	/**
	 * 清货
	 * 
	 * @throws Exception
	 */
	public static void clearGoods() throws Exception {
		System.out.print("请输入商品编号：");
		String goodsId = sc.next();
		int delete = goodsdao.delete(goodsId);
		if (delete > 0) {
			System.out.println("清货成功！" + "\n");
			System.out.println(" = = = = = = = = 库存商品信息  = = = = = = = = "+"\n");
			List<Goods> list = goodsdao.GoodsList();
			for (Goods goods : list) {
				System.out.println("商品编号：" + goods.getNumber() + "   商品名称：" + goods.getCName() + "   供应商："
						+ goods.getSupplier() + "   生产地：" + goods.getPArea() + "   单位：" + goods.getUnit() + "   规格："
						+ goods.getShelfFife() + "   价格：" + goods.getPrice() + "   包装：" + goods.getPack() + "   库存数量："
						+ goods.getFullNumber());
			}
			list.clear();
		} else {
			System.out.println("清货失败！");
		}
	}

	/**
	 * 修改商品的销售价格
	 */
	public static void changePrice()
	{
		System.out.print("请输入商品编号：");
		String number = sc.next();
		System.out.print("请输入商品价格：");
		float price = sc.nextFloat();
		
		try {
			int changeGoodsPrice = goodsdao.changeGoodsPrice(number, price);
			if (changeGoodsPrice>0) {
				System.out.println("修改价格成功！");
				System.out.println(goodsdao.searchGoodsById(number));
			}
			else
			{
				System.out.println("修改价格失败！");
			}
			
		} catch (Exception e) {
			
			System.err.println("修改价格失败"+e.getMessage());
		}
		System.out.println();
	}
	
	
	/**
	 * 商品销售管理
	 * 
	 * @throws Exception
	 */
	public static void GoodsSales() throws Exception {    
		//列出所有商品
		goodslist();
		List<Goods> list = new ArrayList<Goods>();
		try
		{
		while (true) 
		{
			System.out.println("请输入您的选项：");
			System.out.println("1. 添加销售商品");
			System.out.println("2. 修改已添加商品");
			System.out.println("3. 老板，结账！");
			System.out.println("4. 返回上一级");
			System.out.println("5. 退出系统");
			int choose = sc.nextInt();
			
			switch (choose) {
			case 1:
				// 添加销售商品
				System.out.println(" =============== [疯狂购] 购物车 =============== "+"\n");
				shoppingcast(list);
				break;

			case 2:
				// 删除销售商品
				deleteCastGoods(list);
				break;

			case 3:
				// 商品结账
				buyGoods(list);
				break;
			case 4:
				//返回上一级
				UserServiceImpl.salesmanJF();
				break;
			case 5:
				System.out.println("您已退出本系统！");
				System.exit(0);
				break;
			default:
				System.out.println("由于非法操作，您已被移出购物大队！");
				System.exit(0);
				break;
			}
			}
		}
		catch(Exception e)
		{
			System.err.println("非法输入！请按照我的提示操作!");
		}
	}

	/**
	 * 判断在数据库中是否存在该商品
	 * @throws Exception 
	 */
	public static boolean judgeIsId(String id) throws Exception
	{
		goods = goodsdao.searchGoodsById(id);
		if (goods.getCName()!=null && goods.getNumber()!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 添加购物商品
	 * 
	 * @param list
	 * @throws Exception
	 */
	public static void shoppingcast(List<Goods> list) throws Exception {
		while (true) 
		{
			System.out.print("请输入您需要的商品的编号：");
			String id = sc.next();
			if (judgeIsId(id)) 
			{
				System.out.print("请输入您的购买数量：");
				float amount = sc.nextFloat();
				System.out.println("是否继续添加商品？       是 - y   否 - n");
				String over = sc.next();
				// 根据编号获取当前商品的信息
				Goods goodsById = goodsdao.searchGoodsById(id);
				// 获取商品的价格
				Float price = goodsById.getPrice();
				// 获取商品的名称
				String cName = goodsById.getCName();
				goods = new Goods(id, cName, price, amount);
				list.add(goods);
				if (over.equals("n")) {
					break;
				}
			}
			else
			{
				System.out.println("此商品不存在，请重新输入商品编号！");
			}
			
		}
	}

	/**
	 * 购物车商品罗列
	 * @param list
	 */
	public static void CastGoods(List<Goods> list)
	{
		int i = 1;
		System.out.println("\n"+"= = = = = = = = = = = = = = = = = = 购 物 车  = = = = = = = = = = = = = = = = = ="+"\n");
		if (list.isEmpty()) {
			System.out.println("亲~您的购物车一件宝贝都没有哦！");
		}
		else
		{
		for (Goods goods : list) {
			System.out.println("序号 " + i + "   " + goods);
			i++;
		}
		System.out.println("\n"+"= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ="+"\n");
		}
	}
	
	/**
	 * 修改购物车商品
	 * 
	 * @param list
	 */
	public static void deleteCastGoods(List<Goods> list) {
		
		CastGoods(list);
		System.out.print("请输入商品的序号：");
		int order = (sc.nextInt() - 1);
		System.out.println();
		System.out.println("请选择以下哪项操作：");
		System.out.println("1. 修改此商品数量");
		System.out.println("2. 移除此商品");
		int alter = sc.nextInt();
		try
		{
		switch (alter) {
		case 1:
			// 修改商品数量
			alterCast_GoodsNumber(list,order);
			break;

		case 2:
			// 移除某行商品
			removeCastGoods(list,order);
			break;
		}

		CastGoods(list);
		}
		catch (Exception e) {
			System.err.println("操作错误:"+e.getMessage());
			System.out.println("请重新选择！"+"\n");
		}
	}

	/**
	 * 修改购物车中的商品数量
	 */
	public static void alterCast_GoodsNumber(List<Goods> list,int order) {
		removeCastGoods(list,order);
		System.out.println("请输入商品数量：");
		float number = sc.nextFloat();
		if (number!=0.0f) {
			goods.setFullNumber(number);
			list.add(goods);
		}

	}

	/**
	 * 移除购物车中的一行商品
	 * 
	 * @param list
	 */
	public static void removeCastGoods(List<Goods> list,int order) {

		list.remove(order);
	}

	/**
	 * 商品结账
	 * 
	 * @param list
	 * @throws Exception
	 */
	public static void buyGoods(List<Goods> list) throws Exception {
		if (list.isEmpty()) {
			System.out.println("待购买商品为空，请添加商品哦~");
			System.out.println();
		}
		else
		{
		int sales = 0;
		System.out.println("请核对您的宝贝：");
		System.out.println("*   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   ");
		for (Goods goods : list) {
			System.out.println(goods);
			pa.add(goods.getPrice());
			na.add(goods.getFullNumber());
		}
		System.out.println("*   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   ");
		System.out.println("商品件数："+calcAmount(na)+"    总价："+calcPrice(pa, na)+"\n");
		System.out.println("请按提示输入：     确认结账 - y   返回上一级 - n");
		String or = sc.next();
		switch (or) {
		case "y":
			for (Goods goods : list) {

				sales = goodsdao.update(goods.getNumber(), goods.getFullNumber(), '-');
			}
			if (sales <= 0) {
				System.out.println("操作失败，您可以选择其他商品哦~");
				System.out.println();
				GoodsSales();
				break;
			}
			System.out.println("完成结账，谢谢惠顾！");
			print(list);
			System.out.println("温馨提示：请收好您的小票！");
			sc.close();
			System.exit(0);
			break;

		case "n":
			break;
		default:
			break;
		}
		}
	}
	
	
	/**
	 * 销售记录
	 * @param goodslist
	 * @throws Exception
	 */
	public static void salesMessage(List<Goods> list) throws Exception
	{
		BufferedWriter bw = null;
		if (!list.isEmpty()) 
		{
			bw = new BufferedWriter(new FileWriter("salesMessage.txt",true));
			for (Goods goods : list) 
			{
				bw.write("销售时间："+currenttime()+"   商品名称："+goods.getCName()+"   价格："+goods.getPrice()+"   数量："+goods.getFullNumber());
				bw.newLine();
			}
			bw.flush();
			if (bw!=null) {
				bw.close();
			}
		}
		else
		{
			System.out.println("购物车为空！");
		}

	}
	

	/**
	 * 列出所有待销售商品
	 */
	public static void goodslist() throws Exception {
		goodslist = goodsdao.GoodsList();
		System.out.println();
		System.out.println("= = = = = = = = = = = = = = = = = = 可销售商品列表 = = = = = = = = = = = = = = = = = ="+"\n");
		for (Goods goods : goodslist) {
			System.out.println("商品编号：" + goods.getNumber() + "   名称：" + goods.getCName() + "    价格:" + goods.getPrice()
					+ "   库存余量：" + goods.getFullNumber());
			System.out.println();
		}
		goodslist.clear();
	}

	/**
	 * 库存商品详细信息
	 * 
	 * @throws Exception
	 */
	public static void allgoodsMessage() throws Exception {
		System.out.println("--------------------- 以下是库存中的所有商品  ---------------------"+"\n");
		goodslist = goodsdao.GoodsList();
		for (Goods goods : goodslist) {
			System.out.println("商品编号：" + goods.getNumber() + "   商品名称：" + goods.getCName() + "   供应商："
					+ goods.getSupplier() + "   生产地：" + goods.getPArea() + "   单位：" + goods.getUnit() + "   规格："
					+ goods.getShelfFife() + "   价格：" + goods.getPrice() + "   包装：" + goods.getPack() + "   库存数量："
					+ goods.getFullNumber());
		}
		goodslist.clear();
	}

	public static Float calcPrice(List<Float> pa,List<Float> na)
	{
		Float pall = 0.0f;
		// 计算商品总价
		for (int i = 0; i < pa.size(); i++) {
			pall = pall + (pa.get(i) * na.get(i));
		}
		return pall;
	}
	
	public static Float calcAmount(List<Float> na)
	{
		Float gall = 0.0f;
		// 计算商品总量
		for (int i = 0; i < na.size(); i++) {
			gall = gall + na.get(i);
		}
		return gall;
	}
	
	/**
	 * 打印购物小票
	 * 
	 * @param list
	 * @throws Exception
	 */
	public static void print(List<Goods> list) throws Exception {
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("paper.txt"));
			bw.write("* * * * * * * * * * * * * * * * * * *");
			bw.newLine();
			bw.write("                      购物小票");
			bw.newLine();
			for (Goods goods : list) {
				bw.write("商品名称：" + goods.getCName());
				bw.newLine();
				bw.write("商品价格：" + goods.getPrice());
				bw.newLine();
				bw.write("商品数量：" + goods.getFullNumber());
				bw.newLine();
				bw.write("此商品共 "+(goods.getPrice()*goods.getFullNumber())+" 钱钱...");
				bw.newLine();
				bw.write("-------------------------------------");
				bw.newLine();
			}
			bw.write("结账时间：" + currenttime());
			bw.newLine();
			Float gall = calcAmount(na);
			Float pall = calcPrice(pa, na);
			bw.write("商品 一共 " + gall + " 件，合计：" + pall + " RMB");
			bw.newLine();
			bw.newLine();
			bw.write("* * * * * * * * * * * * * * * * * * *");
			bw.flush();
//			System.out.println("商品一共：" + gall + " 件，总价：" + pall + " 元人民币");
			salesMessage(list);
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (bw != null) {
				bw.close();
			}
		}
		
	}

	/**
	 * 获取当下时间
	 * 
	 * @return
	 */
	public static String currenttime() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String format = sf.format(date);
		return format;
	}

	@Override
	public int addGoods(Goods goods) throws Exception {

		return goodsdao.addGoods(goods);
	}

	@Override
	public List<Goods> GoodsList() throws Exception {

		List<Goods> goodsList = goodsdao.GoodsList();
		return goodsList;
	}

	@Override
	public Goods searchGoodsById(String number) throws Exception {
		Goods searchAmount = goodsdao.searchGoodsById(number);
		return searchAmount;
	}

	@Override
	public int update(String number, Float amount, Character operation) throws Exception {

		return goodsdao.update(number, amount, operation);
	}

	@Override
	public int delete(String number) throws Exception {

		return goodsdao.delete(number);
	}

	@Override
	public int changeGoodsPrice(String number, Float price) throws Exception {
		
		return goodsdao.changeGoodsPrice(number, price);
	}

}
