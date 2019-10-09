package com.java.model.command.log;

import com.java.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ning
 * @date Create in 2019/4/23
 */
public class ConfigSettingWindow {

    private List<Command> commands = new ArrayList<>();
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void call(String args) {
        command.execute(args);
        commands.add(command);
    }

    public void save() {
        FileUtil.writeCommands(commands);
    }

    public void recover() {

        List<Command> commands = FileUtil.readCommands();

        for (Command command: commands) {
            command.execute();
        }
    }
}
