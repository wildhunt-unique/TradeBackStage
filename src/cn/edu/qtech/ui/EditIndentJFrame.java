package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.TableModelUnEdit;
import cn.edu.qtech.util.checked_Goods;
import cn.edu.qtech.util.getDay;

public class EditIndentJFrame extends JFrame {
	public EditIndentJFrame(String indent_id, String agent_id) {
		super();
		this.setResizable(false);
		this.setTitle("��������");
		int Dwidth = 1120;
		int Dheight = 720;
		this.setSize(Dwidth, Dheight);
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - Dwidth) / 2, (height - Dheight) / 2);
		this.add(new EditIndentJPanel(indent_id, agent_id));
		this.setVisible(true);
	}

	class EditIndentJPanel extends JPanel {
		private JFrame owner;
		private JScrollPane alreadyAddPanel;
		private JTable alreadyAddGoods;
		private SelectGoodsJFrame selectGOodsDialog;

		private JComboBox<String> idOrName_JComboBox;

		private JButton newSellRecord;
		private JButton indentConfirm;
		private JButton cancelButton;

		private JPopupMenu dialogMenu;
		private JMenuItem addGoodsItem;
		private JMenuItem whiteItem;

		private JPopupMenu multiMenu;
		private JMenuItem editAmountItem;
		private JMenuItem addAmountItem;
		private JMenuItem reduceAmountItem;
		private JMenuItem deleteItem;

		private Font myFont = GeneralTools.getButtonFont();
		private Font itemFont = new Font("����ϸ��", Font.PLAIN, 20);

		private Object[][] alreadyAddGoodsData = null;
		private String[] columnNames = { "��Ʒ��", "����", "��װ���", "��װ��ʽ", "��ѡ������" };
		private ArrayList<checked_Goods> checkedGoods = new ArrayList<checked_Goods>();
		private String agent_id;
		private String indent_id;

		/**
		 * ����ʵ����ģ���ڵ��������,�����á����
		 */
		public void configuringComponents() {
			/*---------------------ʵ�����-----------------------*/
			alreadyAddGoods = new JTable();

			idOrName_JComboBox = new JComboBox<String>();

			newSellRecord = new JButton("�����Ʒ");
			indentConfirm = new JButton("ȷ���޸�");
			cancelButton = new JButton("ȡ��");
			dialogMenu = new JPopupMenu();
			addGoodsItem = new JMenuItem("���     ",new ImageIcon(GeneralTools.getRedSkinPtah() + "add.png"));
			whiteItem = new JMenuItem("  ");

			multiMenu = new JPopupMenu();
			editAmountItem = new JMenuItem("�޸�����",new ImageIcon(GeneralTools.getRedSkinPtah() + "edit.png"));
			addAmountItem = new JMenuItem("��������");
			reduceAmountItem = new JMenuItem("��������           ");
			deleteItem = new JMenuItem("ɾ��",new ImageIcon(GeneralTools.getRedSkinPtah() + "delete.png"));
			alreadyAddGoods.setModel(new DefaultTableModel(alreadyAddGoodsData, columnNames));
			alreadyAddPanel = new JScrollPane(alreadyAddGoods);

			/*---------------------�������-----------------------*/

			newSellRecord.setFont(myFont);
			indentConfirm.setFont(myFont);
			cancelButton.setFont(myFont);
			idOrName_JComboBox.setFont(myFont);

			addGoodsItem.setFont(itemFont);

			addGoodsItem.setSize(250, 30);

			editAmountItem.setFont(itemFont);
			addAmountItem.setFont(itemFont);
			deleteItem.setFont(itemFont);
			reduceAmountItem.setFont(itemFont);
			whiteItem.setFont(itemFont);

			whiteItem.setSize(250, 30);
			editAmountItem.setSize(250, 30);
			addAmountItem.setSize(250, 30);
			reduceAmountItem.setSize(250, 30);
			deleteItem.setSize(250, 30);

			cancelButton.setBounds(900, 575, 150, 50);
			newSellRecord.setBounds(700, 10, 150, 50);
			indentConfirm.setBounds(900, 10, 150, 50);
			alreadyAddPanel.setBounds(50, 70, 1000, 500);

			setListener();
			/*---------------------������-----------------------*/

			this.add(indentConfirm);
			this.add(newSellRecord);
			this.add(alreadyAddPanel);
			this.add(cancelButton);
			this.add(idOrName_JComboBox);
			dialogMenu.add(addGoodsItem);

			multiMenu.add(editAmountItem);
			multiMenu.add(addAmountItem);
			multiMenu.add(reduceAmountItem);
			multiMenu.add(deleteItem);
			multiMenu.add(whiteItem);
			multiMenu.add(whiteItem);
			alreadyAddGoods.add(multiMenu);
			// menu.add(whiteItem);

		}// end configuringComponents()

		/**
		 * ���ø����¼�������
		 */
		private void setListener() {
			// TODO Auto-generated method stub

			/* ȡ��������ť������¼� */
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
				}
			});//ȡ��������ť������¼�

			/* �ύ��ť������¼� */
			indentConfirm.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);

					String[] insertIndent = newIndentData();

					String deleteSQL = "delete from Sell where indent_id = '" + indent_id + "'";

					String updateIndentSQL = "update Indent set state = 'δ�ӵ�' where indent_id = '" + indent_id + "'";
					String insertSellSQL = listToQueryString(checkedGoods, indent_id);
					System.out.println(deleteSQL);
					System.out.println(updateIndentSQL);
					System.out.println(insertSellSQL);
					ConnectManagement.generalUpdate(deleteSQL);
					ConnectManagement.generalUpdate(updateIndentSQL);
					ConnectManagement.generalUpdate(insertSellSQL);
					JOptionPane.showMessageDialog(selectGOodsDialog,"�޸����!","��ʾ",2);
					dispose();
				}
			});//�ύ��ť������¼�

			/* �����Ʒ��ť������¼� */
			newSellRecord.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					selectGOodsDialog = new SelectGoodsJFrame();
					JPopupMenu.setDefaultLightWeightPopupEnabled(false);
					selectGOodsDialog.getPanelQute().add(dialogMenu);
					// ����Ҽ������˵��¼�
					selectGOodsDialog.getPanelQute().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (e.getButton() == MouseEvent.BUTTON3) {
								// �����Ҽ��˵�
								dialogMenu.show(selectGOodsDialog.getPanelQute(), e.getX(), e.getY());
							}
						}// �����Ҽ��˵�
					});// end
				}
			});// end �¼�������

			/* ���ȷ�������Ҽ��˵� */
			addGoodsItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (!selectGOodsDialog.isChecked()) {
						JOptionPane.showMessageDialog(selectGOodsDialog, "����ѡ����Ʒ");
						return;
					}
					String getInput = JOptionPane.showInputDialog(selectGOodsDialog, "����������", "��Ʒ����", 1);
					if (!GeneralTools.isNum(getInput)) {
						JOptionPane.showMessageDialog(selectGOodsDialog, "����������!");
						return;
					}
					int amount = Integer.parseInt(getInput);

					selectGOodsDialog.createArraylist(amount);
					checkedGoods.addAll(selectGOodsDialog.getArraylist());

					/* д��table������ʾ */
					checkedGoods = distinctList(checkedGoods);// ȥ��
					alreadyAddGoods.setModel(new DefaultTableModel(listToObejct(checkedGoods), columnNames));

					// for (int i = 0; i < checkedGoods.size(); i++) {
					// System.out.println(checkedGoods.get(i));
					// }
					// System.out.println("");
					setTable();
				}
			});// end�����Ʒ���ʱ�������

			/* ����ӵ���Ʒ�����Ҽ��¼� */
			alreadyAddGoods.addMouseListener(new MouseAdapter() {
				// �Ҽ������˵�
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						// �����Ҽ��˵�
						multiMenu.show(alreadyAddGoods, e.getX(), e.getY());
					}
				}// �����Ҽ��˵�

			});

			/* �޸�������ť�¼� */
			editAmountItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (alreadyAddGoods.getSelectedRowCount() == 0) {
						JOptionPane.showMessageDialog(selectGOodsDialog, "����ѡ����Ʒ");
						return;
					}
					String getInput = JOptionPane.showInputDialog(selectGOodsDialog, "����������", "�޸���Ʒ����", 1);
					if (!GeneralTools.isNum(getInput)) {
						JOptionPane.showMessageDialog(selectGOodsDialog, "����������!");
						return;
					}
					int amount = Integer.parseInt(getInput);
					for (int i : alreadyAddGoods.getSelectedRows()) {
						checkedGoods.get(i).setAmount(amount);
					}
					checkedGoods = removeTheInvalid(checkedGoods);
					alreadyAddGoods.setModel(new DefaultTableModel(listToObejct(checkedGoods), columnNames));
					setTable();
				}
			});

			/* ����Ҽ��˵�ɾ�����¼� */
			deleteItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (alreadyAddGoods.getSelectedRowCount() == 0) {
						JOptionPane.showMessageDialog(selectGOodsDialog, "����ѡ����Ʒ");
						return;
					}
					for (int i : alreadyAddGoods.getSelectedRows()) {
						checkedGoods.get(i).setAmount(0);
					}
					checkedGoods = removeTheInvalid(checkedGoods);
					alreadyAddGoods.setModel(new DefaultTableModel(listToObejct(checkedGoods), columnNames));
					setTable();
				}
			});

			reduceAmountItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (alreadyAddGoods.getSelectedRowCount() == 0) {
						JOptionPane.showMessageDialog(selectGOodsDialog, "����ѡ����Ʒ");
						return;
					}
					String getInput = JOptionPane.showInputDialog(selectGOodsDialog, "����������", "�޸���Ʒ����", 1);
					if (!GeneralTools.isNum(getInput)) {
						JOptionPane.showMessageDialog(selectGOodsDialog, "����������!");
						return;
					}
					int amount = Integer.parseInt(getInput);

					// int min = checkedGoods.get(0).getAmount();
					// for (int i : alreadyAddGoods.getSelectedRows()) {
					// if(min > checkedGoods.get(i).getAmount()){
					// min = checkedGoods.get(i).getAmount();
					// }
					// }

					for (int i : alreadyAddGoods.getSelectedRows()) {
						if (checkedGoods.get(i).getAmount() <= amount) {
							checkedGoods.get(i).setAmount(0);
						} else {
							checkedGoods.get(i).setAmount(checkedGoods.get(i).getAmount() - amount);
						}
					}
					checkedGoods = removeTheInvalid(checkedGoods);
					alreadyAddGoods.setModel(new DefaultTableModel(listToObejct(checkedGoods), columnNames));
					setTable();
				}
			});

			/* ����Ҽ��˵������������¼� */
			addAmountItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (alreadyAddGoods.getSelectedRowCount() == 0) {
						JOptionPane.showMessageDialog(selectGOodsDialog, "����ѡ����Ʒ");
						return;
					}
					String getInput = JOptionPane.showInputDialog(selectGOodsDialog, "����������", "�޸���Ʒ����", 1);
					if (!GeneralTools.isNum(getInput)) {
						JOptionPane.showMessageDialog(selectGOodsDialog, "����������!");
						return;
					}
					int amount = Integer.parseInt(getInput);
					for (int i : alreadyAddGoods.getSelectedRows()) {
						checkedGoods.get(i).setAmount(amount + checkedGoods.get(i).getAmount());
					}
					checkedGoods = removeTheInvalid(checkedGoods);
					alreadyAddGoods.setModel(new DefaultTableModel(listToObejct(checkedGoods), columnNames));
					setTable();
				}
			});// end ����Ҽ��˵������������¼�
		}

		/* ���캯�� */
		public EditIndentJPanel(String indent_id, String agent_id) {
			// TODO Auto-generated constructor stub
			this.setLayout(null);
			this.indent_id = indent_id;
			this.agent_id = agent_id;
			configuringComponents();
			Object[][] tempdata = ConnectManagement.unionQuery(
					"SELECT Goods.goods_name,Goods.goods_type,Goods.norms,Goods.pack,Sell.amount,Sell.goods_id FROM Sell,Goods WHERE Sell.indent_id = '"
							+ this.indent_id + "' AND Sell.goods_id = Goods.goods_id",
					6);

			for (int i = 0; i < tempdata.length; i++) {
				int amount = Integer.parseInt((String) tempdata[i][4]);
				checkedGoods.add(new checked_Goods((String) tempdata[i][0], (String) tempdata[i][1],
						(String) tempdata[i][2], (String) tempdata[i][3], amount, (String) tempdata[i][5]));
			}
			alreadyAddGoods.setModel(new TableModelUnEdit(listToObejct(checkedGoods), columnNames));
			setTable();
		}// end ���췽��

		/**
		 * ȥ��
		 */
		public ArrayList<checked_Goods> distinctList(ArrayList<checked_Goods> demo) {
			checked_Goods temp_1;
			checked_Goods temp_2;
			for (int i = 0; i < demo.size(); i++) {
				temp_1 = demo.get(i);
				for (int j = i + 1; j < demo.size(); j++) {
					temp_2 = demo.get(j);
					if (temp_1.getGoods_id().equals(temp_2.getGoods_id())) {
						int count_1 = demo.get(i).getAmount();
						int count_2 = demo.get(j).getAmount();
						int amount = count_1 + count_2;
						demo.get(i).setAmount(amount);
						demo.remove(j);
						j--;
					}
				}
			}
			return demo;
		}

		/**
		 * ȥ0
		 */
		public ArrayList<checked_Goods> removeTheInvalid(ArrayList<checked_Goods> demo) {
			checked_Goods temp_1;
			for (int i = 0; i < demo.size(); i++) {
				temp_1 = demo.get(i);
				if (temp_1.getAmount() == 0) {
					demo.remove(i);
					i--;
				}
			}
			return demo;
		}

		/* ��������ڶ��� */
		public void getOwner(JFrame Owner) {
			owner = Owner;
		}

		/**
		 * ����Jtablge��ʽ
		 */
		public void setTable() {
			alreadyAddGoods.setRowHeight(40);
			alreadyAddGoods.getColumnModel().getColumn(0).setPreferredWidth(250);
			alreadyAddGoods.setFont(GeneralTools.getTableFont());
			GeneralTools.makeFace(alreadyAddGoods);
			alreadyAddGoods.setShowVerticalLines(true);
		}

		/**
		 * arrayl����ά���������ת��
		 **/
		public Object[][] listToObejct(ArrayList<checked_Goods> demo) {
			int height = demo.size();
			checked_Goods temp;
			int width = 5;
			Object[][] O = new Object[height][width];
			for (int i = 0; i < height; i++) {
				temp = demo.get(i);
				for (int j = 0; j < 5; j++) {
					O[i][j] = temp.getObejct(j);
				}
			}
			return O;
		}

		/**
		 * �½�һ����������Ϣ
		 */
		private String[] newIndentData() {
			String[] DefaultIndentInf = { "������", "�����̱��", "δ�ӵ�", "δ֧��", "��������" };
			getDay date = new getDay();
			DefaultIndentInf[0] = new String("I" + date.getUnFormatDate());
			DefaultIndentInf[4] = date.getFormatDate();
			return DefaultIndentInf;
		}

		/**
		 * ����Ϣת��ΪSQL��ѯ���
		 * 
		 * @param ���ݵļ���
		 * @return SQL���
		 */
		public String listToQueryString(ArrayList<checked_Goods> demo, String indent_id) {
			String SQL = "insert into Sell values";
			String temp;
			checked_Goods tempdata;
			for (int i = 0; i < demo.size(); i++) {
				tempdata = demo.get(i);
				temp = "('" + indent_id + "','" + tempdata.getGoods_id() + "',null,'" + tempdata.getAmount() + "')";
				if (i != demo.size() - 1) {
					temp = temp + ",";
				}
				SQL = SQL + temp;
			}
			// System.out.println(SQL);
			return SQL;
		}
	}// end ��

}
