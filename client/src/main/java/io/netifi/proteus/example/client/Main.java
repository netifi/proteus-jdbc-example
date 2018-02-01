/*
 * Copyright 2018 Netifi Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.netifi.proteus.example.client;

import io.netifi.proteus.Netifi;
import io.netifi.proteus.example.service.protobuf.EmployeeServiceClient;
import io.netifi.proteus.example.service.protobuf.GetDepartmentEmployeesRequest;
import io.netifi.proteus.rs.NetifiSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Starts the example Proteus Employee Service Client.
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {
        String clientName = "employeeclient-" + UUID.randomUUID().toString();

        // Create Connection to Netifi Proteus Router
        Netifi netifi =
                Netifi.builder()
                        .group("proteus.example.services.employeesclient")  // Client Group Name
                        .destination(clientName)                            // Client Name
                        .accountId(100)
                        .accessKey(7685465987873703191L)
                        .minHostsAtStartup(1)                               // # of Routers Online Before Client Starts
                        .accessToken("PYYgV9XHSJ/3KqgK5wYjz+73MeA=")
                        .host("localhost")                                  // Proteus Router Host
                        .port(8001)                                         // Proteus Router Port
                        .build();

        // Connect to Example Employee Service
        NetifiSocket conn = netifi.connect("proteus.example.services.employeeservices").block();

        EmployeeServiceClient client = new EmployeeServiceClient(conn);

        // Create Request To Receive All Employees in the "Business Development" Department
        GetDepartmentEmployeesRequest request = GetDepartmentEmployeesRequest.newBuilder()
                .setDepartment("Business Development")
                .build();

        LOG.info("Getting all employees in Business Development...");

        // Execute the Request
        client.getDepartmentEmployees(request)
                .onBackpressureBuffer()
                .doOnNext(employee -> {
                    LOG.info("Employee: [id: {}, lastName: {}, firstName: {}, email: {}, dept: {}",
                            employee.getProfileId(),
                            employee.getProfileLastName(),
                            employee.getProfileFirstName(),
                            employee.getProfileEmail(),
                            employee.getProfileDepartment());
                })
                .blockLast();
    }
}
