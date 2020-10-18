package com.ninjarmm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Error
{
  @JsonProperty
  private String errorMessage;

  @JsonProperty
  private HttpStatus status;

  @JsonProperty
  private Integer code;

  @JsonProperty
  private List<ErrorDetail> details = new ArrayList<ErrorDetail>();

  public Error(String errorMessage, HttpStatus status, List<ErrorDetail> details)
  {
    this.errorMessage = errorMessage;
    this.details = details;
    this.status = status;
    this.code = status.value();
  }

  public Error(String title, Throwable throwable, HttpStatus status)
  {
    this.errorMessage = title;
    this.status = status;
    this.code = status.value();

    details.add(createDetail(code, throwable.getMessage(), throwable));
  }

  private ErrorDetail createDetail(Integer code, String message, Throwable throwable)
  {
    ErrorDetail detail = new ErrorDetail();
    detail.setMessage(message);
    detail.setDebugMessage(Arrays.toString(throwable.getStackTrace()));

    return detail;
  }

  public List<ErrorDetail> getDetails()
  {
    return details;
  }

  public void setDetails(List<ErrorDetail> details)
  {
    this.details = details;
  }

  public String getErrorMessage()
  {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage)
  {
    this.errorMessage = errorMessage;
  }

  public HttpStatus getStatus()
  {
    return status;
  }

  public void setStatus(HttpStatus status)
  {
    this.status = status;
  }

  public Integer getCode()
  {
    return code;
  }

  public void setCode(Integer code)
  {
    this.code = code;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Error error = (Error) o;
    return Objects.equals(errorMessage, error.errorMessage) &&
            status == error.status &&
            Objects.equals(details, error.details) &&
            Objects.equals(code, error.code);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(errorMessage, status, details, code);
  }

  @Override
  public String toString()
  {
    return "Error{" +
            "errorMessage='" + errorMessage + '\'' +
            ", status=" + status +
            ", details=" + details +
            ", code=" + code +
            '}';
  }
}
