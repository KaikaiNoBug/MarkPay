# MarkPay
REF: https://spring.io/guides/gs/accessing-data-mysql


# Create DB in MySQL FIRST

mysql> create database MarkPay; -- Creates the new database

mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user

mysql> grant all on MarkPay.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database


# ADD
`curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"tony\",\"password\":\"tony123\",\"email\":\"tony@gmail.com\",\"accountType\":\"ADMIN\",\"address\":\"222 Martin St, Long island city, NY, 11101\"}" http://localhost:8080/user/add`

`curl -X POST -H "Content-Type: application/json" -d "{\"username\":\"kailin\",\"password\":\"kl1234\",\"email\":\"kjin@outlook.com\",\"accountType\":\"CUSTOMER\",\"address\":\"123 Terry AVE, Seattle, WA, 98086\"}" http://localhost:8080/user/add`

# Get
`curl -X GET -H "Content-Type: application/json" http://localhost:8080/user/all`

localhost:8080/user/id/1

localhost:8080/user/username/kailin

# Delete
`curl -X DELETE http://localhost:8080/user/delete/id/7`

# Update
`curl -X PUT -H "Content-Type: application/json" -d "{\"id\":1,\"username\":\"tonyLi\",\"password\":\"tony123321\",\"email\":\"tony@gmail.com\",\"accountType\":\"ADMIN\",\"address\":\"222 Martin St, Long island city, NY, 11101\"}" http://localhost:8080/user/update/id/9`

`curl -X PUT -H "Content-Type: application/json" -d "{\"address\":\"123 St SW, Las Vegas, NV, 12345\"}" http://localhost:8080/user/update/id/10`

`curl -X PUT -H "Content-Type: application/json" -d "{\"username\":\"warren\"}" http://localhost:8080/user/update/username/kailin`

