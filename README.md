# _Staff App_

#### By Margaret Mutungi

## Description

This is a news API for an organization. It categorizes news into general news and departmental news. It also contains information users and the departments they are linked to.
## Specs

The application enables CRUD (Create Read Update Delete) transactions for various objects. There are two models. 

* Users
* News
* DepartmentNews
* Department

News can be general or departmental.

## Setup/Installation Requirements

* Clone this repository
* Open it on your IDE
* Create the databases as using the commands provided. Be sure to alter your database login credentials in the DB.java file in the main folder and the DatabaseRule File in the tests folder.
```postgres-sql
CREATE DATABASE site_maintenance;
CREATE TABLE engineers(id SERIAL PRIMARY KEY, name varchar, eknumber int, phone varchar);
CREATE TABLE sites(id SERIAL PRIMARY KEY, name varchar, location varchar,  engineerid int);
CREATE DATABASE site_maintenance_test WITH TEMPLATE site_maintenance;
```
You can also visit the live site on https://staffappip.herokuapp.com for live interaction.

## Running tests
* Navigate to any of the files in src/test/java
* Open the file
* On the display tab in the IDE, right click the file and run it

## Technologies Used

* _Java_
* _JUnit_
* _Spark Java_
* _SQL (PostgreSQL)_
* _JSON_

### Acknowledgements
_Sam Ngigi (My instructor)_

### License

*This software is licensed under the MIT license.*

Copyright (c) 2019 **_Margaret Mutungi_**



