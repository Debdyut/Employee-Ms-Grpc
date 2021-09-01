package com.employee.app.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * More details about the error as needed
 */
@Schema(description = "More details about the error as needed")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-29T11:21:08.209Z[GMT]")


public class MoreInfo   {
  @JsonProperty("errorCode")
  private String errorCode = null;

  @JsonProperty("propertyField")
  private String propertyField = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("errorType")
  private String errorType = null;

  public MoreInfo errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Error code sent by API w.r.t. each validation
   * @return errorCode
   **/
  @Schema(description = "Error code sent by API w.r.t. each validation")
  
    public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public MoreInfo propertyField(String propertyField) {
    this.propertyField = propertyField;
    return this;
  }

  /**
   * Each exact property field that was validated
   * @return propertyField
   **/
  @Schema(description = "Each exact property field that was validated")
  
    public String getPropertyField() {
    return propertyField;
  }

  public void setPropertyField(String propertyField) {
    this.propertyField = propertyField;
  }

  public MoreInfo message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Each error message on the property level validation done by the API
   * @return message
   **/
  @Schema(description = "Each error message on the property level validation done by the API")
  
    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public MoreInfo errorType(String errorType) {
    this.errorType = errorType;
    return this;
  }

  /**
   * Error Type represents a type String, which represent the generated error type
   * @return errorType
   **/
  @Schema(description = "Error Type represents a type String, which represent the generated error type")
  
    public String getErrorType() {
    return errorType;
  }

  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MoreInfo moreInfo = (MoreInfo) o;
    return Objects.equals(this.errorCode, moreInfo.errorCode) &&
        Objects.equals(this.propertyField, moreInfo.propertyField) &&
        Objects.equals(this.message, moreInfo.message) &&
        Objects.equals(this.errorType, moreInfo.errorType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, propertyField, message, errorType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MoreInfo {\n");
    
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    propertyField: ").append(toIndentedString(propertyField)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    errorType: ").append(toIndentedString(errorType)).append("\n");
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
