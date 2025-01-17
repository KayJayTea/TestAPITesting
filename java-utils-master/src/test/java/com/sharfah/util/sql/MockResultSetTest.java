package com.sharfah.util.sql;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class MockResultSetTest {

	private ResultSet rs;
	private ResultSet emptyRs;

	@Before
	public void setUp() throws SQLException {
		rs = MockResultSet.create(new String[] { "name", "age" },
				new Object[][] { { "Alice", 20 }, { "Bob", 35 }, { "Charles", 50 } });
		emptyRs = MockResultSet.create(new String[] { "col1" }, new Object[][] {});
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testNext() throws SQLException {
		assertThat(emptyRs.next(), is(false));
		assertThat(rs.next(), is(true));
		assertThat(rs.next(), is(true));
		assertThat(rs.next(), is(true));
		assertThat(rs.next(), is(false));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetString() throws SQLException {
		rs.next();
		assertThat(rs.getString("name"), is("Alice"));
		rs.next();
		assertThat(rs.getString("name"), is("Bob"));
		rs.next();
		assertThat(rs.getString("name"), is("Charles"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetStringByIndex() throws SQLException {
		rs.next();
		assertThat(rs.getString(1), is("Alice"));
		rs.next();
		assertThat(rs.getString(1), is("Bob"));
		rs.next();
		assertThat(rs.getString(1), is("Charles"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetInt() throws SQLException {
		rs.next();
		assertThat(rs.getInt("age"), is(20));
		rs.next();
		assertThat(rs.getInt("age"), is(35));
		rs.next();
		assertThat(rs.getInt("age"), is(50));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetObjectByIndex() throws SQLException {
		rs.next();
		assertThat(rs.getObject(1), is("Alice"));
		assertThat(rs.getObject(2), is(20));
		rs.next();
		assertThat(rs.getObject(1), is("Bob"));
		assertThat(rs.getObject(2), is(35));
		rs.next();
		assertThat(rs.getObject(1), is("Charles"));
		assertThat(rs.getObject(2), is(50));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetObject() throws SQLException {
		rs.next();
		assertThat(rs.getObject("name"), is("Alice"));
		assertThat(rs.getObject("age"), is(20));
		rs.next();
		assertThat(rs.getObject("name"), is("Bob"));
		assertThat(rs.getObject("age"), is(35));
		rs.next();
		assertThat(rs.getObject("name"), is("Charles"));
		assertThat(rs.getObject("age"), is(50));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testMetaData() throws SQLException {
		assertThat(rs.getMetaData().getColumnCount(), is(2));
		assertThat(rs.getMetaData().getColumnName(1), is("name"));
		assertThat(rs.getMetaData().getColumnName(2), is("age"));
		assertThat(emptyRs.getMetaData().getColumnCount(), is(1));
		assertThat(emptyRs.getMetaData().getColumnName(1), is("col1"));
	}

}
