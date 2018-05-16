package cn.edu.qtech.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 链接数据库,修改、添加、删除、查询信息的类，内有各种静态方法去连接数据库
 * 
 * @author 丁星&王海涛
 */
public class ConnectManagement {
	private static String warningMessage_String;
	private static String datasorce = "Trade";

	private static String url = "jdbc:mysql://120.24.186.116:3306/" + datasorce + "?characterEncoding=utf-8";
	private static String id = "root";;
	private static String password = "geralt";;

	/**
	 * 判断是否正确的账户
	 * 
	 * @param account_String
	 *            登陆账户
	 * @param password_String
	 *            登录密码
	 * @return true 正确的账户密码,false 错误的账户密码 注:warningMessage_String保留错误信息
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
			warningMessage_String = "账户或者密码错误!";
			return false;
		}
		return true;
	}

	/**
	 * 得到当前的警告信息
	 * 
	 * @return 当前的错误信息
	 */
	public static String getWarningMessage() {
		return warningMessage_String;
	}

	/**
	 * 通用的查询语句
	 * 
	 * @param SQL
	 *            查询语句
	 * @param tableName
	 *            表名
	 * @return 二维数组
	 */
	public static Object[][] generalQuery(String SQL, String tableName) {
		Object[][] data = null;
		Connection con;
		try {
			con = DriverManager.getConnection(url, id, password);
			DatabaseMetaData metadata = con.getMetaData();//
			ResultSet rs1 = metadata.getColumns(null, null, tableName, null);//
			int 字段个数 = 0;
			while (rs1.next()) { // 游标向下移动一行
				字段个数++;
			}
			Statement sql = con.createStatement();
			ResultSet rs = sql.executeQuery(SQL);

			int count = 0;
			while (rs.next()) {
				count++;
			}
			data = new String[count][字段个数];
			while (rs.previous()) {
				count--;
				for (int k = 1; k <= 字段个数; k++) { // 一个字段一个字段的取出来,构建一条记录
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
	 * 通用的更新语句
	 * 
	 * @param SQL
	 *            查询语句
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
	 * 联合查询语句
	 * 
	 * @param SQL
	 *            SQL语句
	 * @param columnCount
	 *            字段数
	 * @return 二维数组
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
				for (int k = 1; k <= columnCount; k++) { // 一个字段一个字段的取出来,构建一条记录
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
	 * 选择商品查询
	 * 
	 * @param condition
	 * @return 二维数组
	 */
	public static String[][] getGoodsInf(String condition) throws Exception {
		String SQL = "select * from Goods " + condition;
		// System.out.println(SQL);
		String[][] data = null;
		Connection con = DriverManager.getConnection(url, id, password);
		DatabaseMetaData metadata = con.getMetaData();
		ResultSet rs1 = metadata.getColumns(null, null, "Goods", null);//
		int 字段个数 = 0;
		while (rs1.next()) { // 游标向下移动一行
			字段个数++;
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
			for (int k = 1; k <= 6; k++) { // 一个字段一个字段的取出来,构建一条记录
				data[count][k - 1] = rs.getString(k);
			}
		}
		con.close();
		return data;
	}

	/**
	 * 获得经销商数据
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
	 * 同步账户信息到数据库之中
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
	 * 同步商品信息中需求数量
	 */
	public static void updateGoodsDemand() {
		ConnectManagement.generalUpdate("update Goods set demand_amount = 0");
		String SQL = "SELECT Indent.state,Sell.goods_id,Sell.amount FROM Indent,Sell WHERE Sell.indent_id = Indent.indent_id";
		Object[][] data = ConnectManagement.unionQuery(SQL, 3);
		for (Object[] temp : data) {
			if (temp[0].equals("已接单")) {
				String updateSQL = "update Goods set Goods.demand_amount = Goods.demand_amount + " + temp[2]
						+ " where goods_id = '" + temp[1] + "'";
				ConnectManagement.generalUpdate(updateSQL);
			}
		}
	}

	/**
	 * 同步商品信息中总库存数量
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

	/* author 王海涛 start 2017.7.7---------------- */
	/**
	 * @TODO 仓库管理数据库查询函数
	 * 
	 * @author 王海涛
	 */
	public static String[][] wareHouseManagement_query(String condition) throws Exception {
		// 数据库操作变量
		Connection connection = DriverManager.getConnection(url, id, password);
		String column = "Warehouse.warehouse_id,Warehouse.warehouse_name,Warehouse.location,SUM(Inventory.inventory_amount)";
		String sql = "SELECT " + column + " FROM Warehouse,Inventory WHERE ";
		if (!condition.equals("")) {
			sql = sql + condition + " AND ";
		}
		sql += "Inventory.warehouse_id = Warehouse.warehouse_id GROUP BY Inventory.warehouse_id";
		// 输出SQL语句
		// System.out.println("SQL语句：" + sql);
		// SQL预处理变量
		PreparedStatement preparedSQL;
		ResultSet result;
		String[][] resultString;
		preparedSQL = connection.prepareStatement(sql);
		result = preparedSQL.executeQuery();
		// 定义返回数据的行数和列数
		int count = 0;
		int columnNumber = 4;
		while (result.next()) {
			count++;
		}
		resultString = new String[count][columnNumber];
		while (result.previous()) {
			count--;
			for (int k = 1; k <= columnNumber; k++) { // 一个字段一个字段的取出来,构建一条记录
				resultString[count][k - 1] = result.getString(k);
			}
		}
		connection.close();
		result.close();
		preparedSQL.close();
		return resultString;
	}

	/**
	 * @TODO 仓库管理数据库查询函数-窗口界面
	 * 
	 * @author 王海涛
	 */
	public static String[][] wareHouseWindow_query(String warehouse_id) throws Exception {
		// 数据库操作变量
		Connection connection = DriverManager.getConnection(url, id, password);
		String sql = "SELECT Goods.goods_id,Goods.goods_name,Goods.goods_type,Goods.norms,Goods.reserve_date,Goods.pack,Inventory.inventory_amount FROM Goods,Warehouse,Inventory WHERE Inventory.goods_id = Goods.goods_id AND Inventory.warehouse_id = Warehouse.warehouse_id AND Inventory.inventory_amount != '0' AND Warehouse.warehouse_id = '";
		sql = sql + warehouse_id + "' ORDER BY Goods.goods_id";
		// 输出SQL语句
		// System.out.println("SQL语句：" + sql);
		// SQL预处理变量
		PreparedStatement preparedSQL;
		ResultSet result;
		String[][] resultString;
		preparedSQL = connection.prepareStatement(sql);
		result = preparedSQL.executeQuery();
		// 定义返回数据的行数和列数
		int count = 0;
		int columnNumber = 7;
		while (result.next()) {
			count++;
		}
		resultString = new String[count][columnNumber];
		while (result.previous()) {
			count--;
			for (int k = 1; k <= columnNumber; k++) { // 一个字段一个字段的取出来,构建一条记录
				resultString[count][k - 1] = result.getString(k);
			}
		}
		connection.close();
		result.close();
		preparedSQL.close();
		return resultString;
	}
	/*-------- 王海涛 end 2017.7.7----------------*/
}
