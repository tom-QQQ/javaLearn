package com.java.model.command.log.impl;

import com.java.model.command.log.Command;

import java.io.Serializable;

/**
 * @author Ning
 * @date Create in 2019/4/23
 */
public class InsertCommand extends Command implements Serializable {

    public InsertCommand(String commandName) {
        super(commandName);
    }

    @Override
    public void execute(String commandArgs) {
        super.commandArgs = commandArgs;
        configOperator.insertNode(commandArgs);
    }

    @Override
    public void execute() {
        configOperator.insertNode(commandArgs);
    }
}
