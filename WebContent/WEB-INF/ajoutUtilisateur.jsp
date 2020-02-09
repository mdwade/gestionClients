<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter client</title>
<link rel="stylesheet" href="../css/mycss.css"> 
<link rel="stylesheet" href="../css/ajoutUtilisateur.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	
	<div class="w3-container w3-row w3-blue w3-padding-16">
		<div class="w3-col m8 l9">
		    <h3><a href="/gestionClients">Plateforme de gestion clients</a></h3>
		 </div>
		 <div class="w3-col m4 l3 lien">
		    <p>
		    	<a href="/gestionClients/client/add"><i class="fa fa-plus"></i> Ajouter un client</a>
		    	<a href="/gestionClients/client/list"><i class="fa fa-bars"></i> Liste des clients</a>
		    </p>
		 </div>
		
	</div>
	
	<h1 class="w3-center w3-text-white" style="margin-top: 5%">Saisir les informations du client</h1>
	
		<form method="post">
			<div class="container">
			
					<div class="row">
						<div class="col-25">
							<label for="fname">Nom</label>
						</div>
						<div class="col-75">
							<input type="text" id="fname" name="nom"
								value="${ nom }" placeholder="Tapez le nom...">
						</div>
					</div>
					<div class="row">
						<div class="col-25">
							<label for="lname">Prénoms</label>
						</div>
						<div class="col-75">
							<input type="text" id="lname" name="prenom"
								value="${ prenom }" placeholder="Tapez le prenom...">
						</div>
					</div>
					
					<div class="row">
						<div class="col-25">
							<label for="fname">Email</label>
						</div>
						<div class="col-75">
							<input type="text" id="fname" name="email" 
								value="${ email }" placeholder="Tapez l'adresse email...">
								<span class="erreur"><i>${erreurs['emailFormatError']}</i></span>
								<span class="erreur"><i>${erreurs['emailAlreadyExistError']}</i></span>
						</div>
					</div>
					
					<div class="row">
						<div class="col-25">
							<label for="subject">Téléphone</label>
						</div>
						<div class="col-75">
							<input type="text" id="lname" name="telephone" 
								value="${ telephone }" placeholder="Tapez le numéro...">
								<span class="erreur"><i>${erreurs['telephoneFormatError']}</i></span>
								<span class="erreur"><i>${erreurs['telephoneAlreadyExistError']}</i></span>
						</div>
					</div>
					
					<div class="row">
						<div class="col-25">
							<label for="subject">Adresse</label>
						</div>
						<div class="col-75">
							<input type="text" id="lname" name="adresse" 
								value="${ adresse }" placeholder="Tapez l'adresse...">
						</div>
					</div>
					
					<div class="row">
						<input type="submit" value="Enregistrer">
					</div>
					
				
			
			</div>			
		</form>
		
		<div class="footer txt-shadow">
		  <p class="w3-small">CopyRight m2glsi-jee 2020 | Diop Wade</p>
		</div> 
</body>
</html>