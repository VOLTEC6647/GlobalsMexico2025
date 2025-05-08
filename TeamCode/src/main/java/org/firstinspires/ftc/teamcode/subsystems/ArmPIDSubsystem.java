package org.firstinspires.ftc.teamcode.subsystems;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Bot;
import org.firstinspires.ftc.teamcode.libs.MiniPID;

public class ArmPIDSubsystem implements AnyArm{

    private double pidOutput;
    private MiniPID pid;
    private Bot bot;
    public int setPoint = 0;
    public int currentPosition = 0;
    public DcMotor motorRotate;
    private TelemetryPacket packet = new TelemetryPacket();
    private FtcDashboard dashboard = FtcDashboard.getInstance();

    public ArmPIDSubsystem(Bot bot) {
        this.bot = bot;

        pid = new MiniPID(0.005,0,0.0012);

        pid.setSetpoint(setPoint);

        pid.setOutputLimits(-0.7, 0.7);


        motorRotate = bot.hMap.get(DcMotor.class, "motorR");
        motorRotate.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        dashboard.sendTelemetryPacket(packet);
    }
    public void disableperiodictwo (){
        bot.telem.addData("servoRotateRight",motorRotate.getCurrentPosition());

    }
    @Override
    public void setSetpoint (int setPoint) {
        this.setPoint = setPoint;
    }

    @Override
    public DcMotor getMotorRotate() {
        return motorRotate;
    }

    public void pickup () {
        setPoint = 759;
    }
    public void close () {
        setPoint = 0;
    }


    @Override
    public void periodic() {

        currentPosition = motorRotate.getCurrentPosition();


        if (bot.opertator.gamepad.right_trigger > 0) {
            setPoint = 764;

        } else if (bot.opertator.gamepad.left_trigger > 0) {
            setPoint = 100;

        } else if (bot.opertator.gamepad.right_bumper) {
            setPoint = 445;

        } else if (bot.opertator.gamepad.left_bumper) {
            setPoint = 650;
        }



        pid.setSetpoint(setPoint);

        pidOutput = pid.getOutput(motorRotate.getCurrentPosition());

        motorRotate.setPower(pidOutput);


    }
}