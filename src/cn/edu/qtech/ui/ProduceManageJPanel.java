package cn.edu.qtech.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ProduceManageJPanel extends JTabbedPane {
	
	private AddProduceRecord addProduceRecord_JPanel;
	private ProduceInfJPanel produceInf_JPanel;

	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		addProduceRecord_JPanel = new AddProduceRecord();
		produceInf_JPanel = new ProduceInfJPanel();
		/*---------------------�������-----------------------*/

		setListener();
		/*---------------------������-----------------------*/
		this.addTab("����������¼", null, addProduceRecord_JPanel, "�����µ�������¼����");
		this.addTab("�鿴������¼", null, produceInf_JPanel, "��ѯ������Ʒ�Ĳִ���Ϣ");
	}

	/**
	 * ���ø����¼�������
	 */
	private void setListener() {

	}

	public ProduceManageJPanel() {
		// TODO Auto-generated constructor stub
		super(JTabbedPane.TOP);
		configuringComponents();
	}
	
	public void setJComboBox(){
		addProduceRecord_JPanel.setGroupData();
		addProduceRecord_JPanel.setWarehouseData();
	}
}
