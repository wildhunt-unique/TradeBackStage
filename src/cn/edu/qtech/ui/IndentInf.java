package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.TableModelUnEdit;

public class IndentInf extends JPanel {

	private JScrollPane indentInf_JScrollPane;
	private JTable identInf_JTable;

	private JPopupMenu main_JPopupMenu;
	private JMenuItem refresh_Item;
	private JMenuItem indentDetail_Item;
	private JMenuItem copyIndentId_Item;
	private JMenuItem orderIndent_Item;
	private JMenuItem shipment_Item;
	private JMenuItem newIndent_Item;
	private JMenuItem edit_Item;
	private JMenuItem delete_Item;

	private JLabel indentSate_JLabel;
	private JLabel paySate_JLabel;
	private JLabel indentId_JLabel;
	private JLabel date_JLabel;
	private JLabel indentAmount_JLable;
	private JComboBox<String> indentSate_JComboBox;
	private JComboBox<String> paySateSate_JComboBox;
	private JComboBox<String> idOrName_JComboBox;

	private JTextField date_JTextField;
	private JTextField indentId_JTextField;
	private JTextField agentInf_JTextField;

	private JButton confirm_JButton;
	private JButton reset_JButton;
	private Font ButtonFont = GeneralTools.getButtonFont();
	private Font JTableFont = GeneralTools.getTableFont();
	private Object[][] data;
	private final String querySQL = "SELECT I.indent_id,A.agent_name,I.state,I.payState,I.indent_date,A.agent_id FROM Indent I,Agent A WHERE A.agent_id = I.agent_id ";
	private final String[] columnNames = { "订单编号", "经销商", "订单状态", "支付状态", "订单日期" };
	int amount_int;
	private final String[] indentState_Array = { "", "'未接单'", "'已接单'", "'已出库'" };
	private final String[] payState_Array = { "", "'已支付'", "'未支付'" };
	private final String[] idOrName_Array = { "", "A.agent_name = ", "A.agent_id =" };

	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		data = ConnectManagement.unionQuery(querySQL+"order by indent_date desc", 6);

		amount_int = data.length;
		identInf_JTable = new JTable();
		identInf_JTable.setModel(new TableModelUnEdit(data, columnNames));
		indentInf_JScrollPane = new JScrollPane(identInf_JTable);

		indentSate_JComboBox = new JComboBox<String>();
		paySateSate_JComboBox = new JComboBox<String>();
		idOrName_JComboBox = new JComboBox<String>();

		indentSate_JLabel = new JLabel("订单状态：", JLabel.CENTER);
		paySate_JLabel = new JLabel("支付状态：", JLabel.CENTER);
		date_JLabel = new JLabel("订单日期：", JLabel.CENTER);
		indentId_JLabel = new JLabel("    订单号：", JLabel.CENTER);

		agentInf_JTextField = new JTextField(10);
		date_JTextField = new JTextField(10);
		indentId_JTextField = new JTextField(10);

		confirm_JButton = new JButton("查询");
		reset_JButton = new JButton("重置");

		indentAmount_JLable = new JLabel("订单数量: " + amount_int, JLabel.CENTER);

		main_JPopupMenu = new JPopupMenu();
		indentDetail_Item = new JMenuItem("订单详情", new ImageIcon(GeneralTools.getRedSkinPtah() + "detail.png"));
		copyIndentId_Item = new JMenuItem("复制订单编号       ", new ImageIcon(GeneralTools.getRedSkinPtah() + "copy.png"));
		orderIndent_Item = new JMenuItem("接单");
		refresh_Item = new JMenuItem("刷新", new ImageIcon(GeneralTools.getRedSkinPtah() + "refresh.png"));
		shipment_Item = new JMenuItem("配置出库信息");
		newIndent_Item = new JMenuItem("新建订单", new ImageIcon(GeneralTools.getRedSkinPtah() + "new.png"));
		edit_Item = new JMenuItem("修改订单", new ImageIcon(GeneralTools.getRedSkinPtah() + "edit.png"));
		delete_Item = new JMenuItem("删除订单");

		/*---------------------定制组件-----------------------*/
		indentInf_JScrollPane.setBounds(20, 150, 1100, 430);

		indentSate_JLabel.setBounds(20, 15, 150, 50);
		indentSate_JComboBox.setBounds(170, 15, 200, 50);

		paySate_JLabel.setBounds(385, 15, 150, 50);
		paySateSate_JComboBox.setBounds(535, 15, 200, 50);

		agentInf_JTextField.setBounds(970, 15, 150, 50);
		idOrName_JComboBox.setBounds(770, 15, 200, 50);

		indentId_JLabel.setBounds(20, 80, 150, 50);
		indentId_JTextField.setBounds(170, 80, 200, 50);

		date_JLabel.setBounds(385, 80, 150, 50);
		date_JTextField.setBounds(535, 80, 200, 50);

		confirm_JButton.setBounds(970, 80, 150, 50);
		reset_JButton.setBounds(770, 80, 150, 50);

		indentAmount_JLable.setBounds(930, 580, 200, 50);

