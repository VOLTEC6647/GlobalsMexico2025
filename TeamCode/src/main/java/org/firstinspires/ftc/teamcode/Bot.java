package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import lombok.Getter;

public class Bot extends Robot {
    @Getter
    private final IMU imu;
    @Getter
    public final Telemetry telem;
    @Getter
    public final HardwareMap hMap;
    @Getter
    public final GamepadEx driver;
    @Getter
    public final GamepadEx opertator;

    public Bot(MultipleTelemetry telem, HardwareMap hMap, GamepadEx gamepad, GamepadEx gamepad2) {
        this.telem = telem;
        this.hMap = hMap;
        this.driver = gamepad;
        this.opertator = gamepad2;

        // TODO: Adjust IMU parameters to match hub orientation
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
}