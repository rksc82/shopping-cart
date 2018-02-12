# Online Shopping Cart

The application provides all the Rest endpoints to create, store and check a shopping cart. It is a Spring boot application and uses PostGres database in the backend.

## Prerequisites
* Java (1.8.x +)
* Docker for Mac (17.x + - assuming you are using Mac OSX)

## Usage

In order to run the application from your local computer, please execute `./localBuildAndRun.sh`. It will bring up your Postgres database and the API.
If running Postgres outside. Update the Postgress url in application. properties and run the application by using 
./gradlew bootRun

## APIs

* **Product APIs** - It gives an endpoint to  view all the available products.
* **User APIs** - It gives endpoints to create and view all the users.
* **Cart APIs** - It gives endpoint to update and view cart by userId.
* **Order APIs** - It gives endpoint to place an order from the carts for a guest or a user.