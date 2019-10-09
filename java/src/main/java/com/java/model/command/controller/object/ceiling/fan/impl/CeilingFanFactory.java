package com.java.model.command.controller.object.ceiling.fan.impl;

import com.java.model.command.controller.object.RoomType;
import com.java.model.command.controller.object.ceiling.fan.CeilingFan;
import org.springframework.util.Assert;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class CeilingFanFactory {

    public static CeilingFan createCeilingFan(RoomType type) {

        if (type == RoomType.LivingRoom) {
            return new LivingRoomCeilingFan();
        } else {
            Assert.isTrue(false, "这个电风扇不存在");
            return null;
        }
    }
}
