package com.smms.pojo;

public class Goods {

	//商品编号
	private String number;
	//商品名称
	private String CName;
	//供应商
	private String supplier;
	//生产地
	private String PArea;
	//单位
	private String unit;
	//规格
	private String shelfFife;
	//价格
	private Float price;
	//包装
	private String pack;
	//数量
	private Float fullNumber;
	
	public Goods() {
		super();
	}



	public Goods(String number, String cName, Float price,Float amount) {

		this.number = number;
		CName = cName;
		this.price = price;
		this.fullNumber = amount;

	}



	public Goods(String number, String cName, String supplier, String pArea, String unit, String shelfFife,
			Float price, String pack, Float fullNumber) {
		super();
		this.number = number;
		CName = cName;
		this.supplier = supplier;
		PArea = pArea;
		this.unit = unit;
		this.shelfFife = shelfFife;
		this.price = price;
		this.pack = pack;
		this.fullNumber = fullNumber;
	}

	@Override
	public String toString() {
		return "商品 [名称=" + CName + ", 价格=" + price + ", 数量=" + fullNumber + "]";
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCName() {
		return CName;
	}

	public void setCName(String cName) {
		CName = cName;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getPArea() {
		return PArea;
	}

	public void setPArea(String pArea) {
		PArea = pArea;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getShelfFife() {
		return shelfFife;
	}

	public void setShelfFife(String shelfFife) {
		this.shelfFife = shelfFife;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public Float getFullNumber() {
		return fullNumber;
	}

	public void setFullNumber(Float fullNumber) {
		if (fullNumber>=0) {
			this.fullNumber = fullNumber;
		}
	}
	
}
