package com.java.model.command.controller.command.impl;


import com.java.model.command.controller.command.Command;

/**
 * 宏命令
 * @author Ning
 * @date Create in 2019/3/31
 */
public class MacroCommand implements Command {

    private Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {

        for (Command command: commands) {

            command.execute();
        }
    }

    @Override
    public void undo() {

        for (Command command: commands) {
            command.undo();
        }
    }
}
