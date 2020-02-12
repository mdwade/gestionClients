package metier;

import java.util.ArrayList;

import beans.Client;

public class MetierClients {
	
	public static boolean checkIfEmpty(String data) 
	{
		Boolean isNotEmpty = false;
		
		if (data.length() == 0 || data.length() == 1) 
		{
			isNotEmpty = true;
		}
			
		return isNotEmpty;
	}
	
	
	public static boolean validatePhoneNumber(String telephone) 
	{
		Boolean valide = false;
		
		// Phone number like 774741740 or +221774741740
		String numberPattern = "^[7]+[0-6-7-8]+[0-9]{7}|^[\"+\"2217]+[0-6-7-8]+[0-9]{7}";
		
		if(telephone.matches(numberPattern)) 
		{
			
			valide = true;
		}		
		
		return valide;
	}
	
	
	public static boolean CheckIfTelephoneExist(String telephone, ArrayList<Client> listClient) 
	{
		Boolean exist = false;
		
		for(Client c : listClient) {
			if(!telephone.equals(c.getTelephone())) 
			{
				exist = true;
			}
			break;
		}
		return exist;
	}
	
	
	public static boolean validateEmailAddress(String email) 
	{
		Boolean valide = false;
		
		String emailPattern = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";
		
		if(email.matches(emailPattern)) 
		{
			
			valide = true;
		}		
		
		return valide;
	}
	
	
	public static boolean CheckIfEmailExist(String email, ArrayList<Client> listClient) 
	{
		Boolean exist = false;
		
		for(Client c : listClient) {
			if(!email.equals(c.getEmail())) 
			{
				exist = true;
			}
			break;
		}
		return exist;
	}
}
