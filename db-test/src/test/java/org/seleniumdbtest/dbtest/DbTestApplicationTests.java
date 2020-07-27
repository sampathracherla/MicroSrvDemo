package org.seleniumdbtest.dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@SpringBootTest
class DbTestApplicationTests {

	final static String MYSQL_URL = "jdbc:mysql://localhost:3306/msdemo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	final static String MONGO_URL = "mongodb+srv://msdemouser:msdemopass@cluster0.5f0bw.mongodb.net";

	static Connection mysqlCon = null;
	static MongoCollection<Document> collection = null;

	@BeforeAll
	public static void load() {
		try {
			mysqlCon = DriverManager.getConnection(MYSQL_URL);
			ConnectionString mongoCon = new ConnectionString(MONGO_URL);
			MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(mongoCon)
					.retryWrites(true).build();
			MongoClient mongoClient = MongoClients.create(settings);
			MongoDatabase database = mongoClient.getDatabase("sample_analytics");
			collection = database.getCollection("accounts");
		} catch (Exception ex) {
			ex.toString();
		}
	}

	@Test
	void contextLoads() {
		try {
			Statement stmt = mysqlCon.createStatement();
			ResultSet rs = stmt.executeQuery("select * from accounts order by account_id asc;");
			MongoCursor<Document> accounts = collection.find().sort(new BasicDBObject("account_id", 1)).cursor();

			while (rs.next() && accounts.hasNext()) {
				Document doc = accounts.next();
				Assert.isTrue(rs.getString("_id").equals(doc.get("_id").toString()), "_ID is equal");
				Assert.isTrue(rs.getInt("account_id") == doc.getInteger("account_id"), "account_id is equal");
				Assert.isTrue(rs.getInt("account_limit") == doc.getInteger("limit"), "limit is equal");
				if (!rs.getString("_id").equals(doc.get("_id").toString())) {
					System.out.println("_id did not match ");
					System.out.println("MYSQL _id:" + rs.getString("_id"));
					System.out.println("MongoDB _id:" + doc.get("_id").toString());
				}
				if (rs.getInt("account_id") != doc.getInteger("account_id")) {
					System.out.println("account_id did not match ");
					System.out.println("MYSQL account_id:" + rs.getInt("account_id"));
					System.out.println("MongoDB _id:" + doc.getInteger("account_id"));
				}
				if (rs.getInt("account_limit") != doc.getInteger("limit")) {
					System.out.println("limit did not match ");
					System.out.println("MYSQL account_id:" + rs.getInt("account_limit"));
					System.out.println("MongoDB _id:" + doc.getInteger("limit"));
				}
			}
		} catch (Exception ex) {
			ex.toString();
		}
	}

}
