package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.Robot;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import lombok.Getter;

public class ImuGlobal extends Robot {

    private static IMU imu;
    public static IMU getImu(HardwareMap hMap) {
        if (imu == null) {
            imu = hMap.get(IMU.class, "imu");
            imu.initialize(
                    new IMU.Parameters(
                            new RevHubOrientationOnRobot(
                                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                                    RevHubOrientationOnRobot.UsbFacingDirection.RIGHT
                            )
                    )
            );
            imu.resetYaw();
        }
        return imu;
    }

}