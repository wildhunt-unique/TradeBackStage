package cn.edu.qtech.ui;

import java.awt.Dialog;
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
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import cn.edu.qtech.util.JxlWriteDemo;
import cn.edu.qtech.util.TableModelUnEdit;
import cn.edu.qtech.util.getDay;
import jxl.write.WriteException;

public class GoodsInf extends JPanel {

	private JTable goodsInf_JTable;
	private Font table_Font = GeneralTools.getTableFont();
	private Font button_Font = GeneralTools.getButtonFont();
	private JComboBox<String> condition_JComboBox;
	private JScrollPane goods_JScrollPane;
	private JTextField conditionKey_JTextField;
	private JButton searchConfirm_JButton;
	private JLabel goodsAmount_JLable;

	private JPopupMenu mainMenu_JPopupMenu;
	private JMenuItem editGoods_Item;
	private JMenuItem addGoods_Item;
	private JMenuItem deleteGoods_Item;
	private JMenuItem refresh_Item;
	private JMenuItem copyGoodsId_Item;
	private JMenuItem writeToXls_Item;

	private String[] conditionSQL = { "", "where goods_name like '%", "where goods_id ='", "where goods_type like '%",
			"where pack like '%" };
	private String[] columnNames = { "��Ʒ���", "��Ʒ��", "����", "��װ���", "������", "��װ��ʽ", "������", "�ܿ��" };
	private String SQL_String = "select * from Goods ";
	private String tableName_String = "Goods";
	private int amount_int;
	private Object[][] goodsInfData = ConnectManagement.generalQuery(SQL_String, tableName_String);
	private getDay date;

	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		conditionKey_JTextField = new JTextField();
		searchConfirm_JButton = new JButton("��ѯ");
		condition_JComboBox = new JComboBox<String>();
		goodsInf_JTable = new JTable();
		goodsInf_JTable.setModel(new TableModelUnEdit(goodsInfData, columnNames));
		goods_JScrollPane = new JScrollPane(goodsInf_JTable);
		mainMenu_JPopupMenu = new JPopupMenu();
		copyGoodsId_Item = new JMenuItem("������Ʒ���       ", new ImageIcon(GeneralTools.getRedSkinPtah() + "copy.png"));
		editGoods_Item = new JMenuItem("�޸���Ʒ��Ϣ       ", new ImageIcon(GeneralTools.getRedSkinPtah() + "edit.png"));
		addGoods_Item = new JMenuItem("������Ʒ");
		deleteGoods_Item = new JMenuItem("ɾ����Ʒ");
		refresh_Item = new JMenuItem("ˢ��", new ImageIcon(GeneralTools.getRedSkinPtah() + "refresh.png"));
		writeToXls_Item = new JMenuItem("����ΪExcel��", new ImageIcon(GeneralTools.getRedSkinPtah() + "Excel.png"));
		amount_int = goodsInfData.length;
		goodsAmount_JLable = new JLabel("��Ʒ����: " + amount_int);
		date = new getDay();
		/*---------------------�������-----------------------*/
		condition_JComboBox.setBounds(50, 0, 300, 50);
		conditionKey_JTextField.setBounds(350, 0, 600, 50);
		searchConfirm_JButton.setBounds(950, 0, 150, 50);
		goods_JScrollPane.setBounds(50, 50, 1050, 520);
		goodsAmount_JLable.setBounds(950, 570, 200, 50);

		condition_JComboBox.setFont(button_Font);
		searchConfirm_JButton.setFont(button_Font);
		conditionKey_JTextField.setFont(button_Font);
		mainMenu_JPopupMenu.setFont(GeneralTools.getTableFont());
		goodsAmount_JLable.setFont(GeneralTools.getTextFont());
		addGoods_Item.setFont(GeneralTools.getTableFont());
		deleteGoods_Item.setFont(GeneralTools.getTableFont());
		refresh_Item.setFont(GeneralTools.getTableFont());
		editGoods_Item.setFont(GeneralTools.getTableFont());
		copyGoodsId_Item.setFont(GeneralTools.getTableFont());
		writeToXls_Item.setFont(GeneralTools.getTableFont());
		editGoods_Item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		setTable();
		goods_JScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		condition_JComboBox.addItem("��ѯ����");
		condition_JComboBox.addItem("�����Ʋ�ѯ");
		condition_JComboBox.addItem("����Ʒ�Ų�ѯ");
		condition_JComboBox.addItem("�������ѯ");
		condition_JComboBox.addItem("����װ��ʽ�Ų�ѯ");

