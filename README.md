# McommerceActivites

activite du tuto microservice

## Pour Commencer

cloner le repository en local avec la commande :

```
git clone https://github.com/Bishem/McommerceActivites.git
```

### Prerequis

- Un IDE
- Une Version de Java
- Des Mains (vous pouvez donc ranger le reste de votre corps au placard vous n'en aurez pas besoin)

```
IDE : 
- IntelliJ IDEA
- Spring Tool Suite

JAVA VERSION : 1.8 

ATTENTION : projet incompatible java 10
```

### Installation

IntelliJ IDEA

```
suivre la procedure du cours / tuto
```

Spring Tool Suite

```
importer projet maven depuis la racine du repository local
```

### Lancement

IntelliJ IDEA

```
suivre la procedure du cours / tuto
```

Spring Tool Suite

```
utiliser le boot dashboard (relativement similaire a IntelliJ)
```

## Informations Supplementaires

Vous aller très vite le voir certaines parties de l'application 
diffèrent du code initial (liste ci-dessous)

Le résultat est cependant celui attendu donc soyez gentil s'il vous plait

### Differences

- les configs on toutes ete externalisees dans le repo de config suivant :

```
https://github.com/Bishem/mcommerce-config-repo.git
```
ce n'est pas clientui qui gere expedition :

- paiement va creer l'expedition
- commande va la mettre a jour
