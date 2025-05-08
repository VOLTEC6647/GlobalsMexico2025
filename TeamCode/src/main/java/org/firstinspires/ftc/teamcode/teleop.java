package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.commands.ArmPIDOrientedCommand;
import org.firstinspires.ftc.teamcode.commands.ClawOrientedCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeOrientedCommand;
import org.firstinspires.ftc.teamcode.commands.SlidesOrientedCommand;
import org.firstinspires.ftc.teamcode.commands.TankDriveCommand;
import org.firstinspires.ftc.teamcode.commands.XDriveFieldOrientedCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmPIDSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SlidesSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.TankSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.XDriveSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp")
public class teleop extends CommandOpMode {
    private Bot bot;
    private MultipleTelemetry telem;
    private GamepadEx driverGamepad;
    private GamepadEx operatorGamepad;
    private ArmPIDSubsystem arm;

    public void initialize() {

        CommandScheduler.getInstance().reset();

        telem = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        arm = new ArmPIDSubsystem(bot);

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        arm.register();

        // drive region

        bot = new Bot(telem, hardwareMap, driverGamepad, operatorGamepad);


        /* arm region

        ArmPIDSubsystem arm = new ArmPIDSubsystem(bot);

        ArmPIDOrientedCommand armCommand = new ArmPIDOrientedCommand(arm);

        arm.setDefaultCommand(armCommand); */

        /* claw region

        ClawSubsystem claw = new ClawSubsystem(bot);

        ClawOrientedCommand clawCommand = new ClawOrientedCommand(claw);

        claw.setDefaultCommand(clawCommand); */

        /* intake region

        IntakeSubsystem intake = new IntakeSubsystem(bot);

        IntakeOrientedCommand intakeCommand = new IntakeOrientedCommand(intake);

        intake.setDefaultCommand(intakeCommand); */

        /* slides region

        SlidesSubsystem slides = new SlidesSubsystem(bot);

        SlidesOrientedCommand slidesCommand = new SlidesOrientedCommand(slides);

        slides.setDefaultCommand(slidesCommand); */

        TankSubsystem drive = new TankSubsystem(bot);

        TankDriveCommand driveCommand = new TankDriveCommand(drive);

        drive.setDefaultCommand(driveCommand);

        while (opModeInInit()){
            arm.disableperiodictwo();
            telem.update();
        }
        //endregion


    }
}