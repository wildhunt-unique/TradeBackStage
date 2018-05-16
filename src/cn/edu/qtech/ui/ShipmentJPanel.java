package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.JxlWriteDemo;
import cn.edu.qtech.util.TableModelUnEdit;
import cn.edu.qtech.util.getDay;
import jxl.write.WriteException;


public class ShipmentJPanel extends JPanel {
	private JLabel indentId_JLabel;
	private JTextField indentId_JTextField;
	private JButton confirm_JButton;
	private JLabel amount_JLabel;
	private JButton writeToXls_JButton;
	
	private String indent_id;
	private JScrollPane exWarehouse_JScrollPane;
	private JTable exWarehouse_JTable;
	private Font buttonFont = GeneralTools.getButtonFont();
	private final String[] exColumnNames = { "��Ʒ��", "��Ʒ��", "�ֿ��", "�ֿ���", "��������" };
	private Object[][] exInf_Data;
	private String query_String = "select Goods.goods_id,Goods.goods_name,Warehouse.warehouse_id,Warehouse.warehouse_name,EXwarehouse.EX_amount "
			+ "from EXwarehouse,Warehouse,Goods " + "where EXwarehouse.warehouse_id = Warehouse.warehouse_id AND EXwarehouse.goods_id = Goods.goods_id AND indent_id = ";
	private getDay date = new getDay();
	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		indentId_JLabel = new JLabel("�����ţ�",JLabel.CENTER);
		indentId_JTextField = new JTextField(10);
		confirm_JButton = new JButton("��ѯ");
		exWarehouse_JTable = new JTable();
		exWarehouse_JTable.setModel(new TableModelUnEdit(null, exColumnNames));
		exWarehouse_JScrollPane = new JScrollPane(exWarehouse_JTable);
		amount_JLabel = new JLabel("��¼������");
		writeToXls_JButton = new JButton("����ΪExcel��");
		/*---------------------�������-----------------------*/
		indentId_JLabel.setBounds(10, 10, 150, 50);
		indentId_JTextField.setBounds(160, 10, 200, 50);
		confirm_JButton.setBounds(360, 10, 150, 50);
		exWarehouse_JScrollPane.setBounds(10, 60, 1120, 500);
		amount_JLabel.setBounds(10, 560, 200, 50);
		writeToXls_JButton.setBounds(920, 560, 200, 50);
		
		indentId_JLabel.setFont(buttonFont);
		indentId_JTextField.setFont(buttonFont);
		confirm_JButton.setFont(buttonFont);
		amount_JLabel.setFont(buttonFont);
		writeToXls_JButton.setFont(buttonFont);
		setListener();
		/*---------------------������-----------------------*/
		this.add(indentId_JLabel);
		this.add(indentId_JTextField);
		this.add(confirm_JButton);
		this.add(exWarehouse_JScrollPane);
		this.add(amount_JLabel);
		this.add(writeToXls_JButton);
	}// end configuringComponents()
	
	/**
	 * ���ø����¼�������
	 */
	private void setListener() {
		/* ����ΪExcel���¼� */
		writeToXls_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(  exInf_Data == null || exInf_Data.length==0){
					JOptionPane.showMessageDialog(null, "������,����ʧ��!", "����", 0);
					return;
				}
				String fileName = indentId_JTextField.getText() + "�ų�����Ϣ-" + date.getUnFormatDate();

				try {
					JxlWriteDemo xlsOut = new JxlWriteDemo(fileName, exInf_Data);
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
		});// ����ΪExcel���¼�
		
		confirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String SQL = query_String+"'"+indentId_JTextField.getText()+"'";
				exInf_Data = ConnectManagement.unionQuery(SQL, 5);
				exWarehouse_JTable.setModel(new TableModelUnEdit(exInf_Data, exColumnNames));
				if(+exInf_Data.length == 0){
					JOptionPane.showMessageDialog(null, "�޴˶����ĳ����¼", "����", 0);
					return;
				}
				amount_JLabel.setText("��¼������"+exInf_Data.length);
				setTable();
			}
		});
	}//end setListener()

	
	public ShipmentJPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		configuringComponents();
	}
	private void setTable(){
		exWarehouse_JTable.setSelectionMode(0);
		exWarehouse_JTable.setRowHeight(40);
		exWarehouse_JTable.setFont(GeneralTools.getTableFont());
		exWarehouse_JTable.setShowVerticalLines(true);
		GeneralTools.makeFace(exWarehouse_JTable);
	}
}
