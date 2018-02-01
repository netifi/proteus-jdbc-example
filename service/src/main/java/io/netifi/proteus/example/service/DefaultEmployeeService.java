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
                .url("jdbc:postgresql://postgres@localhost:5432/postgres")
                .healthCheck(DatabaseType.H2)
                .maxPoolSize(3)
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
