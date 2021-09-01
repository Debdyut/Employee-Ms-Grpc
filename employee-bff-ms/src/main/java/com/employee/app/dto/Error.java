package com.employee.app.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Error represents the error response model.
 */
@Schema(description = "Error represents the error response model.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-29T11:21:08.209Z[GMT]")


public class Error   {
  @JsonProperty("code")
  private String code = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("developerMessage")
  private String developerMessage = null;

  @JsonProperty("moreInfo")
  @Valid
  private List<MoreInfo> moreInfo = null;

  public Error code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Error Code represents a alpha-numeric error code received from the error
   * @return code
   **/
  @Schema(description = "Error Code represents a alpha-numeric error code received from the error")
  
    public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Message represents a textual description of a given error code
   * @return message
   **/
  @Schema(description = "Message represents a textual description of a given error code")
  
    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error developerMessage(String developerMessage) {
    this.developerMessage = developerMessage;
    return this;
  }

  /**
   * Developer Message represents technical details about the error message
   * @return developerMessage
   **/
  @Schema(description = "Developer Message represents technical details about the error message")
  
    public String getDeveloperMessage() {
    return developerMessage;
  }

  public void setDeveloperMessage(String developerMessage) {
    this.developerMessage = developerMessage;
  }

  public Error moreInfo(List<MoreInfo> moreInfo) {
    this.moreInfo = moreInfo;
    return this;
  }

  public Error addMoreInfoItem(MoreInfo moreInfoItem) {
    if (this.moreInfo == null) {
      this.moreInfo = new ArrayList<MoreInfo>();
    }
    this.moreInfo.add(moreInfoItem);
    return this;
  }

  /**
   * More details about the error as needed
   * @return moreInfo
   **/
  @Schema(description = "More details about the error as needed")
      @Valid
    public List<MoreInfo> getMoreInfo() {
    return moreInfo;
  }

  public void setMoreInfo(List<MoreInfo> moreInfo) {
    this.moreInfo = moreInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.message, error.message) &&
        Objects.equals(this.developerMessage, error.developerMessage) &&
        Objects.equals(this.moreInfo, error.moreInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, developerMessage, moreInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    developerMessage: ").append(toIndentedString(developerMessage)).append("\n");
    sb.append("    moreInfo: ").append(toIndentedString(moreInfo)).append("\n");
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
