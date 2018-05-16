package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.TableModelUnEdit;

public class GoodsInventoryJPanel extends JPanel {
	private JLabel indentId_JLabel;
	private JTextField indentId_JTextField;
	private JButton confirm_JButton;
	private JLabel amount_JLabel;
	
	private JScrollPane exWarehouse_JScrollPane;
	private JTable exWarehouse_JTable;
	private Font buttonFont = GeneralTools.getButtonFont();
	private final String[] exColumnNames = { "商品号", "商品名", "仓库号", "仓库名", "数量" };
	private Object[][] exInf_Data;
	private String query_String = "select Goods.goods_id,Goods.goods_name,Inventory.warehouse_id,Warehouse.warehouse_name,Inventory.inventory_amount "
			+ "from Inventory,Warehouse,Goods " + "where Inventory.warehouse_id = Warehouse.warehouse_id AND Inventory.goods_id = Goods.goods_id AND Inventory.goods_id = ";
	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		indentId_JLabel = new JLabel("商品号：",JLabel.CENTER);
		indentId_JTextField = new JTextField(10);
		confirm_JButton = new JButton("查询");
		exWarehouse_JTable = new JTable();
		exWarehouse_JTable.setModel(new TableModelUnEdit(null, exColumnNames));
		exWarehouse_JScrollPane = new JScrollPane(exWarehouse_JTable);
		amount_JLabel = new JLabel("记录条数：");
		
		/*---------------------定制组件-----------------------*/
		indentId_JLabel.setBounds(10, 10, 150, 50);
		indentId_JTextField.setBounds(160, 10, 200, 50);
		confirm_JButton.setBounds(360, 10, 150, 50);
		exWarehouse_JScrollPane.setBounds(10, 60, 1120, 500);
		amount_JLabel.setBounds(920, 560, 200, 50);
		
		indentId_JLabel.setFont(buttonFont);
		indentId_JTextField.setFont(buttonFont);
		confirm_JButton.setFont(buttonFont);
		amount_JLabel.setFont(buttonFont);
		setListener();
		/*---------------------添加组件-----------------------*/
		this.add(indentId_JLabel);
		this.add(indentId_JTextField);
		this.add(confirm_JButton);
		this.add(exWarehouse_JScrollPane);
		this.add(amount_JLabel);
		
	}// end configuringComponents()
	
	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {
		confirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String SQL = query_String+"'"+indentId_JTextField.getText()+"' AND inventory_amount != 0";
				exInf_Data = ConnectManagement.unionQuery(SQL, 5);
				exWarehouse_JTable.setModel(new TableModelUnEdit(exInf_Data, exColumnNames));
				if(+exInf_Data.length == 0){
					JOptionPane.showMessageDialog(null, "该商品不存在,或者无该商品的仓储信息", "错误", 0);
					return;
				}
				amount_JLabel.setText("记录条数："+exInf_Data.length);
				setTable();
			}
		});
	}//end setListener()

	
	public GoodsInventoryJPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		configuringComponents();
	}
	private void setTable(){
		exWarehouse_JTable.setSelectionMode(0);
		exWarehouse_JTable.setRowHeight(40);
		exWarehouse_JTable.setFont(GeneralTools.getTableFont());
		exWarehouse_JTable.setShowVerticalLines(true);
		GeneralTools.makeFace(exWarehouse_JTable);
	}
}
