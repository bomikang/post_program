package kr.or.dgit.bigdata.post_program.test;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.bigdata.post_program.setting.ConnectionFactory;

public class ConnectionFactoryTest {
	private static Connection instance;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		instance = ConnectionFactory.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		instance = null;
	}

	@Test
	public void testConnectionFactory() {
		Assert.assertNotNull(instance);
	}

}
