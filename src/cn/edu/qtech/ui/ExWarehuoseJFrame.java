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
	private final String[] sellColumnNames = { "��Ʒ��", "��Ʒ��", "����", "����", "��װ���", "����", "��װ��ʽ" };
	private final String[] exColumnNames = { "��Ʒ��", "��Ʒ��", "�ֿ��", "�ֿ���", "����" };

	private Object[][] inventory_Data = null;
	private ArrayList<InventoryRecord> inventoryRecord_List;

	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		inventoryRecord_List = new ArrayList<InventoryRecord>();
		main_JPanel = new JPanel();
		exInf_JTable = new JTable();
		sellInf_JTable = new JTable();
		sellInf_JScrollPane = new JScrollPane(sellInf_JTable);
		exInf_JScrollPane = new JScrollPane(exInf_JTable);
		indentId_JLabel = new JLabel("�����ţ�" + indentId_String + " ����Ϣ", JLabel.LEFT);
		exInf_JLabel = new JLabel("���������¼");
		confirm_JButton = new JButton("�ύ");
		cancel_JButton = new JButton("ȡ��");
		reset_JButton = new JButton("����");

		allSelfMotion_JButton = new JButton("ȫ�Զ�����");
		sellMenu_JPopupMenu = new JPopupMenu();
		exMenu_JPopupMenu = new JPopupMenu();
		selfMoiton_Item = new JMenuItem("�Զ����ó�����Ϣ      ", new ImageIcon(GeneralTools.getRedSkinPtah() + "self.png"));
		diyMoiton_Item = new JMenuItem("�Զ������ó�����Ϣ", new ImageIcon(GeneralTools.getRedSkinPtah() + "DIY.png"));
		clearEx_Item = new JMenuItem("���ø���Ʒ�ĳ�����Ϣ");
		delete_Item = new JMenuItem("ɾ��������¼            ", new ImageIcon(GeneralTools.getRedSkinPtah() + "delete.png"));
		editAmount_Item = new JMenuItem("�༭����         ", new ImageIcon(GeneralTools.getRedSkinPtah() + "edit.png"));

		/*---------------------�������-----------------------*/
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
		/*---------------------������-----------------------*/
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
	 * ���ø����¼�������
	 */
	private void setListener() {
		/* ����ı�񵯳��Ҽ��˵� */
		sellInf_JTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// �����Ҽ��˵�
					sellMenu_JPopupMenu.show(sellInf_JTable, e.getX(), e.getY());
				}
			}
		});// end �����Ҽ��˵�

		/* ����ı�񵯳��Ҽ��˵� */
		exInf_JTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// �����Ҽ��˵�
					exMenu_JPopupMenu.show(exInf_JTable, e.getX(), e.getY());
				}
			}
		});// end �����Ҽ��˵�

		/* ȡ����ť�¼� */
		cancel_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});// end ȡ����ť�¼�

		/* ���ð�ť�¼� */
		reset_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				inventoryRecord_List.clear();
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// end ���ð�ť�¼�

		/* ����ĳһ��Ʒ�����г����¼ */
		clearEx_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sellInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δ������Ʒ", "����", 0);
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
		});// end ����ĳһ��Ʒ�����г����¼

		/* ɾ����¼��ť�¼� */
		delete_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (exInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δѡ������¼", "����", 0);
					return;
				}
				inventoryRecord_List.remove(exInf_JTable.getSelectedRow());
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// endɾ����¼��ť�¼�

		/* �༭���˰�ť�¼� */
		editAmount_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (exInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δѡ������¼", "����", 0);
					return;
				}
				String getInput = JOptionPane.showInputDialog(null, "����������", "��Ʒ����", 1);
				if (!GeneralTools.isNum(getInput)) {
					JOptionPane.showMessageDialog(null, "����������!");
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
							JOptionPane.showMessageDialog(null, "�༭�������ڸÿ�Ŀ������" + maxAmount + "!", "����", 0);
							return;
						} else {
							inventoryRecord_List.get(exInf_JTable.getSelectedRow()).setAmount(getInput);
							break;
						}
					}
				}
				clearInvalidRecord();// ȥ0
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// end�༭��ť�¼�

		/* �Զ����ɶ����Ҽ��˵��¼� */
		selfMoiton_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sellInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δѡ�������Ʒ", "����", 0);
					return;
				}
				int choosePos_int = sellInf_JTable.getSelectedRow();
				selfMotion((String) sellInf_Data[choosePos_int][0], (String) sellInf_Data[choosePos_int][2]);
				exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
				setTable();
			}
		});// end �Զ����ɶ����Ҽ��˵��¼�

		/* ȫ�Զ����ö����¼���ť */
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
		});// end ȫ�Զ����ö����¼���ť

		/* �ύ��ť�¼������� */
		confirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<inventoryInf> inventoryInf_List = new ArrayList<inventoryInf>();// �ж����Ѿ���ӵ�����
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
					for (inventoryInf temp_Inf : inventoryInf_List) {// �ҵ�inventoryInf_List����goods_id���ڵ���Ʒ����
						if (temp_Inf.getGoods_id().equals(temp_Objects[0])) {
							recordAmount_int = Integer.parseInt(temp_Inf.getAmount());
							break;
						}
					} // end for
					if (needAmount_int != recordAmount_int) {
						String adj_String;
						int adj_int;
						if (needAmount_int > recordAmount_int) {
							adj_String = "ȱ��";
							adj_int = needAmount_int - recordAmount_int;
						} else {
							adj_String = "���";
							adj_int = recordAmount_int - needAmount_int;
						}
						warning_String = warning_String + "��" + temp_Objects[0] + "�� ����Ʒ  ��" + temp_Objects[1] + "��   "
								+ adj_String + "  " + adj_int + " ��\n";
					}
				}

				/* warning_String����Ϣ ,������д��� */
				if (!warning_String.equals("")) {
					warning_String = "�ύʧ��,ԭ��\n" + warning_String;
					JOptionPane.showMessageDialog(null, warning_String, "����", 0);
					return;
				}
				String SQL = "update Indent set state ='�ѳ���' where indent_id ='"+indentId_String+"'";
				ConnectManagement.generalUpdate(SQL);//��Ϊ�ѳ���
				ConnectManagement.generalUpdate(listToInsertSQL());// ���������Ϣ
				listToDeleteSQL();//���ٿ��
				JOptionPane.showMessageDialog(null, "�ύ�ɹ�", "��ʾ", 2);
				dispose();
			}
		});// end �ύ��ť�¼�������

		/*�Զ���������ð�ť�¼�*/
		diyMoiton_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sellInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δѡ�������Ʒ", "����", 0);
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
							JOptionPane.showMessageDialog(null, "��δѡ��ֿ�", "����", 0);
							return;
						}
						String getInput = JOptionPane.showInputDialog(null, "����������", "��Ʒ����", 1);
						if (getInput== null||!GeneralTools.isNum(getInput)){
							JOptionPane.showMessageDialog(null, "����������!");
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
						clearInvalidRecord();//ȥ0
						exInf_JTable.setModel(new TableModelUnEdit(listToObejct(), exColumnNames));
						setTable();
					}
				});//
			}
		});//�Զ���������ð�ť�¼�

	}// end setListener()

	/* ���캯�� */
	public ExWarehuoseJFrame(String indent_id) {
		// TODO Auto-generated constructor stub
		super();
		this.indentId_String = indent_id;
		this.setResizable(false);
		this.setTitle("������������");
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
	}// end ���캯��

	/**
	 * ���ñ����ʽ
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
	 * �Զ�����ĳһ��Ʒ������Ϣ
	 * 
	 * @param goodsId_String
	 *            ��Ʒ���
	 */
	public void selfMotion(String goodsId_String, String amount_String) {
		/* �����ԭ���Ķ��������¼,�ѽ����Զ��ļ�¼ */
		for (int i = 0; i < inventoryRecord_List.size(); i++) {
			if (inventoryRecord_List.get(i).getGoods_id().equals(goodsId_String)) {
				inventoryRecord_List.get(i).setAmount("0");
			}
		}
		clearInvalidRecord();// ȥ0
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
	 * �õ���Ӧ�洢��Ϣ
	 * 
	 * @return true ��湻��false ��治��
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
			String warning_String = "������Ʒ��治��:\n";
			for (int i = 0; i < inventoryInf_List.size(); i++) {
				warning_String = warning_String + "��" + inventoryInf_List.get(i).getGoods_id() + "������Ʒ��"
						+ inventoryInf_List.get(i).getsetGoods_name() + "��ȱ " + inventoryInf_List.get(i).getAmount()
						+ " ��\n";
			}
			warning_String = warning_String + "�����޷����ó�����Ϣ";
			JOptionPane.showMessageDialog(null, warning_String, "����", 0);
			dispose();
			return false;
		} else {
			return true;
		}
	}// end getInventory()

	/**
	 * ȥ�����������¼����Ч��0ֵ
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
	 * �õ�inventoryRecord_List�Ķ�ά����
	 * 
	 * @return ��άobject����
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
	 * �õ�inventoryRecord_List����Ӧ��EXwarehouse������ϢSQL�������
	 * 
	 * @return SQL�������
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
	 * ִ��inventoryRecord_List����Ӧ�ļ��ٿ��SQLupdate���
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

/*-------------------------------------------�����ṹ------------------------------------------------------------------------*/
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
