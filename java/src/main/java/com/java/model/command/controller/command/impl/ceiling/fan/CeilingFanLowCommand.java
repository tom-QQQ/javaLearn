package com.java.model.command.controller.command.impl.ceiling.fan;


import com.java.model.command.controller.object.ceiling.fan.CeilingFan;

/**
 * @author Ning
 * @date Create in 2019/3/31
 */
public class CeilingFanLowCommand extends AbstractCeilingFanCommand {

    public CeilingFanLowCommand(CeilingFan ceilingFan) {

        super.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.adjustLow();
    }
}
