package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;

public class NewAgentJPanel extends JPanel {
	private JTextField nameJText = new JTextField(15);
	private JTextField agentIdText = new JTextField(10);
	private JTextField phoneText = new JTextField(10);
	private JComboBox<String> areaText = new JComboBox<String>();
	private JTextArea addressText = new JTextArea(20, 3);
	private JTextArea messageArea = new JTextArea(20, 20);
	private Font myFont = new Font("华文细黑", Font.PLAIN, 30);
	private JButton confirm_JButton = new JButton("提交");
	private JButton reset_JButton = new JButton("重置");
	private Object[][] area_Data;

	public NewAgentJPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		area_Data = ConnectManagement.unionQuery("select area_id,area_name from Area", 2);
		for (int i = 0; i < area_Data.length; i++) {
			areaText.addItem((String) area_Data[i][1]);
		}
		/*-----------------------------定制组件-----------------------------*/
		confirm_JButton.setBounds(80, 500, 150, 50);
		confirm_JButton.setFont(GeneralTools.getButtonFont());

		reset_JButton.setFont(GeneralTools.getButtonFont());
		reset_JButton.setBounds(350, 500, 150, 50);
		messageArea.setBounds(650, 100, 450, 400);
		messageArea.setFont(myFont);
		messageArea.setLineWrap(true);
		addressText.setLineWrap(true);
		messageArea.setBorder(BorderFactory.createEtchedBorder());
		addressText.setBorder(BorderFactory.createEtchedBorder());
		nameJText.setBounds(150, 50, 250, 50);
		nameJText.setFont(myFont);

		phoneText.setBounds(150, 150, 200, 50);
		phoneText.setFont(myFont);

		areaText.setBounds(150, 250, 200, 50);
		areaText.setFont(myFont);

		addressText.setBounds(150, 350, 400, 100);
		addressText.setFont(myFont);

		/*-----------------------------添加组件-----------------------------*/
		this.add(nameJText);
		this.add(phoneText);
		this.add(areaText);
		this.add(addressText);
		this.add(messageArea);
		this.add(reset_JButton);
		this.add(confirm_JButton);
		/*---------------------------组件响应事件---------------------------*/

		/* 重置按钮事件 */
		reset_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nameJText.setText("");
				agentIdText.setText("");
				addressText.setText("");
				messageArea.setText("");
				phoneText.setText("");
			}
		});// 重置按钮事件

		/* 提交事件按钮 */
		confirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (nameJText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "经销商姓名不能为空", "错误", 0);
					return;
				}
				if (phoneText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "联系电话不能为空", "错误", 0);
					return;
				}
				/* 错的 */
				Object O[][] = ConnectManagement.generalQuery("select * from Agent ORDER BY agent_id DESC", "Agent");
				String aString = (String) O[0][0];
				String a = aString;
				String b = a.substring(0, 1);
				String c = a.substring(1, 6);
				int d = Integer.parseInt(c) + 1;
				String agentId_String = "A000" + d;
				/* 错的 */
				String SQL = "insert into Agent VALUES ('"+agentId_String+"','"+nameJText.getText()+"','"+phoneText.getText()+"','"+addressText.getText()+"','"+area_Data[areaText.getSelectedIndex()][0]+"','"+messageArea.getText()+"')";                                                          
				System.out.println(SQL);
				JOptionPane.showMessageDialog(null, "提交成功!", "提示", 2);
				nameJText.setText("");
				agentIdText.setText("");
				addressText.setText("");
				messageArea.setText("");
				phoneText.setText("");
			}
		});// 提交事件按钮

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setFont(myFont);
		g.setColor(Color.GRAY);
		g.drawString("姓名", 80, 87);
		g.drawString("电话", 80, 187);
		g.drawString("地区", 80, 287);
		g.drawString("地址", 80, 387);
		g.drawString("留言信息", 650, 80);
	}

}
