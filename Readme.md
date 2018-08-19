# Online Shopping Cart

The application provides all the Rest endpoints to create, store and check a shopping cart. It is a Spring boot application and uses PostGres database in the backend.

## Prerequisites
* Java (1.8.x +)
* Docker for Mac (17.x + - assuming you are using Mac OSX)

## Usage

In order to run the application from your local computer, please execute following commands:
To bring up all dependencies
`docker-compose up`

Update the Postgres url in application.properties 

To run the application locally as a container
`./localBuildAndRun.sh`

Run the application using gradle command
`./gradlew bootRun`

## APIs

* **Product APIs** - It gives an endpoint to  view all the available products.
* **User APIs** - It gives endpoints to create and view all the users.
* **Cart APIs** - It gives endpoint to update and view cart by userId.
* **Order APIs** - It gives endpoint to place an order from the carts for a guest or a user.

