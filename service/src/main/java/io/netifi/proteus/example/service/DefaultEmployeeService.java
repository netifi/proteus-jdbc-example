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

package io.netifi.proteus.example.service;

import io.netifi.proteus.example.service.protobuf.Employee;
import io.netifi.proteus.example.service.protobuf.EmployeeService;
import io.netifi.proteus.example.service.protobuf.GetDepartmentEmployeesRequest;
import io.netty.buffer.ByteBuf;
import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.pool.DatabaseType;
import reactor.adapter.rxjava.RxJava2Adapter;
import reactor.core.publisher.Flux;

/**
 * Service responsible for retrieving employee information.
 */
public class DefaultEmployeeService implements EmployeeService {

    private final Database database;

    public DefaultEmployeeService() {
        this.database = Database
                .nonBlocking()
                .url("jdbc:postgresql://localhost:5432/postgres")
                .healthCheck(DatabaseType.POSTGRES)
                .build();
    }

    @Override
    public Flux<Employee> getDepartmentEmployees(GetDepartmentEmployeesRequest message, ByteBuf metadata) {
        return RxJava2Adapter.flowableToFlux(database.select("SELECT * FROM profile WHERE profile_dept LIKE ?")
                .parameter(message.getDepartment())
                .get(rs -> Employee.newBuilder()
                        .setProfileId(rs.getInt("profile_id"))
                        .setProfileFirstName(rs.getString("profile_firstname"))
                        .setProfileLastName(rs.getString("profile_lastname"))
                        .setProfileEmail(rs.getString("profile_email"))
                        .setProfileSSN(rs.getString("profile_ssn"))
                        .setProfileImage(rs.getString("profile_image"))
                        .setProfileDepartment("profile_dept")
                        .build()
                )
        );
    }
}
