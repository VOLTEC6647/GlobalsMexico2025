package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;

public class ClawOrientedCommand extends CommandBase {
    private final ClawSubsystem claw;
    public ClawOrientedCommand(ClawSubsystem claw){
        this.claw = claw;
        addRequirements(claw);
    }

    public void execute() {
        claw.periodic();
    }
}
