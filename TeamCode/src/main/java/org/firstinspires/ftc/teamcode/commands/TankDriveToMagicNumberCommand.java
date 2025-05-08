package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.Bot;
import org.firstinspires.ftc.teamcode.subsystems.TankSubsystem;
import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.subsystems.XDriveSubsystem;

import java.util.function.DoubleSupplier;

public class TankDriveToMagicNumberCommand extends CommandBase {
    private final TankSubsystem drivetrain;
    private final PIDFController yController = new PIDFController(0.005, 0, 0.0, 0.0);
    private final PIDFController rotController = new PIDFController(0.04, 0, 0.0, 0.0);
    private final double movementTarget;
    private final DoubleSupplier currentPose;
    private double xSpeed;
    private double ySpeed;
    private final Rotation2d targetRotation;
    private Bot bot;

    public TankDriveToMagicNumberCommand(TankSubsystem drive, double target, double ySpeed, Rotation2d rotation){
        this.bot = drive.bot;
        this.drivetrain = drive;
        this.movementTarget = target;
        this.currentPose = drivetrain::getL;
        this.ySpeed = ySpeed;
        this.targetRotation = rotation;
        addRequirements(drivetrain);
        initialize();
    }
    public void initialize(){
        yController.setIntegrationBounds(-1,1);
        yController.setTolerance(20);
        //xSpeed = Math.abs(xSpeed);
        //ySpeed = Math.abs(ySpeed);
    }
    @Override
    public void execute() {
        double yOutput = yController.calculate(currentPose.getAsDouble(), movementTarget);
        double rotOutput = -rotController.calculate(drivetrain.getRobotOrientation().getDegrees(), Utils.getRotationTargetTank(drivetrain, targetRotation));
        drivetrain.setTankDrivePower(
                yOutput * ySpeed,
                rotOutput
        );

        bot.telem.addData("Y is at setpoint", yController.atSetPoint());
        bot.telem.addData("Rot is at setpoint", rotController.atSetPoint());
        bot.telem.addData("Target", movementTarget);
    }
    @Override
    public boolean isFinished(){
        return  (yController.atSetPoint() && ySpeed != 0) && rotController.atSetPoint();
    }

}
