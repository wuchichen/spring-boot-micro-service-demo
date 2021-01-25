# Description

End to end demo for spring-boot micro-services (spring boot REST API, MySQL, Nginx)

## Deploying backend apps using k8s

```
$ docker build -f ./backend/customer/Dockerfile -t demo-spring-k8s-customer .
$ docker build -f ./backend/order/Dockerfile -t demo-spring-k8s-order .
$ docker login & tag & push ...

# Database
$ kubectl apply -f ./deployment/database/mysqldb-secret.yaml
$ kubectl apply -f ./deployment/database/mysql.yaml

# Customer API
$ kubectl apply -f ./deployment/backend/mysql-configmap.yaml
$ kubectl apply -f ./deployment/backend/spring-customer.yaml

# Order API
$ kubectl apply -f ./deployment/backend/spring-customer-configmap.yaml
$ kubectl apply -f ./deployment/backend/spring-order.yaml

```

### Check deploying result of backend apps

```
$ kubectl get pods
$ kubectl logs <pod name>

# With docker CLI
$ docker exec -it <minikube container id> /bin/bash
$ docker exec -it <spring container id> /bin/sh

# With kubectl CLI
$ kubectl exec -it <pods name of spring apps> -- sh

```
