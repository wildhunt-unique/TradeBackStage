package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MainJPanel extends JPanel {
	/*各组件*/
	private TopMainJPanel mainTop_JPanel;
	private HomePageJPanel homePage_JPanel;
	private LeftMainJPanel leftMain_JPanel;
	
	private GoodsManage GoodsManage_JPanel;
	
	/*@author 丁星*/
	private IndentManage indentManage_JPanel;
	private ProduceManageJPanel produceManage_JPanel;
	private AgentManage agentManage_JPanel;
	/*-----------*/
	
	/*@author 王海涛 start 2017.7.7 -----------------*/
    private WareHouseManage warehouseManage_JPanel;
    /*------- 王海涛 end 2017.7.7 ------------------*/
	
	/**
	 * 重新实例该模块内的所有组件,并设置、添加
	 */
	public void configuringComponents() {
		/*---------------------实例组件-----------------------*/
		mainTop_JPanel = new TopMainJPanel();
		leftMain_JPanel = new LeftMainJPanel();
		homePage_JPanel = new HomePageJPanel();
		GoodsManage_JPanel = new GoodsManage();
		indentManage_JPanel = new IndentManage();
		produceManage_JPanel = new ProduceManageJPanel();
		agentManage_JPanel = new AgentManage();
		/*@author 王海涛 start 2017.7.7 -----------------*/
		//实例化仓库管理组件
        warehouseManage_JPanel = new WareHouseManage();
        /*------- 王海涛 end 2017.7.7 ------------------*/

		
		/*---------------------定制组件-----------------------*/
		leftMain_JPanel.setBounds(0, 100, 400, 680);
		homePage_JPanel.setBounds(400, 100, 1200, 680);
		mainTop_JPanel.setBounds(0, 0, 1600, 100);
		
		GoodsManage_JPanel.setBounds(400, 100, 1200, 680);
		indentManage_JPanel.setBounds(400, 100, 1200, 680);
		produceManage_JPanel.setBounds(400, 100, 1200, 680);
		agentManage_JPanel.setBounds(400, 100, 1200, 680);
		/*@author 王海涛 start 2017.7.7 -----------------*/
		//仓库管理窗口组件
        warehouseManage_JPanel.setBounds(400, 100, 1145, 680);
        /*------- 王海涛 end 2017.7.7 ------------------*/
		setListener();
		/*---------------------添加组件-----------------------*/
		this.add(mainTop_JPanel);
		this.add(leftMain_JPanel);
		this.add(homePage_JPanel);
	}
	
	/**
	 * 设置各种事件监听器6
	 */
	private void setListener() {
		
		/*进入商品管理模块*/
		homePage_JPanel.getTop_JPanel().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				homePage_JPanel.getTop_JPanel().inf.setVisible(false);
				homePage_JPanel.getTop_JPanel().setOpaque(false);
				removeAll();
				add(mainTop_JPanel);
				add(leftMain_JPanel);
				add(GoodsManage_JPanel);
				repaint();
			}
			
		});//end 进入商品管理模块
		
		/*进入订单管理模块*/
		homePage_JPanel.getCenter_JPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				homePage_JPanel.getCenter_JPanel().inf.setVisible(false);
				homePage_JPanel.getCenter_JPanel().setOpaque(false);
				removeAll();
				add(mainTop_JPanel);
				add(leftMain_JPanel);
				add(indentManage_JPanel);
				repaint();
			}
			
		});//end 进入订单管理模块
		
		/*首页按钮点击事件*/
		leftMain_JPanel.toHomePage_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeAll();
				add(mainTop_JPanel);
				add(leftMain_JPanel);
				add(homePage_JPanel);
				repaint();
			}
		});//首页按钮点击事件
		
		/*进入生产管理模块*/
		homePage_JPanel.getRight_JPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				homePage_JPanel.getRight_JPanel().inf.setVisible(false);
				homePage_JPanel.getRight_JPanel().setOpaque(false);
				removeAll();
				add(mainTop_JPanel);
				add(leftMain_JPanel);
				add(produceManage_JPanel);
				repaint();
			}
		});//end 进入生产管理模块
		
		  /**
		   * @author 王海涛 start 2017.7.7 -----------------*/
        /*进入库存管理模块*/
        homePage_JPanel.getLeft_JPanel().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // 进入仓库管理模块
                homePage_JPanel.getLeft_JPanel().inf.setVisible(false);
                homePage_JPanel.getLeft_JPanel().setOpaque(false);
                removeAll();
                add(mainTop_JPanel);
                add(leftMain_JPanel);
                add(warehouseManage_JPanel);
                repaint();
            }
        });
        /*------- 王海涛 end 2017.7.7 ------------------*/
        
    	/*进入经销商管理模块*/
		homePage_JPanel.getBottom_JPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				homePage_JPanel.getBottom_JPanel().inf.setVisible(false);
				homePage_JPanel.getBottom_JPanel().setOpaque(false);
				removeAll();
				add(mainTop_JPanel);
				add(leftMain_JPanel);
				add(agentManage_JPanel);
				repaint();
			}
		});//end 经销商管理模块
		
		/*商品信息快速传送门*/
		leftMain_JPanel.toGoodsInf_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				homePage_JPanel.getTop_JPanel().inf.setVisible(false);
				homePage_JPanel.getTop_JPanel().setOpaque(false);
				removeAll();
				add(mainTop_JPanel);
				add(leftMain_JPanel);
				add(GoodsManage_JPanel);
				GoodsManage_JPanel.setSelectedIndex(0);
				repaint();
			}
		});//商品信息快速传送门

		/*订单信息快速传送门*/
		leftMain_JPanel.toIndentInf_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				homePage_JPanel.getCenter_JPanel().inf.setVisible(false);
				homePage_JPanel.getCenter_JPanel().setOpaque(false);
				removeAll();
				add(mainTop_JPanel);
				add(leftMain_JPanel);
				add(indentManage_JPanel);
				indentManage_JPanel.setSelectedIndex(0);
				repaint();
			}
		});//订单信息快速传送门
		
		/*新建订单快速传送门*/
		leftMain_JPanel.toNewIndent_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				homePage_JPanel.getCenter_JPanel().inf.setVisible(false);
				homePage_JPanel.getCenter_JPanel().setOpaque(false);
				removeAll();
				add(mainTop_JPanel);
				add(leftMain_JPanel);
				add(indentManage_JPanel);
				indentManage_JPanel.setSelectedIndex(2);
				repaint();
			}
		});//新建订单快速传送门
		
		/*仓库信息快速传送门*/
		leftMain_JPanel.toWarehouse_JButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 homePage_JPanel.getLeft_JPanel().inf.setVisible(false);
	                homePage_JPanel.getLeft_JPanel().setOpaque(false);
	                removeAll();
	                add(mainTop_JPanel);
	                add(leftMain_JPanel);
	                add(warehouseManage_JPanel);
	                warehouseManage_JPanel.setSelectedIndex(0);
	                repaint();
			}
		});//仓库信息快速传送门
	}

	
	public MainJPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		configuringComponents();
	}
	
}
