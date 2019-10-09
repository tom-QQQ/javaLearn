package com.java.model.command.controller.command.impl;


import com.java.model.command.controller.command.Command;
import com.java.model.command.controller.object.door.GarageDoor;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class GarageDoorUpCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }

    @Override
    public void undo() {
        garageDoor.down();
    }
}
