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

public class LineFollow extends Command {

  private double right, left;
  private String sensors;
  private Direction direction;

  public static enum Direction { LEFT, RIGHT; }

  public LineFollow(Direction d) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.DRIVETRAIN);
    direction = d;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    right = RobotMap.LINE_FOLLOW_SPEED;
    left = RobotMap.LINE_FOLLOW_SPEED;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sensors = Robot.DRIVETRAIN.getPESensors();

    // if(sensors.equals("000") || sensors.equals("111")){
    if (sensors.equals("000") || sensors.equals("111")) {
      // if (direction == Direction.RIGHT) {
      //   left = RobotMap.LINE_FOLLOW_SPEED;
      //   right = -RobotMap.LINE_FOLLOW_SPEED;
      // } else {
      //   left = -RobotMap.LINE_FOLLOW_SPEED;
      //   right = RobotMap.LINE_FOLLOW_SPEED;
      // }
      spin();
    } else if (sensors.equals("010")) {
      left = RobotMap.LINE_FOLLOW_SPEED;
      right = RobotMap.LINE_FOLLOW_SPEED;
    } else if (sensors.equals("001") || sensors.equals("011")) {
      // if ((int) (left * 10) == (int) (-left * 10)) {
      //   left = RobotMap.LINE_FOLLOW_SPEED;
      //   right = RobotMap.LINE_FOLLOW_SPEED;
      // }
      // left += .01;
      // right -= .01;
      direction = Direction.RIGHT;
      spin();
    } else if (sensors.equals("100") || sensors.equals("110")) {
      // if ((int) (left * 10) == (int) (-left * 10)) {
      //   left = RobotMap.LINE_FOLLOW_SPEED;
      //   right = RobotMap.LINE_FOLLOW_SPEED;
      // }
      // left -= .01;
      // right += .01;
      direction = Direction.LEFT;
      spin();
    }

    Robot.DRIVETRAIN.tankDrive(left, right);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.DRIVETRAIN.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  private void spin(){
    if (direction == Direction.RIGHT) {
      left = RobotMap.LINE_FOLLOW_SPEED;
      right = -RobotMap.LINE_FOLLOW_SPEED;
    } else {
      left = -RobotMap.LINE_FOLLOW_SPEED;
      right = RobotMap.LINE_FOLLOW_SPEED;
    }
  }
}
