package cn.edu.qtech.ui;

import javax.swing.*;
import java.awt.event.*;

import cn.edu.qtech.util.GeneralTools;
import cn.edu.qtech.util.TableModelUnEdit;
import cn.edu.qtech.util.ConnectManagement;

/**
 * Created by Tao on 7/7/2017.
 */
public class WareHouseJPanel extends JPanel {
    //仓库信息管理组件
    /*组件*/
    //窗口组件
    private JTable show_table;
    private JTextField search_key;
    private JButton search_button;
    private JComboBox<String> search_select;
    private JScrollPane show_table_scroll;
    private JLabel show_table_information_1, show_table_information_2;

    //表格变量
    private String[] column_names = {"仓库编号", "仓库名", "仓库地址", "储存商品数量"};
    private String[][] table_data = null;
    //组件内容变量
    private String[] selectString = {"查询所有", "按仓库编号查询", "按仓库名查询", "按仓库地址查询"};

    /*方法*/
    public WareHouseJPanel() {
        //默认构造函数
        //组件实例化
        search_select = new JComboBox<>();
        search_key = new JTextField("查询所有");
        search_button = new JButton("查询");
        show_table = new JTable();
        init_show_Table();  //构造表格
        show_table_scroll = new JScrollPane(show_table);
        show_table_information_1 = new JLabel("仓库数量:");
        show_table_information_2 = new JLabel("选中仓库编号:");
        //组件参数设置
        //设置字体
        search_select.setFont(GeneralTools.getButtonFont());
        search_key.setFont(GeneralTools.getButtonFont());
        search_button.setFont(GeneralTools.getButtonFont());
        show_table.setFont(GeneralTools.getTableFont());
        show_table_information_1.setFont(GeneralTools.getTextFont());
        show_table_information_2.setFont(GeneralTools.getTextFont());
        //搜索条件添加
        for (String select : selectString) {
            search_select.addItem(select);
        }
        search_key.setVisible(false);
        //添加边框
//        show_table_information_1.setBorder(BorderFactory.createLineBorder(Color.blue,1));
//        show_table_information_2.setBorder(BorderFactory.createLineBorder(Color.blue,1));
        //组件布局设置
        this.setLayout(null);
        search_select.setBounds(20, 20, 300, 50);
        search_key.setBounds(340, 20, 615, 50);
        search_button.setBounds(975, 20, 150, 50);
        show_table_scroll.setBounds(20, 90, 1105, 495);
        show_table_information_1.setBounds(650, 580, 200, 50);
        show_table_information_2.setBounds(850, 580, 250, 50);
        //组件添加
        this.add(search_select);
        this.add(search_key);
        this.add(search_button);
        this.add(show_table_scroll);
        this.add(show_table_information_1);
        this.add(show_table_information_2);
        //添加事件监听器
        addSearchSelectListener();  /*搜索选择框事件监听器*/
        addSearchKeyListener(); /*搜索框事件监听器*/
        addSearchButtonListener();  /*添加搜索按钮事件监听器*/
        addShow_tableListener(); /*添加表格事件监听器*/
        //初始化表格数据
        queryAll();
        init_show_Table();
        //初始化显示显示信息
        refreshShowInformation();
    }

    private void init_show_Table() {
        //表格初始化函数
        show_table.setModel(new TableModelUnEdit(table_data, column_names));
        show_table.setRowHeight(50);
        //设置表格样式
        show_table.getColumnModel().getColumn(0).setPreferredWidth(180);
        show_table.getColumnModel().getColumn(1).setPreferredWidth(355);
        show_table.getColumnModel().getColumn(2).setPreferredWidth(570);
        show_table.getColumnModel().getColumn(3).setPreferredWidth(200);
        show_table.setFillsViewportHeight(true);
        GeneralTools.makeFace(show_table);
        show_table.setShowVerticalLines(true);
    }

