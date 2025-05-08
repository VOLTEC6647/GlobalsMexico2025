package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

public class IntakeOrientedCommand extends CommandBase {
    private final IntakeSubsystem intake;
    public IntakeOrientedCommand(IntakeSubsystem intake){
        this.intake = intake;
        addRequirements(intake);
    }

    public void execute() {
        intake.periodic();
    }
}
