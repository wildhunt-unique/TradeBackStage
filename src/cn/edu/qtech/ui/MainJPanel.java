package cn.edu.qtech.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MainJPanel extends JPanel {
	/*�����*/
	private TopMainJPanel mainTop_JPanel;
	private HomePageJPanel homePage_JPanel;
	private LeftMainJPanel leftMain_JPanel;
	
	private GoodsManage GoodsManage_JPanel;
	
	/*@author ����*/
	private IndentManage indentManage_JPanel;
	private ProduceManageJPanel produceManage_JPanel;
	private AgentManage agentManage_JPanel;
	/*-----------*/
	
	/*@author ������ start 2017.7.7 -----------------*/
    private WareHouseManage warehouseManage_JPanel;
    /*------- ������ end 2017.7.7 ------------------*/
	
	/**
	 * ����ʵ����ģ���ڵ��������,�����á����
	 */
	public void configuringComponents() {
		/*---------------------ʵ�����-----------------------*/
		mainTop_JPanel = new TopMainJPanel();
		leftMain_JPanel = new LeftMainJPanel();
		homePage_JPanel = new HomePageJPanel();
		GoodsManage_JPanel = new GoodsManage();
		indentManage_JPanel = new IndentManage();
		produceManage_JPanel = new ProduceManageJPanel();
		agentManage_JPanel = new AgentManage();
		/*@author ������ start 2017.7.7 -----------------*/
		//ʵ�����ֿ�������
        warehouseManage_JPanel = new WareHouseManage();
        /*------- ������ end 2017.7.7 ------------------*/

		
		/*---------------------�������-----------------------*/
		leftMain_JPanel.setBounds(0, 100, 400, 680);
		homePage_JPanel.setBounds(400, 100, 1200, 680);
		mainTop_JPanel.setBounds(0, 0, 1600, 100);
		
		GoodsManage_JPanel.setBounds(400, 100, 1200, 680);
		indentManage_JPanel.setBounds(400, 100, 1200, 680);
		produceManage_JPanel.setBounds(400, 100, 1200, 680);
		agentManage_JPanel.setBounds(400, 100, 1200, 680);
		/*@author ������ start 2017.7.7 -----------------*/
		//�ֿ���������
        warehouseManage_JPanel.setBounds(400, 100, 1145, 680);
        /*------- ������ end 2017.7.7 ------------------*/
		setListener();
		/*---------------------������-----------------------*/
		this.add(mainTop_JPanel);
		this.add(leftMain_JPanel);
		this.add(homePage_JPanel);
	}
	
	/**
	 * ���ø����¼�������6
	 */
	private void setListener() {
		
		/*������Ʒ����ģ��*/
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
			
		});//end ������Ʒ����ģ��
		
		/*���붩������ģ��*/
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
			
		});//end ���붩������ģ��
		
		/*��ҳ��ť����¼�*/
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
		});//��ҳ��ť����¼�
		
		/*������������ģ��*/
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
		});//end ������������ģ��
		
		  /**
		   * @author ������ start 2017.7.7 -----------------*/
        /*���������ģ��*/
        homePage_JPanel.getLeft_JPanel().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // ����ֿ����ģ��
                homePage_JPanel.getLeft_JPanel().inf.setVisible(false);
                homePage_JPanel.getLeft_JPanel().setOpaque(false);
                removeAll();
                add(mainTop_JPanel);
                add(leftMain_JPanel);
                add(warehouseManage_JPanel);
                repaint();
            }
        });
        /*------- ������ end 2017.7.7 ------------------*/
        
    	/*���뾭���̹���ģ��*/
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
		});//end �����̹���ģ��
		
		/*��Ʒ��Ϣ���ٴ�����*/
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
		});//��Ʒ��Ϣ���ٴ�����

		/*������Ϣ���ٴ�����*/
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
		});//������Ϣ���ٴ�����
		
		/*�½��������ٴ�����*/
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
		});//�½��������ٴ�����
		
		/*�ֿ���Ϣ���ٴ�����*/
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
		});//�ֿ���Ϣ���ٴ�����
	}

	
	public MainJPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(null);
		configuringComponents();
	}
	
}
