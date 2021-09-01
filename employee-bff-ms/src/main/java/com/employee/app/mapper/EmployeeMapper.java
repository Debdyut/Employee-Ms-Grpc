package com.employee.app.mapper;

import org.apache.commons.lang3.ObjectUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.app.grpc.EmployeeBase;

@Component
public class EmployeeMapper {

	@Autowired
	private StringEncryptor jasyptStringEncryptor;
	
	public EmployeeBase dtoToProto( com.employee.app.dto.Employee source ) {
		if (ObjectUtils.isEmpty(source)) {
			return null;
		}
		var target = EmployeeBase.newBuilder().setName(jasyptStringEncryptor.encrypt(source.getName()))
									.setDob(jasyptStringEncryptor.encrypt(source.getDob()))
									.setSalary(jasyptStringEncryptor.encrypt(Double.toString(source.getSalary())))									
									.setAge(jasyptStringEncryptor.encrypt(Integer.toString(source.getAge())))
									.build();
		return target;
	}
	
}
