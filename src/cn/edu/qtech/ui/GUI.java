package cn.edu.qtech.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import cn.edu.qtech.util.ConnectManagement;
import cn.edu.qtech.util.JxlWriteDemo;
import jxl.write.WriteException;

public class GUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			/* ����BeautyEye�����ư� */
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
		} catch (Exception e) {
			// TODO exception
		}
		
//		long startTime=System.nanoTime();   //��ȡ��ʼʱ��  
//		ConnectManagement.updateGoodsDemand();
//		ConnectManagement.updateGoodsTotal();
//		long endTime=System.nanoTime(); //��ȡ����ʱ��  
//		System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ns"); 
		
		MainFrame main_Frame = new MainFrame();
		//main_Frame.enterMainJPanel();
		main_Frame.setVisible(true);
	}
}
