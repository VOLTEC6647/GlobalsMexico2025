package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.subsystems.XDriveSubsystem;
import org.firstinspires.ftc.teamcode.utils.Utils;
import org.firstinspires.ftc.teamcode.utils.wpilib.Debouncer;

import java.util.function.DoubleSupplier;


public class XSetRotation extends CommandBase {
    private final XDriveSubsystem drivetrain;
    private final PIDFController rotController = new PIDFController(0.1, 0, 0.0, 0.0);
    private final Rotation2d targetRotation;

    private Debouncer rotDebouncer;

    public XSetRotation(XDriveSubsystem drive, Rotation2d rotation){
        this.drivetrain = drive;
        this.targetRotation = rotation;
        addRequirements(drivetrain);
        initialize();
    }

    public void initialize(){
        rotController.setIntegrationBounds(-1,1);
        rotController.setTolerance(2);
        rotDebouncer = new Debouncer(0.3, Debouncer.DebounceType.kRising, drivetrain.bot);

    }

    @Override
    public void execute() {
        double rotOutput = -rotController.calculate(drivetrain.getRobotOrientation().getDegrees(), Utils.getRotationTarget(drivetrain, targetRotation));
        drivetrain.teleopDrive(
                0,
                0,
                rotOutput
        );
        drivetrain.bot.telem.addData("Rot is at setpoint", rotController.atSetPoint());
    }

    @Override
    public boolean isFinished(){
        return rotDebouncer.calculate(rotController.atSetPoint());
    }
}
