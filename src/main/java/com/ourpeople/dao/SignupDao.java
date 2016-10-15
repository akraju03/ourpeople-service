package com.ourpeople.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ourpeople.models.SignupDetails;
import com.ourpeople.models.UserDetails;


public class SignupDao {
	
	private static final String POST_CREATE_USER_QUERY = "INSERT INTO ourpeople.people (email, phone, community_id) VALUES( 'akra', '333', 1 );INSERT INTO ourpeople.people_profile (first_name, address, spouse_name, father_name, mother_name, people_id) VALUES('anil', 'add', 'lak', 'nag', 'ind', (select id FROM ourpeople.people WHERE email='akra')) ;";

	public UserDetails userDetails = new UserDetails();

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/ourpeople?useSSL=false";

	// Database credentials
	final String USER = "root";
	final String PASS = "root";

	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Connection connection = null;

	public UserDetails createuser(SignupDetails signUpDetail) {

		try {

			DbUtils.loadDriver(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

			QueryRunner qRunner = new QueryRunner();
			
			String query1= "INSERT INTO ourpeople.people (email, phone, community_id) VALUES( '"+signUpDetail.getEmail()+"', '"+signUpDetail.getPhone()+"', 1 );";
			String query2 = "INSERT INTO ourpeople.people_profile (first_name, address, spouse_name, father_name, mother_name, people_id) VALUES('"+signUpDetail.getFirst_name()+"', '"+signUpDetail.getAddress()+"', '"+signUpDetail.getSpouse_name()+"', '"+signUpDetail.getFather_name()+"', '"+signUpDetail.getMother_name()+"', (select id FROM ourpeople.people WHERE email='"+signUpDetail.getEmail()+"')) ;";
			System.out.println(query1);
			@SuppressWarnings({ "rawtypes", "unchecked" })
			List beans = (List) qRunner
					.insert(connection,
							query1,
							new BeanListHandler(SignupDetails.class));
			beans = (List) qRunner
					.insert(connection,
							query2,
							new BeanListHandler(SignupDetails.class));

			@SuppressWarnings({ "rawtypes", "unchecked" })
			List beans1 = (List) qRunner
					.query(connection,
							"select ourpeople.people.id,ourpeople.people.email,ourpeople.people.phone,ourpeople.people.is_verified,ourpeople.people.password,communities.community_name from ourpeople.people INNER JOIN ourpeople.communities ON ourpeople.people.community_id=ourpeople.communities.id where phone='"+signUpDetail.getPhone()+"'",
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
