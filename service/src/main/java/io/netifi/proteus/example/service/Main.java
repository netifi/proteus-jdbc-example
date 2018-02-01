package io.netifi.proteus.example.service;

import io.netifi.proteus.Netifi;
import io.netifi.proteus.example.service.protobuf.EmployeeServiceServer;

import java.util.UUID;

/**
 * Starts the example Proteus Employee Service.
 */
public class Main {

    public static void main(String... args) throws Exception {
        String serviceName = "employeeservice-" + UUID.randomUUID().toString();

        // Build Proteus Connection
        Netifi netifi =
                Netifi.builder()
                        .group("proteus.example.services.employeeservices")     // Group name of service
                        .destination(serviceName)                               // Name of this service instance
                        .accountId(100)
                        .accessKey(7685465987873703191L)
                        .accessToken("PYYgV9XHSJ/3KqgK5wYjz+73MeA=")
                        .host("localhost")                                      // Proteus Router Host
                        .port(8001)                                             // Proteus Router Port
                        .build();

        // Add Service to Respond to Requests
        netifi.addService(new EmployeeServiceServer(new DefaultEmployeeService()));

        // Connect to Netifi Proteus Platform
        netifi.connect("proteus.example.services.employeeservices").block();

        // Keep the Service Running
        Thread.currentThread().join();
    }
}
