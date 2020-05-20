# Info-Revit online database

this application was developed for my second individual project while training at QA consultancy.
It provides both back and front end foor a database which may be used to keep track of technical data associated with premanufactured objects such as doors, windows or lifts.

## Table of Contents
1. [Project Info](#project-info)
    1. [Design Process](#design-process)
    1. [Entity Relationship](#entity_relationship)
    1. [Project Structure](#project_structure)
    1. [Design Process](#design-process)
    1. [Version](#version)
    1. [Contributing](#contributing)
    1. [License](#license)
    1. [Risk Assessment](#risk-assessment)
    1. [Using the Website](#using-the-website)
1. [Deploy Project](#release-process)
    1. [Deploy](#deploy)
    1. [Connect users](#connect_users)
    1. [Testing](#testing)
    1. [Development](#development)
    1. [CI Pipeline (Jenkins)](#ci_pipeline_(jenkins))   
1. [Acknowledgements](#acknowledgements)

## Project Info

refer to docs folder for further documentation on planning & testing.

### Design Process
The initial target of this project was to allow for communication between manufacturers and architects with the use of the database, however the project is currently functional as a personal database.
Kanban board used for planning: [Trello](https://trello.com/b/bp9c8Uks/construction-part-database)
### Entity Relationship
The entity relationship for this diagram is as shown below.
![ERD](https://i.imgur.com/pkUbMz9.png)
### Project Structure
The UML of the project is as shown below. It follows a standard method for accessing databases, with the back end accepting API calls from the front end, which are then used for data manipulation. <Type> refers to Manufacturer, Door, Lift or Window, as they are handled very similarly from the backend.
![UML](https://i.imgur.com/cGaNYL1.png)
### Version
Current release: 1.0
Current Test Coverage: 97% java (IntelliJ), 89% SonarQube
![SonarQube](https://i.imgur.com/ZPNKYFD.png)
### Contributing
Due to the nature of this being an individual project, I do not accept contributions to this project as of now.
### License
This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 
### Risk Assessment
A risk assessment of the project is present in docs:
![RiskAssessment](https://i.imgur.com/xr5bWYq.png)

### Using the Website
after deploying the project (see next chapter), navigate to http://localhost:8181/.
You should see the homepage of the website:
![homepage](https://i.imgur.com/zl1bZtN.png)

use the tabs at the top to navigate to the desired section (note, contact support is in development). In order to register a manufacturer, add, update or remove an item, go to "manufacturer tools". In order to search through items, use "browse":

![browse](https://i.imgur.com/BtC2b5I.png)

You may sort using the "sort by..." selector, or filter using the "custom filter" script tool. Allowed filters are:
* Manufacturer:<name>
* MaxPrice:<price>
* MinBWF:FWD<value>
* Style:<name>
* Type:<name>
more options are in development.

## Deploy project
how to deploy the project for personal use.

### Deploy

in order to run the program you will need Maven to obtain the dependencies in order to run the program using Java. To build the project simply:
* Clone this repository using Git or similar
* open command prompt
* cd <path/to/project/folder>
* mvn clean install (this will take a few minutes)
* mvn spring-boot:run

The server will now be live on localhost:8181

### Connect users

in order to connect users outside of your local machine, you will need to be on, or simulate, a LAN. You may do this using [[Hamachi](https://www.vpn.net/)]. If the LAN is real, simply connect to the host device using the private IP (cmd --> ipconfig). If using VLAN, use the IP Provided by Hamachi.

### Testing

building the file using Maven (mvn clean package) will run all the (160) tests. should you want to run specific tests only, an IDE such as IntelliJ or Eclipse is recommended.
When inside of the Project, right click the test/folder you would like to run, and select "Run Tests". Alternatively, you may press Ctrl+Shift+F10.

### Development

in order to get your own development environment for the project, follow these steps:
* Fork this repository
* Change the origin to point at your own GitHub repository
* Open as existing Maven project
* you may change the way data is stored in the application.properties src/main/resources file. H2 dependencies are included, should you want to store data locally.

### CI Pipeline (Jenkins)

* It is recommended that you have separate CI Pipelines for your Dev and Master branch.
* Jenkins is a compatible CI pipeline manager for this project.
* for the master branch, link jenkins to your GitHub repository and set it to branch specifier */master
* linking it up to your SonarQube and Nexus VM requires following windows batch commands:
```
mvn clean package
```
```
mvn sonar:sonar --define sonar.host.url=http://<yourSonarQubeIP>:9000 --define sonar.login.admin=admin --define sonar.password=<password>
```
```
mvn deploy:deploy-file --define generatePom=false --define pomFile=pom.xml --define url=http://<yourNexusIP>:8081/repository/maven-snapshots/ --define file=target/KorbinianRing-SoftwareMarch16-HobbyProject-jar-with-dependencies.jar --define repositoryId=nexus
```
* You may change the artefact name in the pom.xml.


## Acknowledgements
* Jordan [[JHarry444](https://github.com/JHarry444)] in addition to the project layout being heavily based off his project [[SpringDucks](https://github.com/JHarry444/SpringDucks)], he gave essential advice during development of the project.
* Templated [[Templated](https://templated.co/)] for source of CSS templates
* Colorlib [[Colorlib](https://colorlib.com/wp/template/responsive-table-v1/)] for source of CSS templates
