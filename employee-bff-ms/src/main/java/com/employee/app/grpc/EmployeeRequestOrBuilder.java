// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: EmployeeService.proto

package com.employee.app.grpc;

public interface EmployeeRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.employee.v1.EmployeeRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * List of employees
   * </pre>
   *
   * <code>repeated .com.employee.v1.EmployeeBase employee = 1;</code>
   */
  java.util.List<com.employee.app.grpc.EmployeeBase> 
      getEmployeeList();
  /**
   * <pre>
   * List of employees
   * </pre>
   *
   * <code>repeated .com.employee.v1.EmployeeBase employee = 1;</code>
   */
  com.employee.app.grpc.EmployeeBase getEmployee(int index);
  /**
   * <pre>
   * List of employees
   * </pre>
   *
   * <code>repeated .com.employee.v1.EmployeeBase employee = 1;</code>
   */
  int getEmployeeCount();
  /**
   * <pre>
   * List of employees
   * </pre>
   *
   * <code>repeated .com.employee.v1.EmployeeBase employee = 1;</code>
   */
  java.util.List<? extends com.employee.app.grpc.EmployeeBaseOrBuilder> 
      getEmployeeOrBuilderList();
  /**
   * <pre>
   * List of employees
   * </pre>
   *
   * <code>repeated .com.employee.v1.EmployeeBase employee = 1;</code>
   */
  com.employee.app.grpc.EmployeeBaseOrBuilder getEmployeeOrBuilder(
      int index);

  /**
   * <pre>
   * File format
   * </pre>
   *
   * <code>.com.employee.v1.EmployeeRequest.FileFormat fileType = 2;</code>
   * @return The enum numeric value on the wire for fileType.
   */
  int getFileTypeValue();
  /**
   * <pre>
   * File format
   * </pre>
   *
   * <code>.com.employee.v1.EmployeeRequest.FileFormat fileType = 2;</code>
   * @return The fileType.
   */
  com.employee.app.grpc.EmployeeRequest.FileFormat getFileType();

  /**
   * <pre>
   * File Name is the common name of the file.
   * </pre>
   *
   * <code>string name = 3;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   * File Name is the common name of the file.
   * </pre>
   *
   * <code>string name = 3;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();
}
