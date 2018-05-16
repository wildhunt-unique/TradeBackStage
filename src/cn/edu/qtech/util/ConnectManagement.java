package cn.edu.qtech.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �������ݿ�,�޸ġ���ӡ�ɾ������ѯ��Ϣ���࣬���и��־�̬����ȥ�������ݿ�
 * 
 * @author ����&������
 */
public class ConnectManagement {
	private static String warningMessage_String;
	private static String datasorce = "Trade";

	private static String url = "jdbc:mysql://120.24.186.116:3306/" + datasorce + "?characterEncoding=utf-8";
	private static String id = "root";;
	private static String password = "geralt";;

	/**
	 * �ж��Ƿ���ȷ���˻�
	 * 
	 * @param account_String
	 *            ��½�˻�
	 * @param password_String
	 *            ��¼����
	 * @return true ��ȷ���˻�����,false ������˻����� ע:warningMessage_String����������Ϣ
	 */
	public static boolean accountConfirm(String account_String, String password_String) {
		id = account_String;
		password = password_String;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.print(e);
		}

		Connection con;
		try {
			con = DriverManager.getConnection(url, id, password);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			warningMessage_String = "�˻������������!";
			return false;
		}
		return true;
	}

	/**
	 * �õ���ǰ�ľ�����Ϣ
	 * 
	 * @return ��ǰ�Ĵ�����Ϣ
	 */
	public static String getWarningMessage() {
		return warningMessage_String;
	}

	/**
	 * ͨ�õĲ�ѯ���
	 * 
	 * @param SQL
	 *            ��ѯ���
	 * @param tableName
	 *            ����
	 * @return ��ά����
	 */
	public static Object[][] generalQuery(String SQL, String tableName) {
		Object[][] data = null;
		Connection con;
		try {
			con = DriverManager.getConnection(url, id, password);
			DatabaseMetaData metadata = con.getMetaData();//
			ResultSet rs1 = metadata.getColumns(null, null, tableName, null);//
			int �ֶθ��� = 0;
			while (rs1.next()) { // �α������ƶ�һ��
				�ֶθ���++;
			}
			Statement sql = con.createStatement();
			ResultSet rs = sql.executeQuery(SQL);

			int count = 0;
			while (rs.next()) {
				count++;
			}
			data = new String[count][�ֶθ���];
			while (rs.previous()) {
				count--;
				for (int k = 1; k <= �ֶθ���; k++) { // һ���ֶ�һ���ֶε�ȡ����,����һ����¼
					data[count][k - 1] = rs.getString(k);
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * ͨ�õĸ������
	 * 
	 * @param SQL
	 *            ��ѯ���
	 */
	public static void generalUpdate(String SQL) {

		try {
			Connection con;
			PreparedStatement sql;
			con = DriverManager.getConnection(url, id, password);
			sql = con.prepareStatement(SQL);
			sql.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * ���ϲ�ѯ���
	 * 
	 * @param SQL
	 *            SQL���
	 * @param columnCount
	 *            �ֶ���
	 * @return ��ά����
	 */
	public static Object[][] unionQuery(String SQL, int columnCount) {
		Object[][] data = null;
		Connection con;
		try {
			con = DriverManager.getConnection(url, id, password);
			Statement sql = con.createStatement();
			ResultSet rs = sql.executeQuery(SQL);
			int count = 0;
			while (rs.next()) {
				count++;
			}
			data = new String[count][columnCount];
			while (rs.previous()) {
				count--;
				for (int k = 1; k <= columnCount; k++) { // һ���ֶ�һ���ֶε�ȡ����,����һ����¼
					data[count][k - 1] = rs.getString(k);
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * ѡ����Ʒ��ѯ
	 * 
	 * @param condition
	 * @return ��ά����
	 */
	public static String[][] getGoodsInf(String condition) throws Exception {
		String SQL = "select * from Goods " + condition;
		// System.out.println(SQL);
		String[][] data = null;
		Connection con = DriverManager.getConnection(url, id, password);
		DatabaseMetaData metadata = con.getMetaData();
		ResultSet rs1 = metadata.getColumns(null, null, "Goods", null);//
		int �ֶθ��� = 0;
		while (rs1.next()) { // �α������ƶ�һ��
			�ֶθ���++;
		}
		Statement sql = con.createStatement();
		ResultSet rs = sql.executeQuery(SQL);

		int count = 0;
		while (rs.next()) {
			count++;
		}
		data = new String[count][6];
		while (rs.previous()) {
			count--;
			for (int k = 1; k <= 6; k++) { // һ���ֶ�һ���ֶε�ȡ����,����һ����¼
				data[count][k - 1] = rs.getString(k);
			}
		}
		con.close();
		return data;
	}

	/**
	 * ��þ���������
	 */
	public static String[] getAgentInf(String agent_id) {
		String SQL = "select * from Agent,Area where agent_id = '" + agent_id + "'"
				+ " and  Agent.area_id = Area.area_id";
		String[] reName = { "", "", "", "", "", "", "", "" };
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.print(e);
		}

		Connection con;
		try {
			con = DriverManager.getConnection(url, id, password);
			DatabaseMetaData metadata = con.getMetaData();//

			Statement sql = con.createStatement();
			ResultSet rs = sql.executeQuery(SQL);
			while (rs.next()) {
				for (int i = 0; i < 8; i++) {
					reName[i] = rs.getString(i + 1);
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reName;
	}

	/**
	 * ͬ���˻���Ϣ�����ݿ�֮��
	 */
	public static void syncPersonInf(String data[], String agent_id) {
		String SQL = "update Agent set agent_name = '" + data[0] + "',phone = '" + data[1] + "',address = '" + data[2]
				+ "',Message = '" + data[3] + "' where agent_id = '" + agent_id + "'";
		try {
			Connection con;
			PreparedStatement sql;
			con = DriverManager.getConnection(url, id, password);
			sql = con.prepareStatement(SQL);
			sql.executeUpdate();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * ͬ����Ʒ��Ϣ����������
	 */
	public static void updateGoodsDemand() {
		ConnectManagement.generalUpdate("update Goods set demand_amount = 0");
		String SQL = "SELECT Indent.state,Sell.goods_id,Sell.amount FROM Indent,Sell WHERE Sell.indent_id = Indent.indent_id";
		Object[][] data = ConnectManagement.unionQuery(SQL, 3);
		for (Object[] temp : data) {
			if (temp[0].equals("�ѽӵ�")) {
				String updateSQL = "update Goods set Goods.demand_amount = Goods.demand_amount + " + temp[2]
						+ " where goods_id = '" + temp[1] + "'";
				ConnectManagement.generalUpdate(updateSQL);
			}
		}
	}

	/**
	 * ͬ����Ʒ��Ϣ���ܿ������
	 */
	public static void updateGoodsTotal() {
		ConnectManagement.generalUpdate("update Goods set total_amount = 0");
		String SQL = "SELECT SUM(Inventory.inventory_amount),Inventory.goods_id FROM Inventory GROUP BY Inventory.goods_id";
		Object[][] data = ConnectManagement.unionQuery(SQL, 2);
		for (Object[] temp : data) {
			String updateSQL = "update Goods set total_amount = " + temp[0] + " where goods_id = '" + temp[1] + "'";
			ConnectManagement.generalUpdate(updateSQL);
		}
	}

	/* author ������ start 2017.7.7---------------- */
	/**
	 * @TODO �ֿ�������ݿ��ѯ����
	 * 
	 * @author ������
	 */
	public static String[][] wareHouseManagement_query(String condition) throws Exception {
		// ���ݿ��������
		Connection connection = DriverManager.getConnection(url, id, password);
		String column = "Warehouse.warehouse_id,Warehouse.warehouse_name,Warehouse.location,SUM(Inventory.inventory_amount)";
		String sql = "SELECT " + column + " FROM Warehouse,Inventory WHERE ";
		if (!condition.equals("")) {
			sql = sql + condition + " AND ";
		}
		sql += "Inventory.warehouse_id = Warehouse.warehouse_id GROUP BY Inventory.warehouse_id";
		// ���SQL���
		// System.out.println("SQL��䣺" + sql);
		// SQLԤ�������
		PreparedStatement preparedSQL;
		ResultSet result;
		String[][] resultString;
		preparedSQL = connection.prepareStatement(sql);
		result = preparedSQL.executeQuery();
		// ���巵�����ݵ�����������
		int count = 0;
		int columnNumber = 4;
		while (result.next()) {
			count++;
		}
		resultString = new String[count][columnNumber];
		while (result.previous()) {
			count--;
			for (int k = 1; k <= columnNumber; k++) { // һ���ֶ�һ���ֶε�ȡ����,����һ����¼
				resultString[count][k - 1] = result.getString(k);
			}
		}
		connection.close();
		result.close();
		preparedSQL.close();
		return resultString;
	}

	/**
	 * @TODO �ֿ�������ݿ��ѯ����-���ڽ���
	 * 
	 * @author ������
	 */
	public static String[][] wareHouseWindow_query(String warehouse_id) throws Exception {
		// ���ݿ��������
		Connection connection = DriverManager.getConnection(url, id, password);
		String sql = "SELECT Goods.goods_id,Goods.goods_name,Goods.goods_type,Goods.norms,Goods.reserve_date,Goods.pack,Inventory.inventory_amount FROM Goods,Warehouse,Inventory WHERE Inventory.goods_id = Goods.goods_id AND Inventory.warehouse_id = Warehouse.warehouse_id AND Inventory.inventory_amount != '0' AND Warehouse.warehouse_id = '";
		sql = sql + warehouse_id + "' ORDER BY Goods.goods_id";
		// ���SQL���
		// System.out.println("SQL��䣺" + sql);
		// SQLԤ�������
		PreparedStatement preparedSQL;
		ResultSet result;
		String[][] resultString;
		preparedSQL = connection.prepareStatement(sql);
		result = preparedSQL.executeQuery();
		// ���巵�����ݵ�����������
		int count = 0;
		int columnNumber = 7;
		while (result.next()) {
			count++;
		}
		resultString = new String[count][columnNumber];
		while (result.previous()) {
			count--;
			for (int k = 1; k <= columnNumber; k++) { // һ���ֶ�һ���ֶε�ȡ����,����һ����¼
				resultString[count][k - 1] = result.getString(k);
			}
		}
		connection.close();
		result.close();
		preparedSQL.close();
		return resultString;
	}
	/*-------- ������ end 2017.7.7----------------*/
}
