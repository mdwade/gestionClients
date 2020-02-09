package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Exception.DaoException;


public class ConnectionManager {
	
	private static String DB_URL           = "jdbc:mysql://localhost/gestionUtilisateur";
	private static String DB_USER          = "root";
	private static String DB_PASSWORD      = "";
	private static Connection connexion    = null;
	
	public ConnectionManager() {
	}
	
	public static Connection getInstance() throws DaoException {
		
		if(connexion == null) {
			try 
			{
				Class.forName( "com.mysql.jdbc.Driver" );
				connexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			} 
			catch ( ClassNotFoundException e ) 
			{
				throw new DaoException("Erreur de chargement du pilote.", e);
			}
			catch (SQLException e) 
			{
				throw new DaoException("Impossible d'accéder à la base de données.", e);
			}
			
		}
		return connexion;
	}
	
}
