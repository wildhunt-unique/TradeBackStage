package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;

public class AddProduceRecord extends JPanel {
	private SelectGoodsJFrame selectGoodsDialog;

	private JComboBox<String> warehouse_JComboBox;
	private JComboBox<String> produceGroup_JComboBox;

	private JButton selectGoods_JButton;
	private JButton verify_JButton;
	private JButton confrim_JButton;
	private JButton selectButton;
	private JButton viewWarehouse_JButton;
	private JButton reset_JButton;

	private JTextField goodsId_JTextField;
	private JTextField goodsName_JTextField;
	private JTextField produceAmount_JTextField;

	private JLabel goodsId_JLabel;
	private JLabel goodsName_JLabel;
	private JLabel produceAmount_JLabel;
	private JLabel warehouseName_JLabel;
	private JLabel produceGroup_JLabel;
	private JLabel title_JLale;

	private Font buttonFont = GeneralTools.getButtonFont();

	private Object[][] group_Data;
	private Object[][] warehouse_Data;

	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		selectGoods_JButton = new JButton("选择商品");
		verify_JButton = new JButton("检验");
		confrim_JButton = new JButton("提交确认");
		

		goodsId_JLabel = new JLabel("商品编号：", JLabel.RIGHT);
		goodsName_JLabel = new JLabel("商品名称：", JLabel.RIGHT);
		produceAmount_JLabel = new JLabel("生产数量：", JLabel.RIGHT);
		produceGroup_JLabel = new JLabel("选择生产小组：", JLabel.RIGHT);
		warehouseName_JLabel = new JLabel("选择库存仓库：", JLabel.RIGHT);
		title_JLale = new JLabel("新增生产记录", JLabel.CENTER);
		goodsId_JTextField = new JTextField(10);
		goodsName_JTextField = new JTextField(10);
		produceAmount_JTextField = new JTextField(10);

		produceGroup_JComboBox = new JComboBox<String>();
		warehouse_JComboBox = new JComboBox<String>();

		/*---------------------定制组件-----------------------*/
		title_JLale.setBounds(0, 10, 1150, 60);
		produceGroup_JLabel.setBounds(50, 100, 200, 50);
		produceGroup_JComboBox.setBounds(250, 100, 250, 50);
		warehouseName_JLabel.setBounds(50, 200, 200, 50);
		warehouse_JComboBox.setBounds(250, 200, 250, 50);
		produceAmount_JLabel.setBounds(50, 300, 200, 50);
		produceAmount_JTextField.setBounds(250, 300, 250, 50);

		goodsId_JLabel.setBounds(550, 100, 200, 50);
		goodsId_JTextField.setBounds(750, 100, 200, 50);
		selectGoods_JButton.setBounds(950, 100, 150, 50);
		goodsName_JLabel.setBounds(550, 200, 200, 50);
		goodsName_JTextField.setBounds(750, 200, 200, 50);
		verify_JButton.setBounds(950, 200, 150, 50);

		confrim_JButton.setBounds(950, 500, 150, 50);

		produceGroup_JLabel.setFont(buttonFont);
		produceGroup_JComboBox.setFont(buttonFont);
		warehouseName_JLabel.setFont(buttonFont);
		warehouse_JComboBox.setFont(buttonFont);
		produceAmount_JLabel.setFont(buttonFont);
		produceAmount_JTextField.setFont(buttonFont);
		goodsId_JLabel.setFont(buttonFont);
		goodsId_JTextField.setFont(buttonFont);
		selectGoods_JButton.setFont(buttonFont);
		goodsName_JLabel.setFont(buttonFont);
		goodsName_JTextField.setFont(buttonFont);
		verify_JButton.setFont(buttonFont);
		confrim_JButton.setFont(buttonFont);

		goodsName_JTextField.setEditable(false);

		title_JLale.setFont(new Font("微软雅黑", Font.BOLD, 40));

