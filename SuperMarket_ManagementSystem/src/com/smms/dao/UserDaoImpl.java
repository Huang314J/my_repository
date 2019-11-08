package com.smms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.smms.JDBC_Utils.JdbcUtils;
import com.smms.pojo.User;

public class UserDaoImpl implements UserDao {

	private static Connection connection;
	private List<User> userlist = new ArrayList<User>(); 
	private User user = new User();
	
	@Override
	public int register(User user) throws Exception {
		connection = JdbcUtils.connection();
		String sql = "insert into usermessage(username,password,power) values (?,?,?);";
		return JdbcUtils.MyexecutePrepareUpdate(sql, connection, user);
	}

	@Override
	public User login(String username, String password,String power) throws Exception {
		connection = JdbcUtils.getConnection();
		String sql = "select * from usermessage where username='"+username+"' and password='"+password+"' and power='"+power+"'";
		ResultSet resultSet = JdbcUtils.MyexecuteQuery(sql, connection);
		while(resultSet.next())
		{
			user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
		}
		return user;
	}

	@Override
	public List<User> searchAll() throws Exception {
		connection = JdbcUtils.getConnection();
		String sql = "select * from usermessage order by power;";
		ResultSet resultSet = JdbcUtils.MyexecuteQuery(sql, connection);
		
		while(resultSet.next())
		{
			user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
			userlist.add(user);
		}
		
		return userlist;
	}
	

	@Override
	public User searchByName(String username) throws Exception {
		connection = JdbcUtils.connection();
		String sql = "select * from usermessage where username='"+username+"' ";
		ResultSet resultSet = JdbcUtils.MyexecuteQuery(sql, connection);
		user = new User();
		while(resultSet.next())
		{
			user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
		}
		return user;
	}

	@Override
	public int update(User user,String username) throws Exception {
		connection = JdbcUtils.connection();
//		String sql = "update usermessage SET password = '"+user.getPassword()+"',power = '"+user.getPower()+"' where username = '"+user.getUsername()+"' and power='"+power+"';";;
		User searchById = searchByName(username);
		String sql = "update usermessage SET username='"+user.getUsername()+"' ,password = '"+user.getPassword()+"' where id="+searchById.getId();
		return JdbcUtils.MyexecuteUpdate(sql, connection);
	}

	@Override
	public int delete(String username) throws Exception {
		connection = JdbcUtils.getConnection();
		String sql = "delete from usermessage where power='销售员' and username='"+username+"';";
		int i = JdbcUtils.MyexecuteUpdate(sql, connection);
		return i;
	}

	@Override
	public int updatePassword(String username, String password,String power) throws Exception {
		connection = JdbcUtils.connection();
		String sql = "update usermessage SET password = '"+password+"' where username = '"+username+"' and power='"+power+"'";
		return JdbcUtils.MyexecuteUpdate(sql, connection);
	}

	@Override
	public List<User> searchByPower(String power) throws Exception {
		List<User> userlist = new ArrayList<User>();
		connection = JdbcUtils.connection();
		String sql = "select * from usermessage where power = '"+power+"';";
		ResultSet query = JdbcUtils.MyexecuteQuery(sql, connection);
		while (query.next()) {
			user = new User(query.getInt(1),query.getString(2),query.getString(3),query.getString(4));
			userlist.add(user);
		}
		return userlist;
	}

}
