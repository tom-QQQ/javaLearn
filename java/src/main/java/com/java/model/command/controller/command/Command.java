package com.java.model.command.controller.command;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public interface Command {

    /**
     * 执行命令
     */
    void execute();

    /**
     * 撤销
     */
    void undo();
}
