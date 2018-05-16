package cn.edu.qtech.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ProduceManageJPanel extends JTabbedPane {
	
	private AddProduceRecord addProduceRecord_JPanel;
	private ProduceInfJPanel produceInf_JPanel;

	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		addProduceRecord_JPanel = new AddProduceRecord();
		produceInf_JPanel = new ProduceInfJPanel();
		/*---------------------定制组件-----------------------*/

		setListener();
		/*---------------------添加组件-----------------------*/
		this.addTab("新增生产记录", null, addProduceRecord_JPanel, "增加新的生产记录界面");
		this.addTab("查看生产记录", null, produceInf_JPanel, "查询单个商品的仓储信息");
	}

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {

	}

	public ProduceManageJPanel() {
		// TODO Auto-generated constructor stub
		super(JTabbedPane.TOP);
		configuringComponents();
	}
	
	public void setJComboBox(){
		addProduceRecord_JPanel.setGroupData();
		addProduceRecord_JPanel.setWarehouseData();
	}
}
