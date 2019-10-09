package com.java.model.command.log;

import java.io.Serializable;

/**
 * @author Ning
 * @date Create in 2019/4/23
 */
public abstract class Command implements Serializable {

    protected String commandName;
    protected String commandArgs;
    protected ConfigOperator configOperator;

    public Command(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setConfigOperator(ConfigOperator configOperator) {
        this.configOperator = configOperator;
    }

    /**
     * 用于初次执行时保存命令参数
     * @param commandArgs 命令参数
     */
    public abstract void execute(String commandArgs);

    /**
     * 恢复时执行命令
     */
    public abstract void execute();
}
