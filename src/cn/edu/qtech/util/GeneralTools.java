package cn.edu.qtech.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * ͨ�ù�����
 */
public class GeneralTools {
	private static final String RedSkinPtah = "skin//skin_red//";
	private static final Font generalButton_Font = new Font("΢���ź�", Font.PLAIN, 25);
	private static final Font generalText_Font = new Font("����ϸ��", Font.PLAIN, 25);
	private static final Font generalTable_Font = new Font("����ϸ��", Font.PLAIN, 20);

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
	 * ����JTable�������
	 * 
	 * @param �����õ�JTable����
	 * @author �������˵�
	 */
	public static void makeFace(JTable table) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					if (row % 2 == 0)
						setBackground(Color.white); // ���������е�ɫ
					else if (row % 2 == 1)
						setBackground(new Color(206, 231, 255)); // ����ż���е�ɫ
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
	 * �Ƿ�������
	 * 
	 * @param Str
	 *            �������ַ���
	 * @return �� true,����false
	 * @author ����
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