		indentSate_JLabel.setFont(ButtonFont);
		indentSate_JComboBox.setFont(ButtonFont);
		paySate_JLabel.setFont(ButtonFont);
		paySateSate_JComboBox.setFont(ButtonFont);
		agentInf_JTextField.setFont(ButtonFont);
		idOrName_JComboBox.setFont(ButtonFont);

		indentId_JLabel.setFont(ButtonFont);
		indentId_JTextField.setFont(ButtonFont);
		date_JLabel.setFont(ButtonFont);
		date_JTextField.setFont(ButtonFont);
		confirm_JButton.setFont(ButtonFont);
		reset_JButton.setFont(ButtonFont);
		indentAmount_JLable.setFont(ButtonFont);

		indentDetail_Item.setFont(JTableFont);
		copyIndentId_Item.setFont(JTableFont);
		orderIndent_Item.setFont(JTableFont);
		refresh_Item.setFont(JTableFont);
		shipment_Item.setFont(JTableFont);
		newIndent_Item.setFont(JTableFont);
		edit_Item.setFont(JTableFont);
		delete_Item.setFont(JTableFont);

		indentSate_JComboBox.addItem("无");
		indentSate_JComboBox.addItem("未接单");
		indentSate_JComboBox.addItem("已接单");
		indentSate_JComboBox.addItem("已出库");

		idOrName_JComboBox.addItem("无");
		idOrName_JComboBox.addItem("经销商姓名");
		idOrName_JComboBox.addItem("经销商号");

		paySateSate_JComboBox.addItem("无");
		paySateSate_JComboBox.addItem("已支付");
		paySateSate_JComboBox.addItem("未支付");

