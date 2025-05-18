Système de Gestion Hospitalière
Bienvenue dans le projet Système de Gestion Hospitalière, une application web développée pour gérer les informations des patients et les utilisateurs avec un système d'authentification sécurisé. Ce projet utilise une architecture basée sur Spring Boot et fournit une interface utilisateur intuitive pour les administrateurs et les utilisateurs.
Table des Matières

Description du Projet
Fonctionnalités
Technologies Utilisées
Concepts Clés
Installation et Configuration
Exemples de Code
Structure du Projet
Contribuer
Licence

Description du Projet
Le Système de Gestion Hospitalière est une application web conçue pour :

Gérer les informations des patients (nom, date de naissance, état de santé, score).
Fournir un système d'authentification et d'autorisation basé sur des rôles (USER et ADMIN).
Permettre aux administrateurs de créer, modifier et supprimer des patients.
Offrir une interface utilisateur responsive utilisant Bootstrap et Thymeleaf.

L'application utilise une base de données relationnelle (MySQL ou H2 pour le développement) et est construite avec des technologies modernes de Spring.
Fonctionnalités

Gestion des Patients :
Ajouter, modifier, supprimer et rechercher des patients.
Validation des données saisies (par exemple, nom non vide, score minimum).


Authentification et Autorisation :
Connexion sécurisée avec Spring Security.
Rôles USER (accès limité) et ADMIN (accès complet).
Fonctionnalité "Remember Me" pour une connexion persistante.


Interface Utilisateur :
Pages web dynamiques avec Thymeleaf.
Navigation intuitive avec une barre de navigation Bootstrap.
Pagination et recherche par mot-clé pour la liste des patients.


Base de Données :
Persistance des données avec JPA et Hibernate.
Support pour MySQL en production et H2 pour les tests.



Technologies Utilisées

Java 17 : Langage de programmation principal.
Spring Boot 3.4.3 : Framework pour simplifier le développement d'applications Java.
Spring Data JPA : Pour l'accès et la gestion des données relationnelles.
Hibernate : Implémentation de JPA pour la persistance des objets.
Spring Security : Pour l'authentification et l'autorisation.
Thymeleaf : Moteur de template pour générer des pages web dynamiques.
Bootstrap 5.3.3 : Framework CSS pour une interface responsive.
MySQL : Base de données relationnelle (configurable avec H2 pour les tests).
Maven : Gestion des dépendances et construction du projet.
Lombok : Pour réduire le code boilerplate (getters, setters, constructeurs).

Concepts Clés
JPA (Java Persistence API)
JPA est une spécification Java pour la gestion des données relationnelles dans les applications Java. Elle fournit une API standard pour mapper des objets Java à des tables de base de données et effectuer des opérations CRUD (Create, Read, Update, Delete).
Exemple : Dans l'entité Patient, les annotations JPA comme @Entity, @Id, et @GeneratedValue sont utilisées pour mapper la classe à une table de base de données.
Hibernate
Hibernate est une implémentation open-source de JPA. Il agit comme une couche d'abstraction entre l'application Java et la base de données, gérant automatiquement les requêtes SQL et la persistance des objets.
Caractéristiques :

Mapping objet-relationnel (ORM).
Gestion des transactions.
Cache de premier et deuxième niveau pour améliorer les performances.

Exemple : Hibernate est configuré dans le fichier application.properties avec des propriétés comme spring.jpa.hibernate.ddl-auto=update.
Spring Security
Spring Security est un framework puissant pour sécuriser les applications Spring. Il gère l'authentification (qui est l'utilisateur ?) et l'autorisation (que peut faire l'utilisateur ?).
Exemple : La classe SecurityConfig configure les règles d'accès, comme restreindre certaines URL aux utilisateurs ayant le rôle ADMIN.
Thymeleaf
Thymeleaf est un moteur de template côté serveur qui permet de générer des pages HTML dynamiques. Il s'intègre bien avec Spring Boot et prend en charge les expressions pour afficher des données dynamiques.
Exemple : La page patients.html utilise des expressions Thymeleaf comme th:each pour afficher une liste de patients.
Installation et Configuration
Prérequis

