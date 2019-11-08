package com.smms.service;

import java.util.List;

import com.smms.pojo.Goods;

public interface GoodsService {
	
	public abstract int addGoods(Goods goods) throws Exception;
	
	public abstract List<Goods> GoodsList() throws Exception;
	
	public abstract Goods searchGoodsById(String number) throws Exception;
	
	public abstract int update(String number,Float amount,Character operation) throws Exception;
	
	public abstract int delete(String number) throws Exception;
	
	public abstract int changeGoodsPrice(String number,Float price) throws Exception;
}
