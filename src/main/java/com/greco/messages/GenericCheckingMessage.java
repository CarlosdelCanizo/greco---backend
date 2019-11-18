package com.greco.messages;

public enum GenericCheckingMessage {
    ENTITY_NOT_FOUND("Entity not found."),
    FORBIDDEN_ACTION("The user doesn't have permission to do this action."),
    RESETPASSWORD_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH("Password and confirm password doesn't match."),
    RESETPASSWORD_USER_NOT_REGISTERED("This user is not registered."),
    RESETPASSWORD_INVALID_UUID("Invalid uuid"),
    REGISTRATION_EMPTY_EMAIL("Email empty."),
    REGISTRATION_EMPTY_PASSWORD("Password empty."),
    REGISTRATION_EMPTY_CONFIRM_PASSWORD("Confirm password empty."),
    REGISTRATION_INVALID_PASSWORD_FORMAT("Password format wrong."),
    REGISTRATION_INVALID_EMAIL("Email invalid."),
    REGISTRATION_PASSWORD_AND_CONFIRM_PASSWORD_NOT_MATCH("Password and confirm password doesn't match."),
    REGISTRATION_USER_ALREADY_REGISTERED("This user is already registered."),
    REGISTRATION_USERNAME_ALREADY_REGISTERED("This username is already registered."),
    FORGOT_PASSWORD_EMPTY_EMAIL("Email empty."),
    FORGOT_PASSWORD_UNREGISTERED_USER("Unregistered user."),
    SOLAR_PANEL_UPLOAD_PROBLEMS("The file could not be uploaded."),
    SOLAR_PANEL_UPLOADED_MULTIMEDIA_EXCEEDED("You can not upload more multimedia for this solar panel. Limit exceeded."),
    SOLAR_PANEL_NOT_PERMISSION_TO_UPLOAD_MULTIMEDIA("You don't have permissions to upload multimedia in this panel."),
    UPLOAD_FILENAME_EMPTY("Filename is empty."),
    COMMENT_EMPTY_TEXT("The text is empty");
    private String message;

    GenericCheckingMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
