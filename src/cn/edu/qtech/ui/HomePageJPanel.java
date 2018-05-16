package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;

public class HomePageJPanel extends JPanel {

	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	private Image image;
	private topJPanel top_JPanel;
	private leftJPanel left_JPanel;
	private rightJPanel right_JPanel;
	private bottomJPanel bottom_JPanel;
	private cneterJPanel center_JPanel;

	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		top_JPanel = new topJPanel();
		left_JPanel = new leftJPanel();
		right_JPanel = new rightJPanel();
		bottom_JPanel = new bottomJPanel();
		center_JPanel = new cneterJPanel();
		image = Toolkit.getDefaultToolkit().getImage(GeneralTools.getRedSkinPtah() + "homePage.jpg");
		/*---------------------定制组件-----------------------*/
		top_JPanel.setBounds(501, 40, 199, 200);
		left_JPanel.setBounds(299, 241, 199, 199);
		right_JPanel.setBounds(701, 241, 199, 199);
		center_JPanel.setBounds(501, 241, 199, 199);
		bottom_JPanel.setBounds(501, 441, 199, 199);
		
		center_JPanel.setBackground(new Color(233,233,233,150));
		left_JPanel.setBackground(new Color(233,233,233,150));
		right_JPanel.setBackground(new Color(233,233,233,150));
		top_JPanel.setBackground(new Color(233,233,233,150));
		bottom_JPanel.setBackground(new Color(233,233,233,150));

		setListener();
		/*---------------------添加组件-----------------------*/
		this.add(bottom_JPanel);
		this.add(center_JPanel);
		this.add(left_JPanel);
		this.add(right_JPanel);
		this.add(top_JPanel);

	}

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {
		center_JPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				center_JPanel.inf.setVisible(true);
				center_JPanel.setOpaque(true);
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				center_JPanel.inf.setVisible(false);
				center_JPanel.setOpaque(false);
				repaint();
			}
		});
		
		top_JPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				top_JPanel.inf.setVisible(true);
				top_JPanel.setOpaque(true);
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				top_JPanel.inf.setVisible(false);
				top_JPanel.setOpaque(false);
				repaint();
			}
		});
		
		left_JPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				left_JPanel.inf.setVisible(true);
				left_JPanel.setOpaque(true);
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				left_JPanel.inf.setVisible(false);
				left_JPanel.setOpaque(false);
				repaint();
			}
		});
		
		right_JPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				right_JPanel.inf.setVisible(true);
				right_JPanel.setOpaque(true);
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				right_JPanel.inf.setVisible(false);
				right_JPanel.setOpaque(false);
				repaint();
			}
		});
		
		bottom_JPanel.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				bottom_JPanel.inf.setVisible(true);
				bottom_JPanel.setOpaque(true);
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				bottom_JPanel.inf.setVisible(false);
				bottom_JPanel.setOpaque(false);
				repaint();
			}
		});
		
	}

	public HomePageJPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		configuringComponents();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, 1400,2000, this);
		g.drawLine(500, 40, 500, 640);
		g.drawLine(700, 40, 700, 640);
		g.drawLine(300, 240, 900, 240);
		g.drawLine(300, 440, 900, 440);
	}
	
	/**
	 * 获得商品管理
	 * */
	public topJPanel getTop_JPanel() {
		return top_JPanel;
	}

	/**
	 * 获得仓储管理
	 * */
	public leftJPanel getLeft_JPanel() {
		return left_JPanel;
	}

	/**
	 * 获得生产管理
	 * */
	public rightJPanel getRight_JPanel() {
		return right_JPanel;
	}

	/**
	 * 获得经销商管理
	 * */
	public bottomJPanel getBottom_JPanel() {
		return bottom_JPanel;
	}

	/**
	 * 获得订单管理
	 * */
	public cneterJPanel getCenter_JPanel() {
		return center_JPanel;
	}
}





class cneterJPanel extends JPanel {
	private ImageIcon icon_log;
	private JLabel jlpic_log;
	private ImageIcon icon_inf;
	private JLabel jlpic_inf;
	public JLabel inf;
	
	public cneterJPanel() {
		this.setLayout(null);
		this.setOpaque(false);
		icon_log = new ImageIcon(GeneralTools.getRedSkinPtah() + "Oval.png");
		jlpic_log = new JLabel();
		icon_inf = new ImageIcon(GeneralTools.getRedSkinPtah() + "inf_cen.png");
		jlpic_inf = new JLabel();
		
		inf = new JLabel("订单管理");
		inf.setFont(GeneralTools.getTextFont());
		inf.setBounds(50, 100, 110, 50);
		this.add(inf);
		inf.setVisible(false);
		
		jlpic_log.setBounds(10, 10, 180, 180);
		jlpic_log.setHorizontalAlignment(0);
		jlpic_log.setIcon(icon_log);

		jlpic_inf.setBounds(60, 30, 80, 80);
		jlpic_inf.setHorizontalAlignment(0);
		jlpic_inf.setIcon(icon_inf);

		this.add(jlpic_log);
		this.add(jlpic_inf);
	}
}

