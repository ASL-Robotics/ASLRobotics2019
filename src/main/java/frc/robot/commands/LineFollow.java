/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

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
    right = .25;
    left = .5;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sensors = Robot.DRIVETRAIN.getPESensors();

    // if(sensors.equals("000") || sensors.equals("111")){
    if (sensors.equals("000") || sensors.equals("111")) {
      if (direction == Direction.RIGHT) {
        left = .25;
        right = -.25;
      } else {
        left = -.25;
        right = .25;
      }
    } else if (sensors.equals("010")) {
      left = .25;
      right = .25;
    } else if (sensors.equals("001") || sensors.equals("011")) {
      if ((int) (left * 10) == (int) (-left * 10)) {
        left = .25;
        right = .25;
      }
      left += .002;
      right -= .002;
      direction = Direction.RIGHT;
    } else if (sensors.equals("100") || sensors.equals("110")) {
      if ((int) (left * 10) == (int) (-left * 10)) {
        left = .25;
        right = .25;
      }
      left -= .002;
      right += .002;
      direction = Direction.LEFT;
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.DRIVETRAIN.arcadeDrive(0, 0);
  }
}
