package cn.edu.qtech.ui;

import javax.swing.*;

/**
 * Created by Tao on 7/7/2017.
 */
public class WareHouseManage extends JTabbedPane {
    //�������
    /*�ֿ�������*/
    private WareHouseJPanel wareHouseJPanel;

    public WareHouseManage() {
        //Ĭ�Ϲ��캯��
        super(JTabbedPane.TOP);
        initFrameConfig();
    }

    public void initFrameConfig() {
        //ʵ�������
        wareHouseJPanel = new WareHouseJPanel();
        //������
        this.addTab("�ֿ����", null, wareHouseJPanel, "��ѯ������ֿ����Ϣ");
    }

}