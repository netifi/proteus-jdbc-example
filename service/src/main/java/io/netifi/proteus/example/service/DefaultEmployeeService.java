package io.netifi.proteus.example.service;

import io.netifi.proteus.example.service.protobuf.Employee;
import io.netifi.proteus.example.service.protobuf.EmployeeService;
import io.netifi.proteus.example.service.protobuf.GetDepartmentEmployeesRequest;
import io.netty.buffer.ByteBuf;
import org.davidmoten.rx.jdbc.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * Service responsible for retrieving employee information.
 */
public class DefaultEmployeeService implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEmployeeService.class);

    private final Database database;

    public DefaultEmployeeService() {
        this.database = null;
    }

    @Override
    public Flux<Employee> getDepartmentEmployees(GetDepartmentEmployeesRequest message, ByteBuf metadata) {
        return null;
    }
}
