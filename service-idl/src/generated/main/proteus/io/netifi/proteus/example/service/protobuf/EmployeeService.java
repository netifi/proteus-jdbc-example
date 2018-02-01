package io.netifi.proteus.example.service.protobuf;

/**
 */
@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.4.2)",
    comments = "Source: io/netifi/proteus/example/service/protobuf/EmployeeService.proto")
public interface EmployeeService {
  int NAMESPACE_ID = 229120853;
  int SERVICE_ID = 1975164112;
  int METHOD_GET_DEPARTMENT_EMPLOYEES = 2142532737;

  /**
   * <pre>
   * Returns a stream of all employees in the requested department
   * </pre>
   */
  reactor.core.publisher.Flux<io.netifi.proteus.example.service.protobuf.Employee> getDepartmentEmployees(io.netifi.proteus.example.service.protobuf.GetDepartmentEmployeesRequest message, io.netty.buffer.ByteBuf metadata);
}
