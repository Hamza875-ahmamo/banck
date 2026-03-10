package org.softsofi.exeception;

public class BlanceNotSufficientExeception extends Exception {
    public BlanceNotSufficientExeception(String balanceNotSufficient) {
        super(balanceNotSufficient);
    }
}
