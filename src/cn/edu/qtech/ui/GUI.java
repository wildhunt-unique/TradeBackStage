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
			/* 导入BeautyEye外观设计包 */
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
		} catch (Exception e) {
			// TODO exception
		}
		
//		long startTime=System.nanoTime();   //获取开始时间  
//		ConnectManagement.updateGoodsDemand();
//		ConnectManagement.updateGoodsTotal();
//		long endTime=System.nanoTime(); //获取结束时间  
//		System.out.println("程序运行时间： "+(endTime-startTime)+"ns"); 
		
		MainFrame main_Frame = new MainFrame();
		//main_Frame.enterMainJPanel();
		main_Frame.setVisible(true);
	}
}
