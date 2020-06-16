# Spring Boot & Apache Camel Demo

This demo implements a REST service using Spring Boot and mainly Apache Camel, which is used in this demo for implement the endpoints for the service.

## Use of the service

Just execute in console the following command:

```
mvn clean spring-boot:run
```

The endpoints will be available here:

```
http://localhost:9091/apis/
```

## Operations

The service handles operations over employees. The available endpoints are:

* GET ("/employees")

```
curl --location --request GET 'http://localhost:9091/apis/employees/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'
```
* GET ("/employees/{id}")

```
curl --location --request GET 'http://localhost:9091/apis/employees/1' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'
```

* POST ("/employees")

```
curl --location --request POST 'http://localhost:9091/apis/employees' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "3",
    "employee_name": "Gabriel García Márquez",
    "employee_salary": "20134",
    "employee_age": "50",
    "profile_image": ""
}'
```

## Tech stack

* Spring Boot 2.3.1.RELEASE
* Apache Camel 3.3.0
    * camel-servlet (https://camel.apache.org/camel-spring-boot/latest/servlet-starter.html)
    * camel-gson-starter (https://camel.apache.org/components/latest/dataformats/json-gson-dataformat.html)
    * camel-test-junit5 (https://camel.apache.org/components/latest/others/test-junit5.html)