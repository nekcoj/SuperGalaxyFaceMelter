package com.company.network;

import java.io.Serializable;

public class Packet implements Serializable {
    CommandType commandType;
    Object[] params;

    public Packet(CommandType commandType, Object[] params) {
        this.commandType = commandType;
        this.params = params;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public Object[] getParams() {
        return params;
    }
}
