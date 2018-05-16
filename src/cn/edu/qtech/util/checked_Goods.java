package cn.edu.qtech.util;

public 
//{ "商品名", "种类", "包装规格", "包装方式", "已选择数量" };
class checked_Goods {

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	private String goods_name;
	private String goods_type;
	private String norms;
	private String pack;
	private int amount;
	private String goods_id;

	public Object[] getString() {
		Object[] data = { goods_name, goods_type, norms, pack, amount, goods_id };
		// return "checked_Goods [goods_name=" + goods_name + ", goods_type=" +
		// goods_type + ", norms=" + norms + ", pack="
		// + pack + ", amount=" + amount + "]";
		return data;
	}

	public Object getObejct(int j) {
		// TODO Auto-generated method stub
		if (j == 0) {
			return goods_name;
		} else if (j == 1) {
			return goods_type;
		} else if (j == 2) {
			return norms;
		} else if (j == 3) {
			return pack;
		}
		return amount;
	}

	public String toString() {
		return "checked_Goods [goods_name=" + goods_name + ", goods_type=" + goods_type + ", norms=" + norms + ", pack="
				+ pack + ", amount=" + amount + ", goods_id=" + goods_id + "]";
	}

	public checked_Goods(String goods_name, String goods_type, String norms, String pack, int amount, String goods_id) {
		// TODO Auto-generated constructor stub
		this.goods_name = goods_name;
		this.goods_type = goods_type;
		this.norms = norms;
		this.pack = pack;
		this.amount = amount;
		this.goods_id = goods_id;
	}

}