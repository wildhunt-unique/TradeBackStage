package cn.edu.qtech.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

public class IndentManage extends JTabbedPane {
	private IndentInf indentInf_JPanel;
	private NewIndentJPanel indentNew_JPanel;
	private ShipmentJPanel shipment_JPanel;
	
	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		indentInf_JPanel = new IndentInf();
		indentNew_JPanel = new NewIndentJPanel();
		shipment_JPanel = new ShipmentJPanel();
		/*---------------------定制组件-----------------------*/

		setListener();
		/*---------------------添加组件-----------------------*/
		this.addTab("订单信息", null, indentInf_JPanel, "管理订单的界面");
		this.addTab("出库信息", null, shipment_JPanel, "查询订单内出库记录的界面");
		this.addTab("新建订单", null, indentNew_JPanel, "新建订单的界面");
	}// end configuringComponents()

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {
		/*新建订单按钮事件*/
		indentInf_JPanel.getNewIndent_Item().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setSelectedIndex(2);
			}
		});//end 新建订单按钮事件
	}// end setListener()

	public IndentManage() {
		// TODO Auto-generated constructor stub
		super(JTabbedPane.TOP);
		configuringComponents();
	}
}
