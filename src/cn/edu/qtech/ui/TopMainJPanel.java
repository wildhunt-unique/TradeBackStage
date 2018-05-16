package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.edu.qtech.util.GeneralTools;

public class TopMainJPanel extends JPanel {
	private ImageIcon icon_log;
	private JLabel jlpic_log;
	private Image image;

	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		icon_log = new ImageIcon(GeneralTools.getRedSkinPtah() + "logo2.png");
		jlpic_log = new JLabel();
		setListener();
		jlpic_log.setBounds(20, 12, 80, 80);
		jlpic_log.setHorizontalAlignment(0);
		jlpic_log.setIcon(icon_log);
		this.add(jlpic_log);
	}

	/**
	 * 设置各种事件监听器
	 */
	private void setListener() {
	}

	public TopMainJPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		configuringComponents();
		image = Toolkit.getDefaultToolkit().getImage(GeneralTools.getRedSkinPtah() + "leftBackGround.jpg");
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
			g.drawImage(image, 0, 0, 1600, 1000, this);
			g.setColor(Color.DARK_GRAY);
			g.setFont(new Font("方正硬笔行书简体", Font.PLAIN, 50));
			g.drawString("德玛西亚商贸管理系统后台", 120, 65);
		}
		
	}
	
}
