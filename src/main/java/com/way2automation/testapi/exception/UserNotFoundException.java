package com.way2automation.testapi.exception;

public class UserNotFoundException extends RuntimeException {

    private String errorMessage = "User with that ID not found";

    public UserNotFoundException() {
    }

    public UserNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public  UserNotFoundException(Long userId) {
        this.errorMessage = "User with ID " + userId + " not found!";
    }

}
