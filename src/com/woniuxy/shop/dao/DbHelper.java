package com.woniuxy.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.woniuxy.shop.entity.PageBean;
import com.woniuxy.shop.exception.DAOException;


/**
 * 数据库操作工具类
 * 
 * @author
 *
 */
public class DbHelper {
	//	用于保存某个线程共享变量：对于同一个static ThreadLocal，不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量。
	private static ThreadLocal<Connection> conns = new ThreadLocal<>();
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = conns.get();// 调用get方法时，获取当前线程变量
		// 加载驱动,在Java8可省
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new DAOException("驱动加载失败", e);
			}
			// 获取连接
//			Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/woniushop?", "root",
						"123456");
				conns.set(conn);//设置变量conn的值
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("数据库连接失败", e);
			}
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conns.remove();//同一线程一个方法连续调用时，第一个调用完毕关闭连接后边的就会报错
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("数据库关闭异常", e);
		}
	}

	/**
	 * 执行单条insert update delete语句
	 * 
	 * @param sql
	 */
	public static void execute(String sql, Object... params) {
		QueryRunner runner = new QueryRunner();
		Connection conn = getConnection();
		try {
			runner.execute(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("sql" + sql + "异常", e);
		} 
	}

	/**
	 * 批量执行insert，update，delete语句
	 * 
	 * @param sql
	 * @param params
	 */
	public static void executeBatch(String sql, Object[]... params) {
		QueryRunner runner = new QueryRunner();
		Connection conn = getConnection();
		try {
			runner.batch(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("sql" + sql + "异常", e);
		} 
	}

	/**
	 * 执行select语句,返回List类型的结果(前提：列名与属性名一致 )
	 * 
	 * @param <T>
	 * @param sql
	 * @param t
	 * @param params
	 * @return
	 */
	public static <T> List<T> query(String sql, Class<T> c, Object... params) {
		QueryRunner runner = new QueryRunner();
		Connection conn = getConnection();
		List<T> result = null;
		try {
			result = runner.query(conn, sql, new BeanListHandler<T>(c), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("sql" + sql + "异常", e);
		} 
		return result;
	}

	/**
	 * 执行单条select语句，返回至多一条数据，封装到实体类中
	 * 
	 * @param sql
	 * @param c
	 * @param params
	 * @return 返回封装了查询结果的实例，查不到数据则返回null
	 */
	public static <T> T queryForSingleRow(String sql, Class<T> c, Object... params) {
		QueryRunner runner = new QueryRunner();
	Connection conn = getConnection();
		T result = null;
		try {
			result = runner.query(conn, sql, new BeanHandler<T>(c), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("sql" + sql + "异常", e);
		} 
		return result;
	}

	/**
	 * 执行单条select语句，返回标量
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryForScalar(String sql, Object... params) {
		QueryRunner runner = new QueryRunner();
		Connection conn = getConnection();
		T result = null;
		try {
			result = runner.query(conn, sql, new ScalarHandler<T>(), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("sql" + sql + "异常", e);
		} 
		return result;
	}
	/**
	 * 分页查询
	 * @param <E>
	 * @param page
	 * @param c
	 * @param pageSize
	 * @return
	 */
	public static <E>PageBean<E> getDataByPage(String sql,String sql1,Class<E> c,int page, int pageSize) {
		if(pageSize==0) {
			return null;
		}
		if(page<1) {
			throw new RuntimeException("页数有误");
		}
		PageBean<E> pb = new PageBean<>();
//		String sql = "select * from bank_user limit ?,?";
		List<E> data =query(sql, c,(page-1)*pageSize,pageSize);
//		sql1="select count(*) from bank_user";
		int totalNum = ((Long)queryForScalar(sql1)).intValue();//mysql返回的count（*）是long类型
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:(totalNum/pageSize+1);
		int numsOfCurrPage=data.size();//当前页的数量就是查出来date的数量
		pb.setData(data);
		pb.setTotalNum(totalNum);
		pb.setPageSize(pageSize);
		pb.setPage(page);
		pb.setTotalPage(totalPage);
		pb.setNumsOfCurrPage(numsOfCurrPage);
		return pb;
	}
	/**
	 * 获取当前时间
	 */
	public static String getNow() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//大写的M代表月份，大写的H表示24小时制，小写的h表示12小时制
		return sdf.format(new Date());
	}

}
