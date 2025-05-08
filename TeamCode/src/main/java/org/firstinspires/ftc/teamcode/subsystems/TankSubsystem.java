package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Bot;


public class TankSubsystem implements Subsystem {
    private IMU imu = null;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private double driveSpeed = 0.7;
    public Bot bot;
    public Rotation2d getRobotOrientation() {
        return new Rotation2d(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
    }


    public TankSubsystem(Bot bot) {
        this.bot = bot;


        leftMotor = bot.hMap.get(DcMotor.class, "lmotor");
        rightMotor = bot.hMap.get(DcMotor.class, "rmotor");
        imu = bot.hMap.get(IMU.class, "imu");

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void periodic() {

    }
    public void drivePeriodic() {
        double leftPower = bot.driver.gamepad.left_stick_y * driveSpeed;
        double rightPower = -bot.driver.gamepad.right_stick_x * driveSpeed;

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);

    }
    public void setTankDrivePower(double ySpeed, double rotation){

        double leftPower = ySpeed + rotation;
        double rightPower = ySpeed - rotation;

    }
    public double getL(){
        return leftMotor.getCurrentPosition();
    }
    public double getR(){
        return rightMotor.getCurrentPosition();
    }

}