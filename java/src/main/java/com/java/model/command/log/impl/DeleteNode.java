package com.java.model.command.log.impl;

import com.java.model.command.log.Command;

import java.io.Serializable;

/**
 * @author Ning
 * @date Create in 2019/4/23
 */
public class DeleteNode extends Command implements Serializable {

    public DeleteNode(String commandName) {
        super(commandName);
    }

    @Override
    public void execute(String commandArgs) {
        super.commandArgs = commandArgs;
        configOperator.deleteNode(commandArgs);
    }

    @Override
    public void execute() {
        configOperator.deleteNode(commandArgs);
    }
}
