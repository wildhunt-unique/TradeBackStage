package cn.edu.qtech.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

public class IndentManage extends JTabbedPane {
	private IndentInf indentInf_JPanel;
	private NewIndentJPanel indentNew_JPanel;
	private ShipmentJPanel shipment_JPanel;
	
	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		indentInf_JPanel = new IndentInf();
		indentNew_JPanel = new NewIndentJPanel();
		shipment_JPanel = new ShipmentJPanel();
		/*---------------------�������-----------------------*/

		setListener();
		/*---------------------������-----------------------*/
		this.addTab("������Ϣ", null, indentInf_JPanel, "�������Ľ���");
		this.addTab("������Ϣ", null, shipment_JPanel, "��ѯ�����ڳ����¼�Ľ���");
		this.addTab("�½�����", null, indentNew_JPanel, "�½������Ľ���");
	}// end configuringComponents()

	/**
	 * ���ø����¼�������
	 */
	private void setListener() {
		/*�½�������ť�¼�*/
		indentInf_JPanel.getNewIndent_Item().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setSelectedIndex(2);
			}
		});//end �½�������ť�¼�
	}// end setListener()

	public IndentManage() {
		// TODO Auto-generated constructor stub
		super(JTabbedPane.TOP);
		configuringComponents();
	}
}
