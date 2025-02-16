# Customer Management Backend API With Java Spring

This is the public version of my customer management backend API. You can access the production environment via project link. I used a free service to deploy. In that way; if you make a request, the response will come in 50-100 seconds. Please wait to test. 

## Technologies That I Used

- Java Spring Boot
- Swagger UI (You can test with ``/swagger-ui/index.html`` path)
- PostgreSQL
- JWT Token
- Hibernete ORM
- MVC

## Note!

I did not apply the token checker middleware for Swagger UI because otherwise we couldn't access without ``curl`` or frontend. But the PostgreSQL database will not save your requests.
