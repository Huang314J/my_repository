package com.smms.service;

import java.util.List;

import com.smms.pojo.User;

public interface UserService {
	
	public abstract void register() throws Exception;
	
	public abstract void login() throws Exception;
	
	public abstract List<User> searchAll() throws Exception;
	
	public abstract List<User> searchByPower(String power) throws Exception;
	
	public abstract User searchByName(String username) throws Exception;
	
	public abstract int update(User user,String username) throws Exception;
	
	public abstract int updatePassword(String username,String password,String power) throws Exception;
	
	public abstract int delete(String username) throws Exception;
	
}
