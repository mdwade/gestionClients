package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Client;
import dao.DaoClient;
import metier.MetierClients;

/**
 * Servlet implementation class AjoutUtilisateur
 */
@WebServlet({"/client/add", "/client/list", "/client/delete", "/client/update"})
public class AjoutClient extends HttpServlet 
{
	private static final long serialVersionUID                          = 1L;
	public  static ArrayList<Client> listUser                           = new ArrayList<Client>();
	
	private static final String VUE_AJOUT_UTILISATEUR                   = "/WEB-INF/ajoutUtilisateur.jsp";
	private static final String VUE_LIST_UTILISATEUR                    = "/WEB-INF/afficherListUtilisateur.jsp";
	public  static final String TELEPHONE_FORMAT_ERROR_MESSAGE          = "Le numéro de téléphone saisi est incorrecte! Exemple de format accepté : 776280010 ou +221776280010.";
	public  static final String TELEPHONE_ALREADY_EXIST_ERROR_MESSAGE   = "Ce numéro de téléphone existe déjà.";
	public  static final String EMAIL_FORMAT_ERROR_MESSAGE              = "L'adresse email saisie est incorrecte! Exemple de format accepté : johndoe@gmail.com";
	public  static final String EMAIL_ALREADY_EXIST_ERROR_MESSAGE       = "Cette adresse email existe déjà.";
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		request.setCharacterEncoding("utf-8");
		
		switch(path){
		
			case "/client/add":
				getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR).forward(request, response);
				
				break;
			
			case "/client/list":
				request.setAttribute("listUtilisateur", DaoClient.getClients());
				getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR).forward(request, response);
				
				break;
				
			case "/client/delete":
				int id = Integer.parseInt(request.getParameter("id"));
				DaoClient.deleteClient(id);											
				
				response.sendRedirect("list");				
				break;
			
			case "/client/update":
				int idClient = Integer.parseInt(request.getParameter("id"));
				Client u = DaoClient.getClient(idClient);
				
				if(u != null) 
				{			
					request.setAttribute("nom", u.getNom());
					request.setAttribute("prenom", u.getPrenom());
					request.setAttribute("email", u.getEmail());
					request.setAttribute("telephone", u.getTelephone());
					request.setAttribute("adresse", u.getAdresse());									
				}
				
				getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR).forward(request, response);
				break;
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String nom                    =  request.getParameter("nom");
		String prenom                 =  request.getParameter("prenom");
		String email                  =  request.getParameter("email");
		String telephone              =  request.getParameter("telephone");
		String adresse                =  request.getParameter("adresse");
		 
		String path                   =  request.getServletPath();
		Map<String, String> erreurs   =  new HashMap<String, String>();
		
		switch (path) 
		{
			
			case "/client/add":			
				
				erreurs = renderError("/client/add", telephone, email);
				
				if(erreurs.isEmpty()) 
				{
					DaoClient.addClient(new Client(0, nom, prenom, email, telephone, adresse));
					request.setAttribute("listUtilisateur", DaoClient.getClients());
					getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR).forward(request, response);
				}
				else 
				{
					request.setAttribute("erreurs", erreurs);
					request.setAttribute("nom", nom);
					request.setAttribute("prenom", prenom);
					request.setAttribute("email", email);
					request.setAttribute("telephone", telephone);
					request.setAttribute("adresse", adresse);
					
					getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR).forward(request, response);					
				}
				
				break;
	
				
			case "/client/update":		
				int id = Integer.parseInt(request.getParameter("id"));
				erreurs = renderError("/client/update", telephone, email);
				
				if(erreurs.isEmpty()) 
				{
					DaoClient.updateClient(new Client(id, nom, prenom, email, telephone, adresse));											
					request.setAttribute("listUtilisateur", DaoClient.getClients());
					getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR).forward(request, response);
				}
				else 
				{
					request.setAttribute("erreurs", erreurs);
					request.setAttribute("nom", nom);
					request.setAttribute("prenom", prenom);
					request.setAttribute("email", email);
					request.setAttribute("telephone", telephone);
					request.setAttribute("adresse", adresse);
					
					getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR).forward(request, response);					
				}
				
				
				
				break;
				
			default:
				break;
		}			
		
	}
	
	public Map<String, String> renderError(String path, String telephone, String email) {
		
		Map<String, String> erreurs   =  new HashMap<String, String>();
		
		if("/client/add".equals(path)) {
			if(!MetierClients.validatePhoneNumber(telephone)) 
			{
				erreurs.put("telephoneFormatError", TELEPHONE_FORMAT_ERROR_MESSAGE);
			}
			
			if(!MetierClients.CheckIfTelephoneExist(telephone, DaoClient.getClients())) 
			{
				erreurs.put("telephoneAlreadyExistError", TELEPHONE_ALREADY_EXIST_ERROR_MESSAGE);
			}
			
			if(!MetierClients.validateEmailAddress(email))
			{
				erreurs.put("emailFormatError", EMAIL_FORMAT_ERROR_MESSAGE);
			}
			
			if(!MetierClients.CheckIfEmailExist(email, DaoClient.getClients())) 
			{
				erreurs.put("emailAlreadyExistError", EMAIL_ALREADY_EXIST_ERROR_MESSAGE);
			}
		}
		else 
		{
			if(!MetierClients.validatePhoneNumber(telephone)) 
			{
				erreurs.put("telephoneFormatError", TELEPHONE_FORMAT_ERROR_MESSAGE);
			}
			
			if(!MetierClients.validateEmailAddress(email))
			{
				erreurs.put("emailFormatError", EMAIL_FORMAT_ERROR_MESSAGE);
			}
			
		}
		
		
		return erreurs;
	}

}
