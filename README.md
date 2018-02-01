# proteus-jdbc-example

An example of using [Netifi Proteus](https://www.netifi.com/proteus.html) to retrieve data from a database using JDBC.

The example starts a service that exposes a method to query employees in particular departments from a relational database.

## Prerequisites
This example requires the following prerequisites:

1. [Java Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

2. Protocol Buffers Compiler

    You can download the compiler from here: https://github.com/google/protobuf/releases
    
    OR
    
    If you are on a mac using homebrew you can install it with the following command:
    
        $ brew install protobuf

3. PostgeSQL Database

    Start a PostgreSQL instance as a Docker container using the following commands:

        $ docker pull postgres
        $ docker run --name=proteus-db -p 5432:5432 -d postgres

4. Netifi Proteus Router

    Download the router as a Docker container using the following command:

        $ docker pull netifi/proteus
        
## Running the Example
Follow the steps below to run the example:

1. Start the Proteus Router

    Start the Proteus Router by running the following command:
    
        $ docker run -p 8001:8001 -p 7001:7001 -e ROUTER_SERVER_OPTS='-Dnetifi.authentication.0.accessKey=7685465987873703191 -Dnetifi.authentication.0.accessToken=PYYgV9XHSJ/3KqgK5wYjz+73MeA= -Dnetifi.authentication.0.accountId=100' netifi/proteus
    
    You should see a message similar to the following in the terminal when the router starts:
    
        2018-02-01 21:29:30,001 INFO i.n.r.Bootstrap [main] Router started in 474.37 milliseconds

2. Start the Employee Microservice

    Start the Employee microservice by running the following command in a new terminal:
    
        $ ./gradlew :service:run
        
    When the service has successfully connected to the router you will see a message in the terminal similar to the following:
    
        > Task :service:run
        [main] INFO io.netifi.proteus.Netifi - registering with netifi with account id 100, group proteus.example.services.employeeservices, and destination employeeservice-fc07a25f-30ed-4fdb-b752-0e0a29ce0e23

3. Run the Employee Client

    Execute the Employee Client to query for all employees in "Business Development" by running the following command:
    
        $ ./gradlew :client:run
        
    When the client successfully calls the service and returns data you will see a response similar to the following in the terminal:
    
        > Task :client:run
        [main] INFO io.netifi.proteus.Netifi - registering with netifi with account id 100, group proteus.example.services.employeesclient, and destination employeeclient-b1f6c349-af40-424f-8f87-6fa5e6eb7d22
        [main] INFO io.netifi.proteus.example.client.Main - Getting all employees in Business Development...
        [reactor-tcp-nio-4] INFO io.netifi.proteus.example.client.Main - Employee: [id: 11, lastName: Seel, firstName: Randi, email: rseela@flavors.me, dept: Business Development
        [reactor-tcp-nio-4] INFO io.netifi.proteus.example.client.Main - Employee: [id: 15, lastName: Denzilow, firstName: Riva, email: rdenzilowe@usatoday.com, dept: Business Development
        [reactor-tcp-nio-4] INFO io.netifi.proteus.example.client.Main - Employee: [id: 16, lastName: Shipley, firstName: Adelheid, email: ashipleyf@weibo.com, dept: Business Development
        
## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/proteus-jdbc-example/issues).

## License
Copyright 2018 Netifi Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.