# shoppinglist-be
shoppinglist - springboot, mysql (docker) <br/>


you can use to set up mysql db with dockerfile in projectfile resource path. Use below commands in order..<br/>
### `docker build -t shoppinglist/mysql`   <br/>
### `docker run --name SLDB -p3306:3306 -d shoppinglist/mysql`  <br/>
<br/>(entire mysql db command line )<br/>
### `docker exec -it SLDB ./bin/bash ` 
