package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;

@Autonomous (name="Intake Command", group="Command")
public class IntakeCommand extends Command {
    private final Intake intake;
}
