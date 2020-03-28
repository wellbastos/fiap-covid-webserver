#!/bin/bash

function clone_front_repository() {
    if [ ! -d "front-spa-webservices-fiap" ]; then
        sh front-project.sh
    fi
}

function down_app_container() {
    echo "Stoping latest docker image"
    docker-compose  -f front-spa-webservices-fiap/docker-compose.yaml down
    docker-compose  -f api/docker-compose.yaml down
}

function delete_latest_docker_image() {
    echo "Deleting latest docker image..."
    docker rmi -f frontspa:latest
    docker rmi -f fiap-covid-api:latest
}

function build_application() {
    echo "Building app..."
    sleep 2
    echo "Building frontend..."    
    cd front-spa-webservices-fiap
    npm install 
    npm install --production
    npm run build
    npm run lint
    cd .. 
    sleep 2
    echo "Building backend..."  
    cd api    
    ./mvnw clean install
    cd .. 
}

function build_docker_image() {
    echo "Building docker image..."
    docker-compose -f front-spa-webservices-fiap/docker-compose.yaml build
    docker-compose -f api/docker-compose.yaml build
}

function up_app_container() {
    echo "Starting latest docker image"
    docker-compose -f front-spa-webservices-fiap/docker-compose.yaml up -d
    docker-compose -f api/docker-compose.yaml up -d
}

time (clone_front_repository)
time (down_app_container)
time (delete_latest_docker_image)
time (build_application)
time (build_docker_image)
time (up_app_container)

exit 0