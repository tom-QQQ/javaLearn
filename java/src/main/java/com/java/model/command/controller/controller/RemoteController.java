package com.java.model.command.controller.controller;

import com.java.model.command.controller.command.Command;
import com.java.model.command.controller.command.impl.NoCommand;
import org.springframework.util.Assert;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class RemoteController {

    private static int size = 7;

    private Command[] onCommand;
    private Command[] offCommand;
    private LinkedQueue<Command> lastCommands;

    public RemoteController() {

        this.onCommand = new Command[size];
        this.offCommand = new Command[size];
        lastCommands = new LinkedQueue<>(10);

        // 初始化空命令
        for (int index = 0; index < size; index++) {
            Command noCommand = new NoCommand();
            onCommand[index] = noCommand;
            offCommand[index] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {

        Assert.isTrue(slot > -1 && slot < size, "插槽位置错误");

        this.onCommand[slot] = onCommand;
        this.offCommand[slot] = offCommand;
    }

    public void onButtonPressed(int slot) {

        Assert.isTrue(slot > -1 && slot < size, "插槽位置错误");
        onCommand[slot].execute();
        lastCommands.offer(onCommand[slot]);
    }

    public void offButtonPressed(int slot) {

        Assert.isTrue(slot > -1 && slot < size, "插槽位置错误");
        offCommand[slot].execute();
        lastCommands.offer(offCommand[slot]);
    }

    /**
     * 撤销上一步的操作, 最多撤销10次之前的操作
     */
    public void undoButtonWasPushed() {

        // 没有命令时执行空命令
        if (lastCommands.size() == 0) {

            new NoCommand().undo();
            return;
        }

        // 有历史命令时执行最后一条命令后删除最后一条命令
        lastCommands.getLast().undo();
        lastCommands.poolLast();
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n----- 远程遥控器 -----\n");

        for (int index = 0; index < onCommand.length; index++) {
            stringBuilder.append("[插槽 ")
                    .append(index)
                    .append("] ")
                    .append(onCommand[index].getClass().toString())
                    .append(" ")
                    .append(offCommand[index].getClass().toString())
                    .append("\n");
        }

        return stringBuilder.toString();
    }
}
