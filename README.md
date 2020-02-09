# Application de gestion client
Technologie utilisée : <b>```JEE```</b> </br>
Serveur d'application : <b>```TOMCAT```</b>

Le projet consiste à créer une plateforme de gestion client sous <b>```JEE```</b>.
Implémentation des opérations <b>```CRUD```</b>, interaction avec une base de données <b>```SQL```</b> via <b>```JDBC```</b>. 

## Architecture du projet
Pour une meilleure structuration du codes, l'application est découpée en couches qui correspondent à nos packages. Nous avons 5 packages :
<ul>
  <li><b>Beans : </b> contient les classes entité ou model de l'application</li>
  <li><b>Dao</b> : contient les classes qui nous permettent de communiquer avec notre base de données. En d'autres termes cette couche est chargée de persister les données dans la base de données.</li>
  <li><b>Metier : </b> les classes de cette couche servent à vérifier les données entrées avant leur persistance en base.  </li>
  <li><b>Servlets : </b>les classes de cette couche sont les controlleurs. Ils permettent de lier le model et la vue.</li>
  <li><b>Exception : </b> sert à la gestion des exceptions.</li>
</ul>


## A noter
A la racine du projet, vous trouverez un script ```gestionUtilisateur.sql``` pour créer la base de données

Après importation du projet et de la base de données, démarrez le serveur d'application et sur votre naviagteur tapez :

```
https://localhost:9000/gestionClients
```
pour ouvrir la page d'accueil.
