package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.Bot;

@com.acmerobotics.dashboard.config.Config
public class IntakeSubsystem implements Subsystem {

    private Bot bot;
    public final DcMotorEx roller;
    public double speed;

    public IntakeSubsystem(Bot bot) {
        this.bot = bot;

        roller = bot.hMap.get(DcMotorEx.class, "roller");
    }

    @Override
    public void periodic() {

        if (bot.opertator.gamepad.right_trigger > 0){
            roller.setPower(speed);
        } if (bot.opertator.gamepad.left_trigger > 0){
            roller.setPower(-speed);
        } else {
            roller.setPower(0);
        }
    }

    public void in(){
        speed = 1;
    }

    public void out(){
        speed = -1;
    }

    public void stop(){
        speed = 0;
    }

}