package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.ImuGlobal;

import lombok.Getter;

public class Bot extends Robot {
    private final IMU imu;
    public final Telemetry telem;
    public final HardwareMap hMap;
    public final GamepadEx driver;
    public final GamepadEx opertator;

    public final ElapsedTime timer;

    public Bot(Telemetry telem, HardwareMap hMap, GamepadEx gamepad, GamepadEx gamepad2) {
        this.telem = telem;
        this.hMap = hMap;
        this.driver = gamepad;
        this.opertator = gamepad2;

        this.timer = new ElapsedTime();

        imu = ImuGlobal.getImu(hMap);
    }
    /**
     * Get the IMU object for the robot
     * @return the IMU object
     */
    public IMU getImu() { return imu; }

    /**
     * Get the MecanumDrivetrain subsystem of the robot
     * @return the mecanum subsystem of the robot
     */

    public double getTimestamp() {
        return timer.seconds();
    }
}