package com.sbt.terminal.impl;

import com.sbt.terminal.exeptions.*;

import java.util.Date;


public class PinValidator {
    private  final int userId = 1;
    private final int userPassword = 123;
    private int attempt = 0;
    private final long blockTime = 5000;
    private long accountBlockTime = 0;

    /**
     * @param userId        Идентификатор пользователя
     * @param userPassword  Пароль пользователя
     * @return True - При успешной авторизации, False в противном случае.
     * @throws AccountLockedExeption  Выбрасывется при временной блокировки аакаунта после 3-х неудаячных попыток
     *                               Ввода Пин-Кода
     * @throws BadAccount Пользователь с такой комбинацией UserID & UserPassword не найден
     */
    public  boolean userAuth(int userId, int userPassword) throws AccountLockedExeption, BadAccount {

        if (this.userId == userId) {
            this.attempt++;
            if (this.attempt <= 3) {
                if (accountBlockTime == 0 || accountBlockTime < (new Date().getTime())) {
                    accountBlockTime = 0;
                    if (this.userPassword == userPassword) {
                        return true;
                    }
                } else {
                    throw new AccountLockedExeption("You account is locked, please wait" + blockTime / 1000 + "sec");
                }
            } else {
                accountBlockTime = new Date().getTime() + blockTime;
                this.attempt = 0;
                throw new AccountLockedExeption("You account is locked, please wait" + blockTime / 1000 + "sec");
            }
        } else {
            throw new BadAccount("Account id = " + userId + " not found!");
        }
        return false;
    }
}


