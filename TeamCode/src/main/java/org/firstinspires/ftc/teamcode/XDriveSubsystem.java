package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class XDriveSubsystem implements Subsystem {
    private DcMotor frontLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor backRightMotor = null;
    private IMU imu = null;

    public Rotation2d getRobotOrientation() {
        return new Rotation2d(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
    }

    public XDriveSubsystem(Bot bot){
        frontLeftMotor = bot.hMap.get(DcMotor.class, "FL");
        frontRightMotor = bot.hMap.get(DcMotor.class, "FR");
        backLeftMotor = bot.hMap.get(DcMotor.class, "BL");
        backRightMotor = bot.hMap.get(DcMotor.class, "BR");

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = bot.hMap.get(IMU.class, "imu");
    }

    public void teleopDrive(double x, double y, double rot){
        double y_joystick = -y;
        double x_joystick = x;
        double rx_joystick = rot;

        double botHeadingRadians = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        Rotation2d robotOrientation = new Rotation2d(botHeadingRadians);

        double robotFrameX = x_joystick * robotOrientation.getCos() + y_joystick * robotOrientation.getSin();
        double robotFrameY = -x_joystick * robotOrientation.getSin() + y_joystick * robotOrientation.getCos();

        robotFrameX *= 1.1;

        double frontLeftPower  = robotFrameY + robotFrameX + rot;
        double frontRightPower = robotFrameY - robotFrameX - rot;
        double backLeftPower   = robotFrameY - robotFrameX + rot;
        double backRightPower  = robotFrameY + robotFrameX - rot;

        double maxPower = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        if (maxPower > 1.0) {
            frontLeftPower /= maxPower;
            frontRightPower /= maxPower;
            backLeftPower /= maxPower;
            backRightPower /= maxPower;
        }

        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);
    }

}
