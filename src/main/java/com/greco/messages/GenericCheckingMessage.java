package com.greco.messages;

public enum GenericCheckingMessage {
    REGISTRATION_EMPTY_EMAIL("Email empty."),
    REGISTRATION_EMPTY_PASSWORD("Password empty."),
    REGISTRATION_EMPTY_CONFIRM_PASSWORD("Confirm password empty."),
    REGISTRATION_INVALID_PASSWORD_FORMAT("Password format wrong."),
    REGISTRATION_INVALID_EMAIL("Email invalid."),
    REGISTRATION_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH("Password and confirm password doesn't match"),
    REGISTRATION_USER_ALREADY_REGISTERED("This user is already registered"),
    FORGOT_PASSWORD_EMPTY_EMAIL("Email empty."),
    FORGOT_PASSWORD_UNREGISTERED_USER("Unregistered user.");
    private String message;

    GenericCheckingMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
