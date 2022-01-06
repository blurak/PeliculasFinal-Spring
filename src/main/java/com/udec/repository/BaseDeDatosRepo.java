package com.udec.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatosRepo {
	String driver = "org.postgresql.Driver";
	String url = "jdbc:postgresql://localhost:5432/peliculas";
	String user, contra, aviso1, aviso2;
	int indica1, indica2;
	Connection con = null;
	Statement stmt = null;

	public BaseDeDatosRepo(String user, String contra) {
		this.user = user;
		System.out.println("Contraseña=" + contra);
		indica1 = 0;
		indica2 = 0;
		try {
			Class.forName(driver).newInstance();
			System.out.println("Se ha cargado correctamente el driver:" + driver);
			aviso1 = "Se ha cargado correctamente el driver: " + driver;
			indica1 = 1;
		} catch (Exception e) {
			System.out.println("Se ha producido el siguiente error al cargar el driver:\n" + e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, user, contra);
			System.out.println("Se ha conectado correctamente a la base de datos");
			System.out.println("Conection ==>" + (Object) con);
			aviso1 = "Se ha conectado correctamente a la base de datos";
			indica2 = 1;
		} catch (Exception e) {
			System.out.println("Se ha producido un error al momento de conectar la base de datos: \n" + e.getMessage());
		}
		if (indica2 == 1) {
			try {
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			} catch (SQLException e) {
				System.out.println("Error Creando el Statement= " + e.getMessage());
			}
		} else {
			System.out.println("Invalida usuario o contraseña");
		}
	}

	Connection con() {
		return con;
	}

	Statement stamt() {
		return stmt;
	}

	String rAviso1() {
		return aviso1;
	}

	String rAviso2() {
		return aviso2;
	}

	int indica_1() {
		return indica1;
	}

	int indica_2() {
		return indica2;
	}
}
