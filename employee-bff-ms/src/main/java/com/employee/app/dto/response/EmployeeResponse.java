package com.employee.app.dto.response;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Employee Response is the response after storing the file.
 */
@Schema(description = "Employee Response is the response after storing the file.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-30T09:12:59.152Z[GMT]")


public class EmployeeResponse   {
  @JsonProperty("fileName")
  private String fileName = null;

  @JsonProperty("fileSize")
  private Double fileSize = null;

  @JsonProperty("fileType")
  private String fileType = null;

  @JsonProperty("recordsNum")
  private BigDecimal recordsNum = null;

  public EmployeeResponse fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * File Name is the common name of the file.
   * @return fileName
   **/
  @Schema(example = "employee-records-may-21", description = "File Name is the common name of the file.")
  
    public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public EmployeeResponse fileSize(Double fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  /**
   * File Size is the size of the file on disk.
   * @return fileSize
   **/
  @Schema(example = "50", description = "File Size is the size of the file on disk.")
  
    public Double getFileSize() {
    return fileSize;
  }

  public void setFileSize(Double fileSize) {
    this.fileSize = fileSize;
  }

  public EmployeeResponse fileType(String fileType) {
    this.fileType = fileType;
    return this;
  }

  /**
   * File Type is the type of the file.
   * @return fileType
   **/
  @Schema(example = "CSV", description = "File Type is the type of the file.")
  
    public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public EmployeeResponse recordsNum(BigDecimal recordsNum) {
    this.recordsNum = recordsNum;
    return this;
  }

  /**
   * Records Number is the quantity of employee records in the file.
   * @return recordsNum
   **/
  @Schema(example = "255", description = "Records Number is the quantity of employee records in the file.")
  
    @Valid
    public BigDecimal getRecordsNum() {
    return recordsNum;
  }

  public void setRecordsNum(BigDecimal recordsNum) {
    this.recordsNum = recordsNum;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployeeResponse employeeResponse = (EmployeeResponse) o;
    return Objects.equals(this.fileName, employeeResponse.fileName) &&
        Objects.equals(this.fileSize, employeeResponse.fileSize) &&
        Objects.equals(this.fileType, employeeResponse.fileType) &&
        Objects.equals(this.recordsNum, employeeResponse.recordsNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, fileSize, fileType, recordsNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployeeResponse {\n");
    
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    fileSize: ").append(toIndentedString(fileSize)).append("\n");
    sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
    sb.append("    recordsNum: ").append(toIndentedString(recordsNum)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