		setListener();// �����¼�������
		/*---------------------������-----------------------*/
		this.add(goods_JScrollPane);
		this.add(searchConfirm_JButton);
		this.add(conditionKey_JTextField);
		this.add(condition_JComboBox);
		this.add(goodsAmount_JLable);
		goodsInf_JTable.add(mainMenu_JPopupMenu);
		mainMenu_JPopupMenu.add(refresh_Item);
		mainMenu_JPopupMenu.add(addGoods_Item);
		mainMenu_JPopupMenu.add(editGoods_Item);
		mainMenu_JPopupMenu.add(deleteGoods_Item);
		mainMenu_JPopupMenu.add(copyGoodsId_Item);
		mainMenu_JPopupMenu.add(writeToXls_Item);

	}

	/**
	 * ���ø����¼�������
	 */
	private void setListener() {

		/* ����Ϊexcel��ť�¼� */
		writeToXls_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fileName = "��Ʒ��Ϣ-" + date.getUnFormatDate();

				try {
					JxlWriteDemo xlsOut = new JxlWriteDemo(fileName, goodsInfData);
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "����ʧ��", "����", 0);
					return;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "����ʧ��", "����", 0);
					return;
				}
				JOptionPane.showMessageDialog(null, "�����ɹ�,�ļ���Ϊ" + fileName, "��ʾ", 2);

			}
		});// ����Ϊexcel��ť�¼�

		/* ���ư�ť�¼� */
		copyGoodsId_Item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int pos = goodsInf_JTable.getSelectedRow();
				try {
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();// ��ȡϵͳ���а�
					String str = (String) goodsInfData[pos][0];
					StringSelection selection = new StringSelection(str);// ����String��������
					clipboard.setContents(selection, null);// ����ı���ϵͳ���а�
				} catch (Exception e2) {

				}

			}
		});// ���ư�ť�¼�

		/* ˢ�°�ť */
		refresh_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int pos = condition_JComboBox.getSelectedIndex();
				String key = conditionKey_JTextField.getText();
				if (pos == 0) {
					SQL_String = SQL_String + conditionSQL[pos];
				} else if (pos == 1) {
					SQL_String = SQL_String + conditionSQL[pos] + key + "%'";
				} else if (pos == 2) {
					SQL_String = SQL_String + conditionSQL[pos] + key + "'";
				} else if (pos == 3) {
					SQL_String = SQL_String + conditionSQL[pos] + key + "%'";
				} else if (pos == 4) {
					SQL_String = SQL_String + conditionSQL[pos] + key + "%'";
				}

				goodsInfData = ConnectManagement.generalQuery(SQL_String, tableName_String);
				goodsInf_JTable.setModel(new TableModelUnEdit(goodsInfData, columnNames));
				amount_int = goodsInfData.length;
				goodsAmount_JLable.setText("��Ʒ����: " + amount_int);
				SQL_String = "select * from Goods ";
				setTable();
			}
		});

		/* ��ѯ��ť */
		searchConfirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int pos = condition_JComboBox.getSelectedIndex();
				String key = conditionKey_JTextField.getText();
				if (pos == 0) {
					SQL_String = SQL_String + conditionSQL[pos];
				} else if (pos == 1) {
					SQL_String = SQL_String + conditionSQL[pos] + key + "%'";
				} else if (pos == 2) {
					SQL_String = SQL_String + conditionSQL[pos] + key + "'";
				} else if (pos == 3) {
					SQL_String = SQL_String + conditionSQL[pos] + key + "%'";
				} else if (pos == 4) {
					SQL_String = SQL_String + conditionSQL[pos] + key + "%'";
				}

				goodsInfData = ConnectManagement.generalQuery(SQL_String, tableName_String);
				goodsInf_JTable.setModel(new TableModelUnEdit(goodsInfData, columnNames));
				amount_int = goodsInfData.length;
				goodsAmount_JLable.setText("��Ʒ����: " + amount_int);
				SQL_String = "select * from Goods ";
				setTable();
			}
		});// end ��ѯ��ť

		/* �Ҽ������˵� */
		goodsInf_JTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// �����Ҽ��˵�
					mainMenu_JPopupMenu.show(goodsInf_JTable, e.getX(), e.getY());
				}
			}
		});// end�����Ҽ��˵�

		/* �����Ʒ�¼� */
		addGoods_Item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new NewGoodsJFrame();
			}
		});// end�����Ʒ�¼�

		/* ɾ����ť�¼� */
		deleteGoods_Item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (goodsInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δѡ����Ʒ", "����", 0);
					return;
				}
				// choose:0 ȷ�� ,2ȡ��,-1 �رմ���
				int choose_int = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��,ɾ���󲻿ɻָ�!", "ȷ��", 2);
				if (choose_int == 2 || choose_int == -1) {
					return;
				}

				String deletSQL_String = "delete from Goods where ";
				String goodsId_String;
				int j = 0;

				for (int i : goodsInf_JTable.getSelectedRows()) {
					goodsId_String = (String) goodsInfData[i][0];
					deletSQL_String = deletSQL_String + " goods_id = '" + goodsId_String + "'";
					if (j != goodsInf_JTable.getSelectedRowCount() - 1) {
						deletSQL_String = deletSQL_String + " or ";
					}
					j++;
				}
				ConnectManagement.generalUpdate(deletSQL_String);
			}
		});// ɾ����ť�¼�

		/* �༭��ť */
		editGoods_Item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (goodsInf_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δѡ����Ʒ", "����", 0);
					return;
				}
				int choose_int = goodsInf_JTable.getSelectedRow();// ���Ҫ�޸ĵ�λ��
				new EditGoodsJFrame((String) goodsInfData[choose_int][0]);
			}
		});// end �༭��ť�¼�

	}// end setListener

	public GoodsInf() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		configuringComponents();
	}

	public void setTable() {
		goodsInf_JTable.setRowHeight(40);
		goodsInf_JTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		goodsInf_JTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		goodsInf_JTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		goodsInf_JTable.getColumnModel().getColumn(4).setPreferredWidth(30);
		goodsInf_JTable.setFont(table_Font);
		GeneralTools.makeFace(goodsInf_JTable);
		goodsInf_JTable.setShowVerticalLines(true);
	}
}
