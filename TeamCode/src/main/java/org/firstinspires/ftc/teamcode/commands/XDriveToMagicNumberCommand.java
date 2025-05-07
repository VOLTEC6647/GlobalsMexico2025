package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.Utils;
import org.firstinspires.ftc.teamcode.XDriveSubsystem;

import java.util.function.DoubleSupplier;

public class XDriveToMagicNumberCommand extends CommandBase {
    private final XDriveSubsystem drivetrain;
    private final PIDFController xController = new PIDFController(0.1, 0, 0.0, 0.0);
    private final PIDFController yController = new PIDFController(0.1, 0, 0.0, 0.0);
    private final PIDFController rotController = new PIDFController(0.1, 0, 0.0, 0.0);
    private final double movementTarget;
    private final DoubleSupplier currentPose;
    private double xSpeed;
    private double ySpeed;
    private final Rotation2d targetRotation;

    public XDriveToMagicNumberCommand(XDriveSubsystem drive, double target, double xSpeed, double ySpeed, Rotation2d rotation){
        this.drivetrain = drive;
        this.movementTarget = target;
        this.currentPose = drivetrain::getFL;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.targetRotation = rotation;
        addRequirements(drivetrain);
        initialize();
    }

    public XDriveToMagicNumberCommand(XDriveSubsystem drive, double target, double xSpeed, double ySpeed , Rotation2d rotation, DoubleSupplier currentPose){
        this.drivetrain = drive;
        this.movementTarget = target;
        this.currentPose = currentPose;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.targetRotation = rotation;
        addRequirements(drivetrain);
        initialize();
    }

    public void initialize(){
        xController.setIntegrationBounds(-1,1);
        yController.setIntegrationBounds(-1,1);
        rotController.setIntegrationBounds(-1,1);
        xController.setTolerance(1);
        yController.setTolerance(1);
        rotController.setTolerance(3);
        //xSpeed = Math.abs(xSpeed);
        //ySpeed = Math.abs(ySpeed);
    }

    @Override
    public void execute() {
        double xOutput = xController.calculate(currentPose.getAsDouble(), movementTarget);
        double yOutput = yController.calculate(currentPose.getAsDouble(), movementTarget);
        double rotOutput = rotController.calculate(drivetrain.getRobotOrientation().getDegrees(), Utils.getRotationTarget(drivetrain, targetRotation));
        drivetrain.teleopDrive(
                xOutput * xSpeed,
                yOutput * ySpeed,
                rotOutput
        );
        drivetrain.bot.telem.addData("X is at setpoint", xController.atSetPoint());
        drivetrain.bot.telem.addData("Y is at setpoint", yController.atSetPoint());
        drivetrain.bot.telem.addData("Rot is at setpoint", rotController.atSetPoint());
        drivetrain.bot.telem.addData("Target", movementTarget);
    }

    @Override
    public boolean isFinished(){
        return ((xController.atSetPoint() && xSpeed != 0) || (yController.atSetPoint() && xSpeed != 0)) && rotController.atSetPoint();
    }
}
