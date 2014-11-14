package JDBCTools;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class JdbcUtils {
	// 定义数据库的用户名
	private final String USERNAME = "root";
	// 数据库密码
	private final String PASSWORD = "admin";
	// 数据库的驱动信息
	private final String DRIVER = "com.mysql.jdbc.Driver";
	// 定义地址
	private final String URL = "jdbc:mysql://localhost:3306/PO";

	// 定义数据库的链接
	private Connection connection;
	// 定义sql的执行对象
	private PreparedStatement pstmt;
	// 定义查询返回的结果
	private ResultSet resultSet;

	public JdbcUtils() {
		try {
			Class.forName(DRIVER);
//			System.out.println("1234567");//test
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 定义获得数据库的链接
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	// 封装增删改查
	public boolean updateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));// 填充占位符，忽略了ID
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}

	//
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> findSimpleResult(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();//
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();//
		while (resultSet.next()) {
			for (int i = 0; i < col_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
		}
		return map;
	}

	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findMoreResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	// jdbc
	public <T> T findSimpleRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		T resultObject = null;
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);//
				field.set(resultObject, cols_value);
			}
		}
		return resultObject;
	}

	/**
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findMoreRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		List<T> list = new ArrayList<T>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			T resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);
				field.set(resultObject, cols_value);
			}
			list.add(resultObject);
		}
		return list;
	}

	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils = new JdbcUtils();
		//获取与数据库的连接
		jdbcUtils.getConnection();
		//sql语句，删除类似
		 String sql = "insert into CustomerPO(number,sort,level,name) values(?,?,?,?)";
		 List<Object> params = new ArrayList<Object>();
		 params.add("0001");		 
		 params.add("importer");
		 params.add("firstClass");
		 params.add("kmno4");
		 try {
		 boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
		 System.out.println(flag);
		 } catch (SQLException e) {
		 e.printStackTrace();
		 }
//		String sql = "select * from userinfo ";
//		// List<Object> params = new ArrayList<Object>();
//		// params.add(1);
//		try {
//			List<Map<String, Object>> map = jdbcUtils.findMoreResult(sql, null);
//			System.out.println(map);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			jdbcUtils.releaseConn();
//		}
	}

}
