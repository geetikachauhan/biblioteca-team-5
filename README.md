# Biblioteca

Biblioteca is a Digital Library Management System for the Bangalore Public Library. It has a large collection of books and movies. 

They provide the customers with the option of checking out the books and movies . But then they need to return them as well. 

They also provide a library membership. Each customer is given a unique library number.

### The following features are provided by Biblioteca-
1.	Welcome – They welcome their customers with a friendly message on their homepage.
2.	List books with details – They provide a list of books which are available in the library with the following details – 
    Book’s ISBN number
    Boot Title
    Book’s Author
    Year of Publishing
3.	Checkout books – As a customer you can checkout books that are available in the library. You must be authenticated for the checkout.
4.	Return books – Also they expect all the customers to return the books once that have finished reading them.You must be authenticated for the return
5.  List movies with details - They provide a list of movies which are available in the library with the following details -
    Name
    Year
    Director 
    Movie Rating
6.  Checkout movies - As a customer you can checkout movies that are available in the library. You must be authenticated for the checkout.
7.  Customer Details - As a customer you can view your details.
### As a customer, you can access their service by accessing the end points provides by them. The details for the same are as below –

| S.no.         | Service Name  |End point | Expected outcome |
| ------------- | ------------- |----------|------------------|
| 1 | Welcomes its customers  | /welcome | When the customer hits this end point, he/she is greeted with this message- "Hello, welcome to Biblioteca !" |
| 2 | List Books  | /books| When the customer hits this end point, he/she is able to see the list of all the books available in the library. Also it provides the information like Book’s ISBN number, Book title, Book’s author and Year Published |
| 3.a | Successful checkout of the book(with authentication) | /books/&lt;isbn&gt;/checkout | When the customer hits this end point, he/she would be able to checkout the given book if that is available in the library. He/she would get a message "Thank you! Enjoy the book" |
| 3.b | Book is already checkedout(with authentication) | /books/&lt;isbn&gt;/checkout | When the customer hits this end point, and if the book is already checked out then he/she would not be able to checkout the given book. He/she would get a message "That book has been checked out already." |
| 3.c | Unsuccessful checkout of the book(with authentication) | /books/&lt;isbn&gt;/checkout | When the customer hits this end point, and gives an invalid book isbn he/she would get the message "That book is not available in Library." |
| 4.a | Successful return of the book(with authentication) |/books/&lt;isbn&gt;/return | When the customer hits this end point, he/she would be able to return the  books that are checked out from the library and this message will be displayed "Thank you for returning the book" |
| 4.b | Books is already returned(with authentication) |/books/&lt;isbn&gt;/return | When the customer hits this end point, and if the given books is already returned he/she would not be able to return the  books and this message will be displayed "That book has been returned already" |
| 4.c | Unsuccessful return of the book(with authentication) | /books/&lt;isbn&gt;/return |  When the customer hits this end point, and gives an invalid book isbn he/she would get the message "That is not a valid book to return" |
| 5 | List Movies  | /movies| When the customer hits this end point, he/she is able to see the list of all the movies available in the library. Also it provides the information like Movie name, year , director and movie rating |
| 6.a | Successful checkout of the movie (with authentication)| /movies/&lt;movie name&gt;/checkout | When the customer hits this end point, he/she would be able to checkout the given movie if that is available in the library. He/she would get a message "Thank you! Enjoy the movie" |
| 6.b | Movie is already checkedout (with authentication)| /movies/&lt;movie name&gt;/checkout | When the customer hits this end point, and if the movie is already checked out then he/she would not be able to checkout the given movie. He/she would get a message "That movie has been checked out already." |
| 6.c | Unsuccessful checkout of the movie(with authentication) | /movies/&lt;movie name&gt;/checkout | When the customer hits this end point, and gives an invalid movie name he/she would get the message "That movie is not available in Library." |
| 7 | Display Customer Details  | /customer| When the customer hits this end point, he/she is able to see the his/her profile details like name,email and phone number                                         
	
## Technology Stack:

1. Project configuration 
       
        Java 1.8 
             
        SpringBoot 2.3.7
        
        Build tool - Gradle

2. Project Management

        Github - For Version Control
        
        Swagger - API Documentation and Showcase CRUD functionalities
        
3. Code Quality

        Junit5 
        
        Mockito 

        Sonar Lint - To Detect code smell and fix quality issues

4. Database

        H2 Database ( for testing)
        
        Postgres 12.5 (application db)

        Flyway DB ( for db migrations)
        
5. Security

        Spring boot security
        
6 . CI/CD

        Github Actions - continuous integration
        
        Githus Deployment - continuous deployment
     
### Setup

- **Build project**
  
  `./gradlew build`

- **Run tests**

  `./gradlew test`

- **Run Application**

  `./gradlew bootRun`

