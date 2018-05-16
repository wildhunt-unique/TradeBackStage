package cn.edu.qtech.ui;

import javax.swing.*;

/**
 * Created by Tao on 7/7/2017.
 */
public class WareHouseManage extends JTabbedPane {
    //窗口组件
    /*仓库管理组件*/
    private WareHouseJPanel wareHouseJPanel;

    public WareHouseManage() {
        //默认构造函数
        super(JTabbedPane.TOP);
        initFrameConfig();
    }

    public void initFrameConfig() {
        //实例化组件
        wareHouseJPanel = new WareHouseJPanel();
        //添加组件
        this.addTab("仓库管理", null, wareHouseJPanel, "查询、管理仓库的信息");
    }

}