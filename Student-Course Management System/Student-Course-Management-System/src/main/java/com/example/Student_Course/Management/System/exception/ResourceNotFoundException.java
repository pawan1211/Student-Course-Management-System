package com.example.Student_Course.Management.System.exception;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Object id) {
        super(resource + " not found with id " + id);
    }
}
