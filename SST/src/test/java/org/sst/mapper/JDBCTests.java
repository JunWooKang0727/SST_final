package org.sst.mapper;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Connection 객체가 출력되면 정상적으로 데이터 베이스에 연결했다는 것이다.
	@Test
	public void testConnection(){
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "kosta211", "1234")) {
			log.info(con);
		} catch(Exception e){
			fail(e.getMessage());
		}
	}
}
