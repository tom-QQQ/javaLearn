package com.java.model.command.controller;


import com.java.model.command.controller.command.Command;
import com.java.model.command.controller.command.impl.*;
import com.java.model.command.controller.command.impl.ceiling.fan.CeilingFanHighCommand;
import com.java.model.command.controller.command.impl.ceiling.fan.CeilingFanLowCommand;
import com.java.model.command.controller.command.impl.ceiling.fan.CeilingFanMediumCommand;
import com.java.model.command.controller.command.impl.ceiling.fan.CeilingFanOffCommand;
import com.java.model.command.controller.controller.RemoteController;
import com.java.model.command.controller.controller.SimpleRemoteController;
import com.java.model.command.controller.object.RoomType;
import com.java.model.command.controller.object.ceiling.fan.CeilingFan;
import com.java.model.command.controller.object.ceiling.fan.impl.CeilingFanFactory;
import com.java.model.command.controller.object.door.GarageDoor;
import com.java.model.command.controller.object.light.Light;
import com.java.model.command.controller.object.light.impl.BaseLight;
import com.java.model.command.controller.object.light.impl.LightFactory;
import com.java.model.command.controller.object.stereo.Stereo;
import com.java.model.command.controller.object.stereo.impl.StereoFactory;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class CommandTest {

    public static void main(String[] args) {

        Light light = new BaseLight();
        // 使用命令包装操作对象
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        SimpleRemoteController simpleRemoteController = new SimpleRemoteController();

        // 使用控制器设置命令, 而不直接操作对象, 解除控制器和操作对象之间的耦合
        // 这样在新增操作对象或新增操作对象的功能时就不用修改控制器的代码, 只需要修改操作对象的代码和新增命令实现类即可
        // 控制器不需要关心具体执行什么命令, 这个命令是哪个对象的, 只要执行命令就好
        simpleRemoteController.setCommand(lightOnCommand);
        // 虽然调用的是命令接口的方法, 但是实际上调用的还是被操作对象的方法, 命令接口起到桥梁的作用
        simpleRemoteController.buttonWasPressed();


        // 控制器
        RemoteController remoteController;
        remoteController = new RemoteController();

        // 控制对象
        Light kitchenLight = LightFactory.getLight(RoomType.Kitchen);
        Light livingRoomLight = LightFactory.getLight(RoomType.LivingRoom);
        GarageDoor garageDoor = new GarageDoor();
        // 这个对象是公用的, 因此在之后修改speed时对这个对象的修改影响到了其他几个命令对象
        CeilingFan livingRoomCeilingFan;
        livingRoomCeilingFan = CeilingFanFactory.createCeilingFan(RoomType.LivingRoom);
        Stereo livingRoomStereo = StereoFactory.createStereo(RoomType.LivingRoom);

        // 控制命令
        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(livingRoomCeilingFan);
        CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(livingRoomCeilingFan);
        CeilingFanLowCommand ceilingFanLow = new CeilingFanLowCommand(livingRoomCeilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(livingRoomCeilingFan);

        GarageDoorUpCommand garageDoorUp = new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand garageDoorDown = new GarageDoorDownCommand(garageDoor);

        StereoOnWithCdCommand stereoOnWithCd = new StereoOnWithCdCommand(livingRoomStereo);
        StereoOffCommand stereoOff = new StereoOffCommand(livingRoomStereo);

        Command[] macroOnCommads = new Command[]{kitchenLightOn, livingRoomLightOn, ceilingFanHigh, garageDoorUp, stereoOnWithCd};
        Command[] macroOffCommands = new Command[]{kitchenLightOff, livingRoomLightOff, ceilingFanOff, garageDoorDown, stereoOff};
        MacroCommand macroOnCommand = new MacroCommand(macroOnCommads);
        MacroCommand macroOffCommand = new MacroCommand(macroOffCommands);

        // 给控制器设置命令
        remoteController.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteController.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteController.setCommand(2, garageDoorUp, garageDoorDown);
        remoteController.setCommand(3, stereoOnWithCd, stereoOff);
        remoteController.setCommand(4, ceilingFanLow, ceilingFanOff);
        remoteController.setCommand(5, ceilingFanMedium, ceilingFanHigh);
        remoteController.setCommand(6, macroOnCommand, macroOffCommand);

        System.out.println(remoteController);

        // 控制器执行插槽位置的命令
//        remoteController.onButtonPressed(0);
//        remoteController.offButtonPressed(0);
//        remoteController.onButtonPressed(1);
//        remoteController.offButtonPressed(1);
//        remoteController.onButtonPressed(2);
//        remoteController.offButtonPressed(2);
//        remoteController.onButtonPressed(3);
//        remoteController.offButtonPressed(3);
//        remoteController.onButtonPressed(4);
//        remoteController.offButtonPressed(4);
//
//        System.out.println("\n" + "开始撤销命令执行");
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();

//        System.out.println("\n" + "电风扇测试");
//        // 这里先记录了之前的速度, 并修改了公共引用对象的速度后存储到撤销队列中, 此时撤销队列中的最新的command的speed是默认速度0, 公共引用对象livingRoomCeilingFan的speed为1
//        remoteController.onButtonPressed(4);
//        // 再次调用execute()方法后, 撤销队列最新的command对象的speed为1, 公共引用对象livingRoomCeilingFa为2
//        remoteController.onButtonPressed(5);
//        remoteController.offButtonPressed(5);
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();
//        remoteController.undoButtonWasPushed();


        System.out.println("宏命令测试");
        remoteController.onButtonPressed(6);
        remoteController.offButtonPressed(6);
        remoteController.undoButtonWasPushed();
        remoteController.undoButtonWasPushed();


    }
}
