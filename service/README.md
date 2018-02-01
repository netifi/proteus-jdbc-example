# service
Proteus microservice that queries the example employee database.

## Running the Service
Start the Employee microservice by running the following command in a new terminal:

    $ ../gradlew :service:run
    
When the service has successfully connected to the router you will see a message in the terminal similar to the following:

    > Task :service:run
    [main] INFO io.netifi.proteus.Netifi - registering with netifi with account id 100, group proteus.example.services.employeeservices, and destination employeeservice-fc07a25f-30ed-4fdb-b752-0e0a29ce0e23