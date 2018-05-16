package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;

/**
 * 后台端界面窗口
 * 
 * @author 丁星
 */
public class MainFrame extends JFrame {

	private LoginPanel login_Panel;
	private MainJPanel main_JPanel;
	private JMenuBar mainMenu_MenuBar;
	private JMenu account_JMenu;
	private JMenuItem logout_Item;
	private Font menuItemFont = GeneralTools.getTextFont();
	private Color menuColor = new Color(233,233,233);
	private String whiteString="            ";
	
//	private ArrayList<JComponent> itemSet = new ArrayList<JComponent>();
//		for (int i = 0; i < itemSet.size(); i++) {
//			itemSet.get(i).setFont(menuItemFont);
//		}
//		itemSet.add(logout_Item);
//		itemSet.add(account_JMenu);
	
	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		login_Panel = new LoginPanel();
		mainMenu_MenuBar = new JMenuBar();
		account_JMenu = new JMenu("账户");
		logout_Item = new JMenuItem("注销"+whiteString);
		
		
		/*---------------------定制组件-----------------------*/
		
		logout_Item.setFont(menuItemFont);
		account_JMenu.setFont(menuItemFont);
		
		
		mainMenu_MenuBar.setBackground(menuColor);
		mainMenu_MenuBar.setVisible(false);
		
		account_JMenu.setBackground(menuColor);
		
		setListener();
		
		/*---------------------添加组件-----------------------*/
		this.setJMenuBar(mainMenu_MenuBar);
		this.add(login_Panel);
		mainMenu_MenuBar.add(account_JMenu);
		account_JMenu.add(logout_Item);
		
	}// end configuringComponents()

	/**
	 * 设置各种事件监听器
	 */
	public void setListener() {

		/* 登陆按钮的登陆事件 */
		login_Panel.getLoginConfrim_JButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String account_String = login_Panel.getAccount();
				String password_String = login_Panel.getPassword();
				boolean isLogin = ConnectManagement.accountConfirm(account_String, password_String);
				if (isLogin) {
					enterMainJPanel();
				} else {
					JOptionPane.showMessageDialog(login_Panel, ConnectManagement.getWarningMessage(), "错误", 0);// 0=叉 1.圆形i 2=三角形i 3=实心圆i
				}
			}
		});// end 登陆按钮的登陆事件
		
		/*菜单栏注销事件*/
		logout_Item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				enterLoginPanel();
			}
		});//end 菜单栏注销事件

	}// end setListener()

	/* 构造函数 */
	public MainFrame() {
		// TODO Auto-generated constructor stub
		configuringComponents();
		this.setTitle("管理系统后台端");
		this.setSize(1600, 900);
		this.setResizable(false);

		this.addWindowListener(new WindowAdapter() {
			// 窗口结束事件
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});// end 窗口结束事件

		/* 设置窗口居中 */
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 1600) / 2, (height - 900) / 2);
	}

	/**
	 * 进入主界面
	 */
	public void enterMainJPanel() {
		this.setVisible(false);
		mainMenu_MenuBar.setVisible(true);
		this.remove(login_Panel);
		main_JPanel = new MainJPanel();
		this.add(main_JPanel);
		this.setVisible(true);
	}
	
	/**
	 * 进入登陆面
	 */
	public void enterLoginPanel() {
		this.setVisible(false);
		mainMenu_MenuBar.setVisible(false);
		this.remove(main_JPanel);
		login_Panel.clearPassword();
		this.add(login_Panel);
		this.setVisible(true);
	}
	
}
