package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.Bot;
import org.firstinspires.ftc.teamcode.commands.ArmPIDOrientedCommand;
import org.firstinspires.ftc.teamcode.commands.XSetRotation;
import org.firstinspires.ftc.teamcode.subsystems.ArmPIDSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.XDriveSubsystem;
import org.firstinspires.ftc.teamcode.commands.XDriveToMagicNumberCommand;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Auto Ejemplo", group = "Auto")
public class AutoEjemplo extends CommandOpMode {
    private Bot bot;
    private MultipleTelemetry telem;
    private GamepadEx driverGamepad;
    private GamepadEx operatorGamepad;
    private ArmPIDSubsystem arm;

    public void initialize() {

        CommandScheduler.getInstance().reset();

        telem = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        arm = new ArmPIDSubsystem(bot);

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        bot = new Bot(telem, hardwareMap, driverGamepad, operatorGamepad);
        bot.getImu().resetYaw();

        XDriveSubsystem xDrive = new XDriveSubsystem(bot);
        xDrive.register();
        arm.register();

        //xDrive, double target, double xSpeed, double ySpeed, Rotation2d rotation
        schedule(
                new SequentialCommandGroup(
                        new XDriveToMagicNumberCommand(xDrive, 559,0,1, Rotation2d.fromDegrees(0)),
                        new WaitCommand(2000),
                        new ArmPIDOrientedCommand(arm, 200),
                        new XSetRotation(xDrive,Rotation2d.fromDegrees(90)),
                        new XDriveToMagicNumberCommand(xDrive, 559,0,1, Rotation2d.fromDegrees(0))
                        //new WaitCommand(3000),
                        //new XDriveToMagicNumberCommand(xDrive, 100,1,0, Rotation2d.fromDegrees(0))

                        ));
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();
        telem.addData("status","start");
        telem.update();
    }
}