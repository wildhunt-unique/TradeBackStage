package cn.edu.qtech.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GoodsManage extends JTabbedPane {
	// private
	private GoodsInf GoodsInf_JPanel;
	
	private GoodsInventoryJPanel inventory_JPanel;
	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		GoodsInf_JPanel = new GoodsInf();
		inventory_JPanel = new GoodsInventoryJPanel();
		/*---------------------�������-----------------------*/

		setListener();
		/*---------------------������-----------------------*/
		this.addTab("��Ʒ����", null, GoodsInf_JPanel, "������Ʒ�Ĳ�ѯ���޸ġ�����ɾ��");
		this.addTab("�ִ���Ϣ", null, inventory_JPanel, "��ѯ������Ʒ�Ĳִ���Ϣ");
	}

	/**
	 * ���ø����¼�������
	 */
	private void setListener() {

	}

	public GoodsManage() {
		// TODO Auto-generated constructor stub
		super(JTabbedPane.TOP);
		configuringComponents();
	}
}
