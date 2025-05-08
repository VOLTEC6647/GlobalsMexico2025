package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.SlidesSubsystem;

public class SlidesOrientedCommand extends CommandBase {
    private final SlidesSubsystem slides;
    public SlidesOrientedCommand(SlidesSubsystem slides){
        this.slides = slides;
        addRequirements(slides);
    }

    public void execute() {
        slides.periodic();
    }
}
