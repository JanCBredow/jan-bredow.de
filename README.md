![alt](https://i.imgur.com/gtwjSq1.png)
# jan-bredow.de Webpresence


## Introduction

This project is a Springboot and Thymeleaf based Webserver,
including HTML5 Template by [Bucky Maler](https://github.com/BuckyMaler/global)

## Requirements
This project requires:
* Java 16.0.1
* PostgreSQL 11 (tested)
* 512MB RAM (minimum)
* 2 Core CPU (recommended)
* DNS & Certbot Knowledge (self signing Certificates)

## Database

Create the required Tables
~~~~sql
CREATE TABLE IF NOT EXISTS crytek_application(
    visit_count BIGINT default -1
);
~~~~

## Install
Clone the Project

~~~~git
git clone git@github.com:JanCBredow/jan-bredow.de.git
git checkout origin/master
~~~~

## Run


run the Application
~~~~bash
java -jar webpresence.jar
~~~~

run the Application unattended
~~~~terminal
apt-get install screen -y &&
screen -S webpresence java -jar webpresence.jar
~~~~
