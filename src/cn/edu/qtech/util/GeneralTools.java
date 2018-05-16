package cn.edu.qtech.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 通用工具类
 */
public class GeneralTools {
	private static final String RedSkinPtah = "skin//skin_red//";
	private static final Font generalButton_Font = new Font("微软雅黑", Font.PLAIN, 25);
	private static final Font generalText_Font = new Font("华文细黑", Font.PLAIN, 25);
	private static final Font generalTable_Font = new Font("华文细黑", Font.PLAIN, 20);

	public static Font getTableFont() {
		return generalTable_Font;
	}
	
	public static Font getButtonFont() {
		return generalButton_Font;
	}

	public static Font getTextFont() {
		return generalText_Font;
	}

	public static String getRedSkinPtah() {
		return RedSkinPtah;
	}

	/**
	 * 设置JTable蓝白相间
	 * 
	 * @param 待设置的JTable对象
	 * @author 引用他人的
	 */
	public static void makeFace(JTable table) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					if (row % 2 == 0)
						setBackground(Color.white); // 设置奇数行底色
					else if (row % 2 == 1)
						setBackground(new Color(206, 231, 255)); // 设置偶数行底色
					return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				}
			};
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 是否是数字
	 * 
	 * @param Str
	 *            待检查的字符串
	 * @return 是 true,不是false
	 * @author 丁星
	 */
	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.') {
				return false;
			}
		}
		return true;
	}
}
