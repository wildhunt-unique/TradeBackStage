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
	private Font myFont = new Font("����ϸ��", Font.PLAIN, 30);
	private JButton confirm_JButton = new JButton("�ύ");
	private JButton reset_JButton = new JButton("����");
	private Object[][] area_Data;

	public NewAgentJPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		area_Data = ConnectManagement.unionQuery("select area_id,area_name from Area", 2);
		for (int i = 0; i < area_Data.length; i++) {
			areaText.addItem((String) area_Data[i][1]);
		}
		/*-----------------------------�������-----------------------------*/
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

		/*-----------------------------������-----------------------------*/
		this.add(nameJText);
		this.add(phoneText);
		this.add(areaText);
		this.add(addressText);
		this.add(messageArea);
		this.add(reset_JButton);
		this.add(confirm_JButton);
		/*---------------------------�����Ӧ�¼�---------------------------*/

		/* ���ð�ť�¼� */
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
		});// ���ð�ť�¼�

		/* �ύ�¼���ť */
		confirm_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (nameJText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��������������Ϊ��", "����", 0);
					return;
				}
				if (phoneText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "��ϵ�绰����Ϊ��", "����", 0);
					return;
				}
				/* ��� */
				Object O[][] = ConnectManagement.generalQuery("select * from Agent ORDER BY agent_id DESC", "Agent");
				String aString = (String) O[0][0];
				String a = aString;
				String b = a.substring(0, 1);
				String c = a.substring(1, 6);
				int d = Integer.parseInt(c) + 1;
				String agentId_String = "A000" + d;
				/* ��� */
				String SQL = "insert into Agent VALUES ('"+agentId_String+"','"+nameJText.getText()+"','"+phoneText.getText()+"','"+addressText.getText()+"','"+area_Data[areaText.getSelectedIndex()][0]+"','"+messageArea.getText()+"')";                                                          
				System.out.println(SQL);
				JOptionPane.showMessageDialog(null, "�ύ�ɹ�!", "��ʾ", 2);
				nameJText.setText("");
				agentIdText.setText("");
				addressText.setText("");
				messageArea.setText("");
				phoneText.setText("");
			}
		});// �ύ�¼���ť

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setFont(myFont);
		g.setColor(Color.GRAY);
		g.drawString("����", 80, 87);
		g.drawString("�绰", 80, 187);
		g.drawString("����", 80, 287);
		g.drawString("��ַ", 80, 387);
		g.drawString("������Ϣ", 650, 80);
	}

}
