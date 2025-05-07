package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.XDriveSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "AutoHelper", group = "Tools")
public class autohelper extends CommandOpMode {
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

        XDriveSubsystem xDrive = new XDriveSubsystem(bot);
        xDrive.setBreakMode(DcMotor.ZeroPowerBehavior.FLOAT);
        xDrive.register();

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