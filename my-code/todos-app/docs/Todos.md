# Todos for this project

  - [X] On the ControllerUtils.getUserIdFromToken if there is not the Bearer but there is a string
    try to pass the string there to the jwtService to parse.

  - [X] Checking if I can do some useCases with only one SQL query. Exp: Instead of checking if the
    todo exists and if the todo is from the user before deleting it. Try just to delete it if it has
    the todoId = ? and userId = ? and then check if the operation was a success or not. This way
    make the useCase a lot simpler but not changing its functionality.

  - [ ] Make the ToggleTodo use case in the three versions: full, simplified and cached so it will be
    like a reference to be used for other useCases if they wanted to change

  - [ ] Check out the Spring Framework how to replace the UseCasesFactory by the spring beans
    functionality.

  - [ ] Check out how to do in the Spring Framework to manage the database connection opening and
    closing it and getting the connectionString from the env. All using the Spring stuff, probably
    filters or middlewares. But not using the connection for every route only the ones which the
    connection is required.

  - [ ] Use a filter to parse the request.headers.Authorization if its present and then add the
    authUserId present in it to the request or context or whatever is most convenient.

  - [ ] Make a home page for the API that contains all the routes probably the same of the
    Routes.txt of this doc but in html or json version

  - [ ] Create a use case for the createTodo that gets the form and then uses TodoEntity.of(form) to
    make an entity Todo. Then with this entity you can use all other methods. Having the same
    function that the createTodo with only DTOs, but using it as an entity to check how it, as an
    alternative.
