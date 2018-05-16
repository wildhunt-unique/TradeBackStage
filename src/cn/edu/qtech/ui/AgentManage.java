package cn.edu.qtech.ui;

import javax.swing.JTabbedPane;

public class AgentManage extends JTabbedPane{
	private AgentInf agentInf_JPanel;
	private NewAgentJPanel newAgent_JPanel;
	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		agentInf_JPanel = new AgentInf();
		newAgent_JPanel = new NewAgentJPanel();
		
		/*---------------------定制组件-----------------------*/
		
		setListener();
		/*---------------------添加组件-----------------------*/
		this.addTab("经销商信息管理", null, agentInf_JPanel, "进行经销商信息的查询、修改、增加删除");
		this.addTab("新建经销商信息", null, newAgent_JPanel, "进行经销商信息增加");
	}// end configuringComponents()
	
	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {

	}//end setListener()

	
	public AgentManage() {
		// TODO Auto-generated constructor stub
		super(JTabbedPane.TOP);
		configuringComponents();
	}
}
