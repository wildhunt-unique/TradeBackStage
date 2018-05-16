package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.TableModelUnEdit;

public class ExWarehuoseJFrame extends JFrame {
	private JPanel main_JPanel;
	private JScrollPane sellInf_JScrollPane;
	private JScrollPane exInf_JScrollPane;
	private JTable sellInf_JTable;
	private JTable exInf_JTable;

	private JLabel exInf_JLabel;
	private JLabel indentId_JLabel;

	private JButton confirm_JButton;
	private JButton allSelfMotion_JButton;
	private JButton reset_JButton;
	private JButton cancel_JButton;

	private JPopupMenu sellMenu_JPopupMenu;
	private JPopupMenu exMenu_JPopupMenu;
	private JMenuItem selfMoiton_Item;
	private JMenuItem diyMoiton_Item;
	private JMenuItem clearEx_Item;
	private JMenuItem delete_Item;
	private JMenuItem editAmount_Item;

	private Font buttonFont = GeneralTools.getButtonFont();
	private String indentId_String;
	private Object[][] sellInf_Data = null;
	private Object[][] exInf_Data = null;
	private final String[] sellColumnNames = { "商品号", "商品名", "数量", "单价", "包装规格", "种类", "包装方式" };
	private final String[] exColumnNames = { "商品号", "商品名", "仓库号", "仓库名", "数量" };

	private Object[][] inventory_Data = null;
	private ArrayList<InventoryRecord> inventoryRecord_List;

	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		inventoryRecord_List = new ArrayList<InventoryRecord>();
		main_JPanel = new JPanel();
		exInf_JTable = new JTable();
		sellInf_JTable = new JTable();
		sellInf_JScrollPane = new JScrollPane(sellInf_JTable);
		exInf_JScrollPane = new JScrollPane(exInf_JTable);
		indentId_JLabel = new JLabel("订单号：" + indentId_String + " 的信息", JLabel.LEFT);
		exInf_JLabel = new JLabel("订单出库记录");
		confirm_JButton = new JButton("提交");
		cancel_JButton = new JButton("取消");
		reset_JButton = new JButton("重置");

		allSelfMotion_JButton = new JButton("全自动配置");
		sellMenu_JPopupMenu = new JPopupMenu();
		exMenu_JPopupMenu = new JPopupMenu();
		selfMoiton_Item = new JMenuItem("自动配置出库信息      ", new ImageIcon(GeneralTools.getRedSkinPtah() + "self.png"));
		diyMoiton_Item = new JMenuItem("自定义配置出库信息", new ImageIcon(GeneralTools.getRedSkinPtah() + "DIY.png"));
		clearEx_Item = new JMenuItem("重置该商品的出库信息");
		delete_Item = new JMenuItem("删除该条记录            ", new ImageIcon(GeneralTools.getRedSkinPtah() + "delete.png"));
		editAmount_Item = new JMenuItem("编辑数量         ", new ImageIcon(GeneralTools.getRedSkinPtah() + "edit.png"));

		/*---------------------定制组件-----------------------*/
		main_JPanel.setLayout(null);
		sellInf_JScrollPane.setBounds(10, 60, 1200, 300);
		exInf_JScrollPane.setBounds(10, 420, 1200, 300);
		indentId_JLabel.setBounds(10, 5, 400, 50);
		exInf_JLabel.setBounds(10, 365, 200, 50);
		confirm_JButton.setBounds(1050, 5, 150, 50);
		allSelfMotion_JButton.setBounds(850, 5, 150, 50);
		cancel_JButton.setBounds(1050, 365, 150, 50);
		reset_JButton.setBounds(850, 365, 150, 50);

