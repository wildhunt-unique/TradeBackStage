package cn.edu.qtech.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.TableModelUnEdit;

/**
 * 选择添加出库记录的弹出窗口
 */
public class SelectExWarehouse extends JFrame {
	

	private JPanel main_JPanel;
	private JScrollPane inventory_JScrollPane;
	private JTable inventory_JTable;
	private JLabel goodsInf_JLabel;
	private JButton addExInf_JButton;
	private JButton close_JButton;

	private String indentId_String;
	private String goodsId_String;
	private Object[][] inventory_Data;
	private final String[] exColumnNames = { "商品号", "商品名", "仓库号", "仓库名", "数量" };
	private String goodsName_String;

	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		main_JPanel = new JPanel();
		inventory_JTable = new JTable();
		addExInf_JButton = new JButton("选择出库数量");
		close_JButton = new JButton("关闭");
		inventory_JScrollPane = new JScrollPane(inventory_JTable);
		inventory_JTable.setModel(new TableModelUnEdit(inventory_Data, exColumnNames));
		goodsInf_JLabel = new JLabel(goodsId_String + "-" + goodsName_String + " 仓储信息：");
		
		/*---------------------定制组件-----------------------*/
		main_JPanel.setLayout(null);
		inventory_JScrollPane.setBounds(10, 70, 530, 470);
		goodsInf_JLabel.setBounds(10, 5, 500, 50);
		close_JButton.setBounds(10, 550, 150, 50);
		addExInf_JButton.setBounds(340,550, 200, 50);

		goodsInf_JLabel.setFont(GeneralTools.getTextFont());
		close_JButton.setFont(GeneralTools.getButtonFont());
		addExInf_JButton.setFont(GeneralTools.getButtonFont());

		setTable();
		setListener();
		/*---------------------添加组件-----------------------*/
		this.add(main_JPanel);
		main_JPanel.add(goodsInf_JLabel);
		main_JPanel.add(inventory_JScrollPane);
		main_JPanel.add(addExInf_JButton);
		main_JPanel.add(close_JButton);

	}// end configuringComponents()

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {
		
		/*关闭按钮事件*/
		close_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});//关闭按钮事件
	}// end setListener()

	public SelectExWarehouse(String goodsId_String, String goodsName_String) {
		// TODO Auto-generated constructor stub
		super();

		this.setResizable(false);
		this.setTitle("订单出库配置");
		this.setVisible(true);
		int Dwidth = 600;
		int Dheight = 700;
		this.setSize(Dwidth, Dheight);
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - Dwidth) / 2, (height - Dheight) / 2);

		this.goodsName_String = goodsName_String;
		this.goodsId_String = goodsId_String;
		String inventoryQuery = "select Goods.goods_id,Goods.goods_name,Inventory.warehouse_id,Warehouse.warehouse_name,Inventory.inventory_amount from Inventory,Warehouse,Goods where Inventory.warehouse_id = Warehouse.warehouse_id AND Goods.goods_id = Inventory.goods_id AND Inventory.goods_id ='"
				+ this.goodsId_String + "'";
		inventory_Data = ConnectManagement.unionQuery(inventoryQuery, 5);
		configuringComponents();
	}
	
	
	/**
	 * 设置表格样式
	 */
	private void setTable() {
		inventory_JTable.setRowHeight(40);
		inventory_JTable.setFont(GeneralTools.getTableFont());
		inventory_JTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		inventory_JTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		GeneralTools.makeFace(inventory_JTable);
		inventory_JTable.setShowVerticalLines(true);
		inventory_JTable.setSelectionMode(0);
	}
	
	public JTable getJTable(){
		return inventory_JTable;
	}
	
	public JButton getAddExInf_JButton() {
		return addExInf_JButton;
	}
	
	public String[] getWarehouseIdAndGoodsId(int choosePos_int){
		String data[] = new String[2];
		data[0] = (String)inventory_Data[choosePos_int][0];
		data[1] = (String)inventory_Data[choosePos_int][2];
		return data;
	}
}
