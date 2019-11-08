package com.smms.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.junit.Test;

import com.smms.JDBC_Utils.JdbcUtils;
import com.smms.dao.GoodsDao;
import com.smms.dao.GoodsDaoImpl;
import com.smms.dao.UserDao;
import com.smms.dao.UserDaoImpl;
import com.smms.pojo.Goods;
import com.smms.pojo.User;

public class TestDemo {
	static UserDao userdao = new UserDaoImpl();

/*	@Test
	public void test_login() throws Exception
	{

		User login = userdao.login("佐助", "111","管理员");
		System.out.println(login);
	}*/
	
//	@Test
//	public void test_register() throws Exception
//	{
//		int register = userdao.register(new User("测试2","ceshiyi","管理员"));
		
//		System.out.println(register);
//	}
	
/*	@Test
	public void test1()
	{
		//类加载器只能访问src文件夹下的文件
		Properties p = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("db.properties");
		try {
			p.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(p);
		System.out.println(p.getProperty("DriverClassName")+"--"+p.getProperty("url")+"--"+p.getProperty("username")+"--"+p.getProperty("password"));
	}*/
	
//	@Test
//	public void update() throws Exception
//	{
//		User user = new User("二货","yiyiyi","销售员");
//		int update = userdao.update(user);
//		System.out.println(update);
//	}
	
//	@Test
//	public void delete() throws Exception
//	{
//		int delete = userdao.delete("yiyi");
//		System.out.println(delete);
//	}
	
//	@Test
//	public void updatePwd() throws Exception
//	{
//		int updatePassword = userdao.updatePassword("嘤嘤嘤", "yinyinyin");
//		System.out.println(updatePassword);
//	}
	
//	@Test
//	public void searchAll() throws Exception
//	{
//		List<User> userlist = userdao.searchAll();
//		for (User user : userlist) {
//
//			System.out.println(user);
//		}
//	}
	
//	@Test
//	public void isExit() throws Exception
//	{
//		User user = new User("一一","111","管理员");
//		List<User> allList = userdao.searchAll();
//		for (User us : allList) {
//			if (user.getUsername().equals(us.getUsername())) {
//				System.out.println("已存在");
//			}
//		}
//		
//	}
	
//	@Test
//	public void searchByName() throws Exception
//	{
//		User searchById = userdao.searchById("一一");
//		
//		System.out.println(searchById);
//	}
	
	@Test
	public void searchPower() throws Exception
	{
		List<User> searchByPower = userdao.searchByPower("销售员");
		for (User user : searchByPower) {
			System.out.println(user);
		}
	}
	
	
//	@Test
//	public void password()
//	{
//		@SuppressWarnings("resource")
//		Scanner sc = new Scanner(System.in);
//		System.out.print("请设置您的密码：");
//		String pwd = sc.next();
//		char[] sec = pwd.toCharArray();
//		for (int i = 0; i < sec.length; i++) {
//			sec[i] = (char) (sec[i]^2000);
//		}
//		System.out.println(new String(sec));
//	}
	
	
	static GoodsDao goodsdao = new GoodsDaoImpl();
	
/*	@Test
	public void allgoods() throws Exception
	{
		List<Goods> goodsList = goodsdao.GoodsList();
		for (Goods goods : goodsList) {
			System.out.println(goods);
		}
	}*/
	
/*	@Test
	public void update() throws Exception
	{
		int update = goodsdao.update("0001", 1.0f);
		System.out.println(update);
	}*/
	
/*	@Test
	public void addGoods() throws Exception
	{
		int i = goodsdao.addGoods(new Goods("1","1","1","1","1","1",5.0f,"",60.0f));
		System.out.println(i);
	}*/
	
/*	@Test
	public void deleteGoods() throws Exception
	{
		int delete = goodsdao.delete("1");
		System.out.println(delete);
	}*/
	
/*	@Test
	public void searchAmount() throws Exception
	{
		Goods searchAmount = goodsdao.searchGoodsById("0001");
		System.out.println(searchAmount.getFullNumber());
	}*/
	
/*	@Test
	public void productList()
	{
		List<Goods> prodlist = new ArrayList<Goods>();
		Goods e = new Goods("0001","可口可乐",3.5f,2.0f);
		Goods f = new Goods("0002","可口可乐",3.5f,3.0f);
		prodlist.add(0,e);
		prodlist.add(1,f);
		System.out.println(prodlist);
		System.out.println();
//		Goods remove = prodlist.remove(1);
//		System.out.println(remove);
		System.out.println();
		prodlist.remove(e);
		System.out.println(prodlist);
	}*/
	
	@Test
	public void back() throws Exception
	{
		Thread.currentThread().run();
		for (int i = 0; i < 5; i++) {
			System.out.println("1111111111111");
			Thread.currentThread().sleep(1000);
		}
		
		
	}
	
	
}


