package com.java.model.command.controller.command.impl;


import com.java.model.command.controller.command.Command;
import com.java.model.command.controller.object.light.Light;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
