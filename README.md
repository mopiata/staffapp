# _Staff News API_

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
CREATE DATABASE staff_app;

CREATE TABLE users(id SERIAL PRIMARY KEY, name VARCHAR, eknumber int, departmentid int, position VARCHAR, role VARCHAR);
CREATE TABLE department_news(id SERIAL PRIMARY KEY, title VARCHAR, content TEXT, createdat BIGINT, departmentid int);
CREATE TABLE general_news(id SERIAL PRIMARY KEY, title VARCHAR, content TEXT, createdat BIGINT);
CREATE TABLE department(id SERIAL PRIMARY KEY, name VARCHAR, description VARCHAR, employeecount int);

CREATE DATABASE staff_app_test WITH TEMPLATE staff_app;
```
You can also visit the live site on https://staffappip.herokuapp.com for live interaction.

## Using the API
### Post using Postman
* Add department(POST): /departments/new
```
{
"name": "Brand",
"employeeCount": 0,
"description": "Company brand image"
}
```

* Add user(POST): /users/new
```
{
"name": "Maria",
"eknumber": 6737,
"departmentId": 1,
"position": "Engineer",
"role": "Engineering"
}
```

* Add general_news(POST): /generalnews/new
```
{
"title": "Crazy Deals",
"content": "Shoo bee shoo bee",
"createdat": 54667283849
}
```

* Add department_news(POST): /departmentnews/new
```
{
"departmentId": 1,
"title": "Stinger",
"content": "He pleases the customers again with speed, simplicity and trust",
"createdat": 54667283849
}
```
### Get using the paths. Path base(https://staffappip.herokuapp.com)
* Get all Departments: /departments
* Get single Department: //departments/:departmentid
* Get all users in a department: /departments/:departmentid/users
* Get all users: /users
* Get specific user: /users/:userid
* Get all general news: /generalnews
* Get details of specific general news: /generalnews/:generalnewsid
* Get a department's news: /departmentnews/:departmentid
* Get details of individual department news: /departmentnews/:departmentid/:departmentnewsid

### Deleting
* Delete all departments, users, generalnews, departmentnews:
```
/departmentsdeleteall
/usersdeleteall
/generaldeleteall
/departmentnewsdeleteall
```

* Delete single department news: /departmentnews/:departmentid/:newsid/delete
* Delete single general news: /generalnews/:newsid/delete
* Delete single user: /users/:userid/delete
* Delete single department: /departments/:departmentid/delete


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



