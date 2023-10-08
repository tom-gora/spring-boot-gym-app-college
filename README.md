# GYM APP

### College project

Built as a college project to try something new, while the course expected a Swing app to be made as GUI training.

The app uses Spring Boot to read the database and create endpoint URLs on the localhost. Maven is used to manage dependencies and build a JAR package. Spring Boost gets the details necessary to connect to the database from a standard “application.properties” config file. The main package contains a runnable GymAppRemasterApplication.class and Member.class responsible for defining Member objects that the program operates on. There are also two “sub-packages” as well. Firstly, database holds DatabaseOperations.class that provide methods to query the database, secondly controllers that hold Spring Boot controller classes that map the endpoints to URLs pass data as objects of Model class that Thymeleaf maps into DOM on the server and a static site is then sent to the client.

Fetatures: 

- Integrated with a MySQL database
- Basic frontend interface for Spring backend
- Responsive on mobile screens

* Added two simple JUnit tests written as testing assignment. Not much as there isn't really that much custom functionalities built in Java from scratch.


Deployed demo:

[Click to see the demo on my portfolio page  🚀 🚀 🚀](http://tomgora.online:8080/gym_home)


Desktop demo:

![desktop](./assets/desktop_demo.apng)

Mobile demo:

![desktop](./assets/mobile_demo.apng)
