package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Bot;
import org.firstinspires.ftc.teamcode.libs.MiniPID;

public class SlidesSubsystem implements Subsystem {
    private double pidOutput;
    private MiniPID pid;
    private DcMotor slideMotorRight;
    private DcMotor slideMotorLeft;

    private Bot bot;

    public int setPoint = slideMotorRight.getCurrentPosition();
    public int currentPositionLeft = 0;
    public int currentPositionRight = 0;

    public SlidesSubsystem(Bot bot){
        this.bot = bot;

        pid = new MiniPID(0.005,0,0.0012);
        pid.setSetpoint(setPoint);
        pid.setOutputLimits(-0.7, 0.7);

        slideMotorRight = bot.hMap.get(DcMotor.class, "slideR");
        slideMotorLeft = bot.hMap.get(DcMotor.class, "slideL");


        slideMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        slideMotorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        bot.telem.addData("Status", "Initialized");
    }
    public void disableperiodic (){
        bot.telem.addData("ViperR",slideMotorRight.getCurrentPosition());
        bot.telem.addData("ViperL",slideMotorLeft.getCurrentPosition());
    }
    public void up () {
        setPoint = 1500;
    }
    public void down () {
        setPoint = 5;
    }

    @Override
    public void periodic() {

        currentPositionLeft = slideMotorRight.getCurrentPosition();
        currentPositionRight = slideMotorRight.getCurrentPosition();

        if (bot.opertator.gamepad.dpad_up) {
            setPoint = 1500 ;

        } else if (bot.opertator.gamepad.dpad_down) {
            setPoint = 5;

        }

        pid.setSetpoint(setPoint);

        pidOutput = pid.getOutput(slideMotorRight.getCurrentPosition());

        slideMotorRight.setPower(pidOutput);
        slideMotorLeft.setPower(pidOutput);

    }


}