		// 设置快捷键
		copyIndentId_Item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));

		setTable();
		setListener();
		/*---------------------添加组件-----------------------*/
		this.add(indentInf_JScrollPane);
		this.add(indentSate_JComboBox);
		this.add(paySateSate_JComboBox);
		this.add(idOrName_JComboBox);
		this.add(indentSate_JLabel);
		this.add(paySate_JLabel);
		this.add(date_JLabel);
		this.add(indentId_JLabel);
		this.add(indentAmount_JLable);
		this.add(agentInf_JTextField);
		this.add(date_JTextField);
		this.add(indentId_JTextField);
		this.add(confirm_JButton);
		this.add(reset_JButton);
		identInf_JTable.add(main_JPopupMenu);
		main_JPopupMenu.add(refresh_Item);
		main_JPopupMenu.add(indentDetail_Item);
		main_JPopupMenu.add(copyIndentId_Item);
		main_JPopupMenu.add(shipment_Item);
		main_JPopupMenu.add(orderIndent_Item);
		main_JPopupMenu.add(newIndent_Item);
		main_JPopupMenu.add(edit_Item);
		main_JPopupMenu.add(delete_Item);
	}// end configuringComponents()

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {
		/* 重置条件按钮 */
		reset_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				agentInf_JTextField.setText("");
				date_JTextField.setText("");
				indentId_JTextField.setText("");
				idOrName_JComboBox.setSelectedIndex(0);
				indentSate_JComboBox.setSelectedIndex(0);
				paySateSate_JComboBox.setSelectedIndex(0);
			}
		});// 重置条件按钮

		/* 查询按钮事件 */
		confirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				unionQuery();
			}
		});// end 查询按钮点击事件

		identInf_JTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// 弹出右键菜单
					main_JPopupMenu.show(identInf_JTable, e.getX(), e.getY());
				}
			}
		});// end弹出右键菜单

		/* 复制按钮事件 */
		copyIndentId_Item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int pos = identInf_JTable.getSelectedRow();
				try {
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();// 获取系统剪切板
					String str = (String) data[pos][0];
					StringSelection selection = new StringSelection(str);// 构建String数据类型
					clipboard.setContents(selection, null);// 添加文本到系统剪切板
				} catch (Exception e2) {
				}
			}
		});// 复制按钮事件

		/* 刷新按钮事件 */
		refresh_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				unionQuery();
			}
		});// 刷新按钮事件

		/* 订单详情按钮事件 */
		indentDetail_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (identInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未选择订单", "错误", 0);
					return;
				}
				int choosePos_int = identInf_JTable.getSelectedRow();
				String indentId_String = (String) data[choosePos_int][0];
				new IndentDetailJFrame(indentId_String);
			}
		});// 订单详情

		/* 删除订单 */
		delete_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (identInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未选择订单", "错误", 0);
					return;
				}
				// choose:0 确定 ,2取消,-1 关闭窗口
				int choose_int = JOptionPane.showConfirmDialog(null, "确定删除? 删除后不可恢复!", "确认", 2);
				if (choose_int == 2 || choose_int == -1) {
					return;
				}
				String deleteSQL = "delete from Indent where ";
				int j = 0;
				for (int i : identInf_JTable.getSelectedRows()) {
					deleteSQL = deleteSQL + "indent_id = '" + data[i][0] + "'";
					if (j != identInf_JTable.getSelectedRowCount() - 1) {
						deleteSQL = deleteSQL + " or ";
					}
					j++;
				}
				ConnectManagement.generalUpdate(deleteSQL);
				deleteSQL = "delete from Sell where ";
				j = 0;
				for (int i : identInf_JTable.getSelectedRows()) {
					deleteSQL = deleteSQL + "indent_id = '" + data[i][0] + "'";
					if (j != identInf_JTable.getSelectedRowCount() - 1) {
						deleteSQL = deleteSQL + " or ";
					}
					j++;
				}
				ConnectManagement.generalUpdate(deleteSQL);
				JOptionPane.showMessageDialog(null, "删除成功", "提示", 1);
			}
		});// end 删除订单

		/* 接单按钮事件 */
		orderIndent_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (identInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未选择订单", "错误", 0);
					return;
				}
				int choosePos_int = identInf_JTable.getSelectedRow();
				String indentId_String = (String) data[choosePos_int][0];
				String indentState_String = (String) data[choosePos_int][2];
				if (!indentState_String.equals("未接单")) {
					JOptionPane.showMessageDialog(null, indentId_String + "号订单已接单!", "错误", 0);
					return;
				}
				new OrderIndentJFrame(indentId_String);
			}
		});// 接单按钮事件

		/* 修改订单事件 */
		edit_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (identInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未选择订单", "错误", 0);
					return;
				}
				int choosePos_int = identInf_JTable.getSelectedRow();
				if(data[choosePos_int][2].equals("已出库")){
					JOptionPane.showMessageDialog(null, "已出库订单无法修改!", "错误", 0);
					return;
				}
				new EditIndentJFrame((String) data[choosePos_int][0], (String) data[choosePos_int][5]);
			}
		});// 修改订单事件
		
		/*配置出库信息按钮事件*/
		shipment_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (identInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未选择订单", "错误", 0);
					return;
				}
				int choosePos_int = identInf_JTable.getSelectedRow();
				if(data[choosePos_int][2].equals("已出库")){
					JOptionPane.showMessageDialog(null, "已出库订单无法配置出库信息!", "错误", 0);
					return;
				}
				if(data[choosePos_int][2].equals("未接单")){
					JOptionPane.showMessageDialog(null, "未接单无法配置出库信息!，请先接单", "错误", 0);
					return;
				}
				new ExWarehuoseJFrame((String) data[choosePos_int][0]);
			}
		});//配置出库信息按钮事件

	}// end setListener()

	public IndentInf() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		configuringComponents();
	}

	/**
	 * 设置表格样式
	 */
	public void setTable() {
		identInf_JTable.setRowHeight(40);
		identInf_JTable.setFont(GeneralTools.getTableFont());
		GeneralTools.makeFace(identInf_JTable);
		identInf_JTable.setShowVerticalLines(true);
	}

	/* 联合查询 */
	public void unionQuery() {
		String unionQuery_String;
		int flag = 0;
		String indentStateCondition = "";
		String payStateCondition = "";
		String idOrNameCondition = "";
		String indentIdCondition = " and indent_id = ";
		String dateCondition = "";

		int choosePos_int;
		if ((choosePos_int = indentSate_JComboBox.getSelectedIndex()) != 0) {
			indentStateCondition = "and state = " + indentState_Array[choosePos_int];
			flag++;
		}
		if ((choosePos_int = paySateSate_JComboBox.getSelectedIndex()) != 0) {
			payStateCondition = "  and payState = " + payState_Array[choosePos_int];
			flag++;
		}
		if ((choosePos_int = idOrName_JComboBox.getSelectedIndex()) != 0) {
			idOrNameCondition = " and " + idOrName_Array[choosePos_int] + "'" + agentInf_JTextField.getText() + "'";
			flag++;
		}
		if (!indentId_JTextField.getText().equals("")) {
			String SQL = querySQL + indentIdCondition + "'" + indentId_JTextField.getText() + "'";
			data = ConnectManagement.unionQuery(SQL, 6);
			identInf_JTable.setModel(new TableModelUnEdit(data, columnNames));
			setTable();
			return;
		}
		if (!date_JTextField.getText().equals("")) {
			dateCondition = " and indent_date like " + dateCondition + "'%" + date_JTextField.getText() + "%'";
			flag++;
		}
		if (choosePos_int == 0) {
			data = ConnectManagement.unionQuery(querySQL, 6);
			identInf_JTable.setModel(new TableModelUnEdit(data, columnNames));
			setTable();
		}
		String SQL = querySQL + indentStateCondition + payStateCondition + idOrNameCondition + dateCondition;
		data = ConnectManagement.unionQuery(SQL+"order by indent_date desc", 6);
		identInf_JTable.setModel(new TableModelUnEdit(data, columnNames));
		indentAmount_JLable.setText("订单数量：" + data.length);
		setTable();
	}// end unionQuery()

	public JMenuItem getNewIndent_Item() {
		return newIndent_Item;
	}

	public void setNewIndent_Item(JMenuItem newIndent_Item) {
		this.newIndent_Item = newIndent_Item;
	}
}
