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
 * ��̨�˽��洰��
 * 
 * @author ����
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
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		login_Panel = new LoginPanel();
		mainMenu_MenuBar = new JMenuBar();
		account_JMenu = new JMenu("�˻�");
		logout_Item = new JMenuItem("ע��"+whiteString);
		
		
		/*---------------------�������-----------------------*/
		
		logout_Item.setFont(menuItemFont);
		account_JMenu.setFont(menuItemFont);
		
		
		mainMenu_MenuBar.setBackground(menuColor);
		mainMenu_MenuBar.setVisible(false);
		
		account_JMenu.setBackground(menuColor);
		
		setListener();
		
		/*---------------------������-----------------------*/
		this.setJMenuBar(mainMenu_MenuBar);
		this.add(login_Panel);
		mainMenu_MenuBar.add(account_JMenu);
		account_JMenu.add(logout_Item);
		
	}// end configuringComponents()

	/**
	 * ���ø����¼�������
	 */
	public void setListener() {

		/* ��½��ť�ĵ�½�¼� */
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
					JOptionPane.showMessageDialog(login_Panel, ConnectManagement.getWarningMessage(), "����", 0);// 0=�� 1.Բ��i 2=������i 3=ʵ��Բi
				}
			}
		});// end ��½��ť�ĵ�½�¼�
		
		/*�˵���ע���¼�*/
		logout_Item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				enterLoginPanel();
			}
		});//end �˵���ע���¼�

	}// end setListener()

	/* ���캯�� */
	public MainFrame() {
		// TODO Auto-generated constructor stub
		configuringComponents();
		this.setTitle("����ϵͳ��̨��");
		this.setSize(1600, 900);
		this.setResizable(false);

		this.addWindowListener(new WindowAdapter() {
			// ���ڽ����¼�
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});// end ���ڽ����¼�

		/* ���ô��ھ��� */
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - 1600) / 2, (height - 900) / 2);
	}

	/**
	 * ����������
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
	 * �����½��
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
