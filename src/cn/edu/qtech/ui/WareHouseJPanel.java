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
    //�ֿ���Ϣ�������
    /*���*/
    //�������
    private JTable show_table;
    private JTextField search_key;
    private JButton search_button;
    private JComboBox<String> search_select;
    private JScrollPane show_table_scroll;
    private JLabel show_table_information_1, show_table_information_2;

    //������
    private String[] column_names = {"�ֿ���", "�ֿ���", "�ֿ��ַ", "������Ʒ����"};
    private String[][] table_data = null;
    //������ݱ���
    private String[] selectString = {"��ѯ����", "���ֿ��Ų�ѯ", "���ֿ�����ѯ", "���ֿ��ַ��ѯ"};

    /*����*/
    public WareHouseJPanel() {
        //Ĭ�Ϲ��캯��
        //���ʵ����
        search_select = new JComboBox<>();
        search_key = new JTextField("��ѯ����");
        search_button = new JButton("��ѯ");
        show_table = new JTable();
        init_show_Table();  //������
        show_table_scroll = new JScrollPane(show_table);
        show_table_information_1 = new JLabel("�ֿ�����:");
        show_table_information_2 = new JLabel("ѡ�вֿ���:");
        //�����������
        //��������
        search_select.setFont(GeneralTools.getButtonFont());
        search_key.setFont(GeneralTools.getButtonFont());
        search_button.setFont(GeneralTools.getButtonFont());
        show_table.setFont(GeneralTools.getTableFont());
        show_table_information_1.setFont(GeneralTools.getTextFont());
        show_table_information_2.setFont(GeneralTools.getTextFont());
        //�����������
        for (String select : selectString) {
            search_select.addItem(select);
        }
        search_key.setVisible(false);
        //��ӱ߿�
//        show_table_information_1.setBorder(BorderFactory.createLineBorder(Color.blue,1));
//        show_table_information_2.setBorder(BorderFactory.createLineBorder(Color.blue,1));
        //�����������
        this.setLayout(null);
        search_select.setBounds(20, 20, 300, 50);
        search_key.setBounds(340, 20, 615, 50);
        search_button.setBounds(975, 20, 150, 50);
        show_table_scroll.setBounds(20, 90, 1105, 495);
        show_table_information_1.setBounds(650, 580, 200, 50);
        show_table_information_2.setBounds(850, 580, 250, 50);
        //������
        this.add(search_select);
        this.add(search_key);
        this.add(search_button);
        this.add(show_table_scroll);
        this.add(show_table_information_1);
        this.add(show_table_information_2);
        //����¼�������
        addSearchSelectListener();  /*����ѡ����¼�������*/
        addSearchKeyListener(); /*�������¼�������*/
        addSearchButtonListener();  /*���������ť�¼�������*/
        addShow_tableListener(); /*��ӱ���¼�������*/
        //��ʼ���������
        queryAll();
        init_show_Table();
        //��ʼ����ʾ��ʾ��Ϣ
        refreshShowInformation();
    }

    private void init_show_Table() {
        //����ʼ������
        show_table.setModel(new TableModelUnEdit(table_data, column_names));
        show_table.setRowHeight(50);
        //���ñ����ʽ
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
                                search_button.setText("��ѯ����");
                                search_key.setText("��ѯ����");
                            } else {
                                search_key.setVisible(true);
                                switch (search_select.getSelectedIndex()) {
                                    case 1:
                                        //��ѯ���
                                        search_button.setText("��ѯ���");
                                        search_key.setText("�������ѯ�ֿ���");
                                        break;
                                    case 2:
                                        //��ѯ����
                                        search_button.setText("��ѯ����");
                                        search_key.setText("�������ѯ�ֿ���");
                                        break;
                                    case 3:
                                        //��ѯ��ַ
                                        search_button.setText("��ѯ��ַ");
                                        search_key.setText("�������ѯ�ֿ��ַ");
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
        //���������ť�¼�������
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
                        /* �ж����������Ƿ�Ϊ�� */
                        if ((!search_key.getText().equals("")) && (!search_key.getText().equals("�������ѯ�ֿ���")) && (!search_key.getText().equals("�������ѯ�ֿ��ַ")) && (!search_key.getText().equals("�������ѯ�ֿ���"))) {
                            isEditIn = true;
                        } else {
                            //����������ʾ�Ի���
                            JOptionPane.showMessageDialog(null, "�������ѯ��Ϣ", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                        }
                        select_choice = search_select.getSelectedIndex();
                        if (isEditIn)
                            switch (select_choice) {
                                case 0:
                                /*��ѯ����*/
                                    queryAll();
                                    break;
                                case 1:
                                    //��ѯ�ֿ���
                                    condition = columnName[0] + " LIKE '%" + search_key.getText() + "%'";
                                    try {
                                        table_data = ConnectManagement.wareHouseManagement_query(condition);
                                    } catch (Exception exception) {
                                        System.out.println("��ѯ�ֿ�_���ģ�����ݿ����");
                                        System.out.print(exception.getMessage());
                                    }
                                    break;
                                case 2:
                                    //��ѯ�ֿ���
                                    condition = columnName[1] + " LIKE '%" + search_key.getText() + "%'";
                                    try {
                                        table_data = ConnectManagement.wareHouseManagement_query(condition);
                                    } catch (Exception exception) {
                                        System.out.println("��ѯ�ֿ�_����ģ�����ݿ����");
                                        System.out.print(exception.getMessage());
                                    }
                                    break;
                                case 3:
                                    //��ѯ��ַ
                                    condition = columnName[2] + " LIKE '%" + search_key.getText() + "%'";
                                    try {
                                        table_data = ConnectManagement.wareHouseManagement_query(condition);
                                    } catch (Exception exception) {
                                        System.out.println("��ѯ�ֿ�_��ַģ�����ݿ����");
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
                            //ʵ����������Ʒ����
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
        //��ѯ���вֿ���Ϣ����
        try {
            table_data = ConnectManagement.wareHouseManagement_query("");
        } catch (Exception exception) {
            System.out.println("��ѯ�ֿ�_ȫ��ģ�����ݿ����");
            System.out.print(exception.getMessage());
        }
    }

    private void refreshShowInformation() {
        //��ʼ����ʾ��Ϣ
        int count_num, select_num;
        count_num = show_table.getRowCount();
        select_num = show_table.getSelectedRow();
        show_table_information_1.setText("�ֿ�����:" + count_num);
        if (select_num != -1) {
            show_table_information_2.setText("ѡ�вֿ���:" + (select_num + 1));
        } else {
            show_table_information_2.setText("ѡ�вֿ���:");
        }
    }

    private String selectedIndex(int rowIndex, int columnIndex) {
        //��ȡ��ѡ�еĲֿ�id
        return (String) show_table.getValueAt(rowIndex, columnIndex);
    }
}
