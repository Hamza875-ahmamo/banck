# Digital Bank API

Cette API RESTful développée avec **Quarkus** gère les opérations backend d'une banque digitale, permettant de manipuler des clients (Customers) et leurs comptes bancaires (Courant ou Épargne), ainsi que d'effectuer des opérations comme des crédits ou débits.

## 🚀 Technologies utilisées

- **Quarkus** (Le framework Java Supersonic Subatomic)
- **Java 17+**
- **Hibernate ORM avec Panache** pour l'accès aux données.
- **PostgreSQL** comme base de données principale.
- **RESTEasy** (Jakarta REST) pour exposer les endpoints d'API.
- **MapStruct & Lombok** pour le mapping DTO (Data Transfer Object) fluide.

## ⚙️ Prérequis

- JDK 17 ou supérieur
- Maven 3.8.1+
- PostgreSQL en cours d'exécution (avec une base `Banck` et des identifiants valides configurés dans `application.properties`)

## 🏃 Lancement du projet en mode Développement

Vous pouvez lancer l'application en mode "Live Coding" (rechargement à chaud) grâce à la commande :

```shell script
./mvnw quarkus:dev
```

> **Note :** Quarkus intègre une interface Dev UI accessible uniquement en mode développement via [http://localhost:8080/q/dev/](http://localhost:8080/q/dev/).

## 📦 Packaging et exécution en mode Production

Pour empaqueter l'application :

```shell script
./mvnw package
```

L'application compilée peut alors être exécutée avec :

```shell script
java -jar target/quarkus-app/quarkus-run.jar
```

## 🔌 API Endpoints principaux

L'API de gestion des clients est exposée sur `http://localhost:8080/api/customers`.

| Méthode HTTP | URL                   | Description                             |
| ------------ | --------------------- | --------------------------------------- |
| `GET`        | `/api/customers`      | Récupère la liste de tous les clients   |
| `GET`        | `/api/customers/{id}` | Récupère les détails d'un client par ID |
| `POST`       | `/api/customers`      | Crée un nouveau client                  |