Java 17 ou supérieur.
Maven pour gérer les dépendances.
MySQL (ou utilisez H2 pour les tests).
Un IDE comme IntelliJ IDEA ou Eclipse.

Étapes d'Installation

Cloner le dépôt :
git clone https://github.com/votre-utilisateur/hospital_system.git
cd hospital_system


Configurer la base de données :

Pour MySQL, créez une base de données nommée hospital-db.
Mettez à jour le fichier src/main/resources/application.properties avec vos informations de connexion :spring.datasource.url=jdbc:mysql://localhost:3306/hospital-db?createDatabaseIfNotExist=true
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe




Construire et exécuter l'application :
mvn clean install
mvn spring-boot:run


Accéder à l'application :

Ouvrez votre navigateur et allez à http://localhost:8084/.
Connectez-vous avec les identifiants par défaut :
Utilisateur : user1, Mot de passe : 1234 (rôle USER).
Admin : admin, Mot de passe : 1234 (rôles USER et ADMIN).





Exemples de Code
Entité Patient
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min=4, max=40)
    private String nom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private boolean malade;
    @DecimalMin("100")
    private int score;
}

Explication : Cette classe définit une entité Patient avec des contraintes de validation (par exemple, nom doit avoir entre 4 et 40 caractères).
Configuration de Spring Security
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(ar -> ar.loginPage("/login").defaultSuccessUrl("/").permitAll())
                .rememberMe(rm -> rm.key("remember-me-key").tokenValiditySeconds(40000))
                .exceptionHandling(ar -> ar.accessDeniedPage("/notAuthorized"))
                .userDetailsService(userDetailsServiceImpl)
                .authorizeHttpRequests(ar -> ar
                        .requestMatchers("/webjars/**", "/css/**", "/js/**", "/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }
}

Explication : Cette configuration définit une page de connexion personnalisée, active la fonctionnalité "Remember Me", et restreint l'accès aux utilisateurs authentifiés.
Page Thymeleaf pour la Liste des Patients
<table class="table">
    <thead>
        <tr>
            <th>Id</th><th>Nom</th><th>Malade</th><th>Date</th><th>Score</th>
        </tr>
    </thead>
    <tbody>
        <tr th:each="p:${patients}">
            <td th:text="${p.id}"></td>
            <td th:text="${p.nom}"></td>
            <td th:text="${p.malade}"></td>
            <td th:text="${p.dateNaissance}"></td>
            <td th:text="${p.score}"></td>
            <td th:if="${#authorization.expression('hasRole(''ADMIN'')')}">
                <a th:href="@{/admin/deletePatient(id=${p.id}, keyword =${keyword}, page=${currentPage})}" class="btn btn-danger">
                    <i class="bi bi-trash"></i>
                </a>
            </td>
        </tr>
    </tbody>
</table>

Explication : Cette page affiche une table des patients avec des boutons de suppression visibles uniquement pour les administrateurs.
Structure du Projet
hospital_system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ma/enset/hospital_system/
│   │   │       ├── entities/           # Entités JPA (Patient, AppUser, AppRole)
│   │   │       ├── repository/         # Interfaces de repositories JPA
│   │   │       ├── security/           # Configuration de Spring Security
│   │   │       ├── web/                # Contrôleurs pour les pages web
│   │   │       └── HospitalSystemApplication.java # Classe principale
│   │   ├── resources/
│   │   │   ├── templates/              # Pages Thymeleaf (HTML)
│   │   │   ├── application.properties  # Configuration de l'application
│   │   │   └── static/                 # Ressources statiques (CSS, JS)
│   └── test/                           # Tests unitaires
├── pom.xml                             # Fichier de configuration Maven
└── README.md                           # Ce fichier

Contribuer
Les contributions sont les bienvenues ! Pour contribuer :

Forkez le dépôt.
Créez une branche pour votre fonctionnalité (git checkout -b feature/nouvelle-fonctionnalite).
Commitez vos changements (git commit -m 'Ajout de nouvelle fonctionnalité').
Poussez votre branche (git push origin feature/nouvelle-fonctionnalite).
Ouvrez une Pull Request.

Licence
Ce projet est sous licence MIT. Consultez le fichier LICENSE pour plus de détails.
