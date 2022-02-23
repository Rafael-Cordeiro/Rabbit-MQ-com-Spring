# RabbitMQ Docker container guide

In ./rabbitmq, run this command to create a RabbitMQ Docker container
```
docker-compose up -d
```
It will not start once you created the container. You must run this container using the Docker CLI or some Docker GUI, such as Docker Desktop or Portainer

Once you created the container, you can start it with
```
docker start rabbitmq
```
Remind that, all things you do on docker, must have administration permission. Consider to run with ```sudo``` instruction or in a authorizerd command console.