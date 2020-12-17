# Biblioteca

Biblioteca is a Digital Library Management System for the Bangalore Public Library. It has a large collection of books. They provide the customers with the option of checking out the books to read at home. But then they need to return them as well. 

### The following features are provided by Biblioteca-
1.	Welcome – They welcome their customers with a friendly message on their homepage.
2.	List books with details – They provide a list of books with are available in the library with the following details – 
    Book’s ISBN number
    Boot Title
    Book’s Author
    Year of Publishing
3.	Checkout books – As a customer you can checkout books that are available in the library
4.	Return books – Also they expect all the customers to return the books once that have finished reading them.

### As a customer, you can access their service by accessing the end points provides by them. The details for the same are as below –

| S.no.         | Service Name  |End point | Expected outcome |
| ------------- | ------------- |----------|------------------|
| 1             | Welcomes its customers  | /welcome | When the customer hits this end point, he/she is greeted with this message-Hello, welcome to Biblioteca ! |
| 2             | List Books  | /books| When the customer hits this end point, he/she is able to see the list of all the books available in the library. Also it provides the information like Book’s ISBN number, Book title, Book’s author and Year Published |
| 3 | Successful checkout of the book | /books/&lt;book name&gt;/checkout | When the customer hits this end point, he/she would be able to checkout books that are available in the library |
| 4 | Unsuccessful checkout of the book | /books/&lt;Invalid book name&gt;/checkout | When the customer hits this end point, and gives an invalid book title he/she would get the message "That book is not available" |
| 5 | Successful return of the book |/books/&lt;book name&gt;/return | When the customer hits this end point, he/she would be able to return the  books that are checked out from the library |
| 6 | Unsuccessful return of the book | /books/&lt;Invalid book name&gt;/return |  When the customer hits this end point, and gives an invalid book title he/she would get the message "That is not a valid book to return" |
                                        
	
## Tech stack-

Project configuration - Java 1.8, Junit5, Mockito, SpringBoot 2.3.7
Build tool - gradle
versioning tool - github
Sonar lint - for removing code smells
swagger - API documentation and showcase crud

Database - 
    H2-testing
    Dec-Postgres
     
flywaydb - for db migrations

For Continuous Integration - github actions
For Continuous Deployment - Heroku





### Setup

- **Build project**
  
  `./gradlew build`

- **Run tests**

  `./gradlew test`

- **Run Application**

  `./gradlew bootRun`

