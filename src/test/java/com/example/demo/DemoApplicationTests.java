package com.example.demo;

import com.example.demo.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DemoApplicationTests {
	@Mock
	UrlJpa mockUrlJpa;

	@Mock
	ConversionClass mockConversionClass;

	@InjectMocks
	ConversionSerivce mockConversionService;

	/*
	JDBC Connection
	 */
	public static void main(String args[]){
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/urlshortener","root","Passw0rd");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from emp");
		while(rs.next())
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		con.close();
	}catch(Exception e){ System.out.println(e);}
	}

	/*
		Test to get the shorter URL
 	*/
	@Test
	public void getShorterUrlTest() {
		var urlObj = new UrlObj();
		urlObj.setFullUrl("https://www.neueda.com/");
		urlObj.setIdNum(1);

		when(mockUrlJpa.save(any(UrlObj.class))).thenReturn(urlObj);
		when(mockConversionClass.encodeUrlMethod(urlObj.getIdNum())).thenReturn("p");

		var urlPostRequest = new UrlPost();
		urlPostRequest.setFullUrl("https://www.neueda.com/");

		assertEquals("f", mockConversionService.convertToShortMethod(urlPostRequest));
	}

	/*
	Test to get the full url
	 */
	@Test
	public void getFullUrl() {
		when(mockConversionClass.decodeUrlMethod("x")).thenReturn((long) 7);

		var urlObj = new UrlObj();
		urlObj.setFullUrl("https://www.neueda.com/");
		urlObj.setIdNum(2);

		when(mockUrlJpa.findById((long) 2)).thenReturn(java.util.Optional.of(urlObj));
		assertEquals("https://www.neueda.com/", mockConversionService.getOriginalUrl("n"));

	}
}
