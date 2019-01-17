package lxx.linearAlgebra.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static PreparedStatement ps = null;
	public static ResultSet rs = null;
	public static Connection connection = null;
	
	/**
	 * 返回一个数据库连接
	 */
	public static Connection getConnByProperties() {
		Connection connection = null;
		try {
			Class.forName(PropertiesUtil.getDriverProperties("MySQL"));
			connection = DriverManager.getConnection(PropertiesUtil.getUrlProperties("MySQL"),
					PropertiesUtil.getUsernameProperties("MySQL"), PropertiesUtil.getPasswordProperties("MySQL"));
		} catch (ClassNotFoundException e) {
			System.out.println("类驱动加载失败");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 通用增删改
	 * @param sql sql语句
	 * @param params sql语句中参数的值
	 * @return 操作成功返回true，失败返回false
	 */
	public static boolean executeUpdate(String sql, Object[] params) {
		try {
			connection = getConnByProperties();
			ps = creatPreparedStatement(sql, params);
			int count = ps.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, ps, connection);
		}
		return false;
	}
	
	/**
	 * 获取改造好的PreparedStatement对象
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement creatPreparedStatement(String sql, Object[] params) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, params[i]);
		}
		return ps;
	}
	
	/**
	 * 通用查
	 * @param sql sql语句
	 * @param params sql语句中参数的值
	 * @return 查找出来的结果集
	 */
	public static ResultSet executeQuery(String sql, Object[] params) {
		try {
			connection = getConnByProperties();
			ps = creatPreparedStatement(sql, params);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 关闭所有链接
	 * @param rs
	 * @param stmt
	 * @param connection
	 */
	public static void closeAll(ResultSet rs, Statement stmt, Connection connection) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询总数
	 * @param sql
	 * @return
	 */
	public static int getTotalCount(String sql) {
		int count = -1;
		try {
			ps = creatPreparedStatement(sql, null);
			rs = ps.executeQuery();
			if(rs.next()) {
				rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, ps, connection);
		}
		return count;
	}
	
}
