package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.checked_Goods;
import cn.edu.qtech.util.getDay;

public class NewIndentJPanel extends JPanel {
	private JFrame owner;
	private JScrollPane alreadyAddPanel;
	private JTable alreadyAddGoods;
	private SelectGoodsJFrame selectGOodsDialog;

	private JComboBox<String> idOrName_JComboBox;
	private JTextField agentInf_JTextField;
	private JButton verifyAgent_JButton;

	private JButton newIndent;
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
	private JButton confirm_JButton;
	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		alreadyAddGoods = new JTable();

		idOrName_JComboBox = new JComboBox<String>();
		verifyAgent_JButton = new JButton("����");
		agentInf_JTextField = new JTextField(10);
		confirm_JButton = new JButton("���");
		newIndent = new JButton("�½�����");
		newSellRecord = new JButton("�����Ʒ");
		indentConfirm = new JButton("�ύ����");
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
		confirm_JButton.setBounds(750, 655, 150, 50);
		confirm_JButton.setFont(myFont);
		
		newIndent.setFont(myFont);
		newSellRecord.setFont(myFont);
		indentConfirm.setFont(myFont);
		cancelButton.setFont(myFont);
		idOrName_JComboBox.setFont(myFont);
		verifyAgent_JButton.setFont(myFont);
		agentInf_JTextField.setFont(myFont);

		addGoodsItem.setFont(itemFont);
		addGoodsItem.setSize(250, 30);
		editAmountItem.setFont(itemFont);
		addAmountItem.setFont(itemFont);
		deleteItem.setFont(itemFont);
		reduceAmountItem.setFont(itemFont);
		whiteItem.setFont(itemFont);

		addAmountItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		
		cancelButton.setBounds(900, 575, 150, 50);
		newIndent.setBounds(50, 30, 150, 50);
		newSellRecord.setBounds(700, 10, 150, 50);
		indentConfirm.setBounds(900, 10, 150, 50);
		alreadyAddPanel.setBounds(50, 70, 1000, 500);
		idOrName_JComboBox.setBounds(50, 10, 200, 50);
		agentInf_JTextField.setBounds(250, 10, 150, 50);
		verifyAgent_JButton.setBounds(400, 10, 100, 50);

		idOrName_JComboBox.addItem("�����̱��");
		idOrName_JComboBox.addItem("����������");

		alreadyAddPanel.setVisible(false);
		newSellRecord.setVisible(false);
		indentConfirm.setVisible(false);
		cancelButton.setVisible(false);
		idOrName_JComboBox.setVisible(false);
		agentInf_JTextField.setVisible(false);
		verifyAgent_JButton.setVisible(false);

		setListener();
		/*---------------------������-----------------------*/

