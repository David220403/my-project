/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {

	private static Connection mysqlconfig;

	public static Connection configDB() throws SQLException {
		try {
			String url, user, pass;
			url = "jdbc:mysql://localhost/db_skincare";
			user = "root";
			pass = "";
			mysqlconfig = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Database tidak terhubung");
		}
		return mysqlconfig;
	}
}
