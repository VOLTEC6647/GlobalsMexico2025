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
import org.firstinspires.ftc.teamcode.subsystems.XDriveSubsystem;
import org.firstinspires.ftc.teamcode.commands.XDriveToMagicNumberCommand;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp")
public class AutoEjemplo extends CommandOpMode {
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


        //xDrive, double target, double xSpeed, double ySpeed, Rotation2d rotation
        schedule(
                new SequentialCommandGroup(
                        new XDriveToMagicNumberCommand(xDrive, 100,0,1, Rotation2d.fromDegrees(0)),
                        new WaitCommand(3000),
                        new XDriveToMagicNumberCommand(xDrive, 100,1,0, Rotation2d.fromDegrees(0))

                        ));

        while (opModeInInit()){
            telem.update();
        }

    }
}