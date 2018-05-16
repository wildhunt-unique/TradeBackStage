package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.TableModelUnEdit;

public class OrderIndentJFrame extends JFrame {
	private JPanel main_JPanel;
	private JScrollPane sellInf_JScrollPane;
	private JTable sellInf_JTable;
	private Object[][] data;
	private String indentId_String;

	private JLabel indentInf_JLabel;
	private JLabel totalAmount_JLabel;

	private JButton confirm_JButton;
	private JButton cancel_JButton;
	private JButton pricing_JButton;

	private final Font ButtonFont = GeneralTools.getButtonFont();
	private final String[] columnNames = { "商品号", "商品名", "数量", "单价", "包装规格", "种类", "包装方式" };

	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		main_JPanel = new JPanel();
		sellInf_JTable = new JTable();
		sellInf_JTable.setModel(new TableModelUnEdit(data, columnNames));
		sellInf_JScrollPane = new JScrollPane(sellInf_JTable);
		createJLables();

		confirm_JButton = new JButton("提交");
		cancel_JButton = new JButton("取消");
		pricing_JButton = new JButton("定价");

		/*---------------------定制组件-----------------------*/
		main_JPanel.setLayout(null);
		sellInf_JScrollPane.setBounds(10, 60, 1210, 500);
		indentInf_JLabel.setBounds(10, 0, 400, 50);
		totalAmount_JLabel.setBounds(10, 570, 300, 50);
		confirm_JButton.setBounds(1120, 0, 100, 50);
		cancel_JButton.setBounds(1000, 0, 100, 50);
		pricing_JButton.setBounds(1120, 570, 100, 50);

		indentInf_JLabel.setFont(ButtonFont);
		totalAmount_JLabel.setFont(ButtonFont);
		confirm_JButton.setFont(ButtonFont);
		cancel_JButton.setFont(ButtonFont);
		pricing_JButton.setFont(ButtonFont);
		sellInf_JTable.setSelectionMode(0);
		setTable();
		setListener();
		/*---------------------添加组件-----------------------*/
		this.add(main_JPanel);

		main_JPanel.add(indentInf_JLabel);
		main_JPanel.add(totalAmount_JLabel);
		main_JPanel.add(cancel_JButton);
		main_JPanel.add(pricing_JButton);
		main_JPanel.add(confirm_JButton);
		main_JPanel.add(sellInf_JScrollPane);

	}// end configuringComponents()

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {
		/* 取消按钮事件 */
		cancel_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();// 关闭窗口
			}
		});// 取消按钮事件

		/* 定价按钮事件 */
		pricing_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sellInf_JTable.getSelectedRowCount()==0){
					JOptionPane.showMessageDialog(null, "尚未选择商品", "错误", 0);
					for (int i = 0; i < data.length; i++) {//判断还有没有没定价的
						if(data[i][3]==null){
							sellInf_JTable.setRowSelectionInterval(i, i);
							return;
						}
					}
					return;
				}
				int choosePos_int = sellInf_JTable.getSelectedRow();
				String price_String = JOptionPane.showInputDialog(null, "商品价格", "输入商品价格", 2);
				if (!GeneralTools.isNum(price_String)) {
					JOptionPane.showMessageDialog(null, "请输入数字", "错误", 0);
					return;
				}
				data[choosePos_int][3] = price_String;
				sellInf_JTable.setModel(new TableModelUnEdit(data, columnNames));
				setTable();
				for (int i = 0; i < data.length; i++) {//判断还有没有没定价的
					if(data[i][3]==null){
						sellInf_JTable.setRowSelectionInterval(i, i);
						return;
					}
				}
			}
		});// 定价按钮事件

		/* 提交事件按钮 */
		confirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < data.length; i++) {//判断还有没有没定价的
					if(data[i][3]==null){
						JOptionPane.showMessageDialog(null, "商品“"+data[i][1]+"”未定价", "错误", 0);
						sellInf_JTable.setRowSelectionInterval(i, i);
						return;
					}
				}
				for (int i = 0; i < data.length; i++) {//判断还有没有没定价的
					String updateSQL = "update Sell set price = '"+data[i][3]+"' where indent_id ='"+indentId_String+"' and goods_id ='"+data[i][0]+"';";
					ConnectManagement.generalUpdate(updateSQL);
					String updateDemandSQL = "update Goods set demand_amount = demand_amount + "+data[i][2]+" where goods_id = '"+data[i][0]+"';";
					ConnectManagement.generalUpdate(updateDemandSQL);
				}
				String updateIndentSQL = "update Indent set state = '已接单' where indent_id = '"+indentId_String+"';";
				ConnectManagement.generalUpdate(updateIndentSQL);
				JOptionPane.showMessageDialog(null, "提交成功!", "提示", 2);
				dispose();// 关闭窗口
			}
		});// 提交事件按钮

	}// end setListener()

	public OrderIndentJFrame(String indentId_String) {
		// TODO Auto-generated constructor stub
		super();
		this.indentId_String = indentId_String;
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle("订单详情");
		int Dwidth = 1280;
		int Dheight = 720;
		this.setSize(Dwidth, Dheight);
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - Dwidth) / 2, (height - Dheight) / 2);
		data = ConnectManagement.unionQuery(
				"select Goods.goods_id,Goods.goods_name,Sell.amount,Sell.price, Goods.norms,Goods.goods_type,Goods.pack  from Sell,Goods where Goods.goods_id = Sell.goods_id and indent_id ='"
						+ indentId_String + "'",
				7);
		configuringComponents();
	}

	/**
	 * 设置表格样式
	 */
	public void setTable() {
		sellInf_JTable.setRowHeight(40);
		sellInf_JTable.setFont(GeneralTools.getTableFont());
		GeneralTools.makeFace(sellInf_JTable);
		sellInf_JTable.setShowVerticalLines(true);
	}

	public void createJLables() {
		int length = data.length;
		int Amount_Array[] = new int[length];
		int totalAmount_int = 0;

		for (int i = 0; i < length; i++) {
			Amount_Array[i] = Integer.parseInt((String) data[i][2]);
		}
		for (int i = 0; i < length; i++) {
			totalAmount_int = totalAmount_int + Amount_Array[i];
		}
		totalAmount_JLabel = new JLabel("总共：" + totalAmount_int + "件商品");
		indentInf_JLabel = new JLabel("订单号    " + indentId_String + "   详情：");
	}
}
