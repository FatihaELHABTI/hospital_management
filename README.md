# Système de Gestion Hospitalière

## Description
Le **Système de Gestion Hospitalière** est une application web développée avec **Spring Boot**, **Spring Data JPA**, **Spring Security**, et **Thymeleaf** pour la gestion des patients dans un hôpital. L'application permet de gérer les informations des patients (ajout, suppression, modification, recherche) et inclut une gestion sécurisée des utilisateurs avec des rôles (USER et ADMIN). Elle utilise une base de données relationnelle (MySQL ou H2 pour le développement) et fournit une interface utilisateur moderne avec Bootstrap.

## Fonctionnalités
- **Gestion des patients** :
  - Lister les patients avec pagination et recherche par nom.
  - Ajouter un nouveau patient (réservé aux administrateurs).
  - Modifier les informations d'un patient existant (réservé aux administrateurs).
  - Supprimer un patient (réservé aux administrateurs).
- **Gestion des utilisateurs et sécurité** :
  - Authentification des utilisateurs avec login/logout.
  - Gestion des rôles (USER et ADMIN) pour restreindre l'accès aux fonctionnalités.
  - Protection des mots de passe avec **BCrypt**.
  - Gestion des sessions avec une fonctionnalité "Remember Me".
  - Page d'erreur pour les accès non autorisés.
- **Interface utilisateur** :
  - Interface responsive utilisant **Bootstrap 5.3.3** et **Bootstrap Icons**.
  - Formulaires pour l'ajout et la modification des patients avec validation des données.
  - Barre de navigation avec menu déroulant pour les actions des patients et la déconnexion.

## Architecture du Projet
Le projet suit une architecture MVC (Modèle-Vue-Contrôleur) avec les packages suivants :
- **ma.enset.hospital_system.entities** : Contient les entités JPA (`Patient`, `AppUser`, `AppRole`).
- **ma.enset.hospital_system.repository** : Interfaces de repository pour les opérations CRUD sur les entités.
- **ma.enset.hospital_system.security** : Configuration de la sécurité (Spring Security) et gestion des utilisateurs/rôles.
- **ma.enset.hospital_system.web** : Contrôleurs web pour gérer les requêtes HTTP et les vues Thymeleaf.
- **ma.enset.hospital_system** : Classe principale de l'application et configuration initiale.

### Technologies Utilisées
- **Backend** :
  - Spring Boot 3.x
  - Spring Data JPA
  - Spring Security
  - Hibernate
- **Frontend** :
  - Thymeleaf
  - Bootstrap 5.3.3
  - Bootstrap Icons 1.11.3
- **Base de données** :
  - MySQL (production)
  - H2 (développement, optionnel)
- **Autres** :
  - Maven pour la gestion des dépendances
  - BCrypt pour l'encodage des mots de passe

## Prérequis
- **Java** : Version 17 ou supérieure
- **Maven** : Version 3.6 ou supérieure
- **MySQL** : Version 8.x (ou H2 pour les tests)
- **Navigateur web** : Chrome, Firefox, ou tout autre navigateur moderne

## Installation
1. **Cloner le projet** :
   ```bash
   git clone <URL-du-dépôt>
   cd hospital_system
   ```

2. **Configurer la base de données** :
   - Créez une base de données MySQL nommée `hospital-db`.
   - Mettez à jour les informations de connexion dans `src/main/resources/application.properties` :
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/hospital-db?createDatabaseIfNotExist=true
     spring.datasource.username=root
     spring.datasource.password=<votre-mot-de-passe>
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
     ```

3. **Construire le projet** :
   ```bash
   mvn clean install
   ```

4. **Lancer l'application** :
   ```bash
   mvn spring-boot:run
   ```

5. **Accéder à l'application** :
   - Ouvrez un navigateur et allez à `http://localhost:8084`.
   - Connectez-vous avec les identifiants par défaut :
     - Utilisateur : `user1` ou `user2` (mot de passe : `1234`, rôle : USER)
     - Admin : `admin` (mot de passe : `1234`, rôles : USER, ADMIN)

## Utilisation
1. **Connexion** :
   - Accédez à la page de connexion (`/login`) et entrez vos identifiants.
   - Cochez "Remember Me" pour une connexion persistante.
2. **Gestion des patients** :
   - **Utilisateurs (rôle USER)** : Peuvent voir la liste des patients et effectuer une recherche par nom.
   - **Administrateurs (rôle ADMIN)** : Peuvent ajouter, modifier, ou supprimer des patients via les options du menu déroulant.
3. **Déconnexion** :
   - Utilisez le menu déroulant en haut à droite pour vous déconnecter.

## Détails Techniques
- **Entités** :
  - `Patient` : Représente un patient avec des champs comme `id`, `nom`, `dateNaissance`, `malade`, et `score`.
  - `AppUser` : Représente un utilisateur avec `userId`, `username`, `email`, `password`, et une liste de rôles.
  - `AppRole` : Représente un rôle avec un champ `role` (ex. USER, ADMIN).
- **Repositories** :
  - `PatientRepository` : Fournit des méthodes pour rechercher les patients par nom avec pagination.
  - `AppUserRepository` et `AppRoleRepository` : Gèrent les utilisateurs et les rôles.
- **Sécurité** :
  - Utilise **Spring Security** pour l'authentification et l'autorisation.
  - Les routes `/admin/*` sont restreintes aux utilisateurs avec le rôle ADMIN.
  - Les mots de passe sont encodés avec **BCrypt**.
- **Vues** :
  - Les templates Thymeleaf (`patients.html`, `formPatients.html`, `editPatient.html`, `login.html`, `notAuthorized.html`) gèrent l'affichage.
  - La navigation est centralisée dans `template1.html` avec une barre de navigation responsive.

## Structure des Fichiers
```
hospital_system/
├── src/
│   ├── main/
│   │   ├── java/ma/enset/hospital_system/
│   │   │   ├── entities/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   │   ├── entities/
│   │   │   │   ├── repositories/
│   │   │   │   ├── services/
│   │   │   ├── web/
│   │   │   └── HospitalSystemApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── patients.html
│   │       │   ├── formPatients.html
│   │       │   ├── editPatient.html
│   │       │   ├── login.html
│   │       │   ├── notAuthorized.html
│   │       │   └── template1.html
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Améliorations Possibles
- Ajouter des tests unitaires et d'intégration.
- Implémenter une gestion des erreurs plus robuste.
- Ajouter des fonctionnalités comme la gestion des rendez-vous ou des dossiers médicaux.
- Améliorer l'interface utilisateur avec des graphiques ou des statistiques.

