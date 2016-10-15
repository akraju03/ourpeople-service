package com.ourpeople.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.ourpeople.models.UserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDao {

	private static final String GET_USER_QUERY = "select id,email,phone,is_verified,password,community_id,name from people where name =? and password= ?";

	public UserDetails userDetails = new UserDetails();

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/ourpeople?useSSL=false";

	// Database credentials
	final String USER = "root";
	final String PASS = "root";

	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Connection connection = null;

	public UserDetails getUserDetails(String email, String password) {

		try {

			DbUtils.loadDriver(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			QueryRunner qRunner = new QueryRunner();
			String query = "select ourpeople.people.id,ourpeople.people.email,ourpeople.people.phone,ourpeople.people.is_verified,ourpeople.people.password,communities.community_name from ourpeople.people INNER JOIN ourpeople.communities ON ourpeople.people.community_id=ourpeople.communities.id where email='"+email+"'";
			System.out.println(query);
			@SuppressWarnings({ "rawtypes", "unchecked" })
			List beans1 = (List) qRunner
					.query(connection,
							query,
							new BeanListHandler(UserDetails.class));

			if(beans1.size() != 0){
				userDetails = (UserDetails) beans1.get(0);
			}

		} catch (SQLException e) {
			// handle the exception
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(connection);
		}
		return userDetails;
	}
}
