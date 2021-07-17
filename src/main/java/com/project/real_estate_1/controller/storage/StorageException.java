package com.project.real_estate_1.controller.storage;

public class StorageException extends RuntimeException{

    public StorageException(String message) {
        super(message);
    }
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
