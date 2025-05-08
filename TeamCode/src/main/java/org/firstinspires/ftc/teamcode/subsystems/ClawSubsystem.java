package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.Servo;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.Bot;

public class ClawSubsystem implements Subsystem {
    private Servo clawServo;
    private Bot bot;
    public ClawSubsystem(Bot bot) {
        this.bot = bot;

        clawServo = bot.hMap.get(Servo.class,"claw");
    }
    public void open () {
        clawServo.setPosition(1);
    }
    public void close () {
        clawServo.setPosition(0);
    }

    @Override
    public void periodic(){
        double currentPosition = clawServo.getPosition();

        if(bot.opertator.gamepad.y) {
            clawServo.setPosition(1);
        }
        else if (bot.opertator.gamepad.b){
            clawServo.setPosition(0);
        }
    }
}
