package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import cn.edu.qtech.util.GeneralTools;

public class LeftMainJPanel extends JPanel{
	
	private Image image;
	public JButton toHomePage_JButton;
	public JButton toIndentInf_JButton;
	public JButton toNewIndent_JButton;
	public JButton toGoodsInf_JButton;
	public JButton toWarehouse_JButton;
	private JLabel title_JLabel;
	private Font buttonFont = GeneralTools.getButtonFont();
	
	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*组件实例*/
		
		/*组件定制*/
		toHomePage_JButton = new JButton("回到首页", new ImageIcon(GeneralTools.getRedSkinPtah() + "homepage.png"));
		toIndentInf_JButton = new JButton("订单信息", new ImageIcon(GeneralTools.getRedSkinPtah() + "indent.png"));
		toNewIndent_JButton = new JButton("新建订单", new ImageIcon(GeneralTools.getRedSkinPtah() + "bigNew.png"));
		toWarehouse_JButton = new JButton("仓库信息", new ImageIcon(GeneralTools.getRedSkinPtah() + "house.png"));
		toGoodsInf_JButton = new JButton("商品信息", new ImageIcon(GeneralTools.getRedSkinPtah() + "goods.png"));
		title_JLabel = new JLabel("快速传送门");
		
		toHomePage_JButton.setBounds(50, 100, 300, 70);
		toIndentInf_JButton.setBounds(50, 200, 300, 70);
		toNewIndent_JButton.setBounds(50, 300, 300, 70);
		toGoodsInf_JButton.setBounds(50, 400, 300, 70);
		toWarehouse_JButton.setBounds(50, 500, 300, 70);
		title_JLabel.setBounds(20, 0, 300, 100);
		
		toHomePage_JButton.setFont(buttonFont);
		toIndentInf_JButton.setFont(buttonFont);
		toNewIndent_JButton.setFont(buttonFont);
		toGoodsInf_JButton.setFont(buttonFont);
		toWarehouse_JButton.setFont(buttonFont);
		title_JLabel.setFont(new Font("华文隶书",Font.BOLD,40));
		setListener();
		/*组件添加*/
		this.add(toHomePage_JButton);
		this.add(toIndentInf_JButton);
		this.add(toNewIndent_JButton);
		this.add(toGoodsInf_JButton);
		this.add(toWarehouse_JButton);
		this.add(title_JLabel);
	}
	
	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {
		
	}

	
	public LeftMainJPanel() {
		// TODO Auto-generated constructor stub
		super();
		configuringComponents();
		this.setLayout(null);
		image = Toolkit.getDefaultToolkit().getImage(GeneralTools.getRedSkinPtah() + "homePage.jpg");
		this.setBackground(new Color(255,255,255,150));
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, 1024,955, this);
	}
}
