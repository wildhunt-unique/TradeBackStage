package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;

public class EditGoodsJFrame extends JFrame {
	private JPanel panel = new JPanel();

	private JTextField goodsName_JTextField;
	private JTextField goodsType_JTextField;
	private JTextField norms_JTextField;
	private JTextField reserveDate_JTextField;
	private JTextField pack_JTextField;

	private JLabel goodsName_JLabel;
	private JLabel goodsType_JLabel;
	private JLabel norms__JLabel;
	private JLabel reserveDate__JLabel;
	private JLabel pack__JLabel;
	private JLabel title__JLabel;

	private JButton confrim_JButton;
	private JButton cancel_JButton;

	private Font textFont = GeneralTools.getTextFont();
	private String editSQL_String = "update Goods set ";
	private String goodsID_String;
	private Object[][] data;

	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		goodsName_JTextField = new JTextField(15);
		goodsType_JTextField = new JTextField(15);
		norms_JTextField = new JTextField(15);
		reserveDate_JTextField = new JTextField(15);
		pack_JTextField = new JTextField(15);

		goodsName_JLabel = new JLabel("��Ʒ����");
		goodsType_JLabel = new JLabel("��Ʒ����");
		norms__JLabel = new JLabel("��װ���");
		reserveDate__JLabel = new JLabel("    ������");
		pack__JLabel = new JLabel("��װ��ʽ");
		title__JLabel = new JLabel("�޸���Ʒ");
		confrim_JButton = new JButton("����");
		cancel_JButton = new JButton("ȡ��");

		/*---------------------�������-----------------------*/
		panel.setLayout(null);

		goodsName_JLabel.setBounds(50, 100, 100, 50);
		goodsName_JTextField.setBounds(180, 100, 300, 50);

		goodsType_JLabel.setBounds(50, 200, 100, 50);
		goodsType_JTextField.setBounds(180, 200, 300, 50);
		goodsType_JLabel.setToolTipText("��Ʒ������,����:̼�����ϡ���֭���衢����Ʒ�����ô���ˮ��");

		title__JLabel.setBounds(180, 0, 200, 70);

		norms__JLabel.setBounds(50, 300, 100, 50);
		norms_JTextField.setBounds(180, 300, 300, 50);
		norms_JTextField.setToolTipText("������� 250ml*25");

		reserveDate__JLabel.setBounds(50, 400, 100, 50);
		reserveDate_JTextField.setBounds(180, 400, 300, 50);

		pack__JLabel.setBounds(50, 500, 100, 50);
		pack_JTextField.setBounds(180, 500, 300, 50);

		confrim_JButton.setBounds(50, 600, 200, 50);
		cancel_JButton.setBounds(280, 600, 200, 50);

		title__JLabel.setFont(new Font("΢���ź�", Font.BOLD, 50));
		goodsName_JLabel.setFont(textFont);
		goodsName_JTextField.setFont(textFont);
		goodsType_JLabel.setFont(textFont);
		goodsType_JTextField.setFont(textFont);
		norms_JTextField.setFont(textFont);
		norms__JLabel.setFont(textFont);
		reserveDate_JTextField.setFont(textFont);
		reserveDate__JLabel.setFont(textFont);
		pack_JTextField.setFont(textFont);
		pack__JLabel.setFont(textFont);
		confrim_JButton.setFont(GeneralTools.getButtonFont());
		cancel_JButton.setFont(GeneralTools.getButtonFont());

		goodsName_JTextField.setText((String) data[0][1]);
		goodsType_JTextField.setText((String) data[0][2]);
		norms_JTextField.setText((String) data[0][3]);
		reserveDate_JTextField.setText((String) data[0][4]);
		pack_JTextField.setText((String) data[0][5]);

		setListener();
		/*---------------------������-----------------------*/
		panel.add(goodsName_JLabel);
		panel.add(goodsName_JTextField);
		panel.add(goodsType_JLabel);
		panel.add(goodsType_JTextField);
		panel.add(norms_JTextField);
		panel.add(norms__JLabel);
		panel.add(pack_JTextField);
		panel.add(pack__JLabel);
		panel.add(reserveDate_JTextField);
		panel.add(reserveDate__JLabel);
		panel.add(cancel_JButton);
		panel.add(confrim_JButton);
		panel.add(title__JLabel);
		this.add(panel);
	}// end configuringComponents()

	/**
	 * ���ø����¼�������
	 */
	private void setListener() {
		/* ȷ����ť�¼� */
		confrim_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String reserveDate_String = reserveDate_JTextField.getText();
				if (!GeneralTools.isNum(reserveDate_String)) {
					JOptionPane.showMessageDialog(getOwner(), "����������", "����������Ƿ�", 0);
					return;
				}
				String goodsName_String = goodsName_JTextField.getText();
				String goodsType_String = goodsType_JTextField.getText();
				String norms_String = norms_JTextField.getText();
				String pack_String = pack_JTextField.getText();

				if (goodsName_String.equals("")) {
					JOptionPane.showMessageDialog(getOwner(), "��Ʒ������Ϊ��", "����Ƿ�", 0);
					return;
				}
				if (goodsType_String.equals("")) {
					JOptionPane.showMessageDialog(getOwner(), "��Ʒ���಻��Ϊ��", "����Ƿ�", 0);
					return;
				}
				if (norms_String.equals("")) {
					JOptionPane.showMessageDialog(getOwner(), "��װ�����Ϊ��", "����Ƿ�", 0);
					return;
				}
				if (reserveDate_String.equals("")) {
					JOptionPane.showMessageDialog(getOwner(), "�����ڲ���Ϊ��", "����Ƿ�", 0);
					return;
				}
				if (pack_String.equals("")) {
					JOptionPane.showMessageDialog(getOwner(), "��װ���ʽ����Ϊ��", "����Ƿ�", 0);
					return;
				}
				editSQL_String = editSQL_String +"goods_name = '"
						+ goodsName_String + "',goods_type ='" + goodsType_String + "',norms = '" + norms_String
						+ "',reserve_date ='" +reserveDate_String+ "',pack = '" + pack_String + "' where goods_id = '" + goodsID_String + "'";
				ConnectManagement.generalUpdate(editSQL_String);
				JOptionPane.showMessageDialog(getOwner(), "�޸ĳɹ���", "ȷ��", 1);
				dispose();
				
			}
		});// ȷ����ť�¼�

		/* ȡ����ť�¼� */
		cancel_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});// end ȡ����ť

	}// end setListener()

	public EditGoodsJFrame(String goodsID_String) {
		// this.setAlwaysOnTop(true);
		this.goodsID_String = goodsID_String;
		data = ConnectManagement.generalQuery("select * from Goods where goods_id ='" + goodsID_String + "'", "Goods");
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle("����������Ʒ��Ϣ��Ϣ");
		int Dwidth = 600;
		int Dheight = 800;
		this.setSize(Dwidth, Dheight);
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - Dwidth) / 2, (height - Dheight) / 2);

		configuringComponents();
	}
}
