/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ElevatorAutoCommand extends Command {

  private double[] targets;
  private double tempSpeed;

  public ElevatorAutoCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.ELEVATOR);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.elevatorOverrideChooser.getSelected()){
        if(Robot.ELEVATOR.isDown() && -Robot.oi.OPERATOR.getRawAxis(RobotMap.OPERATOR_ELEVATOR_AXIS) <= 0) Robot.ELEVATOR.setMotorSpeed(0);
        else Robot.ELEVATOR.setMotorSpeed(-Robot.oi.OPERATOR.getRawAxis(RobotMap.OPERATOR_ELEVATOR_AXIS));

    } else {
        targets = Robot.ELEVATOR.getTargetArray();
        Robot.ELEVATOR.positionOffset += (int) (100*Robot.oi.OPERATOR.getRawAxis(RobotMap.OPERATOR_ELEVATOR_AXIS));
        if(!RobotMap.hasBall && !Robot.ELEVATOR.isDown() && Robot.ELEVATOR.stage==1 
              && Robot.oi.OPERATOR.getRawAxis(RobotMap.OPERATOR_ELEVATOR_AXIS) < .005){
          tempSpeed = -Robot.ELEVATOR.getEncoderDistance()/20000;
          if(tempSpeed < -.5) tempSpeed = -.5;
          if(tempSpeed > -.3) tempSpeed = -.3;
          Robot.ELEVATOR.setMotorSpeed(tempSpeed);
          System.out.println(tempSpeed);
        }else if(!RobotMap.hasBall && Robot.ELEVATOR.isDown() && Robot.ELEVATOR.stage==1) {
          Robot.ELEVATOR.stopMotor();
        }
        else{
          Robot.ELEVATOR.setMotorPosition(targets[Robot.ELEVATOR.stage-1]);
        }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.ELEVATOR.stopMotor();
    Robot.ELEVATOR.positionOffset = 0;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