		setGroupData();
		setWarehouseData();
		setListener();
		/*---------------------添加组件-----------------------*/
		this.add(produceGroup_JComboBox);
		this.add(warehouse_JComboBox);
		this.add(confrim_JButton);
		this.add(goodsId_JLabel);
		this.add(goodsId_JTextField);
		this.add(goodsName_JLabel);
		this.add(goodsName_JTextField);
		this.add(produceAmount_JLabel);
		this.add(produceAmount_JTextField);
		this.add(produceGroup_JLabel);
		this.add(selectGoods_JButton);
		this.add(verify_JButton);
		this.add(warehouseName_JLabel);
		this.add(title_JLale);

	}// end configuringComponents()

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {

		/* 选择商品按钮事件 */
		selectGoods_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectGoodsDialog = new SelectGoodsJFrame();
				selectButton = new JButton("选择");
				selectButton.setFont(buttonFont);
				selectButton.setBounds(750, 655, 150, 50);
				selectGoodsDialog.getDiagloJPanel().add(selectButton);
				selectGoodsDialog.getGoodsInfTable().setSelectionMode(0);
				selectButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (selectGoodsDialog.getGoodsInfTable().getSelectedRowCount() == 0) {
							JOptionPane.showMessageDialog(selectGoodsDialog, "尚未选择商品", "错误", 0);
							return;
						}
						int choosePos_int = selectGoodsDialog.getGoodsInfTable().getSelectedRow();
						goodsId_JTextField.setText(selectGoodsDialog.getGoodsInfData()[choosePos_int][0]);
						selectGoodsDialog.dispose();
					}
				});
			}
		});// 选择商品按钮事件

		/* 检验按钮事件 */
		verify_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String SQL = "select goods_name from Goods where goods_id = '" + goodsId_JTextField.getText() + "'";
				Object[][] temp_Data = ConnectManagement.unionQuery(SQL, 1);
				if (temp_Data.length == 0 || temp_Data == null) {
					JOptionPane.showMessageDialog(selectGoodsDialog, "无编号为："+goodsId_JTextField.getText()+"的商品", "错误", 0);
					return;
				}
				else {
					goodsName_JTextField.setText((String)temp_Data[0][0]);
				}
			}
		});// 检验按钮事件

		/*提交按钮事件*/
		confrim_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(goodsId_JTextField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "尚未输入商品编号", "错误", 0);
					return;
				}
				if(produceAmount_JTextField.getText().equals("")||produceAmount_JTextField.getText().equals("0")){
					JOptionPane.showMessageDialog(null, "尚未商品数量", "错误", 0);
					return;
				}
				String inputAmount = produceAmount_JTextField.getText();
				if(!GeneralTools.isNum(inputAmount)){
					JOptionPane.showMessageDialog(null, "商品数量只能输入数字", "错误", 0);
					return;
				}
				String SQL = "select goods_name from Goods where goods_id = '" + goodsId_JTextField.getText() + "'";
				Object[][] temp_Data = ConnectManagement.unionQuery(SQL, 1);
				if (temp_Data.length == 0 || temp_Data == null) {
					JOptionPane.showMessageDialog(null, "无编号为："+goodsId_JTextField.getText()+"的商品", "错误", 0);
					return;
				}
				String goodsId = goodsId_JTextField.getText();
				String warehouseId = (String)warehouse_Data[warehouse_JComboBox.getSelectedIndex()][1];
				String groupId = (String)group_Data[produceGroup_JComboBox.getSelectedIndex()][1];
				SQL = "CALL Produce_goods('"+goodsId+"','"+warehouseId+"','"+groupId+"', "+inputAmount+" )";
				ConnectManagement.generalUpdate(SQL);
				JOptionPane.showMessageDialog(null, "添加成功", "提示", 2);
				setClaer();
			}
		});//提交按钮事件
		
	}// end setListener()

	
	public AddProduceRecord() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		configuringComponents();
	}
	
	public void setClaer(){
		goodsId_JTextField.setText("");
		goodsName_JTextField.setText("");
		produceAmount_JTextField.setText("");
	} 

	public void setGroupData() {
		String SQL = "select group_name,group_id from ProduceGroup";
		group_Data = ConnectManagement.unionQuery(SQL, 2);
		for (Object[] temp : group_Data) {
			produceGroup_JComboBox.addItem((String) temp[0]);
		}
	}

	public void setWarehouseData() {
		String SQL = "select warehouse_name,warehouse_id from Warehouse";
		warehouse_Data = ConnectManagement.unionQuery(SQL, 2);
		for (Object[] temp : warehouse_Data) {
			warehouse_JComboBox.addItem((String) temp[0]);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawLine(30, 90, 1120, 90);
	}

}
