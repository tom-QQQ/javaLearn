package com.java.model.command.controller.object.stereo.impl;

import com.java.model.command.controller.object.RoomType;
import com.java.model.command.controller.object.stereo.Stereo;
import org.springframework.util.Assert;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class StereoFactory {

    public static Stereo createStereo(RoomType type) {

        if (type == RoomType.LivingRoom) {
            return new LivingRoomStereo();

        } else {
            Assert.isTrue(false, "这种类型的音响不存在");
            return null;
        }
    }
}
