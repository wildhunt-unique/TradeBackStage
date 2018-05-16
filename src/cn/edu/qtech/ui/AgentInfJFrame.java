package cn.edu.qtech.ui;

import java.awt.Color;
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

public class AgentInfJFrame extends JFrame {
	person_Inf_Panel main_JPanel;
	
	
	public AgentInfJFrame(String idnentId_String){
		this.setResizable(false);
		this.setTitle("经销商信息");
		int Dwidth = 1200;
		int Dheight = 700;
		this.setSize(Dwidth, Dheight);
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - Dwidth) / 2, (height - Dheight) / 2);
		
		main_JPanel = new person_Inf_Panel(idnentId_String);
		this.add(main_JPanel);
		this.setVisible(true);
	}
}
class person_Inf_Panel extends JPanel {

	private JTextField nameJText = new JTextField(15);
	private JTextField agentIdText = new JTextField(10);
	private JTextField phoneText = new JTextField(10);
	private JTextField areaText = new JTextField(10);
	private JTextArea addressText = new JTextArea(20, 3);
	private JTextArea messageArea = new JTextArea(20, 20);
	private Font myFont = new Font("华文细黑", Font.PLAIN, 30);
	private boolean Visiable = false;
	private JButton editInf = new JButton("编辑");
	private JButton saveInf = new JButton("保存");
	private String personInfData[];
	private String getPersonInfData[] = { "", "", "", "" };
	private String idnentId_String;

	public void resetPersonInf() {
		personInfData = ConnectManagement.getAgentInf(idnentId_String);
		agentIdText.setText(personInfData[0]);
		nameJText.setText(personInfData[1]);
		phoneText.setText(personInfData[2]);
		addressText.setText(personInfData[3]);
		messageArea.setText(personInfData[5]);
		areaText.setText(personInfData[7]);
	}

	public void getPersonInf() {
		getPersonInfData[0] = nameJText.getText();
		getPersonInfData[1] = phoneText.getText();
		getPersonInfData[2] = addressText.getText();
		getPersonInfData[3] = messageArea.getText();
	}

	public person_Inf_Panel(String idnentId_String) {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		this.idnentId_String = idnentId_String;
		/*-----------------------------定制组件-----------------------------*/

		messageArea.setBounds(650, 100, 450, 400);
		messageArea.setEditable(false);
		messageArea.setFont(myFont);
		messageArea.setLineWrap(true);
		addressText.setLineWrap(true);
		messageArea.setOpaque(Visiable);

		nameJText.setBounds(150, 50, 300, 50);
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

		editInf.setFont(myFont);
		editInf.setBounds(870, 550, 100, 50);

		saveInf.setFont(myFont);
		saveInf.setBounds(1020, 550, 100, 50);
		saveInf.setVisible(false);

		resetPersonInf();

		/*-----------------------------添加组件-----------------------------*/
		this.add(nameJText);
		this.add(agentIdText);
		this.add(phoneText);
		this.add(areaText);
		this.add(addressText);
		this.add(messageArea);
		this.add(editInf);
		this.add(saveInf);
		/*---------------------------组件响应事件---------------------------*/
		editInf.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Visiable = !Visiable;
				/* 设置可见 */
				repaint();
				saveInf.setVisible(true);
				editInf.setVisible(false);

				nameJText.setOpaque(Visiable);
				nameJText.setEditable(Visiable);

				agentIdText.setOpaque(Visiable);

				phoneText.setOpaque(Visiable);
				phoneText.setEditable(Visiable);

				areaText.setOpaque(Visiable);

				addressText.setOpaque(Visiable);
				addressText.setEditable(Visiable);

				messageArea.setOpaque(Visiable);
				messageArea.setEditable(Visiable);
			}
		});

		saveInf.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Visiable = !Visiable;
				super.mouseClicked(e);

				/* 设置可见 */
				editInf.setVisible(true);
				saveInf.setVisible(false);

				nameJText.setOpaque(Visiable);
				nameJText.setEditable(Visiable);

				agentIdText.setOpaque(Visiable);

				phoneText.setOpaque(Visiable);
				phoneText.setEditable(Visiable);

				areaText.setOpaque(Visiable);

				addressText.setOpaque(Visiable);
				addressText.setEditable(Visiable);

				messageArea.setOpaque(Visiable);
				messageArea.setEditable(Visiable);

				/* 数据同步到数据库 */
				getPersonInf();// 先获得文本框内的数据
				ConnectManagement.syncPersonInf(getPersonInfData,agentIdText.getText());// 同步数据

				repaint();
			}
		});
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
		if (!Visiable) {
			g.drawLine(80, 120, 600, 120);
			g.drawLine(80, 220, 600, 220);
			g.drawLine(80, 320, 600, 320);
			g.drawLine(80, 420, 600, 420);
			g.drawLine(600, 50, 600, 520);
			g.drawLine(600, 90, 1100, 90);
		}
	}

}
