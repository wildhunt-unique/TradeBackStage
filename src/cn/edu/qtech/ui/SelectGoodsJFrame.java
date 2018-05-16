package cn.edu.qtech.ui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.checked_Goods;

public class SelectGoodsJFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel diagloJPanel = new JPanel();

	private ArrayList<checked_Goods> checkedGoods = new ArrayList<checked_Goods>();
	private String goodsInfData[][];
	private JTable goodsInfTable;
	private Font myFont = GeneralTools.getButtonFont();
	private String[] columnNames = { "商品编号", "商品名", "种类", "包装规格", "保质期", "包装方式" };
	private JComboBox<String> Condition = new JComboBox<String>();
	private JScrollPane scrollPane;
	private JTextField conditionKey = new JTextField();
	private JButton searchConfirm = new JButton("查询");
	private String[] searchSQL = { "", "where goods_name like '%", "where goods_id ='", "where goods_type like '%",
			"where pack like '%" };
	private String SQL;
	

	/**
	 * 是否选中了行
	 * 
	 * @return 选中了true,没有选中 false
	 * @author 丁星
	 */

	public boolean isChecked() {
		if (goodsInfTable.getSelectedRowCount() == 0) {
			return false;
		}
		return true;
	}

	public JPanel getDiagloJPanel() {
		return diagloJPanel;
	}

	public String[][] getGoodsInfData() {
		return goodsInfData;
	}

	public JTable getGoodsInfTable() {
		return goodsInfTable;
	}

	public JTable getPanelQute() {
		return goodsInfTable;
	}

	public ArrayList<checked_Goods> getArraylist() {
		return checkedGoods;
		// for(int i = 0;i<checkedGoods.size();i++){
		// System.out.println(checkedGoods.get(i));
		// }
	}

	public void createArraylist(int amount) {
		checkedGoods.clear();
		for (int i : goodsInfTable.getSelectedRows()) {
			checkedGoods.add(new checked_Goods(goodsInfData[i][1], goodsInfData[i][2], goodsInfData[i][3],
					goodsInfData[i][5], amount, goodsInfData[i][0]));
		}
	}

	public void setTable() {
		goodsInfTable.setRowHeight(50);
		goodsInfTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		goodsInfTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		goodsInfTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		goodsInfTable.getColumnModel().getColumn(4).setPreferredWidth(30);
		goodsInfTable.setFont(GeneralTools.getTableFont());
		GeneralTools.makeFace(goodsInfTable);
		goodsInfTable.setShowVerticalLines(true);
	}

	public SelectGoodsJFrame() {
		// TODO Auto-generated constructor stub
		try {
			goodsInfData = ConnectManagement.getGoodsInf("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setResizable(false);
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		this.setTitle("插入货物信息");
		int Dwidth = 1000;
		int Dheight = 800;
		this.setSize(Dwidth, Dheight);
		int width, height;
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width - Dwidth) / 2, (height - Dheight) / 2);

		/*---------------------------定制组件------------------------*/
		diagloJPanel.setLayout(null);
		goodsInfTable = new JTable(goodsInfData, columnNames);
		setTable();
		
		scrollPane = new JScrollPane(goodsInfTable);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		Condition.addItem("查询所有");
		Condition.addItem("按名称查询");
		Condition.addItem("按商品号查询");
		Condition.addItem("按种类查询");
		Condition.addItem("按包装方式查询");

		Condition.setFont(myFont);
		searchConfirm.setFont(myFont);
		conditionKey.setFont(myFont);

		// 1000 800
		diagloJPanel.setBounds(0, 0, Dwidth, Dheight);
		Condition.setBounds(50, 0, 250, 50);
		conditionKey.setBounds(300, 0, 450, 50);
		searchConfirm.setBounds(750, 0, 150, 50);
		scrollPane.setBounds(50, 50, 850, 600);
		

		/*---------------------------添加组件------------------------*/
		this.add(diagloJPanel);
		diagloJPanel.add(scrollPane);
		diagloJPanel.add(Condition);
		diagloJPanel.add(conditionKey);
		diagloJPanel.add(searchConfirm);
		

		/*----------------------------组件事件监听器----------------------*/
		searchConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				int pos = Condition.getSelectedIndex();
				String key = conditionKey.getText();
				if (pos == 0) {
					SQL = searchSQL[pos];
				} else if (pos == 1) {
					SQL = searchSQL[pos] + key + "%'";
				} else if (pos == 2) {
					SQL = searchSQL[pos] + key + "'";
				} else if (pos == 3) {
					SQL = searchSQL[pos] + key + "%'";
				} else if (pos == 4) {
					SQL = searchSQL[pos] + key + "%'";
				}
				try {
					goodsInfData = ConnectManagement.getGoodsInf(SQL);
				} catch (Exception e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}
				goodsInfTable.setModel(new DefaultTableModel(goodsInfData, columnNames));
				setTable();

			}

		});
	}
}
