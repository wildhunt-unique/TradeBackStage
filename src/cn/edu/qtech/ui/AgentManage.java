package cn.edu.qtech.ui;

import javax.swing.JTabbedPane;

public class AgentManage extends JTabbedPane{
	private AgentInf agentInf_JPanel;
	private NewAgentJPanel newAgent_JPanel;
	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		agentInf_JPanel = new AgentInf();
		newAgent_JPanel = new NewAgentJPanel();
		
		/*---------------------�������-----------------------*/
		
		setListener();
		/*---------------------������-----------------------*/
		this.addTab("��������Ϣ����", null, agentInf_JPanel, "���о�������Ϣ�Ĳ�ѯ���޸ġ�����ɾ��");
		this.addTab("�½���������Ϣ", null, newAgent_JPanel, "���о�������Ϣ����");
	}// end configuringComponents()
	
	/**
	 * ���ø����¼�������
	 */
	private void setListener() {

	}//end setListener()

	
	public AgentManage() {
		// TODO Auto-generated constructor stub
		super(JTabbedPane.TOP);
		configuringComponents();
	}
}