		indentId_JLabel.setFont(buttonFont);
		exInf_JLabel.setFont(buttonFont);
		confirm_JButton.setFont(buttonFont);
		allSelfMotion_JButton.setFont(buttonFont);
		cancel_JButton.setFont(buttonFont);
		reset_JButton.setFont(buttonFont);
		selfMoiton_Item.setFont(GeneralTools.getTableFont());
		diyMoiton_Item.setFont(GeneralTools.getTableFont());
		delete_Item.setFont(GeneralTools.getTableFont());
		clearEx_Item.setFont(GeneralTools.getTableFont());
		editAmount_Item.setFont(GeneralTools.getTableFont());

		sellInf_Data = ConnectManagement.unionQuery(
				"select Goods.goods_id,Goods.goods_name,Sell.amount,Sell.price, Goods.norms,Goods.goods_type,Goods.pack  from Sell,Goods where Goods.goods_id = Sell.goods_id and indent_id ='"
						+ indentId_String + "'",
				7);
		sellInf_JTable.setModel(new TableModelUnEdit(sellInf_Data, sellColumnNames));
		exInf_JTable.setModel(new TableModelUnEdit(exInf_Data, exColumnNames));
		setTable();

		setListener();
		/*---------------------添加组件-----------------------*/
		this.add(main_JPanel);
		main_JPanel.add(sellInf_JScrollPane);
		main_JPanel.add(exInf_JScrollPane);
		main_JPanel.add(indentId_JLabel);
		main_JPanel.add(exInf_JLabel);
		main_JPanel.add(confirm_JButton);
		main_JPanel.add(allSelfMotion_JButton);
		main_JPanel.add(cancel_JButton);
		main_JPanel.add(reset_JButton);

		sellMenu_JPopupMenu.add(diyMoiton_Item);
		sellMenu_JPopupMenu.add(selfMoiton_Item);
		sellMenu_JPopupMenu.add(clearEx_Item);
		sellInf_JTable.add(sellMenu_JPopupMenu);

