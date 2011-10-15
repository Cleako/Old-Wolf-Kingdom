package org.lupus_regnum.gs.states;


public enum CombatState {
    ERROR, // Can be attacked
    LOST,
    // Can be attacked, // Can't be attacked
    RUNNING, // Can be attacked
    WAITING, // Can be attacked
    WON
}