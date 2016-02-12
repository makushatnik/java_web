package org.ajax.jboss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AjaxFormServlet
 */
public class AjaxFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AjaxFormServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		String catalogId = request.getParameter("catalogId");
		PrintWriter out = response.getWriter();
		InitialContext ic = new InitialContext();
		DataSource ds = (DataSource)ic.lookup("java:jboss/datasource/MySQLDS");
		Connection conn = ds.getConnection();
		
		String query = "SELECT c.catalog_id FROM catalog as c WHERE c.id=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, catalogId);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			out.println("<catalog><valid>false</valid><journal>" +
					rs.getString(2) + "</journal><publisher>" +
					rs.getString(3) + "</publisher><edition>" +
					rs.getString(4) + "</edition><title>" +
					rs.getString(5) + "</title><author>" +
					rs.getString(6) + "</catalog>");
		} else {
			out.println("<valid>true</true>");
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		rs.close();
		ps.close();
		conn.close();
		} catch (NamingException | SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:jboss/datasource/MySQLDS");
			Connection conn = ds.getConnection();
			
			String catalogId = request.getParameter("catalogId");
			String journal = request.getParameter("journal");
			String publisher = request.getParameter("publisher");
			String edition = request.getParameter("edition");
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			
			Statement stat = conn.createStatement();
			String sql = "INSERT INTO catalog VALUES(\'"+catalogId+"\',\'"+journal+"\',\'"+
					publisher+"\',\'"+edition+"\',\'"+title+"\',\'"+author+"\'";
			
			stat.executeQuery(sql);
			
			response.sendRedirect("created.jsp");
			
			stat.close();
			conn.close();
		} catch (NamingException |SQLException e) {
			response.sendRedirect("error.jsp");
		}
	}

}
