package com.sbt.terminal.interfaces;

import com.sbt.terminal.exeptions.BadBalance;
import com.sbt.terminal.exeptions.BadMultiplicity;

public interface Terminal {
    int getBalance(int userId) throws BadBalance;

    boolean addFunds(int userId, int cashSum) throws BadMultiplicity;

    boolean receiveFunds(int userId, int cashSum) throws BadMultiplicity, BadBalance;
}