class topJPanel extends JPanel {
	private ImageIcon icon_log;
	private JLabel jlpic_log;
	private ImageIcon icon_inf;
	private JLabel jlpic_inf;
	public JLabel inf;
	public topJPanel() {
		this.setOpaque(false);
		this.setLayout(null);
		icon_log = new ImageIcon(GeneralTools.getRedSkinPtah() + "Oval.png");
		jlpic_log = new JLabel();
		icon_inf = new ImageIcon(GeneralTools.getRedSkinPtah() + "inf_top.png");
		jlpic_inf = new JLabel();

		inf = new JLabel("商品管理");
		inf.setFont(GeneralTools.getTextFont());
		inf.setBounds(50, 100, 110, 50);
		this.add(inf);
		inf.setVisible(false);
		
		jlpic_log.setBounds(10, 10, 180, 180);
		jlpic_log.setHorizontalAlignment(0);
		jlpic_log.setIcon(icon_log);

		jlpic_inf.setBounds(60, 30, 80, 80);
		jlpic_inf.setHorizontalAlignment(0);
		jlpic_inf.setIcon(icon_inf);

		this.add(jlpic_log);
		this.add(jlpic_inf);
	}
}

class rightJPanel extends JPanel {
	private ImageIcon icon_log;
	private JLabel jlpic_log;
	private ImageIcon icon_inf;
	private JLabel jlpic_inf;
	public JLabel inf;
	public rightJPanel() {
		this.setOpaque(false);
		this.setLayout(null);
		icon_log = new ImageIcon(GeneralTools.getRedSkinPtah() + "Oval.png");
		jlpic_log = new JLabel();
		icon_inf = new ImageIcon(GeneralTools.getRedSkinPtah() + "inf_right.png");
		jlpic_inf = new JLabel();
		
		inf = new JLabel("生产管理");
		inf.setFont(GeneralTools.getTextFont());
		inf.setBounds(50, 100, 110, 50);
		this.add(inf);
		inf.setVisible(false);
		
		jlpic_log.setBounds(10, 10, 180, 180);
		jlpic_log.setHorizontalAlignment(0);
		jlpic_log.setIcon(icon_log);

		jlpic_inf.setBounds(60, 30, 80, 80);
		jlpic_inf.setHorizontalAlignment(0);
		jlpic_inf.setIcon(icon_inf);

		this.add(jlpic_log);
		this.add(jlpic_inf);
	}
}

class leftJPanel extends JPanel {
	private ImageIcon icon_log;
	private JLabel jlpic_log;
	private ImageIcon icon_inf;
	private JLabel jlpic_inf;
	public JLabel inf;
	
	public leftJPanel() {
		this.setOpaque(false);
		this.setLayout(null);
		icon_log = new ImageIcon(GeneralTools.getRedSkinPtah() + "Oval.png");
		jlpic_log = new JLabel();
		icon_inf = new ImageIcon(GeneralTools.getRedSkinPtah() + "inf_left.png");
		jlpic_inf = new JLabel();

		inf = new JLabel("仓储管理");
		inf.setFont(GeneralTools.getTextFont());
		inf.setBounds(50, 100, 110, 50);
		this.add(inf);
		inf.setVisible(false);
		
		jlpic_log.setBounds(10, 10, 180, 180);
		jlpic_log.setHorizontalAlignment(0);
		jlpic_log.setIcon(icon_log);

		jlpic_inf.setBounds(60, 30, 80, 80);
		jlpic_inf.setHorizontalAlignment(0);
		jlpic_inf.setIcon(icon_inf);

		this.add(jlpic_log);
		this.add(jlpic_inf);
	}
}

class bottomJPanel extends JPanel {
	private ImageIcon icon_log;
	private JLabel jlpic_log;
	private ImageIcon icon_inf;
	private JLabel jlpic_inf;
	public JLabel inf;
	
	public bottomJPanel() {
		this.setOpaque(false);
		this.setLayout(null);
		icon_log = new ImageIcon(GeneralTools.getRedSkinPtah() + "Oval.png");
		jlpic_log = new JLabel();
		icon_inf = new ImageIcon(GeneralTools.getRedSkinPtah() + "inf_bottom.png");
		jlpic_inf = new JLabel();

		inf = new JLabel("经销管理");
		inf.setFont(GeneralTools.getTextFont());
		inf.setBounds(50, 100, 110, 50);
		this.add(inf);
		inf.setVisible(false);
		
		jlpic_log.setBounds(10, 10, 180, 180);
		jlpic_log.setHorizontalAlignment(0);
		jlpic_log.setIcon(icon_log);

		jlpic_inf.setBounds(60, 30, 80, 80);
		jlpic_inf.setHorizontalAlignment(0);
		jlpic_inf.setIcon(icon_inf);

		this.add(jlpic_log);
		this.add(jlpic_inf);
			
	}
	
	
}