		exMenu_JPopupMenu.add(editAmount_Item);
		exMenu_JPopupMenu.add(delete_Item);
		exInf_JTable.add(exMenu_JPopupMenu);
	}// end configuringComponents()

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {
		/* 上面的表格弹出右键菜单 */
		sellInf_JTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// 弹出右键菜单
					sellMenu_JPopupMenu.show(sellInf_JTable, e.getX(), e.getY());
				}
			}
		});// end 弹出右键菜单

		/* 下面的表格弹出右键菜单 */
		exInf_JTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// 弹出右键菜单
					exMenu_JPopupMenu.show(exInf_JTable, e.getX(), e.getY());
				}
			}
		});// end 弹出右键菜单

		/* 取消按钮事件 */
		cancel_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});// end 取消按钮事件

		/* 重置按钮事件 */
		reset_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				inventoryRecord_List.clear();
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// end 重置按钮事件

		/* 重置某一商品的所有出库记录 */
		clearEx_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sellInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未出库商品", "错误", 0);
					return;
				}
				int choosePos_int = sellInf_JTable.getSelectedRow();
				for (InventoryRecord temp : inventoryRecord_List) {
					if (temp.getGoods_id().equals(sellInf_Data[choosePos_int][0])) {
						temp.setAmount("0");
					}
				}
				clearInvalidRecord();
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// end 重置某一商品的所有出库记录

		/* 删除记录按钮事件 */
		delete_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (exInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未选择出库记录", "错误", 0);
					return;
				}
				inventoryRecord_List.remove(exInf_JTable.getSelectedRow());
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// end删除记录按钮事件

		/* 编辑输了按钮事件 */
		editAmount_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (exInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未选择出库记录", "错误", 0);
					return;
				}
				String getInput = JOptionPane.showInputDialog(null, "请输入数量", "商品数量", 1);
				if (!GeneralTools.isNum(getInput)) {
					JOptionPane.showMessageDialog(null, "请输入数字!");
					return;
				}
				int editAmount = Integer.parseInt(getInput);
				exInf_Data = listToObejct();
				String goodsId_String = (String) exInf_Data[exInf_JTable.getSelectedRow()][0];
				String warehouseId_String = (String) exInf_Data[exInf_JTable.getSelectedRow()][2];

				for (int i = 0; i < inventory_Data.length; i++) {
					if (inventory_Data[i][0].equals(goodsId_String)
							&& inventory_Data[i][2].equals(warehouseId_String)) {
						int maxAmount = Integer.parseInt((String) inventory_Data[i][4]);
						if (editAmount > maxAmount) {
							JOptionPane.showMessageDialog(null, "编辑数量大于该库的库存数量" + maxAmount + "!", "错误", 0);
							return;
						} else {
							inventoryRecord_List.get(exInf_JTable.getSelectedRow()).setAmount(getInput);
							break;
						}
					}
				}
				clearInvalidRecord();// 去0
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// end编辑按钮事件

		/* 自动生成订单右键菜单事件 */
		selfMoiton_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sellInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未选择出库商品", "错误", 0);
					return;
				}
				int choosePos_int = sellInf_JTable.getSelectedRow();
				selfMotion((String) sellInf_Data[choosePos_int][0], (String) sellInf_Data[choosePos_int][2]);
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// end 自动生成订单右键菜单事件

		/* 全自动配置订单事件按钮 */
		allSelfMotion_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				inventoryRecord_List.clear();
				for (int i = 0; i < sellInf_Data.length; i++) {
					selfMotion((String) sellInf_Data[i][0], (String) sellInf_Data[i][2]);
				}
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// end 全自动配置订单事件按钮

		/* 提交按钮事件监听器 */
		confirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<inventoryInf> inventoryInf_List = new ArrayList<inventoryInf>();// 判断现已经添加的数量
				for (InventoryRecord temp_Record : inventoryRecord_List) {
					boolean flag = true;
					for (inventoryInf temp_Inf : inventoryInf_List) {
						if (temp_Record.getGoods_id().equals(temp_Inf.getGoods_id())) {
							int addAmount = Integer.parseInt(temp_Record.getAmount());
							int newAmount = Integer.parseInt(temp_Inf.getAmount()) + addAmount;
							temp_Inf.setAmount("" + newAmount);
							flag = false;
							break;
						}
					} // end for
					if (flag) {
						inventoryInf_List.add(new inventoryInf(temp_Record.getGoods_id(), temp_Record.getGoods_name(),
								temp_Record.getAmount()));
					}
				} // end for
				String warning_String = "";
				for (Object[] temp_Objects : sellInf_Data) {
					int needAmount_int = Integer.parseInt((String) temp_Objects[2]);
					int recordAmount_int = 0;
					for (inventoryInf temp_Inf : inventoryInf_List) {// 找到inventoryInf_List中其goods_id对于的商品总量
						if (temp_Inf.getGoods_id().equals(temp_Objects[0])) {
							recordAmount_int = Integer.parseInt(temp_Inf.getAmount());
							break;
						}
					} // end for
					if (needAmount_int != recordAmount_int) {
						String adj_String;
						int adj_int;
						if (needAmount_int > recordAmount_int) {
							adj_String = "缺少";
							adj_int = needAmount_int - recordAmount_int;
						} else {
							adj_String = "多出";
							adj_int = recordAmount_int - needAmount_int;
						}
						warning_String = warning_String + "“" + temp_Objects[0] + "” 号商品  “" + temp_Objects[1] + "”   "
								+ adj_String + "  " + adj_int + " 件\n";
					}
				}

				/* warning_String有信息 ,则表明有错误 */
				if (!warning_String.equals("")) {
					warning_String = "提交失败,原因：\n" + warning_String;
					JOptionPane.showMessageDialog(null, warning_String, "错误", 0);
					return;
				}
				String SQL = "update Indent set state ='已出库' where indent_id ='"+indentId_String+"'";
				ConnectManagement.generalUpdate(SQL);//置为已出库
				ConnectManagement.generalUpdate(listToInsertSQL());// 插入出库信息
				listToDeleteSQL();//减少库存
				JOptionPane.showMessageDialog(null, "提交成功", "提示", 2);
				dispose();
			}
		});// end 提交按钮事件监听器

		/*自定义出库配置按钮事件*/
		diyMoiton_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sellInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "尚未选择出库商品", "错误", 0);
					return;
				}
				int choosePos_int = sellInf_JTable.getSelectedRow();
				SelectExWarehouse selectExWarehouse_JFram = new SelectExWarehouse((String)sellInf_Data[choosePos_int][0],(String)sellInf_Data[choosePos_int][1]);
				
				selectExWarehouse_JFram.getAddExInf_JButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JTable temp_JTable = selectExWarehouse_JFram.getJTable();
						if(temp_JTable.getSelectedRowCount() == 0){
							JOptionPane.showMessageDialog(null, "尚未选择仓库", "错误", 0);
							return;
						}
						String getInput = JOptionPane.showInputDialog(null, "请输入数量", "商品数量", 1);
						if (getInput== null||!GeneralTools.isNum(getInput)){
							JOptionPane.showMessageDialog(null, "请输入数字!");
							return;
						}
						int Amount = Integer.parseInt(getInput);
						String[] data = selectExWarehouse_JFram.getWarehouseIdAndGoodsId(temp_JTable.getSelectedRow());
						boolean flag = true;
						for(InventoryRecord temp:inventoryRecord_List){
							if(temp.getGoods_id().equals(data[0])&&temp.getWarehouse_id().equals(data[1])){
								temp.setAmount(""+Amount);
								flag = false;
								break;
							}
						}
						if(flag){
							String SQL = " select warehouse_name,goods_name from Warehouse,Goods where warehouse_id='"+data[1]+"'and goods_id='"+data[0]+"'";
							Object[][] temp = ConnectManagement.unionQuery(SQL, 2);
							inventoryRecord_List.add(new InventoryRecord(data[0], (String)temp[0][1], data[1],(String)temp[0][0], ""+Amount));
						}
						clearInvalidRecord();//去0
						exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
						setTable();
					}
				});//
			}
		});//自定义出库配置按钮事件

	}// end setListener()

	/* 构造函数 */
	public ExWarehuoseJFrame(String indent_id) {
		// TODO Auto-generated constructor stub
		super();
		this.indentId_String = indent_id;
		this.setResizable(false);
		this.setTitle("订单出库配置");
		int Dwidth = 1280;
		int Dheight = 820;
		this.setSize(Dwidth, Dheight);
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - Dwidth) / 2, (height - Dheight) / 2);
		configuringComponents();
		if (!getInventory()) {
			this.setVisible(false);
			this.dispose();
		} else {
			this.setVisible(true);
		}
	}// end 构造函数

	/**
	 * 设置表格样式
	 */
	private void setTable() {
		sellInf_JTable.setRowHeight(40);
		sellInf_JTable.setFont(GeneralTools.getTableFont());
		GeneralTools.makeFace(sellInf_JTable);
		sellInf_JTable.setShowVerticalLines(true);
		sellInf_JTable.setSelectionMode(0);
		exInf_JTable.setSelectionMode(0);
		exInf_JTable.setRowHeight(40);
		exInf_JTable.setFont(GeneralTools.getTableFont());
		exInf_JTable.setShowVerticalLines(true);
		GeneralTools.makeFace(exInf_JTable);
	}

	/**
	 * 自动配置某一商品出库信息
	 * 
	 * @param goodsId_String
	 *            商品编号
	 */
	public void selfMotion(String goodsId_String, String amount_String) {
		/* 先清除原来的订单出库记录,已建立自动的记录 */
		for (int i = 0; i < inventoryRecord_List.size(); i++) {
			if (inventoryRecord_List.get(i).getGoods_id().equals(goodsId_String)) {
				inventoryRecord_List.get(i).setAmount("0");
			}
		}
		clearInvalidRecord();// 去0
		int needAmount_int = Integer.parseInt(amount_String);
		String SQL = "select Goods.goods_id,Goods.goods_name,Inventory.warehouse_id,Warehouse.warehouse_name,Inventory.inventory_amount "
				+ "from Inventory,Warehouse,Goods " + "where Inventory.warehouse_id = Warehouse.warehouse_id "
				+ "AND Goods.goods_id = Inventory.goods_id " + "AND Inventory.goods_id ='" + goodsId_String
				+ "' ORDER BY inventory_amount DESC;";

		Object[][] temp_data = ConnectManagement.unionQuery(SQL, 5);
		for (int i = 0; i < temp_data.length; i++) {
			int inventoryAmount_int = Integer.parseInt((String) temp_data[i][4]);
			if (inventoryAmount_int < needAmount_int) {
				inventoryRecord_List.add(new InventoryRecord((String) temp_data[i][0], (String) temp_data[i][1],
						(String) temp_data[i][2], (String) temp_data[i][3], (String) temp_data[i][4]));
				needAmount_int = needAmount_int - inventoryAmount_int;
			} else {
				inventoryRecord_List.add(new InventoryRecord((String) temp_data[i][0], (String) temp_data[i][1],
						(String) temp_data[i][2], (String) temp_data[i][3], "" + needAmount_int));
				break;
			}
		}
	}

	/**
	 * 得到相应存储信息
	 * 
	 * @return true 库存够，false 库存不够
	 */

	private boolean getInventory() {
		String inventoryQuery = "select Goods.goods_id,Goods.goods_name,Inventory.warehouse_id,Warehouse.warehouse_name,Inventory.inventory_amount from Inventory,Warehouse,Goods where Inventory.warehouse_id = Warehouse.warehouse_id AND Goods.goods_id = Inventory.goods_id AND Inventory.goods_id IN(";
		for (int i = 0; i < sellInf_Data.length; i++) {
			inventoryQuery = inventoryQuery + "'" + sellInf_Data[i][0] + "'";
			if (i != sellInf_Data.length - 1) {
				inventoryQuery = inventoryQuery + ",";
			}
		}
		inventoryQuery = inventoryQuery + ")";
		inventory_Data = ConnectManagement.unionQuery(inventoryQuery, 5);
		ArrayList<inventoryInf> inventoryInf_List = new ArrayList<inventoryInf>();
		boolean flag_boolean = true;
		for (int i = 0; i < sellInf_Data.length; i++) {
			String SQL = "SELECT SUM(inventory_amount),Goods.goods_name FROM Inventory ,Goods WHERE Goods.goods_id = Inventory.goods_id AND Inventory.goods_id ='"
					+ sellInf_Data[i][0] + "'";
			Object temp[][] = ConnectManagement.unionQuery(SQL, 2);
			if (temp.length == 0 || temp[0][0] == null) {
				inventoryInf_List.add(new inventoryInf((String) sellInf_Data[i][0], (String) temp[0][1],
						(String) sellInf_Data[i][2]));
				flag_boolean = false;
			} else {
				int inventoryAmount_int = Integer.parseInt((String) temp[0][0]);
				int needAmount_int = Integer.parseInt((String) sellInf_Data[i][2]);
				if (inventoryAmount_int < needAmount_int) {
					int temp_int = needAmount_int - inventoryAmount_int;
					inventoryInf_List.add(new inventoryInf((String) sellInf_Data[i][0], (String) temp[0][1],
							Integer.toString(temp_int)));
					flag_boolean = false;
				}
			}
		} // end for
		if (!flag_boolean) {
			String warning_String = "以下商品库存不足:\n";
			for (int i = 0; i < inventoryInf_List.size(); i++) {
				warning_String = warning_String + "“" + inventoryInf_List.get(i).getGoods_id() + "”号商品“"
						+ inventoryInf_List.get(i).getsetGoods_name() + "”缺 " + inventoryInf_List.get(i).getAmount()
						+ " 件\n";
			}
			warning_String = warning_String + "导致无法配置出库信息";
			JOptionPane.showMessageDialog(null, warning_String, "错误", 0);
			dispose();
			return false;
		} else {
			return true;
		}
	}// end getInventory()

	/**
	 * 去除订单出库记录中无效的0值
	 */
	public void clearInvalidRecord() {
		for (int i = 0; i < inventoryRecord_List.size(); i++) {
			if (inventoryRecord_List.get(i).getAmount().equals("0")) {
				inventoryRecord_List.remove(i);
				i--;
			}
		}
	}// end clearInvalidRecord()

	/**
	 * 得到inventoryRecord_List的二维数组
	 * 
	 * @return 二维object数组
	 */
	private Object[][] listToObejct() {
		int h = inventoryRecord_List.size();
		int w = 5;
		Object[][] data = new Object[h][w];
		for (int i = 0; i < h; i++) {
			data[i][0] = inventoryRecord_List.get(i).getGoods_id();
			data[i][1] = inventoryRecord_List.get(i).getGoods_name();
			data[i][2] = inventoryRecord_List.get(i).getWarehouse_id();
			data[i][3] = inventoryRecord_List.get(i).getWarehouse_name();
			data[i][4] = inventoryRecord_List.get(i).getAmount();
		}
		return data;
	}

	/**
	 * 得到inventoryRecord_List所对应的EXwarehouse出库信息SQL插入语句
	 * 
	 * @return SQL插入语句
	 */
	private String listToInsertSQL() {
		String SQL = "insert into EXwarehouse values ";
		int i = 1;
		for (InventoryRecord temp : inventoryRecord_List) {
			SQL = SQL + "('" + indentId_String + "','" + temp.getGoods_id() + "','" + temp.getWarehouse_id() + "','"
					+ temp.getAmount() + "')";
			if (i != inventoryRecord_List.size()) {
				SQL = SQL + ",";
			}
			i++;
		}
		return SQL;
	}

	/**
	 * 执行inventoryRecord_List所对应的减少库存SQLupdate语句
	 */
	private void listToDeleteSQL() {
		String SQL1 = "update Inventory set inventory_amount = inventory_amount - ";
		String SQL2 = "update Goods set total_amount = total_amount - ";
		for (InventoryRecord temp : inventoryRecord_List) {
			String updateSQL1 = SQL1 + temp.getAmount()+" where warehouse_id = '"+temp.getWarehouse_id()+"' and goods_id ='"+temp.getGoods_id()+"';";
			ConnectManagement.generalUpdate(updateSQL1);
			String updateSQL2 = SQL2 + temp.getAmount()+",demand_amount = demand_amount - "+temp.getAmount()+" where goods_id ='"+temp.getGoods_id()+"';";
			ConnectManagement.generalUpdate(updateSQL2);
		}
	}//end listToDeleteSQL()

}

/*-------------------------------------------辅助结构------------------------------------------------------------------------*/
class inventoryInf {
	@Override
	public String toString() {
		return "inventoryInf [goods_id=" + goods_id + ", goods_name=" + goods_name + ", amount=" + amount + "]";
	}

	private String goods_id;
	private String goods_name;
	private String amount;

	public inventoryInf(String goods_id, String goods_name, String amount) {
		// TODO Auto-generated constructor stub
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.amount = amount;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getAmount() {
		return amount;
	}

	public String getsetGoods_name() {
		return goods_name;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}

class InventoryRecord {
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public String getWarehouse_id() {
		return warehouse_id;
	}

	public String getWarehouse_name() {
		return warehouse_name;
	}

	public String getAmount() {
		return amount;
	}

	private String goods_id;
	private String goods_name;
	private String warehouse_id;
	private String warehouse_name;
	private String amount;

	public InventoryRecord(String goods_id, String goods_name, String warehouse_id, String warehouse_name,
			String amount) {
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.warehouse_id = warehouse_id;
		this.warehouse_name = warehouse_name;
		this.amount = amount;
	}
}
