package com.smms.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.smms.dao.UserDao;
import com.smms.dao.UserDaoImpl;
import com.smms.pojo.User;

public class UserServiceImpl implements UserService {

	private static UserService userservice = new UserServiceImpl();
	private static UserDao userdao = new UserDaoImpl();
	private static Scanner sc = new Scanner(System.in);
	private static User user = new User();
	private static String power;

	@Override
	public void register() throws Exception {

		System.out.print("请输入您的姓名：");
		String name = sc.next();
		System.out.print("请设置您的密码：");
		String pwd = sc.next();
		power = "销售员";
		user = new User(name, pwd, power);

		if (isExit(user)) {
			System.out.println("该用户已注册！");
			// register();
		} else {
			int register = userdao.register(user);
			if (register > 0) {
				System.out.println("恭喜您，注册成功！");
			} else {
				System.out.println("很遗憾，注册失败！");
			}
		}
	}

	@Override
	public void login() throws Exception {

		int count = 3;
		while (count > 0) {

			System.out.print("请输入您的账号：");
			String username = sc.next();
			System.out.print("请输入您的密码：");
			String password = sc.next();
			power = power();
			user = userdao.login(username, password, power);
			if (user.getId() != null && user.getPassword() != null) {
				System.out.println();
				switch (power) {
				case "管理员":
					managerJF();
					break;
				case "销售员":
					salesmanJF();
					break;
				}

				break;
			} else {

				System.out.println("账号或密码错误！");
				if (count != 0) {
					System.out.println("温馨提示：您一共有3次登录机会，您还剩下 " + (count - 1) + " 次机会哦~");
				} else {
					System.out.println("您的登录机会已用完，请尽快向管理员寻求帮助！");
				}
			}
			count--;
		}
	}

	@Override
	public List<User> searchAll() throws Exception {
		List<User> searchAll = userdao.searchAll();
		return searchAll;
	}

	@Override
	public User searchByName(String username) throws Exception {
		User user = userdao.searchByName(username);
		return user;
	}

	@Override
	public int update(User user, String username) throws Exception {
		return userdao.update(user, username);
	}

	@Override
	public int updatePassword(String username, String password, String power) throws Exception {
		return userdao.updatePassword(username, password, power);
	}

	@Override
	public int delete(String username) throws Exception {
		return userdao.delete(username);
	}

	@Override
	public List<User> searchByPower(String power) throws Exception {

		return userdao.searchByPower(power);
	}

