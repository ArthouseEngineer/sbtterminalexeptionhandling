package com.sbt.terminal;

import com.sbt.terminal.impl.*;
import com.sbt.terminal.exeptions.*;
import com.sbt.terminal.consolemenu.*;

import java.util.Scanner;

public class Terminalimpl {
    public static void main(String[] args) {
        int userId = 0, pin = 0, sum = 0;
        byte action = 0;
        boolean status = false;
        String menu = "Выбирите действие со счетом \n 1) Положить деньги \n " +
                "2) Снятие наличных  \n 3) Проверить баланс \n 0) - выход";

        PinValidator pinValidator = new PinValidator();

        Scanner scanner = new Scanner(System.in);

        while (status == false) {
            try {
                System.out.println("Введите идентификатор пользователя User id = ");
                userId = scanner.nextInt();
                System.out.println("Введите пароль :");
                pin = scanner.nextInt();
                status = pinValidator.userAuth(userId, pin);
                System.out.println(status);
                if (status == true) {
                    TerminalServer terminalServer = new TerminalServer();
                    System.out.println("Ваш Баланас равен : " + terminalServer.getBalance(userId));
                    System.out.println(menu);
                    action = scanner.nextByte();
                    switch (action) {
                        case 1:
                            System.out.println("Введите сумму зачисления кратную 100");
                            sum = scanner.nextInt();
                            terminalServer.addFunds(userId, sum);
                            System.out.println(terminalServer.getBalance(userId));
                            break;
                        case 2:
                            System.out.println("Введите сумму списания кратную 100");
                            sum = scanner.nextInt();
                            terminalServer.receiveFunds(userId, sum);
                            System.out.println(terminalServer.getBalance(userId));
                            break;
                        case 3:
                            System.out.println("Ваш баланс равен : " + terminalServer.getBalance(userId));
                            break;
                        case 0:
                            break;
                    }
                }
            } catch (BadMultiplicity badMultiplicity) {
                System.err.println(badMultiplicity.getMessage());
            } catch (AccountLockedExeption e) {
                System.err.println(e.getMessage());
            } catch (BadAccount e) {
                System.err.println(e.getMessage());
            } catch (BadBalance badBalance) {
                System.err.println(badBalance);
            }
        }
    }
}
