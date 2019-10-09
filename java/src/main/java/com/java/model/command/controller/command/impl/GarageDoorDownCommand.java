package com.java.model.command.controller.command.impl;


import com.java.model.command.controller.command.Command;
import com.java.model.command.controller.object.door.GarageDoor;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class GarageDoorDownCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.down();
    }

    @Override
    public void undo() {
        garageDoor.up();
    }
}
