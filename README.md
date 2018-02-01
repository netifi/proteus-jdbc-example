# proteus-jdbc-example

An example of using [Netifi Proteus](https://www.netifi.com/proteus.html) to retrieve data from a database using JDBC.

The example starts a service that exposes a method to query employees in particular departments from a relational database.

## Prerequisites
This example requires a running PostgreSQL database. Start a PostgreSQL instance as a Docker container using the following commands:

        $ docker pull postgres
        $ docker run --name=proteus-db -p 5432:5432 -d postgres
        
## Running the Example
Follow the steps below to run the example:

1. Start the Service

2. Run the Client
        
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