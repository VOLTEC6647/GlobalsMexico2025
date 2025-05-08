package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmPIDSubsystem;

public class ArmPIDOrientedCommand extends CommandBase {
    private final ArmPIDSubsystem arm;
    private int setPoint;
    public ArmPIDOrientedCommand (ArmPIDSubsystem arm, int setPoint){
        this.arm = arm;
        this.setPoint = setPoint;
        addRequirements(arm);
    }

    public void execute() {
        arm.setSetpoint(setPoint);
    }
    @Override
    public boolean isFinished(){
        return Math.abs(setPoint - arm.motorRotate.getCurrentPosition()) < 20;
    }
}
