package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.JxlWriteDemo;
import cn.edu.qtech.util.TableModelUnEdit;
import cn.edu.qtech.util.getDay;
import jxl.write.WriteException;

public class IndentDetailJFrame extends JFrame {
	private JPanel main_JPanel;
	private JScrollPane sellInf_JScrollPane;
	private JTable sellInf_JTable;
	private Object[][] data;
	private String indentId_String;
	private JButton writeToXls_JButton;

	private JLabel indentInf_JLabel;
	private JLabel stateInf_JLabel;
	private JLabel payStateInf_JLabel;
	private JLabel totalAmount_JLabel;
	private JLabel totalPrices_JLabel;

	private final Font ButtonFont = GeneralTools.getButtonFont();
	private final String[] columnNames = { "��Ʒ��", "��Ʒ��", "����", "����", "��װ���", "����", "��װ��ʽ" };
	private getDay date = new getDay();

	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		main_JPanel = new JPanel();
		sellInf_JTable = new JTable();
		sellInf_JTable.setModel(new TableModelUnEdit(data, columnNames));
		sellInf_JScrollPane = new JScrollPane(sellInf_JTable);
		writeToXls_JButton = new JButton("����ΪExcel��");
		createJLables();

		/*---------------------�������-----------------------*/
		main_JPanel.setLayout(null);
		sellInf_JScrollPane.setBounds(10, 60, 1210, 500);
		indentInf_JLabel.setBounds(10, 0, 400, 50);
		stateInf_JLabel.setBounds(1000, 0, 200, 50);
		totalAmount_JLabel.setBounds(10, 570, 300, 50);
		totalPrices_JLabel.setBounds(480, 570, 400, 50);
		payStateInf_JLabel.setBounds(750, 570, 200, 50);
		writeToXls_JButton.setBounds(1000, 570, 200, 50);

		indentInf_JLabel.setFont(ButtonFont);
		stateInf_JLabel.setFont(ButtonFont);
		totalAmount_JLabel.setFont(ButtonFont);
		totalPrices_JLabel.setFont(ButtonFont);
		payStateInf_JLabel.setFont(ButtonFont);
		writeToXls_JButton.setFont(ButtonFont);

		setTable();
		setListener();
		/*---------------------������-----------------------*/
		this.add(main_JPanel);

		main_JPanel.add(indentInf_JLabel);
		main_JPanel.add(payStateInf_JLabel);
		main_JPanel.add(stateInf_JLabel);
		main_JPanel.add(totalPrices_JLabel);
		main_JPanel.add(totalAmount_JLabel);
		main_JPanel.add(writeToXls_JButton);
		main_JPanel.add(sellInf_JScrollPane);

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
				String fileName = indentId_String + "�Ŷ�����Ϣ-" + date.getUnFormatDate();

				try {
					JxlWriteDemo xlsOut = new JxlWriteDemo(fileName, data);
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
	}// end setListener()

	public IndentDetailJFrame(String indentId_String) {
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
		Object[][] indentData = ConnectManagement
				.generalQuery("select * from Indent where Indent.indent_id = '" + indentId_String + "'", "Indent");

		int length = data.length;
		int Amount_Array[] = new int[length];
		float price_Array[] = new float[length];
		int totalAmount_int = 0;
		float totalPrice_float = 0;
		String state_String = (String) indentData[0][2];
		String pyaState_String = (String) indentData[0][3];

		for (int i = 0; i < length; i++) {
			Amount_Array[i] = Integer.parseInt((String) data[i][2]);
			if (!state_String.equals("δ�ӵ�")) {
				price_Array[i] = Float.parseFloat((String) data[i][3]);
			}
		}
		for (int i = 0; i < length; i++) {
			totalAmount_int = totalAmount_int + Amount_Array[i];
			if (!state_String.equals("δ�ӵ�")) {
				totalPrice_float = totalPrice_float + (Amount_Array[i] * price_Array[i]);
			}
		}
		totalAmount_JLabel = new JLabel("�ܹ���" + totalAmount_int + "����Ʒ");
		if (!state_String.equals("δ�ӵ�")) {
			totalPrices_JLabel = new JLabel("�ܼۣ�" + totalPrice_float);
		} else {
			totalPrices_JLabel = new JLabel("�ܼۣ�");
		}
		stateInf_JLabel = new JLabel("����״̬��" + state_String);
		payStateInf_JLabel = new JLabel("֧��״̬��" + pyaState_String);
		indentInf_JLabel = new JLabel("������    " + indentId_String + "   ���飺");
	}
}
