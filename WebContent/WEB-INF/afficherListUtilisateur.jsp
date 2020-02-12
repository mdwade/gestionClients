<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste Utilisateur</title>
<link rel="stylesheet" href="../css/mycss.css"> 
<link rel="stylesheet" href="../css/afficherUtilisateur.css"> 
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
	
	<h3 style="text-align:center">Liste des clients</h3>
	
	<% //ArrayList<Utilisateur> listUtilisateur = (ArrayList<Utilisateur>)request.getAttribute("listUtilisateur"); %>
	
	<jsp:useBean id="listUtilisateur" class="java.util.ArrayList" scope="request"></jsp:useBean>
	
	<table class="w3-table w3-centered w3-hoverable w3-card">
		<thead>
			<tr>
				<th> ID </th>
				<th> Nom </th>
				<th> Prenom </th>
				<th> Email </th>
				<th> Téléphone </th>
				<th> Adresse </th>
				<th colspan="2" class="action"> Actions </th>
			</tr>
		</thead>
		<tbody>	
			<c:if test="${ listUtilisateur.size() == 0}">
				<p class="emptyMsg"> La liste des clients est vide ! </p>
			</c:if>		
			<c:forEach var="u" items="${ listUtilisateur }">
				<tr>
					<td> ${ u.getId() } </td>
					<td> ${ u.getNom() }</td>
					<td> ${ u.getPrenom() }</td>
					<td> ${ u.getEmail() }</td>
					<td> ${ u.getTelephone() }</td>
					<td> ${ u.getAdresse() } </td>
					<td class="w3-center"><a href="/gestionClients/client/update?id=${ u.getId() }" class="w3-btn w3-blue w3-round-large w3-large"><i class="fa fa-edit"></i></a></td>
					<td class="w3-center"><a href="/gestionClients/client/delete?id=${ u.getId() }" onclick="return confirm('Êtes vous sûr de vouloir supprimer ?')" class="w3-btn w3-red w3-round-large w3-large"><i class="fa fa-trash"></i></a></td>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
</body>
</html>