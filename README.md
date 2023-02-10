# Demo
TestTask solution

For starting application execute following command `docker-compose up --build` in terminal. Which will create necessary database and redis server alongside with application.This Spring application contains authorization/authentication service for user managment and product/orders. Necessary endpoints can be found at following url localhost:8080/swagger-ui.html.
User managment consists of 3 roles: admin, seller and client. Managment edit/update is managed by administrator. For persistance MySQL database is used, for PUB/SUB Redis Server is used and for application logic Spring reactor. Database is automatically initialized by flyway migration tool(instead of backup) files.