    private void addSearchKeyListener() {
        search_key.addFocusListener(
                new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        super.focusGained(e);
                        search_key.setText("");
                    }
                }
        );
    }

    private void addSearchSelectListener() {
        search_select.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            search_key.setText("");
                            if (search_select.getSelectedIndex() == 0) {
                                search_key.setVisible(false);
                                search_button.setText("查询所有");
                                search_key.setText("查询所有");
                            } else {
                                search_key.setVisible(true);
                                switch (search_select.getSelectedIndex()) {
                                    case 1:
                                        //查询编号
                                        search_button.setText("查询编号");
                                        search_key.setText("请输入查询仓库编号");
                                        break;
                                    case 2:
                                        //查询名字
                                        search_button.setText("查询名字");
                                        search_key.setText("请输入查询仓库名");
                                        break;
                                    case 3:
                                        //查询地址
                                        search_button.setText("查询地址");
                                        search_key.setText("请输入查询仓库地址");
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
        );
    }

    private void addSearchButtonListener() {
        //添加搜索按钮事件监听器
        search_button.addMouseListener(
                new MouseAdapter() {
                    int select_choice;
                    boolean isEditIn;
                    String[] columnName = {"Warehouse.warehouse_id", "Warehouse.warehouse_name", "Warehouse.location"};
                    String condition;

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        isEditIn = false;
                        /* 判断输入内容是否为空 */
                        if ((!search_key.getText().equals("")) && (!search_key.getText().equals("请输入查询仓库编号")) && (!search_key.getText().equals("请输入查询仓库地址")) && (!search_key.getText().equals("请输入查询仓库名"))) {
                            isEditIn = true;
                        } else {
                            //弹出输入提示对话框
                            JOptionPane.showMessageDialog(null, "请输入查询信息", "提示", JOptionPane.INFORMATION_MESSAGE);
                        }
                        select_choice = search_select.getSelectedIndex();
                        if (isEditIn)
                            switch (select_choice) {
                                case 0:
                                /*查询所有*/
                                    queryAll();
                                    break;
                                case 1:
                                    //查询仓库编号
                                    condition = columnName[0] + " LIKE '%" + search_key.getText() + "%'";
                                    try {
                                        table_data = ConnectManagement.wareHouseManagement_query(condition);
                                    } catch (Exception exception) {
                                        System.out.println("查询仓库_编号模块数据库错误");
                                        System.out.print(exception.getMessage());
                                    }
                                    break;
                                case 2:
                                    //查询仓库名
                                    condition = columnName[1] + " LIKE '%" + search_key.getText() + "%'";
                                    try {
                                        table_data = ConnectManagement.wareHouseManagement_query(condition);
                                    } catch (Exception exception) {
                                        System.out.println("查询仓库_名称模块数据库错误");
                                        System.out.print(exception.getMessage());
                                    }
                                    break;
                                case 3:
                                    //查询地址
                                    condition = columnName[2] + " LIKE '%" + search_key.getText() + "%'";
                                    try {
                                        table_data = ConnectManagement.wareHouseManagement_query(condition);
                                    } catch (Exception exception) {
                                        System.out.println("查询仓库_地址模块数据库错误");
                                        System.out.print(exception.getMessage());
                                    }
                                    break;
                                default:
                                    break;
                            }
                        init_show_Table();
                        refreshShowInformation();
                    }
                }
        );
    }

    private void addShow_tableListener() {
        show_table.addMouseListener(
                new MouseAdapter() {
                    private boolean click_show_judge;

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        refreshShowInformation();
                        click_show_judge = false;
                        if (show_table.getSelectedRow() != -1) {
                            if (show_table.getSelectedRowCount() == 1) {
                                if (e.getClickCount() == 2) {
                                    click_show_judge = true;
                                }
                            }
                        }
                        if (click_show_judge) {
                            //实例化订单商品窗口
                            //System.out.println(show_table.getSelectedRow());
                            WareHouseWindowInf wareHouseWindowInf = new WareHouseWindowInf(selectedIndex(show_table.getSelectedRow(), 0));
                            wareHouseWindowInf.setWarehouse_variable(selectedIndex(show_table.getSelectedRow(), 1), selectedIndex(show_table.getSelectedRow(), 3));
                            wareHouseWindowInf.refreshInformation();
                        }
                    }
                }
        );
    }

    private void queryAll() {
        //查询所有仓库信息函数
        try {
            table_data = ConnectManagement.wareHouseManagement_query("");
        } catch (Exception exception) {
            System.out.println("查询仓库_全部模块数据库错误");
            System.out.print(exception.getMessage());
        }
    }

    private void refreshShowInformation() {
        //初始化显示信息
        int count_num, select_num;
        count_num = show_table.getRowCount();
        select_num = show_table.getSelectedRow();
        show_table_information_1.setText("仓库数量:" + count_num);
        if (select_num != -1) {
            show_table_information_2.setText("选中仓库编号:" + (select_num + 1));
        } else {
            show_table_information_2.setText("选中仓库编号:");
        }
    }

    private String selectedIndex(int rowIndex, int columnIndex) {
        //获取所选行的仓库id
        return (String) show_table.getValueAt(rowIndex, columnIndex);
    }
}
