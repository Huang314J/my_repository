package com.smms.JDBC_Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.smms.pojo.Goods;
import com.smms.pojo.User;

/**
 * 数据库工具类
 * @author HT
 *
 */
public class JdbcUtils {

	private static Properties p = new Properties();
	
	private static DataSource ds;
	
	static 
	{
	try {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("db.properties");
		p.load(resourceAsStream);
		ds = DruidDataSourceFactory.createDataSource(p);
		
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	/**
	 * 数据库连接
	 * @return Connection
	 * @throws Exception
	 */
	public static Connection connection() throws Exception
	{
		
		Class.forName(p.getProperty("DriverClassName"));
		Connection connection = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
		return connection;
	}
	
	/**
	 * 通过druid连接池技术连接数据库
	 * @return Connection
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception
	{
		
		Connection connection = ds.getConnection();
		
		return connection;
	}
	
	public static DataSource getDataSource() throws Exception
	{
		return ds;
	}

	/**
	 * 执行查询方法
	 * @param sql
	 * @param connection
	 * @return ResultSet
	 * @throws Exception
	 */
	public static ResultSet MyexecuteQuery(String sql,Connection connection) throws Exception
	{
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		return resultSet;
		
	}

	/**
	 * 普通执行更新方法
	 * @param sql
	 * @param connection
	 * @return int
	 * @throws Exception
	 */
	public static int MyexecuteUpdate(String sql,Connection connection) throws Exception
	{
		int i = 0;
		try
		{
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			i = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			connection.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		return i;
	}

	/**
	 * 用户
	 * 预编译执行更新方法
	 * 增 删 改
	 * @param sql
	 * @param connection
	 * @param user
	 * @return int
	 * @throws Exception
	 */
	public static int MyexecutePrepareUpdate(String sql,Connection connection,User user) throws Exception
	{
		PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setString(3,user.getPower());
		
		int i = statement.executeUpdate();
		
		ResultSet set = statement.getGeneratedKeys();
		set.next();
		user.setId(set.getInt(1));
		
		set.close();
		return i;
	}
	
	/**
	 * 商品 
	 * 预编译执行更新方法
	 * @param sql
	 * @param connection
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	public static int executePrepareUpdate(String sql,Connection connection,Goods goods) throws Exception
	{
		PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		statement.setString(1,goods.getNumber());
		statement.setString(2,goods.getCName());
		statement.setString(3,goods.getSupplier());
		statement.setString(4,goods.getPArea());
		statement.setString(5,goods.getUnit());
		statement.setString(6,goods.getShelfFife());
		statement.setFloat(7,goods.getPrice());
		statement.setString(8,goods.getPack());
		statement.setFloat(9,goods.getFullNumber());
		
		int i = statement.executeUpdate();
		return i;
	}
	
	/**
	 * 释放资源
	 * @param set
	 * @param sta
	 * @param con
	 * @return void
	 */
	public static void closed(ResultSet set,Statement sta,Connection con)
	{
		if (set!=null) 
		{
			try {
				set.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		if (sta!=null) 
		{
			try {
				sta.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
