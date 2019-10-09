package com.java.model.command.controller.command.impl;


import com.java.model.command.controller.command.Command;

/**
 * 空对象, 当没有功能时用来替代null检查
 * @author Ning
 * @date Create in 2019/3/30
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
        System.out.println("无操作");
    }

    @Override
    public void undo() {
        System.out.println("无操作");
    }
}
