# spring-boot
- Rest API use cases:
    + List/detail game
    + Add to cart
    + Order with payment methods

# database and workflow diagram
- please check the file root/etc/solution-design.docx.

# technologies
- Jdk17
- Spring Boot 3
- Hibernate 6
- H2 database
- Lombok

# how to config and run the application
- checkout develop branch at latest code
- open source code in IntelliJ IDEA with Jdk17
- maven command: mvn clean install
- start application in IntelliJ quickly at /springboot001/proj-web/src/main/java/trungitnt95/springboot001/Springboot001Application.java
- import root/etc/spring-boot-001.postman_collection.json to Postman software to send these requests and test APIs.

# project structure
root
    |-etc
    |-springboot001                             (parent module)
        |-proj-common                           (sub modules)
                |-entities              
        |-proj-dao
                |-repositories
        |-proj-service
                |-services
                |-exceptions
        |-proj-web
                |-configurations
                |-controllers
                |-mappers
                |-dto
                |-Springboot001Application.java

# patterns & principles
- design patterns applied for the requirement:
  + chain of responsibility:
        Use this pattern to handle the validation check the orders whether they are valid or not.
        We have 3 handlers (validators) ProductValidator, VoucherValidator, PaymentValidator(nested classes inside OrderServiceImpl).
        Suppose the business need to be validated in order as above.
        When ProductValidator execute the check, it will check the next handler to handle next business VoucherValidator, and then PaymentValidator.
        So, when the order have many step/infos to validate, it will be easy to handle and maintain.
  + factory method:
        When order a game with some payment methods.
        We only call the abstract method 'processPaymentForOrder' of inteface PaymentHandler.
        The detail payment processing with different method (Cash, Bank, Momo) is hiding for order process.
        This pattern will be easy to maintain/separate the different kind of business, easy to add new one in future.
  + template method:
        When mapping data from entities to dto.
        It is duplicated code for several classes.
        So, the abstract class WebMapper has an abstract method.
        With some kind of mapper classes (ProductV1Mapper,ProductV2Mapper,..) the implementation will override and define the special/different for it.
  +

- principles were applied for REST API:
    + API version: different version is designed by inheritance in controllers(ProductV1RestController, ProductV2RestController, ProductV1Dto, ProductV2Dto,..)
    + URL paths represents for resource/business(See more in root/etc/spring-boot-001.postman_collection.json)
    + Handle status code (ok, exceptions,...) in controllers and global exception handler ApplicationExceptionHandler.java
    + Generate documentation of APIs automatic (by execute the test with spring-restdocs-mockmvc)(check output at proj-web/target/snippets after executed the GenerateApiDocumentTest.java ).
    + 


# TODO: (not done yet)
- Unit test to cover business
- need to be applied the spring-security with JWT to secure the API.
- handle concurrent update, thread-safe.
- scanning for vulnerabilities issue, owasp on libraries, docker images.


# licences
This project developed by github.com/trungitnt95. It is used to apply my job and practice my skills. Please donate me if it is useful/use it :)
Contact me at trung.it.nt.95@gmail.com if you need support to run the app.
