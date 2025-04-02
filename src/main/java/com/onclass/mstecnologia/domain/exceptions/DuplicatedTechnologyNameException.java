package com.onclass.mstecnologia.domain.exceptions;

public class DuplicatedTechnologyNameException extends RuntimeException {
    public DuplicatedTechnologyNameException(String name) {
        super("A technology with the name '" + name + "' already exists.");
    }
}