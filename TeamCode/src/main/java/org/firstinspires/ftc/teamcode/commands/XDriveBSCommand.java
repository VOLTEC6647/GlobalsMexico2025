package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.subsystems.XDriveSubsystem;

public class XDriveBSCommand extends CommandBase {
    private final GamepadEx driverGamepad;
    private final XDriveSubsystem drivetrain;
    private final PIDFController rotationController = new PIDFController(0.1, 0, 0.0, 0.0);
    private Rotation2d currentRotation = new Rotation2d();
    public XDriveBSCommand(XDriveSubsystem drive, GamepadEx driverGamepad){
        this.driverGamepad = driverGamepad;
        this.drivetrain = drive;
        rotationController.setIntegrationBounds(-1,1);
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        if (Math.abs(driverGamepad.getRightX()) > 0.4 || Math.abs(driverGamepad.getRightY()) > 0.4) {
            currentRotation = new Rotation2d(driverGamepad.getRightX(), driverGamepad.getRightY());
        }
        double rotationOutput = rotationController.calculate(drivetrain.getRobotOrientation().getDegrees(), Utils.getRotationTarget(drivetrain, currentRotation));
        drivetrain.teleopDrive(
                driverGamepad.getLeftX(),
                driverGamepad.getLeftY(),
                rotationOutput
        );

        drivetrain.bot.telem.addData("Rotation is at setpoint", rotationController.atSetPoint());
        drivetrain.bot.telem.addData("Rotation Target", currentRotation.getDegrees());
        drivetrain.bot.telem.addData("Rotation Output", rotationOutput);
    }
}
