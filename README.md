First of all let me thank you for the opportunity. I'm very excited to potentially work with a team and company like Ninja.

A couple of notes first:

1. Yes I made it way harder and complicated than it needed to be. I could have used JPA and knocked this out pretty quickly. However, I wanted to have something to
talk about in our next conversation so I used an ORM called JOOQ. It was overkill for this project and probably not the best choice in terms of time management. It's
strength is for developing against existing databases that might not be OO friendly. So working with a green field - JPA would have been the smarter choice - but 
I'm sure you've seen plenty of those. I have an assignment (from the company I'm with currently on my github that has a demo of JPA if you'd like to see that. 
(https://github.com/markmaz/redriver)

2. I spent more time working on thngs that I thought might be interesting to talk about then spending a bunch of time on tests. I know the importance of tests and 
am a big proponent of TDD. I took a gamble on not writing tests to show some other things.

Enough about that - 

How to run the application:

1. Ensure you have access to a Postgres database. Personally, I used a docker container for my tests. 
   (docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres) This environmental variable sets the password for the default user called
   postgres. You can use this username/password in the next step. Also update the datasource-url if you are not using something on your local machine running on port
   5432.
   
2. Clone the repo to your local machine. Then change the username/password in the java/main/resources/application.yml file under datasource-username/password.

3. You can start the application with mvn spring-boot:run. This will generate the DDL and some test data in the Postgres database and start a tomcat server running
   on port 8080. If you have something already running on 8080 it will fail.
   
4. Once you are up and running, you can go to the Swagger documentation at http://localhost:8080/swagger-ui.html. This will show you almost all the available 
   endpoints. 
   
5. I'm using JWT and Spring Security to secure the site. You can create a user at /rmm-services-server-app/v1/users/sign-up by POSTing a username and password.

   {
      "username": "something",
      "password": "password"
    }
    
    I encryopt the password on the server side before it is saved to the database. Once you've created a user, you can acquire a token at
    /rmm-services-server-app/v1/users/login
    
    The token will be in the header of the response. You'll neeed to copy that and add it to the header of any subsequent calls under key: Authorization 
    value: Bearer + JWT Tokem (pretty standard)
    
 6. From there you can use the API. Let me know if you have any questions or any issues. 
 
 Again, thank you for the opportunity.
 
 Mark
    

    
   
   
