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
    //���
    private JPanel mainPanel;
    private JTable show_table;
    private JScrollPane show_table_scroll;
    private JLabel show_ware_information_1, show_ware_information_2;
    private JLabel show_table_information_1, show_table_information_2;
    //����
    private Font font1 = new Font("Monospace", Font.BOLD, 25);
    //������
    private String[] column_names = {"��Ʒ���", "��Ʒ��", "����", "���", "������", "��װ��ʽ", "�ִ�����"};
    private String[][] table_data = null;
    //����
    private String warehouse_id = null;
    private String warehouse_name = null;
    private String wareHouse_total_goods_amount = null;

    //���캯��
    public WareHouseWindowInf() {
        //Ĭ�Ϲ��캯��
        this("");
    }

    public WareHouseWindowInf(String warehouse_id) {
        this.setTitle("�ֿ� " + warehouse_id + " ����");
        this.warehouse_id = warehouse_id;
        initWindow();
    }

    private void initWindow() {
        //���湹�캯��
        this.setSize(1500, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //���ʵ����
        mainPanel = new JPanel(null);
        show_table = new JTable();
        show_ware_information_1 = new JLabel("�ֿ���:");
        show_ware_information_2 = new JLabel("�ֿ���:");
        show_table_information_1 = new JLabel("��Ʒ������:");
        show_table_information_2 = new JLabel("��Ʒ����:");
        //���ʵ����
        init_show_Table(); //���������
        show_table_scroll = new JScrollPane(show_table);
        //�����������
        //��ӱ߿�
//        show_ware_information_1.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
//        show_ware_information_2.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
//        show_table_information_1.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
//        show_table_information_2.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
        //����������
        show_table_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //��������
        show_table.setFont(font1);
        show_ware_information_1.setFont(font1);
        show_ware_information_2.setFont(font1);
        show_table_information_1.setFont(font1);
        show_table_information_2.setFont(font1);
        //���沼��
        show_table_scroll.setBounds(20, 50, 1410, 585);
        show_ware_information_1.setBounds(20, 0, 220, 50);
        show_ware_information_2.setBounds(240, 0, 320, 50);
        show_table_information_1.setBounds(880, 635, 285, 50);
        show_table_information_2.setBounds(1165, 635, 265, 50);
        //������
        mainPanel.add(show_table_scroll);
        mainPanel.add(show_ware_information_1);
        mainPanel.add(show_ware_information_2);
        mainPanel.add(show_table_information_1);
        mainPanel.add(show_table_information_2);
        this.add(mainPanel);
        //��ѯ���ݿ�
        search_information();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void setWarehouse_variable(String warehouse_name, String warehouse_total_goods_amount) {
        //���òֿ�������
        this.warehouse_name = warehouse_name;
        this.wareHouse_total_goods_amount = warehouse_total_goods_amount;
    }

    public void refreshInformation() {
        //ˢ������ͳ����Ϣ
        show_ware_information_1.setText("�ֿ���:" + warehouse_id);
        show_ware_information_2.setText("�ֿ���:" + warehouse_name);
        show_table_information_1.setText("��Ʒ������:" + show_table.getRowCount());
        show_table_information_2.setText("��Ʒ����:" + wareHouse_total_goods_amount);
    }

    // ���ݿ��ѯ����
    private void search_information() {
        //��ѯ���ݿ⺯��
        try {
            table_data = wareHouseWindow_query(this.warehouse_id);
        } catch (Exception exception) {
            System.out.println("��ѯ�ֿ�_ȫ��ģ�����ݿ����");
            System.out.print(exception.getMessage());
        }
        //�ػ���
        init_show_Table();
    }

    //������Ӧ����
    private void init_show_Table() {
        //����ʼ������
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
