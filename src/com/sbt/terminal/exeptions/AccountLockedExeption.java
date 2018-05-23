package com.sbt.terminal.exeptions;

public class AccountLockedExeption extends Exception {
    public AccountLockedExeption(String message) {
        super(message);
    }
}
