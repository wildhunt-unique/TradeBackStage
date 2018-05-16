package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.TableModelUnEdit;

public class ProduceInfJPanel extends JPanel {
	private JComboBox<String> condition_JComboBox;
	private JTable produce_JTable;
	private JTextField condition_JTextField;
	private JButton search_JButton;
	private JScrollPane produceRecord_JScrollPane;
	private JLabel amount_JLabel;
	private final String[] ColumnNames = { "��������", "��Ʒ��", "��Ʒ��", "�ֿ��", "�ֿ���", "��������" };
	private Object[][] produceRecord_Data = null;
	private final String querySQL = "SELECT ProduceGroup.group_name,Goods.goods_id,Goods.goods_name,Warehouse.warehouse_id,Warehouse.warehouse_name,Produce.produce_amount FROM Produce,ProduceGroup,Goods ,Warehouse WHERE Produce.group_id = ProduceGroup.group_id AND Produce.goods_id = Goods.goods_id AND Produce.warehouse_id = Warehouse.warehouse_id";
	private final String[] condition_Strings = {""," AND ProduceGroup.group_id = "," AND ProduceGroup.group_name = "," AND Goods.goods_id = "," AND Warehouse.warehouse_id = "};
	private Font buttonFont = GeneralTools.getButtonFont();
	
	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		condition_JComboBox = new JComboBox<String>();
		produce_JTable = new JTable();
		condition_JTextField = new JTextField(10);
		search_JButton = new JButton("��ѯ");
		produceRecord_JScrollPane = new JScrollPane(produce_JTable);
		amount_JLabel = new JLabel("��¼������");

		/*---------------------�������-----------------------*/
		condition_JComboBox.addItem("��ѯ����");
		condition_JComboBox.addItem("���������Ų�ѯ");
		condition_JComboBox.addItem("�����������Ʋ�ѯ");
		condition_JComboBox.addItem("����Ʒ�Ų�ѯ");
		condition_JComboBox.addItem("���ֿ�Ų�ѯ");

		produceRecord_JScrollPane.setBounds(20, 60, 1100, 500);
		
		amount_JLabel.setBounds(950,565, 200, 50);
		condition_JComboBox.setBounds(20, 5, 300, 50);
		search_JButton.setBounds(970, 5, 150, 50);
		condition_JTextField.setBounds(320, 5, 650, 50);
		
		condition_JComboBox.setFont(buttonFont);
		search_JButton.setFont(buttonFont);
		condition_JTextField.setFont(buttonFont);
		amount_JLabel.setFont(buttonFont);

		produce_JTable.setModel(new TableModelUnEdit(produceRecord_Data, ColumnNames));
		setTable();
		setListener();
		/*---------------------������-----------------------*/
		this.add(condition_JComboBox);
		this.add(amount_JLabel);
		this.add(condition_JTextField);
		this.add(produceRecord_JScrollPane);
		this.add(search_JButton);

	}// end configuringComponents()

	/**
	 * ���ø����¼�������
	 */
	private void setListener() {
		
		/*��ѯ��ť����¼�*/
		search_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String SQL = querySQL + condition_Strings[condition_JComboBox.getSelectedIndex()]+"'"+condition_JTextField.getText()+"'";
				produceRecord_Data = ConnectManagement.unionQuery(SQL, 6);
				produce_JTable.setModel(new TableModelUnEdit(produceRecord_Data, ColumnNames));
				setTable();
			}
		});//��ѯ��ť����¼�
		
	}// end setListener()

	public ProduceInfJPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		produceRecord_Data = ConnectManagement.unionQuery(querySQL, 6);
		configuringComponents();
		amount_JLabel.setText("��¼������"+produceRecord_Data.length);
	}

	private void setTable() {
		produce_JTable.setRowHeight(40);
		produce_JTable.getColumnModel().getColumn(5).setPreferredWidth(30);
		produce_JTable.setFont(GeneralTools.getTableFont());
		GeneralTools.makeFace(produce_JTable);
		produce_JTable.setShowVerticalLines(true);
	}
}
