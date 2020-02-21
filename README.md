# Better Micronaut Tutorial #

This guide is available at http://guides.micronaut.io/creating-your-first-micronaut-app/guide/index.html

### 1.1. What you will need

* Decent text editor e.g. vim
* Or decent IDE e.g. Intellij IDEA
* JDK 1.8

## 1. Preparation

This is the guide to create a microservice written in Java EE, using:
* micronaut
* deployed in a small docker instance with minimal configuration

### 1.2. Solution

Clone from Githubb

[Github repository](https://github.com/muntasirrahman/micronautdemo)
`git clone https://github.com/muntasirrahman/micronautdemo.git`


## 6. Generating Micronaut Native 

```shell script
native-image --no-server -cp build/libs/complete-0.1-all.jar
```

## 7. Running it on Docker in Oracle Linux 7 Slim 

```shell script
FROM oraclelinux:7-slim
COPY micronautguide .
RUN ./micronautguide
```