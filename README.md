# Better Micronaut Tutorial #

This guide is a slight improvement of previous guide at http://guides.micronaut.io/creating-your-first-micronaut-app/guide/index.html

## Goals

* Create a simple microservices, using Java EE, not Spring Boot :)
* Deployed in a on minimum profile instance in a cloud provider
* Alternatively, deployed in a small docker instance (or as part of a Kubernetes cluster)

Why we need native binary compiler? Because we want to run it without Java VM, so we can deploy that as a service in a very small cloud instance, or in docker container.

### 1.1. What you will need

* Decent text editor e.g. vim
* Or decent IDE e.g. Intellij IDEA Community Edition
* JDK with native image compiler e.g. GraalVM JDK

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

#### Install GraalVM Java SDK

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

#### Creating a decent Linux VM



*CentOS Linux*

Update linux yum
`sudo yum update`

Optionally you might need other utilities such as ssh, unzip, or git:
```
sudo yum install openssh-server
sudo yum install unzip
sudo yum install git
```

Install gcc and its development libraries.
`sudo yum install glibc-devel glibc-devel zlib-devel gcc`
 
Install g++ C++ compiler and its development libraries.
`sudo install g++ glibc++-devel libstdc++6 libc++-devel libstdc++-static`

*Ubuntu Linux*

Replace yum with apt-get.
  
#### Install Docker
It depends on your OS: based on

* CentOS [https://www.linuxtechi.com/install-docker-ce-centos-8-rhel-8/](https://www.linuxtechi.com/install-docker-ce-centos-8-rhel-8/)
* Mac OS X [https://docs.docker.com/docker-for-mac](https://docs.docker.com/docker-for-mac/)
* Windows 10 [https://runnable.com/docker/install-docker-on-windows-10](https://runnable.com/docker/install-docker-on-windows-10)


### 1.3. Get the source code from Github


Github repository [https://github.com/muntasirrahman/micronautdemo](https://github.com/muntasirrahman/micronautdemo)

`git clone https://github.com/muntasirrahman/micronautdemo.git`


## 6. Generating Micronaut Native Image 

Generate the native image binary

```shell script
native-image --no-server -cp build/libs/complete-0.1-all.jar
```

The native image is at the sub-directory complete.

To change the output name, modify [native-image.properties](complete/src/main/resources/META-INF/native-image/example.micronaut/micronautguide/native-image.properties)

Change this line:
`-H:Name=micronautguide \` to something else.

## 7. Running it on Docker in Oracle Linux 7 Slim 

```shell script
FROM oraclelinux:7-slim
COPY micronautguide .
EXPOSE 80
RUN ./micronautguide
```