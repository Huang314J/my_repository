package com.smms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.smms.JDBC_Utils.JdbcUtils;
import com.smms.pojo.Goods;

public class GoodsDaoImpl implements GoodsDao {

	private static Connection connection;
	private static Goods goods = new Goods();
	private static List<Goods> goodslist = new ArrayList<Goods>();
	
	@Override
	public int addGoods(Goods goods) throws Exception {

		int i = 0;
		try
		{
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "insert into goodsmessage values (?,?,?,?,?,?,?,?,?);";
			i = JdbcUtils.executePrepareUpdate(sql, connection, goods);
			connection.commit();
		}
		catch(Exception e)
		{
			System.err.println("插入新品失败原因："+e.getMessage());
			connection.rollback();
		}
		return i;
	}

	@Override
	public List<Goods> GoodsList() throws Exception {
		connection = JdbcUtils.getConnection();
		String sql = "select * from goodsmessage order by number;";
		ResultSet resultSet = JdbcUtils.MyexecuteQuery(sql , connection);
		while(resultSet.next())
		{
			goods = new Goods(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getFloat(7),resultSet.getString(8),resultSet.getFloat(9));
			goodslist.add(goods);
		}
		return goodslist;
	}

	/**
	 * 对库存商品进行数量操作
	 * 进货
	 * 
	 * @param number
	 * @param amount
	 * @param operation
	 * @return
	 */
	public Float operateGoodsAmount(String number,Float amount,Character operation)
	{
		Goods goodsmessage;
		try {
			goodsmessage = searchGoodsById(number);
			switch(operation)
			{
			case '+':
				//商品进货 原来的数量+进货数量 = 最后的库存数量
				amount = goodsmessage.getFullNumber()+amount;
				break;
			case '-':
				//销售商品 库存数量-销售数量 = 库存剩余数量
				if (goodsmessage.getFullNumber()<amount) {
					
					System.out.println("商品 "+goodsmessage.getCName()+" 库存余量不足！");
//					break;
				}
				amount = goodsmessage.getFullNumber()-amount;
				break;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return amount;
	}
	
	@Override
	public int update(String number, Float amount,Character operation) throws Exception {
		
		int update = 0;
		try
		{
		amount = operateGoodsAmount(number, amount, operation);
		if (amount>=0) {
			String sql = "update goodsmessage set fullNumber="+amount+" where number='"+number+"';";
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			//演绎突发情况或者取消付款
//			int i=update/0;
			update = JdbcUtils.MyexecuteUpdate(sql, connection);
			connection.commit();
		}
		}
		catch(Exception e)
		{
			connection.rollback();
			System.err.println("付款失败!   原因："+e.getMessage());
			System.out.println();
		}
		return update;
	}

	@Override
	public int delete(String number) throws Exception {
		int i=0;
		try
		{
			connection = JdbcUtils.connection();
			connection.setAutoCommit(false);
			String sql="delete from goodsmessage where number='"+number+"';";
			i = JdbcUtils.MyexecuteUpdate(sql, connection);
			connection.commit();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			connection.rollback();
		}
		return i;
	}
	
	@Override
	public Goods searchGoodsById(String number) throws Exception {
		goods = new Goods();
		connection = JdbcUtils.connection();
		String sql = "select * from goodsmessage where number='"+number+"';";
		ResultSet resultSet = JdbcUtils.MyexecuteQuery(sql, connection);
		while (resultSet.next()) {
			goods = new Goods(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getFloat(7),resultSet.getString(8),resultSet.getFloat(9));
			return goods;
		}
		return goods;
	}

	@Override
	public int changeGoodsPrice(String number,Float price) throws Exception {
		connection = JdbcUtils.getConnection();
		String sql = "update goodsmessage set price="+price+" where number='"+number+"'";
		return JdbcUtils.MyexecuteUpdate(sql, connection);
	}

}
