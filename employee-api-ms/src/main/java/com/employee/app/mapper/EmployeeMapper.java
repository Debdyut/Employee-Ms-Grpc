package com.employee.app.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.employee.app.grpc.EmployeeBase;
import com.employee.app.model.EmployeeForXml;
import com.employee.app.model.EmployeeListForXml;
import com.employee.app.model.EmployeeModel;

@Component
public class EmployeeMapper {

	private StringEncryptor jasyptStringEncryptor;

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy");
	
	@Autowired
	public EmployeeMapper (final StringEncryptor jasyptStringEncryptor) {
		this.jasyptStringEncryptor = jasyptStringEncryptor;
	}

	public EmployeeModel protoToModel(EmployeeBase source) {
		var target = new EmployeeModel();
		target.setName(jasyptStringEncryptor.decrypt(source.getName()));
		target.setDob(fromProtoDate(jasyptStringEncryptor.decrypt(source.getDob())));
		target.setSalary(Double.parseDouble(jasyptStringEncryptor.decrypt(source.getSalary())));
		target.setAge(Integer.parseInt(jasyptStringEncryptor.decrypt(source.getAge())));
		return target;
	}

	public List<EmployeeModel> protoToModel(List<EmployeeBase> source) {
		if (CollectionUtils.isEmpty(source)) {
			return new ArrayList<>();
		}

		return source.stream().map(this::protoToModel).collect(Collectors.toList());
	}

	public EmployeeForXml modelToJaxbModel(EmployeeModel source) {
		var target = new EmployeeForXml();
		target.setName(source.getName());
		if (!ObjectUtils.isEmpty(source.getDob())) {
			target.setDob(source.getDob().format(dateTimeFormatter));
		}
		target.setSalary(source.getSalary());
		target.setAge(source.getAge());
		return target;
	}

	public EmployeeListForXml modelToJaxbModel(EmployeeModel[] source) {
		if (ArrayUtils.isEmpty(source)) {
			return null;
		}
		var employeeListForXml = new EmployeeListForXml();
		for (var employeeModel : source) {
			employeeListForXml.addEmployee(modelToJaxbModel(employeeModel));
		}
		return employeeListForXml;
	}

	private LocalDate fromProtoDate(String source) {
		return LocalDate.parse(source, dateTimeFormatter);
	}

}
