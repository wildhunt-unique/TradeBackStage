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
	private final String[] columnNames = { "��Ʒ��", "��Ʒ��", "����", "����", "��װ���", "����", "��װ��ʽ" };

	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		main_JPanel = new JPanel();
		sellInf_JTable = new JTable();
		sellInf_JTable.setModel(new TableModelUnEdit(data, columnNames));
		sellInf_JScrollPane = new JScrollPane(sellInf_JTable);
		createJLables();

		confirm_JButton = new JButton("�ύ");
		cancel_JButton = new JButton("ȡ��");
		pricing_JButton = new JButton("����");

		/*---------------------�������-----------------------*/
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
		/*---------------------������-----------------------*/
		this.add(main_JPanel);

		main_JPanel.add(indentInf_JLabel);
		main_JPanel.add(totalAmount_JLabel);
		main_JPanel.add(cancel_JButton);
		main_JPanel.add(pricing_JButton);
		main_JPanel.add(confirm_JButton);
		main_JPanel.add(sellInf_JScrollPane);

	}// end configuringComponents()

	/**
	 * ���ø����¼�������
	 */
	private void setListener() {
		/* ȡ����ť�¼� */
		cancel_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();// �رմ���
			}
		});// ȡ����ť�¼�

		/* ���۰�ť�¼� */
		pricing_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sellInf_JTable.getSelectedRowCount()==0){
					JOptionPane.showMessageDialog(null, "��δѡ����Ʒ", "����", 0);
					for (int i = 0; i < data.length; i++) {//�жϻ���û��û���۵�
						if(data[i][3]==null){
							sellInf_JTable.setRowSelectionInterval(i, i);
							return;
						}
					}
					return;
				}
				int choosePos_int = sellInf_JTable.getSelectedRow();
				String price_String = JOptionPane.showInputDialog(null, "��Ʒ�۸�", "������Ʒ�۸�", 2);
				if (!GeneralTools.isNum(price_String)) {
					JOptionPane.showMessageDialog(null, "����������", "����", 0);
					return;
				}
				data[choosePos_int][3] = price_String;
				sellInf_JTable.setModel(new TableModelUnEdit(data, columnNames));
				setTable();
				for (int i = 0; i < data.length; i++) {//�жϻ���û��û���۵�
					if(data[i][3]==null){
						sellInf_JTable.setRowSelectionInterval(i, i);
						return;
					}
				}
			}
		});// ���۰�ť�¼�

		/* �ύ�¼���ť */
		confirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < data.length; i++) {//�жϻ���û��û���۵�
					if(data[i][3]==null){
						JOptionPane.showMessageDialog(null, "��Ʒ��"+data[i][1]+"��δ����", "����", 0);
						sellInf_JTable.setRowSelectionInterval(i, i);
						return;
					}
				}
				for (int i = 0; i < data.length; i++) {//�жϻ���û��û���۵�
					String updateSQL = "update Sell set price = '"+data[i][3]+"' where indent_id ='"+indentId_String+"' and goods_id ='"+data[i][0]+"';";
					ConnectManagement.generalUpdate(updateSQL);
					String updateDemandSQL = "update Goods set demand_amount = demand_amount + "+data[i][2]+" where goods_id = '"+data[i][0]+"';";
					ConnectManagement.generalUpdate(updateDemandSQL);
				}
				String updateIndentSQL = "update Indent set state = '�ѽӵ�' where indent_id = '"+indentId_String+"';";
				ConnectManagement.generalUpdate(updateIndentSQL);
				JOptionPane.showMessageDialog(null, "�ύ�ɹ�!", "��ʾ", 2);
				dispose();// �رմ���
			}
		});// �ύ�¼���ť

	}// end setListener()

	public OrderIndentJFrame(String indentId_String) {
		// TODO Auto-generated constructor stub
		super();
		this.indentId_String = indentId_String;
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle("��������");
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
	 * ���ñ����ʽ
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
		totalAmount_JLabel = new JLabel("�ܹ���" + totalAmount_int + "����Ʒ");
		indentInf_JLabel = new JLabel("������    " + indentId_String + "   ���飺");
	}
}
