package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.commands.XDriveBSCommand;
import org.firstinspires.ftc.teamcode.commands.XDriveFieldOrientedCommand;
import org.firstinspires.ftc.teamcode.subsystems.XDriveSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp")
public class teleop extends CommandOpMode {
    private Bot bot;
    private MultipleTelemetry telem;
    private GamepadEx driverGamepad;
    private GamepadEx operatorGamepad;

    public void initialize() {

        CommandScheduler.getInstance().reset();

        telem = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        bot = new Bot(telem, hardwareMap, driverGamepad, operatorGamepad);
        bot.getImu().resetYaw();

        XDriveSubsystem xDrive = new XDriveSubsystem(bot);
        xDrive.register();

        CommandBase driveCommand;
        if(false){
            driveCommand = new XDriveFieldOrientedCommand(
                    xDrive,
                    driverGamepad
            );
        }else{
            driveCommand = new XDriveBSCommand(
                    xDrive,
                    driverGamepad
            );
        }

        xDrive.setDefaultCommand(driveCommand);

        telem.addData("status","init");
        telem.update();

    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();
        telem.addData("status","start");
        telem.update();
    }


}