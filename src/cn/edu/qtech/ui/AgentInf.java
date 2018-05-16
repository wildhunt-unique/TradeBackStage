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

public class AgentInf extends JPanel {
	private JComboBox<String> condition_JComboBox;
	private JTable produce_JTable;
	private JTextField condition_JTextField;
	private JButton search_JButton;
	private JScrollPane produceRecord_JScrollPane;
	private JLabel amount_JLabel;
	private final String[] ColumnNames = { "�����̱��", "����������", "��ϵ�绰", "���۵���", "��������" };
	private Object[][] produceRecord_Data = null;
	private final String querySQL = "SELECT Agent.agent_id,Agent.agent_name,Agent.phone,Area.area_name,COUNT(Indent.indent_id) FROM Agent,Area,Indent WHERE Agent.area_id = Area.area_id AND Indent.agent_id = Agent.agent_id ";
	private final String[] condition_Strings = { "", " AND Agent.agent_id = ", "AND Agent.agent_name like " };
	private Font buttonFont = GeneralTools.getButtonFont();

	private JPopupMenu main_JPopupMenu;
	private JMenuItem refresh_Item;
	private JMenuItem detail_Item;
	private JMenuItem copyId_Item;
	private JMenuItem new_Item;
	private JMenuItem edit_Item;
	private JMenuItem delete_Item;

	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		main_JPopupMenu = new JPopupMenu();
		refresh_Item = new JMenuItem("ˢ��", new ImageIcon(GeneralTools.getRedSkinPtah() + "refresh.png"));
		detail_Item = new JMenuItem("��������Ϣ����        ", new ImageIcon(GeneralTools.getRedSkinPtah() + "detail.png"));
		copyId_Item = new JMenuItem("���ƾ����̱��");
		new_Item = new JMenuItem("�½���������Ϣ");
		edit_Item = new JMenuItem("�༭��Ϣ", new ImageIcon(GeneralTools.getRedSkinPtah() + "edit.png"));
		delete_Item = new JMenuItem("ɾ����������Ϣ");
		edit_Item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		condition_JComboBox = new JComboBox<String>();
		produce_JTable = new JTable();
		condition_JTextField = new JTextField(10);
		search_JButton = new JButton("��ѯ");
		produceRecord_JScrollPane = new JScrollPane(produce_JTable);
		amount_JLabel = new JLabel("��¼������");

		/*---------------------�������-----------------------*/
		condition_JComboBox.addItem("��ѯ����");
		condition_JComboBox.addItem("�������̱�Ų�ѯ");
		condition_JComboBox.addItem("��������������ѯ");

		produceRecord_JScrollPane.setBounds(20, 60, 1100, 500);

		amount_JLabel.setBounds(950, 565, 200, 50);
		condition_JComboBox.setBounds(20, 5, 300, 50);
		search_JButton.setBounds(970, 5, 150, 50);
		condition_JTextField.setBounds(320, 5, 650, 50);

		condition_JComboBox.setFont(buttonFont);
		search_JButton.setFont(buttonFont);
		condition_JTextField.setFont(buttonFont);
		amount_JLabel.setFont(buttonFont);
		refresh_Item.setFont(GeneralTools.getTableFont());
		detail_Item.setFont(GeneralTools.getTableFont());
		copyId_Item.setFont(GeneralTools.getTableFont());
		new_Item.setFont(GeneralTools.getTableFont());
		edit_Item.setFont(GeneralTools.getTableFont());
		delete_Item.setFont(GeneralTools.getTableFont());

