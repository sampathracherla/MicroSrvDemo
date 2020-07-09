package org.seleniumdbtest.dbtest;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DbTestApplicationTests {

	final String MYSQL_URL = "jdbc:mysql://localhost:3306/msdemo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	Connection mysqlCon = null;
	
	@BeforeAll
	public void load() {
		try {
			this.mysqlCon = DriverManager.getConnection(MYSQL_URL);
		} catch(Exception ex) {
			ex.toString();
		}
	}
	
	@Test
	void contextLoads() {
		
	}

}
