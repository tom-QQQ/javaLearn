package com.java.model.command.log;

import com.java.model.command.log.impl.DeleteNode;
import com.java.model.command.log.impl.InsertCommand;
import com.java.model.command.log.impl.ModifyNode;

/**
 * @author Ning
 * @date Create in 2019/4/23
 */
public class LogCommandTest {

    public static void main(String[] args) {

        ConfigSettingWindow configSettingWindow = new ConfigSettingWindow();
        ConfigOperator configOperator = new ConfigOperator();

        Command insertCommand = new InsertCommand("增加");
        insertCommand.setConfigOperator(configOperator);
        configSettingWindow.setCommand(insertCommand);
        configSettingWindow.call("网站首页");

        Command insertPort = new InsertCommand("增加");
        insertPort.setConfigOperator(configOperator);
        configSettingWindow.setCommand(insertPort);
        configSettingWindow.call("端口号");


        Command modifyPage = new ModifyNode("修改");
        modifyPage.setConfigOperator(configOperator);
        configSettingWindow.setCommand(modifyPage);
        configSettingWindow.call("网站首页");


        Command modifyNode = new ModifyNode("修改");
        modifyNode.setConfigOperator(configOperator);
        configSettingWindow.setCommand(modifyNode);
        configSettingWindow.call("端口号");


//        Command deleteNode = new DeleteNode("删除");
//        deleteNode.setConfigOperator(configOperator);
//        configSettingWindow.setCommand(deleteNode);
//        configSettingWindow.call("网站首页");


        System.out.println("----------------------------");
        System.out.println("保存配置");

        configSettingWindow.save();

        System.out.println("----------------------------");
        System.out.println("恢复配置");
        System.out.println("----------------------------");
        configSettingWindow.recover();

    }
}
