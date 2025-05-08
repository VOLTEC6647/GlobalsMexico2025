package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.SlidesSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TankSubsystem;

public class TankDriveCommand extends CommandBase {
    private final TankSubsystem drive;
    public TankDriveCommand(TankSubsystem drive){
        this.drive = drive;
        addRequirements(drive);
    }

    public void execute() {
        drive.drivePeriodic();
    }
}