		produce_JTable.setModel(new TableModelUnEdit(produceRecord_Data, ColumnNames));
		setTable();
		setListener();
		/*---------------------������-----------------------*/
		this.add(condition_JComboBox);
		this.add(amount_JLabel);
		this.add(condition_JTextField);
		this.add(produceRecord_JScrollPane);
		this.add(search_JButton);
		main_JPopupMenu.add(refresh_Item);
		main_JPopupMenu.add(detail_Item);
		main_JPopupMenu.add(copyId_Item);
		main_JPopupMenu.add(edit_Item);
		main_JPopupMenu.add(new_Item);
		//main_JPopupMenu.add(delete_Item);
		produce_JTable.add(main_JPopupMenu);

	}// end configuringComponents()

	/**
	 * ���ø����¼�������
	 */
	private void setListener() {

		/* ��ѯ��ť����¼� */
		search_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String SQL;
				if (condition_JComboBox.getSelectedIndex() == 0) {
					SQL = querySQL;
				} else if (condition_JComboBox.getSelectedIndex() == 2) {
					SQL = querySQL + condition_Strings[condition_JComboBox.getSelectedIndex()] + "'%"
							+ condition_JTextField.getText() + "%'";
				} else {
					SQL = querySQL + condition_Strings[condition_JComboBox.getSelectedIndex()] + "'"
							+ condition_JTextField.getText() + "'";
				}

				produceRecord_Data = ConnectManagement
						.unionQuery(SQL + " GROUP BY agent_id ORDER BY COUNT(Indent.indent_id) DESC", 5);
				produce_JTable.setModel(new TableModelUnEdit(produceRecord_Data, ColumnNames));
				setTable();
			}
		});// end ��ѯ��ť����¼�

		/* �Ҽ�����˵��¼� */
		produce_JTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					// �����Ҽ��˵�
					main_JPopupMenu.show(produce_JTable, e.getX(), e.getY());
				}
			}
		});// end �����Ҽ��˵�

		/* ˢ���Ҽ��˵��¼� */
		refresh_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String SQL;
				if (condition_JComboBox.getSelectedIndex() == 0) {
					SQL = querySQL;
				} else if (condition_JComboBox.getSelectedIndex() == 2) {
					SQL = querySQL + condition_Strings[condition_JComboBox.getSelectedIndex()] + "'%"
							+ condition_JTextField.getText() + "%'";
				} else {
					SQL = querySQL + condition_Strings[condition_JComboBox.getSelectedIndex()] + "'"
							+ condition_JTextField.getText() + "'";
				}

				produceRecord_Data = ConnectManagement
						.unionQuery(SQL + " GROUP BY agent_id ORDER BY COUNT(Indent.indent_id) DESC", 5);
				produce_JTable.setModel(new TableModelUnEdit(produceRecord_Data, ColumnNames));
				setTable();
			}
		});// end ˢ���Ҽ��˵��¼�

		/* ���Ʊ���¼� */
		copyId_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (produce_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δѡ���κ���Ϣ", "����", 0);
					return;
				}
				int pos = produce_JTable.getSelectedRow();
				try {
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();// ��ȡϵͳ���а�
					String str = (String) produceRecord_Data[pos][0];
					StringSelection selection = new StringSelection(str);// ����String��������
					clipboard.setContents(selection, null);// ����ı���ϵͳ���а�
				} catch (Exception e2) {
				}
			}
		});// end ���Ʊ���¼�

		/* ��Ϣ�����¼� */
		detail_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (produce_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δѡ���κ���Ϣ", "����", 0);
					return;
				}
				int choosePos_int = produce_JTable.getSelectedRow();
				new AgentInfDialog((String) produceRecord_Data[choosePos_int][0]);
			}
		});// end ��Ϣ�����¼�

		/* �༭�¼� */
		edit_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (produce_JTable.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "��δѡ���κ���Ϣ", "����", 0);
					return;
				}
				int choosePos_int = produce_JTable.getSelectedRow();
				new AgentInfJFrame((String) produceRecord_Data[choosePos_int][0]);
			}
		});// end �༭�¼�

	}// end setListener()

	public AgentInf() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		produceRecord_Data = ConnectManagement
				.unionQuery(querySQL + " GROUP BY agent_id ORDER BY COUNT(Indent.indent_id) DESC", 5);
		configuringComponents();
		amount_JLabel.setText("��¼������" + produceRecord_Data.length);
	}

	private void setTable() {
		produce_JTable.setRowHeight(40);
		// produce_JTable.getColumnModel().getColumn(5).setPreferredWidth(30);
		produce_JTable.setFont(GeneralTools.getTableFont());
		GeneralTools.makeFace(produce_JTable);
		produce_JTable.setShowVerticalLines(true);
	}
}
