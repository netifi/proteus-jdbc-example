# client
Proteus client that calls the example [Employee Service](../service) to retrieve employee information.

## Running the Example
Execute the Employee Client to query for all employees in "Business Development" by running the following command:

    $ ../gradlew :client:run
    
When the client successfully calls the service and returns data you will see a response similar to the following in the terminal:

    > Task :client:run
    [main] INFO io.netifi.proteus.Netifi - registering with netifi with account id 100, group proteus.example.services.employeesclient, and destination employeeclient-b1f6c349-af40-424f-8f87-6fa5e6eb7d22
    [main] INFO io.netifi.proteus.example.client.Main - Getting all employees in Business Development...
    [reactor-tcp-nio-4] INFO io.netifi.proteus.example.client.Main - Employee: [id: 11, lastName: Seel, firstName: Randi, email: rseela@flavors.me, dept: Business Development
    [reactor-tcp-nio-4] INFO io.netifi.proteus.example.client.Main - Employee: [id: 15, lastName: Denzilow, firstName: Riva, email: rdenzilowe@usatoday.com, dept: Business Development
    [reactor-tcp-nio-4] INFO io.netifi.proteus.example.client.Main - Employee: [id: 16, lastName: Shipley, firstName: Adelheid, email: ashipleyf@weibo.com, dept: Business Development