package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.edu.qtech.util.ConnectManagement;

public class AgentInfDialog extends JFrame {
	AgentInfJPanel main_JPanel;

	public AgentInfDialog(String agent_id) {
		this.setResizable(false);
		this.setTitle("经销商信息");
		int Dwidth = 1200;
		int Dheight = 700;
		this.setSize(Dwidth, Dheight);
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - Dwidth) / 2, (height - Dheight) / 2);

		main_JPanel = new AgentInfJPanel(agent_id);
		this.add(main_JPanel);
		this.setVisible(true);
	}
}

class AgentInfJPanel extends JPanel {

	private JTextField nameJText = new JTextField(15);
	private JTextField agentIdText = new JTextField(10);
	private JTextField phoneText = new JTextField(10);
	private JTextField areaText = new JTextField(10);
	private JTextArea addressText = new JTextArea(20, 3);
	private JTextArea messageArea = new JTextArea(20, 20);
	private Font myFont = new Font("华文细黑", Font.PLAIN, 30);
	private boolean Visiable = false;
	private String personInfData[];
	private String agent_id;

	public void resetPersonInf() {
		personInfData = ConnectManagement.getAgentInf(agent_id);
		agentIdText.setText(personInfData[0]);
		nameJText.setText(personInfData[1]);
		phoneText.setText(personInfData[2]);
		addressText.setText(personInfData[3]);
		messageArea.setText(personInfData[5]);
		areaText.setText(personInfData[7]);
	}

	public AgentInfJPanel(String agent_id) {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		this.agent_id = agent_id;
		/*-----------------------------定制组件-----------------------------*/

		messageArea.setBounds(650, 100, 450, 400);
		messageArea.setEditable(false);
		messageArea.setFont(myFont);
		messageArea.setLineWrap(true);
		addressText.setLineWrap(true);
		messageArea.setOpaque(Visiable);

		nameJText.setBounds(150, 50, 250, 50);
		nameJText.setEditable(false);
		nameJText.setFont(myFont);
		nameJText.setOpaque(Visiable);

		agentIdText.setBounds(150, 150, 200, 50);
		agentIdText.setEditable(false);
		agentIdText.setFont(myFont);
		agentIdText.setOpaque(Visiable);

		phoneText.setBounds(150, 250, 200, 50);
		phoneText.setEditable(false);
		phoneText.setFont(myFont);
		phoneText.setOpaque(Visiable);

		areaText.setBounds(150, 350, 200, 50);
		areaText.setEditable(false);
		areaText.setFont(myFont);
		areaText.setOpaque(Visiable);

		addressText.setBounds(150, 450, 400, 100);
		addressText.setEditable(false);
		addressText.setFont(myFont);
		addressText.setOpaque(Visiable);

		resetPersonInf();

		/*-----------------------------添加组件-----------------------------*/
		this.add(nameJText);
		this.add(agentIdText);
		this.add(phoneText);
		this.add(areaText);
		this.add(addressText);
		this.add(messageArea);
		/*---------------------------组件响应事件---------------------------*/
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setFont(myFont);
		g.setColor(Color.GRAY);
		g.drawString("姓名", 80, 87);
		g.drawString("编号", 80, 187);
		g.drawString("电话", 80, 287);
		g.drawString("地区", 80, 387);
		g.drawString("地址", 80, 487);
		g.drawString("留言信息", 650, 80);

		g.drawLine(80, 120, 600, 120);
		g.drawLine(80, 220, 600, 220);
		g.drawLine(80, 320, 600, 320);
		g.drawLine(80, 420, 600, 420);
		g.drawLine(600, 50, 600, 550);
		g.drawLine(600, 90, 1100, 90);

	}
}