	/**
	 * 主界面
	 * 
	 * @throws Exception
	 */
	public static void SimpleJFrame() throws Exception {

		System.out.println("------------------------超市管理系统------------------------");
		try {
			while (true) {

				System.out.println("---> 超市管理系统 [主界面] --->");
				System.out.println("");
				System.out.println("请输入您的选项：         1. 登录          2. 退出            ");

				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					// 登录
					userservice.login();
					break;

				case 2:
					// 退出
					System.out.println("您已退出本系统，期待您的下次使用！");
					sc.close();
					System.exit(0);
					break;

				}
			}
		} catch (Exception e) {
			
			System.err.println("输入错误！" + e.getMessage());
		}

	}

	/**
	 * 管理员 主界面
	 * 
	 * @throws Exception
	 */
	public static void managerJF() throws Exception {
		
		System.out.println(" ============== 欢迎 " + user.getUsername() + " 进入管理员界面 ============== ");

		while (true) {
			System.out.println();
			System.out.println("请输入您的选项：");
			// 对销售人员的管理：查询和删除
			System.out.println("1. 注册销售员");
			System.out.println("2. 所有人员列表");
			System.out.println("3. 删除销售员");
			System.out.println("4. 查询人员信息");
			System.out.println("5. 修改个人密码");
			System.out.println("6. 修改个人信息");
			// 进货管理：对库存商品进行 增、删、查 操作
			System.out.println("7. 库存商品管理");
			System.out.println("8. 返回上一级");
			System.out.println("9. 退出本系统");
			System.out.println();
			int mc = sc.nextInt();
			switch (mc) {
			case 1:
				// 注册销售员
				userservice.register();
				break;
			case 2:
				// 查询所有人信息
				allUser();
				break;
			case 3:
				// 删除销售人员
				deleteSalesman();
				break;
			case 4:
				// 查询人员
				searchMan();
				break;
			case 5:
				// 修改个人密码
				updatePwd();
				break;
			case 6:
				// 修改个人信息
				updateSelfmessage();
				break;
			case 7:
				// 进货管理
				GoodsServiceImpl.GoodsManagement();
				break;
			case 8:
				// 主界面
				SimpleJFrame();
				break;
			default:
				System.out.println("您已退出本系统！");
				System.exit(0);
				break;
			}

		}

	}

	/**
	 * 销售员 主界面
	 * 
	 * @throws Exception
	 */
	public static void salesmanJF() throws Exception {
		
		System.out.println(" ============== 欢迎 " + user.getUsername() + " 进入销售员界面 ============== ");

		while (true) {
			System.out.println();
			System.out.println("请输入您的选项：");
			// 销售管理：增加、删除 销售商品 ， 结账
			System.out.println("1.销售管理");
			System.out.println("2.个人信息修改");
			System.out.println("3.个人密码修改");
			System.out.println("4. 返回上一级");
			System.out.println("5. 退出本系统");
			int sch = sc.nextInt();

			switch (sch) {
			case 1:
				// 销售管理
				GoodsServiceImpl.GoodsSales();
				break;

			case 2:
				// 个人信息修改
				updateSelfmessage();
				System.out.println();
				break;

			case 3:
				// 个人密码修改
				updatePwd();
				System.out.println();
				break;
			case 4:
				// 主界面
				SimpleJFrame();
				break;
			default:
				System.out.println("您已退出本系统！");
				System.exit(0);
				break;
			}
		}

	}

	/**
	 * 查询所有人的信息
	 */
	public static void allUser() {
		System.out.println(" ------------------- 以下是所有人员的信息 ------------------- ");
		List<User> list;
		try {
			list = userdao.searchAll();
			for (User user : list) {
				System.out.println("编号：" + user.getId() + "  姓名：" + user.getUsername() + "  密码："
						+ password(user.getPassword()) + "  身份：" + user.getPower());
			}
			list.clear();

		} catch (Exception e) {

			System.err.println("信息列表查询失败：" + e.getMessage());
		}

		System.out.println();
	}

	/**
	 * 查询人员信息
	 * 
	 */
	public static void searchMan() {
		System.out.print("请输入姓名：");
		String name = sc.next();
		try {
			User searchByName = userdao.searchByName(name);
			if (searchByName.getUsername() != null && searchByName.getPassword() != null) {
				System.out.println("编号：" + searchByName.getId() + "  姓名：" + searchByName.getUsername() + "  密码："
						+ password(searchByName.getPassword()) + "  身份：" + searchByName.getPower());
			} else {
				System.out.println("查无此人！");
			}

		} catch (Exception e) {

			System.err.println("查询失败：" + e.getMessage());
		}

		System.out.println();
	}

	/**
	 * 删除销售员
	 */
	public static void deleteSalesman() {
		System.out.print("请输入需要删除的销售员的姓名：");
		String name = sc.next();
		int delete;
		try {
			delete = userdao.delete(name);
			if (delete > 0) {
				System.out.println("删除成功！");
				System.out.println();
				System.out.println("================ 销售员 信息列表 ================");
				System.out.println();
				List<User> newlist = userdao.searchByPower("销售员");
				for (User us : newlist) {
					System.out.println("编号：" + us.getId() + "   姓名：" + us.getUsername() + "   密码："
							+ password(us.getPassword()) + "   身份：" + us.getPower());
				}
				newlist.clear();
			} else {
				System.out.println("删除失败，不存在此销售员！");
			}
		} catch (Exception e) {

			System.err.println("删除失败：" + e.getMessage());
		}

	}

	/**
	 * 修改个人密码
	 */
	public static void updatePwd() {
		System.out.print("请输入您的新密码：");
		String password = sc.next();
		int i;
		try {
			i = userdao.updatePassword(user.getUsername(), password, user.getPower());
			if (i > 0) {
				System.out.println("修改密码成功！");
				System.out.println(userdao.searchByName(user.getUsername()));
			} else {
				System.out.println("修改密码失败！");
			}
		} catch (Exception e) {

			System.err.println("修改个人密码失败：" + e.getMessage());
		}

	}

	/**
	 * 修改个人信息
	 */
	public static void updateSelfmessage() {
		System.out.print("请输入您的新昵称：");
		String newname = sc.next();
		System.out.print("请输入您的新密码：");
		String pwd = sc.next();
		/*
		 * System.out.print("请输入您的新身份（管理员 or 销售员）："); String power = sc.next();
		 */
		String oldname = user.getUsername();
		user = new User(newname, pwd, power);
		int update;
		try {
			update = userdao.update(user, oldname);
			if (update > 0) {
				System.out.println("个人信息更新成功！");
				System.out.println(userdao.searchByName(newname));
			} else {
				System.out.println("个人信息更新失败！");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * 获取权限
	 * 
	 * @return String
	 */
	public static String power() {
		System.out.println("请输入您的身份：         1. 管理员         2. 销售员");
		int identity = sc.nextInt();
		switch (identity) {
		case 1:
			power = "管理员";
			break;
		case 2:
			power = "销售员";
			break;
		default:
			System.out.println("您的输入有误,请重新输入！");
			power();
			break;
		}
		return power;
	}

	/**
	 * 判断是否是已注册用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public static boolean isExit(User user) throws Exception {
		List<User> allList = userdao.searchAll();
		for (User us : allList) {
			if (user.getUsername().equals(us.getUsername())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 密码加密
	 * 
	 * @param pwd
	 * @return
	 */
	public static String password(String pwd) {
		char[] charArray = pwd.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char) (charArray[i] ^ 2000);
		}
		pwd = new String(charArray);
		return pwd;
	}

}
