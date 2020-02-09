package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Exception.DaoException;
import beans.Client;


public class DaoClient {
	
	private static final String      ADD_CLIENT            =   "INSERT INTO clients VALUES (0, ?, ?, ?, ?, ?)";
	private static final String      SELECT_ALL_CLIENTS    =   "SELECT * FROM clients";
	private static final String      SELECT_CLIENT         =   "SELECT * FROM clients where id = ?";
	private static final String      DELETE_CLIENT         =   "DELETE FROM clients WHERE id = ?";
	private static final String      UPDATE_CLIENT         =   "UPDATE clients SET nom = ?, prenom = ?, email = ?, adresse = ?, telephone = ? WHERE id = ?";
	private static final Connection  connexion             =   ConnectionManager.getInstance();
	
	
	public static void addClient(Client u) throws DaoException{
		PreparedStatement stmt;
		
		try 
		{
			stmt = (PreparedStatement) connexion.prepareStatement(ADD_CLIENT);
			stmt.setString(1, u.getNom());
			stmt.setString(2, u.getPrenom());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getTelephone());
			stmt.setString(5, u.getAdresse());
			
			stmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new DaoException("Erreur d'excution de la requête", e);
		}
	}
	
	
	public static ArrayList<Client> getClients() throws DaoException {
		ArrayList<Client> listClient = null;
		
		try 
		{
			Statement stmt = connexion.createStatement();
			ResultSet result = stmt.executeQuery(SELECT_ALL_CLIENTS);
			
			listClient = new ArrayList<Client>();
			int id;
			String nom, prenom, email, telephone, adresse;
			
			while(result.next()) {
				id          =   result.getInt("id");
				nom         =   result.getString("nom");
				prenom      =   result.getString("prenom");
				email       =   result.getString("email");
				telephone   =   result.getString("telephone");
				adresse     =   result.getString("adresse");
				
				Client u = new Client(id, nom, prenom, email, telephone, adresse);
				listClient.add(u);
			}
			
			return  listClient;
		}
		catch(SQLException e)
		{
			throw new DaoException("Erreur d'excution de la requête",e);
		}
	}
	
	
	public static void updateClient(Client u) throws DaoException{
		PreparedStatement stmt;
		
		try 
		{
			stmt = (PreparedStatement) connexion.prepareStatement(UPDATE_CLIENT);
			stmt.setString(1, u.getNom());
			stmt.setString(2, u.getPrenom());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getAdresse());
			stmt.setString(5, u.getTelephone());
			stmt.setInt(6, u.getId());
			
			stmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new DaoException("Erreur d'excution de la requête",e);
		}
	}
	
	
	public static void deleteClient(int id) throws DaoException{
		PreparedStatement stmt;
		
		try 
		{
			stmt = (PreparedStatement) connexion.prepareStatement(DELETE_CLIENT);
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			throw new DaoException("Erreur d'exécution de la requête", e);
		}
	}
	
	
	public static Client getClient(int $id) {
		PreparedStatement stmt;
		Client c = null;
		
		try 
		{
			stmt = (PreparedStatement) connexion.prepareStatement(SELECT_CLIENT);
			stmt.setInt(1, $id);
			
			ResultSet result = stmt.executeQuery();
			int id;
			String nom, prenom, email, telephone, adresse;
			
			while(result.next()) 
			{	
				id         =   result.getInt("id");
				nom        =   result.getString("nom");
				prenom     =   result.getString("prenom");
				email      =   result.getString("email");
				telephone  =   result.getString("telephone");
				adresse    =   result.getString("adresse");
				
				c = new Client($id, nom, prenom, email, telephone, adresse);
			}
			
			return c;
			
		}
		catch(SQLException e) 
		{
			throw new DaoException("Erreur d'excution de la requête",e);
		}
	}
}
