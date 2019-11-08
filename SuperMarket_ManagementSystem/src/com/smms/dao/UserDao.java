package com.smms.dao;

import java.util.List;

import com.smms.pojo.User;

public interface UserDao {

	public abstract int register(User user) throws Exception;
	
	public abstract User login(String username,String password,String power) throws Exception;
	
	public abstract List<User> searchAll() throws Exception;
	
	public abstract List<User> searchByPower(String username) throws Exception;
	
	public abstract User searchByName(String username) throws Exception;
	
	public abstract int update(User user,String username) throws Exception;
	
	public abstract int updatePassword(String username,String password,String power) throws Exception;
	
	public abstract int delete(String username) throws Exception;
	
}
