package com.java.model.command.controller.command.impl.ceiling.fan;


import com.java.model.command.controller.command.Command;
import com.java.model.command.controller.object.ceiling.fan.CeilingFan;

/**
 * @author Ning
 * @date Create in 2019/3/31
 */
public abstract class AbstractCeilingFanCommand implements Command {

    CeilingFan ceilingFan;
    int prevSpeed;

    @Override
    public void undo() {

        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.adjustHigh();

        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.adjustMedium();

        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.adjustLow();

        } else {
            ceilingFan.off();
        }
    }
}
