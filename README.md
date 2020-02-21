# Better Micronaut Tutorial #

This guide is a slight improvement of previous guide at http://guides.micronaut.io/creating-your-first-micronaut-app/guide/index.html

## Goals

* Create a simple microservices, using Java EE.
* Deployed in a on minimum profile instance in a cloud provider
* Alternatively, deployed in a small docker instance (or as part of a Kubernete cluster)

### 1.1. What you will need

* Decent text editor e.g. vim
* Or decent IDE e.g. Intellij IDEA
* JDK 1.8

### 1.2. Pre-requisite software

* SDKMan package manager
* native-image tool
* Docker
* Linux VM with sufficient allocated RAM (8 GB) and CPU (4 cores)

This linux VM is necessary to generate native image, because the suggested way to use Docker container, can takes up to 2 hours to generate native image.
  
#### SDKMAN Java Package Manager
* A Java package manager 
[SDKMAN](https://sdkman.io/install)

`curl -s "https://get.sdkman.io | bash"`

Open a new terminal, and type:
`source "$HOME/.sdkman/bin/sdkman-init.sh"`

#### Install Graal Java SDK

List available Java SDK
`sdk list java`

Choose to install GraalVM Java

`sdk install java 20.0.0.r11-grl`

At the first column find the variant of Java SDK, which is GraalVM.
Find the most recent version  20.0.0.r11-grl at the most right column.

#### Install Micronaut

`sdk install micronaut`

#### Install native-image tool

`gu install native-image`

#### Install Docker
It depends on your OS: based on

* CentOS [https://www.linuxtechi.com/install-docker-ce-centos-8-rhel-8/](https://www.linuxtechi.com/install-docker-ce-centos-8-rhel-8/)
* Mac OS X [https://docs.docker.com/docker-for-mac](https://docs.docker.com/docker-for-mac/)
* Windows 10 [https://runnable.com/docker/install-docker-on-windows-10](https://runnable.com/docker/install-docker-on-windows-10)

#### 

### 1.3. Get the source code from Github

Clone from Github.

Github repository [https://github.com/muntasirrahman/micronautdemo](https://github.com/muntasirrahman/micronautdemo)

`git clone https://github.com/muntasirrahman/micronautdemo.git`


## 6. Generating Micronaut Native 

```shell script
native-image --no-server -cp build/libs/complete-0.1-all.jar
```

## 7. Running it on Docker in Oracle Linux 7 Slim 

```shell script
FROM oraclelinux:7-slim
COPY micronautguide .
EXPOSE 80
RUN ./micronautguide
```