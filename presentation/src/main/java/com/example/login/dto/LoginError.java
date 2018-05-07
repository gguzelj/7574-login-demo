package com.example.login.dto;

import java.util.Objects;

public class LoginError {

    private int status;
    private String message;
    private String exception;

    public LoginError() {
    }

    public LoginError(int status, String message, String exception) {
        this.status = status;
        this.message = message;
        this.exception = exception;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginError that = (LoginError) o;
        return status == that.status &&
                Objects.equals(message, that.message) &&
                Objects.equals(exception, that.exception);
    }

    @Override
    public int hashCode() {

        return Objects.hash(status, message, exception);
    }

    @Override
    public String toString() {
        return "LoginError{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", exception='" + exception + '\'' +
                '}';
    }
}
