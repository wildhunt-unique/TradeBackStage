package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.qtech.util.GeneralTools;

/**
 * <br>
 * 登陆面板
 * 
 * @author 丁星
 */
public class LoginPanel extends JPanel {/* 登陆界面面板 */

	private Image image;
	private LoginPanelsubmodule submodule_JPanel;// 登陆子模块
	private JButton loginConfrim_JButton;
	private ImageIcon icon_log;
	private JLabel jlpic_log;

	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		submodule_JPanel = new LoginPanelsubmodule();
		loginConfrim_JButton = new JButton("登陆");
		icon_log = new ImageIcon(GeneralTools.getRedSkinPtah() + "fohoologo.png");
		jlpic_log = new JLabel();
		
		/*---------------------定制组件-----------------------*/
		jlpic_log.setBounds(100, 200, 500,500);
		jlpic_log.setHorizontalAlignment(0);
		jlpic_log.setIcon(icon_log);
		this.add(jlpic_log);
		
		submodule_JPanel.setBounds(1000, 150, 500, 600);
		loginConfrim_JButton.setBounds(50, 500, 400, 70);
		
		
		submodule_JPanel.setBackground(new Color(231, 237, 242, 200));
		loginConfrim_JButton.setFont(GeneralTools.getButtonFont());

		/*---------------------添加组件-----------------------*/
		this.add(submodule_JPanel);
		submodule_JPanel.add(loginConfrim_JButton);

	}// end configuringComponents()

	public LoginPanel() {
		super();
		this.setLayout(null);
		image = Toolkit.getDefaultToolkit().getImage(GeneralTools.getRedSkinPtah() + "BackGroundImage1.png");
		configuringComponents();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		if (image != null) {
			int height = image.getHeight(this);
			int width = image.getWidth(this);

			if (height != -1 && height > getHeight())
				height = getHeight();

			if (width != -1 && width > getWidth())
				width = getWidth();

			int x = (int) (((double) (getWidth() - width)) / 2.0);
			int y = (int) (((double) (getHeight() - height)) / 2.0);
			g.drawImage(image, x, y, width, height, this);
		}
		g.setColor(Color.white);
		g.setFont(new Font("方正硬笔行书简体", Font.PLAIN, 80));
		g.setColor(Color.white);
		g.drawString("德玛西亚商贸管理系统", 400, 100);
		g.setColor(Color.white);
		g.drawLine(800, 150, 800, 750);
	}

	public JButton getLoginConfrim_JButton() {
		// TODO Auto-generated method stub
		return loginConfrim_JButton;
	}
	
	public String getAccount() {
		return submodule_JPanel.getAccount();
	}

	public String getPassword() {
		return submodule_JPanel.getPassword();
	}

	public void clearPassword() {
		submodule_JPanel.clearPassword();
	}
	
}

/**
 * <br>
 * 登陆面板子模块
 * 
 * @author 丁星
 */
class LoginPanelsubmodule extends JPanel {

	private JPasswordField loginPassword;
	private JLabel JLable_Password;
	private JLabel JLable_account;
	private Font myFont = GeneralTools.getButtonFont();

	private JTextField loginAccount;

	private ImageIcon icon_lc;
	private JLabel jlpic_lc;

	private ImageIcon icon_ac;
	private JLabel jlpic_ac;

	private ImageIcon icon_inf;
	private JLabel jlpic_inf;

	private ImageIcon icon_inf_2;
	private JLabel jlpic_inf_2;

	private ImageIcon icon_log;
	private JLabel jlpic_log;

	
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		loginPassword = new JPasswordField(10);
		JLable_Password = new JLabel("密  码");
		JLable_account = new JLabel("账  户");

		loginAccount = new JTextField(10);
		icon_lc = new ImageIcon(GeneralTools.getRedSkinPtah() + "lock.png");
		jlpic_lc = new JLabel();

		icon_ac = new ImageIcon(GeneralTools.getRedSkinPtah() + "ac.png");
		jlpic_ac = new JLabel();

		icon_inf = new ImageIcon(GeneralTools.getRedSkinPtah() + "inf.png");
		jlpic_inf = new JLabel();

		icon_inf_2 = new ImageIcon(GeneralTools.getRedSkinPtah() + "inf.png");
		jlpic_inf_2 = new JLabel();

		icon_log = new ImageIcon(GeneralTools.getRedSkinPtah() + "log.png");
		jlpic_log = new JLabel();
		/*---------------------定制组件-----------------------*/

		JLable_Password.setBounds(210, 320, 100, 100);
		JLable_Password.setFont(myFont);

		JLable_account.setBounds(210, 200, 100, 100);
		JLable_account.setFont(myFont);

		loginPassword.setBounds(100, 400, 300, 50);
		loginPassword.setOpaque(false);
		loginPassword.setBackground(new Color(231, 237, 242, 255));
		loginPassword.setHorizontalAlignment(JTextField.CENTER);
		loginPassword.setFont(myFont);

		loginAccount.setBounds(100, 280, 300, 50);
		loginAccount.setHorizontalAlignment(JTextField.CENTER);
		loginAccount.setFont(myFont);
		loginAccount.setOpaque(false);
		loginAccount.setBackground(new Color(231, 237, 242, 255));

		jlpic_lc.setBounds(45, 400, 50, 50);
		jlpic_lc.setHorizontalAlignment(0);
		jlpic_lc.setIcon(icon_lc);

		jlpic_log.setBounds(120, 0, 250, 250);
		jlpic_log.setHorizontalAlignment(0);
		jlpic_log.setIcon(icon_log);

		jlpic_ac.setBounds(45, 280, 50, 50);
		jlpic_ac.setHorizontalAlignment(0);
		jlpic_ac.setIcon(icon_ac);

		jlpic_inf.setBounds(405, 280, 50, 50);
		jlpic_inf.setHorizontalAlignment(0);
		jlpic_inf.setIcon(icon_inf);

		jlpic_inf_2.setBounds(405, 400, 50, 50);
		jlpic_inf_2.setHorizontalAlignment(0);
		jlpic_inf_2.setIcon(icon_inf_2);
		/*---------------------添加组件-----------------------*/
		this.add(jlpic_log);
		this.add(loginPassword);
		this.add(JLable_Password);
		this.add(JLable_account);
		this.add(jlpic_lc);
		this.add(loginAccount);
		this.add(jlpic_ac);
		this.add(jlpic_inf);
		this.add(jlpic_inf_2);
	}// end configuringComponents()
	
	public LoginPanelsubmodule() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		this.setOpaque(false);
		configuringComponents();

		// this.add(JLable_Login);

		jlpic_inf.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});

		jlpic_inf_2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		jlpic_inf_2.setToolTipText("系统管理员专用密码");
		jlpic_inf.setToolTipText("系统管理员专属账号");
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setFont(new Font("楷体", Font.BOLD, 50));
		g.setColor(Color.DARK_GRAY);
		g.drawLine(45, 455, 455, 455);
		g.drawLine(45, 335, 455, 335);
	}

	public String getAccount() {
		return loginAccount.getText();
	}

	public String getPassword() {
		return loginPassword.getText();
	}

	public void clearPassword() {
		loginPassword.setText("");
	}
}