package com.sbt.terminal.impl;


import com.sbt.terminal.interfaces.Terminal;
import com.sbt.terminal.exeptions.*;

public class TerminalServer implements Terminal {

    private int balance = 1500;

    @Override
    public int getBalance(int userId) throws BadBalance {
        if (this.balance == 0)
            throw new BadBalance("You balance is 0!");
        return balance;
    }

    @Override
    public boolean addFunds(int userId, int cashSum) throws BadMultiplicity {
        if (cashSum % 100 != 0)
            throw new BadMultiplicity("Введенная сумма не кратна 100");
        this.balance += cashSum;
        return true;
    }

    @Override
    public boolean receiveFunds(int userId, int cashSum) throws BadMultiplicity {
        if (cashSum % 100 != 0)
            throw new BadMultiplicity("Введенная сумма не кратна 100");
        this.balance -= cashSum;
        return true;
    }
}
