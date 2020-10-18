package com.ninjarmm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Error
 */
public class ErrorDetail
{
  @JsonProperty("message")
  private String message;

  @JsonProperty
  private String debugMessage;

  public ErrorDetail message(String message)
  {
    this.message = message;
    return this;
  }

  /**
   * Get message
   *
   * @return message
   */
  @NotNull

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public String getDebugMessage()
  {
    return debugMessage;
  }

  public void setDebugMessage(String debugMessage)
  {
    this.debugMessage = debugMessage;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ErrorDetail detail = (ErrorDetail) o;
    return  Objects.equals(message, detail.message) && Objects.equals(debugMessage, detail.debugMessage);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(message, debugMessage);
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDetail {\n");

    sb.append("    message: ").append(message).append("\n");
    sb.append("}");
    return sb.toString();
  }

}