		this.add(indentConfirm);
		this.add(newIndent);
		this.add(newSellRecord);
		this.add(alreadyAddPanel);
		this.add(cancelButton);
		this.add(idOrName_JComboBox);
		this.add(agentInf_JTextField);
		this.add(verifyAgent_JButton);
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
		/* ����������ť������¼� */
		newIndent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				newIndent.setVisible(false);
				alreadyAddPanel.setVisible(true);
				newSellRecord.setVisible(true);
				indentConfirm.setVisible(true);
				cancelButton.setVisible(true);
				idOrName_JComboBox.setVisible(true);
				agentInf_JTextField.setVisible(true);
				verifyAgent_JButton.setVisible(true);
			}
		});//����������ť������¼�

		/* ȡ��������ť������¼� */
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newIndent.setVisible(true);
				alreadyAddPanel.setVisible(false);
				newSellRecord.setVisible(false);
				indentConfirm.setVisible(false);
				cancelButton.setVisible(false);
				idOrName_JComboBox.setVisible(false);
				agentInf_JTextField.setVisible(false);
				verifyAgent_JButton.setVisible(false);
				checkedGoods.clear();
				alreadyAddGoods.setModel(new DefaultTableModel(null, columnNames));
				if(selectGOodsDialog!=null){
					selectGOodsDialog.dispose();
				}
			}
		});

		/* �ύ��ť������¼� */
		indentConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(checkedGoods.size() == 0){
					JOptionPane.showMessageDialog(null, "������Ʒ����Ϊ��!", "����", 0);
					return;
				}
				
				Object[][] tempData = null;
				String querySQL = "SELECT * from Agent where ";
				String condition_String = agentInf_JTextField.getText();
				if (condition_String.equals("")) {
					JOptionPane.showMessageDialog(null, "����������Ϊ��!", "����", 0);
					return;
				}
				if (idOrName_JComboBox.getSelectedIndex() == 0) {
					String SQL = querySQL + "agent_id ='" + condition_String + "'";
					tempData = ConnectManagement.generalQuery(SQL, "Agent");
				} else {
					String SQL = querySQL + "agent_name ='" + condition_String + "'";
					tempData = ConnectManagement.generalQuery(SQL, "Agent");
				}
				
				if (tempData.length == 0) {
					if (idOrName_JComboBox.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "�ޱ��Ϊ��"+condition_String+"���ľ�����", "����", 0);
						return;
					} else {
						JOptionPane.showMessageDialog(null, "������Ϊ��"+condition_String+"���ľ�����", "����", 0);
						return;
					}
				}
				
				
				newIndent.setVisible(true);
				alreadyAddPanel.setVisible(false);
				newSellRecord.setVisible(false);
				indentConfirm.setVisible(false);
				cancelButton.setVisible(false);
				idOrName_JComboBox.setVisible(false);
				agentInf_JTextField.setVisible(false);
				verifyAgent_JButton.setVisible(false);
				String[] insertIndent = newIndentData();
				String insertIndentSQL = "insert into Indent values('" + insertIndent[0] + "','" + tempData[0][0] + "','"
						+ insertIndent[2] + "','" + insertIndent[3] + "','" + insertIndent[4] + "');";
				String insertSellSQL = listToQueryString(checkedGoods, insertIndent[0]);
				// System.out.println(insertIndentSQL);
				// System.out.println(insertSellSQL);
				ConnectManagement.generalUpdate(insertIndentSQL);
				ConnectManagement.generalUpdate(insertSellSQL);
				checkedGoods.clear();
				alreadyAddGoods.setModel(new DefaultTableModel(null, columnNames));
				if(selectGOodsDialog!=null){
					selectGOodsDialog.dispose();
				}
				JOptionPane.showMessageDialog(null, "�����ύ�ɹ�!", "��ʾ", 2);
			}
		});//end �ύ��ť�¼�������

		/* �����Ʒ��ť������¼� */
		newSellRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				selectGOodsDialog = new SelectGoodsJFrame();
				JPopupMenu.setDefaultLightWeightPopupEnabled(false);
				selectGOodsDialog.getPanelQute().add(dialogMenu);
				confirm_JButton.setVisible(true);
				confirm_JButton.setOpaque(true);
				selectGOodsDialog.add(confirm_JButton);
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
		});// end�����Ʒ����½�������

		/*�����Ʒ����½�������*/
		confirm_JButton.addActionListener(new ActionListener() {
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
		});// end�����Ʒ����½�������

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
		
		/*�ı���Ʒ������ť�¼�*/
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
		});//�ı���Ʒ������ť�¼�

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

		/*������Ʒ��ť�¼�*/
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
		});//������Ʒ��ť�¼�

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

		/* ���鰴ť�¼� */
		verifyAgent_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[][] tempData = null;
				String querySQL = "SELECT * from Agent where ";
				String condition_String = agentInf_JTextField.getText();
				if (condition_String.equals("")) {
					JOptionPane.showMessageDialog(null, "����������Ϊ��!", "����", 0);
					return;
				}
				if (idOrName_JComboBox.getSelectedIndex() == 0) {
					String SQL = querySQL + "agent_id ='" + condition_String + "'";
					tempData = ConnectManagement.generalQuery(SQL, "Agent");
				} else {
					String SQL = querySQL + "agent_name ='" + condition_String + "'";
					tempData = ConnectManagement.generalQuery(SQL, "Agent");
				}
				
				if (tempData.length == 0) {
					if (idOrName_JComboBox.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "�ޱ��Ϊ��"+condition_String+"���ľ�����", "����", 0);
						return;
					} else {
						JOptionPane.showMessageDialog(null, "������Ϊ��"+condition_String+"���ľ�����", "����", 0);
						return;
					}
				}
				new AgentInfDialog((String)tempData[0][0]);
			}
		});// ���鰴ť�¼�
	}

	/* ���캯�� */
	public NewIndentJPanel() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		configuringComponents();

	}// end ���췽��

	private JPanel getclass() {
		return this;
	}

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
		return SQL;
	}
	
	

}// end ��