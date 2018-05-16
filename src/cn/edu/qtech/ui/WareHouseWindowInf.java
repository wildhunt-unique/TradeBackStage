package cn.edu.qtech.ui;

import javax.swing.*;
import java.awt.*;

import cn.edu.qtech.util.TableModelUnEdit;
import cn.edu.qtech.util.GeneralTools;

import static cn.edu.qtech.util.ConnectManagement.wareHouseWindow_query;

/**
 * Created by Tao on 7/9/2017.
 */
public class WareHouseWindowInf extends JFrame {
    //组件
    private JPanel mainPanel;
    private JTable show_table;
    private JScrollPane show_table_scroll;
    private JLabel show_ware_information_1, show_ware_information_2;
    private JLabel show_table_information_1, show_table_information_2;
    //字体
    private Font font1 = new Font("Monospace", Font.BOLD, 25);
    //表格组件
    private String[] column_names = {"商品编号", "商品名", "种类", "规格", "保质期", "包装方式", "仓储数量"};
    private String[][] table_data = null;
    //变量
    private String warehouse_id = null;
    private String warehouse_name = null;
    private String wareHouse_total_goods_amount = null;

    //构造函数
    public WareHouseWindowInf() {
        //默认构造函数
        this("");
    }

    public WareHouseWindowInf(String warehouse_id) {
        this.setTitle("仓库 " + warehouse_id + " 详情");
        this.warehouse_id = warehouse_id;
        initWindow();
    }

    private void initWindow() {
        //界面构造函数
        this.setSize(1500, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //组件实例化
        mainPanel = new JPanel(null);
        show_table = new JTable();
        show_ware_information_1 = new JLabel("仓库编号:");
        show_ware_information_2 = new JLabel("仓库名:");
        show_table_information_1 = new JLabel("商品种类数:");
        show_table_information_2 = new JLabel("商品数量:");
        //表格实例化
        init_show_Table(); //构造表格组件
        show_table_scroll = new JScrollPane(show_table);
        //组件参数设置
        //添加边框
//        show_ware_information_1.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
//        show_ware_information_2.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
//        show_table_information_1.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
//        show_table_information_2.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
        //滚动条设置
        show_table_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //字体设置
        show_table.setFont(font1);
        show_ware_information_1.setFont(font1);
        show_ware_information_2.setFont(font1);
        show_table_information_1.setFont(font1);
        show_table_information_2.setFont(font1);
        //界面布局
        show_table_scroll.setBounds(20, 50, 1410, 585);
        show_ware_information_1.setBounds(20, 0, 220, 50);
        show_ware_information_2.setBounds(240, 0, 320, 50);
        show_table_information_1.setBounds(880, 635, 285, 50);
        show_table_information_2.setBounds(1165, 635, 265, 50);
        //组件添加
        mainPanel.add(show_table_scroll);
        mainPanel.add(show_ware_information_1);
        mainPanel.add(show_ware_information_2);
        mainPanel.add(show_table_information_1);
        mainPanel.add(show_table_information_2);
        this.add(mainPanel);
        //查询数据库
        search_information();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void setWarehouse_variable(String warehouse_name, String warehouse_total_goods_amount) {
        //设置仓库名变量
        this.warehouse_name = warehouse_name;
        this.wareHouse_total_goods_amount = warehouse_total_goods_amount;
    }

    public void refreshInformation() {
        //刷新数量统计信息
        show_ware_information_1.setText("仓库编号:" + warehouse_id);
        show_ware_information_2.setText("仓库名:" + warehouse_name);
        show_table_information_1.setText("商品种类数:" + show_table.getRowCount());
        show_table_information_2.setText("商品数量:" + wareHouse_total_goods_amount);
    }

    // 数据库查询函数
    private void search_information() {
        //查询数据库函数
        try {
            table_data = wareHouseWindow_query(this.warehouse_id);
        } catch (Exception exception) {
            System.out.println("查询仓库_全部模块数据库错误");
            System.out.print(exception.getMessage());
        }
        //重绘表格
        init_show_Table();
    }

    //界面相应函数
    private void init_show_Table() {
        //表格初始化函数
        show_table.setModel(new TableModelUnEdit(table_data, column_names));
        show_table.setRowHeight(50);
        show_table.getColumnModel().getColumn(0).setPreferredWidth(150);
        show_table.getColumnModel().getColumn(1).setPreferredWidth(400);
        show_table.getColumnModel().getColumn(2).setPreferredWidth(250);
        show_table.getColumnModel().getColumn(3).setPreferredWidth(200);
        show_table.getColumnModel().getColumn(4).setPreferredWidth(125);
        show_table.getColumnModel().getColumn(5).setPreferredWidth(125);
        show_table.getColumnModel().getColumn(6).setPreferredWidth(160);
        show_table.setFillsViewportHeight(true);
        GeneralTools.makeFace(show_table);
        show_table.setShowVerticalLines(true);
    }
}
