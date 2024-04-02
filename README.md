implement these features later:


## IMP
1. currently service code is int controller methods eg createUser in UserController. all that should be in userDAO
2. implement custom exceptions, eg - UserNotFoundException currently gives status code 500, should give 404 . use 204(no content)[https://stackoverflow.com/questions/2342579/http-status-code-for-update-and-delete]
3. EntiyModel to provide links
4. static and dynamic filtering of fields like password etc.[169,170]

## NOT IMP
1. API documentation using Swagger UI
2. content negotiation 
3. internationalization 
4. API versioning 
5. Hateos 
