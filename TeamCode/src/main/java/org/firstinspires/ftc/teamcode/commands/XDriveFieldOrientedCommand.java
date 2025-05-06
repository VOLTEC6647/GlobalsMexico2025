package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.XDriveSubsystem;

public class XDriveFieldOrientedCommand extends CommandBase {
    private final GamepadEx driverGamepad;
    private final XDriveSubsystem drivetrain;
    public XDriveFieldOrientedCommand(XDriveSubsystem drive, GamepadEx driverGamepad){
        this.driverGamepad = driverGamepad;
        this.drivetrain = drive;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.teleopDrive(
                driverGamepad.getLeftX(),
                driverGamepad.getLeftY(),
                driverGamepad.getRightX()
        );
    }
}
