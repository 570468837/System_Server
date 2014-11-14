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

import PO.CustomerPO;
import ResultMessage.ResultMessage;

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
			// System.out.println("1234567");//test
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
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//模糊查找
	//maps=jdbcUtils.findPO("CustomerPO","name","k");//maps为所有的name中包含k的PO的集合
	public List<Map<String,Object>> fuzzySerch(String nameOfPO, String key,
			String value){
		String sql = "select * from " + nameOfPO + " where " + key + " like "
				+ "'%" + value + "%'";
		
		List<Map<String,Object>> maps=new ArrayList<Map<String,Object>>();
		try {
			maps=this.findMoreResult(sql, null);
			return maps;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//返回空值则查找失败
			return null;
		}					
	}

	//查找操作
	// 通过PO的属性和属性名获得其他属性
	// 范例：
	// Map<String,Object>
	// myMap=jdbcUtils.findPO("CustomerPO","id","1");//查找id为1的customer，返回其所有属性
	// System.out.println(myMap);
	public Map<String, Object> findPO(String nameOfPO, String key,
			String value) {
		Map<String, Object> map;
		String sql = "select * from " + nameOfPO + " where " + key + " = "
				+ "'" + value + "'";

		try {
			map = this.findSimpleResult(sql, null);
			return map;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return null;
	}
	
	//增加操作
	// 增加对象到数据库,建议大家根据自己的类写出类似的类
	public ResultMessage addPO(CustomerPO po) {
		//在这里写出对象的所有属性以及后面的占位符
		String sql = "insert into CustomerPO(number,sort,level,name,phone,address,zipcode,mail,clerk) values(?,?,?,?,?,?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		//按照上面的顺序填写po的属性
		params.add(po.getNumber());
		params.add(po.getSort());
		params.add(po.getLevel());
		params.add(po.getName());
		params.add(po.getPhone());
		params.add(po.getAddress());
		params.add(po.getZipCode());
		params.add(po.getMail());
		params.add(po.getClerk());
		try {
			//
			boolean flag=this.updateByPreparedStatement(sql,params);
			return ResultMessage.add_success;
			//需要返回一些提示成功
		} catch (Exception e) {
			
			e.printStackTrace();
			return ResultMessage.add_failure;
		}
		
	}
	
	//删除操作
	public ResultMessage deletePO(String nameOfPO, String key,
			String value){
		//删除key为value的一行
		String sql="delete from "+nameOfPO+" where "+key+" = '"+value+"'";
		try {
			this.updateByPreparedStatement(sql,null);
			return ResultMessage.delete_success;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.delete_success;

		}
		
	}
	
	//修改操作
	public ResultMessage updatePO(String nameOfPO, String keyForFind,String valueForFind,String keyToChange,
			String valueToChange){
		//将nameofpo数据库中满足keyToFind=valueToFind的一行的keyTochange修改为valueToChange		
		String sql="update "+nameOfPO+" set "+keyToChange+" = "+valueToChange+" where "+keyForFind+" = "+"'"+valueForFind+"'"; 
		try {
			this.updateByPreparedStatement(sql,null);
			return ResultMessage.update_success;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.update_failure;
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//使用前需要获得与数据库的连接，使用后需要释放连接
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils = new JdbcUtils();
		// 获取与数据库的连接
		jdbcUtils.getConnection();

		// 增加操作的范例
		// String sql =
		// "insert into CustomerPO(number,sort,level,name) values(?,?,?,?)";
		// List<Object> params = new ArrayList<Object>();
		// params.add("0001");
		// params.add("importer");
		// params.add("firstClass");
		// params.add("kmno4");
		// try {
		// boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
		// System.out.println(flag);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

		// 查找操作的范例
		// String sql = "select * from CustomerPO where id like '%1%'";
		// // List<Object> params = new ArrayList<Object>();
		// // params.add(1);
		// try {
		// List<Map<String, Object>> map = jdbcUtils.findMoreResult(sql, null);
		// CustomerPO po=new CustomerPO();
		// System.out.println(map.get(0).get("name"));
		// // System.out.println(map);
		// } catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// } finally {
		// jdbcUtils.releaseConn();
		// }

		jdbcUtils.releaseConn();
	}
}
