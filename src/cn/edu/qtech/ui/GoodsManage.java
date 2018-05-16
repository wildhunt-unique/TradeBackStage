package cn.edu.qtech.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GoodsManage extends JTabbedPane {
	// private
	private GoodsInf GoodsInf_JPanel;
	
	private GoodsInventoryJPanel inventory_JPanel;
	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		GoodsInf_JPanel = new GoodsInf();
		inventory_JPanel = new GoodsInventoryJPanel();
		/*---------------------定制组件-----------------------*/

		setListener();
		/*---------------------添加组件-----------------------*/
		this.addTab("商品管理", null, GoodsInf_JPanel, "进行商品的查询、修改、增加删除");
		this.addTab("仓储信息", null, inventory_JPanel, "查询单个商品的仓储信息");
	}

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {

	}

	public GoodsManage() {
		// TODO Auto-generated constructor stub
		super(JTabbedPane.TOP);
		configuringComponents();
	}
}
