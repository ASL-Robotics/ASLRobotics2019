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

public class DrivetrainCommand extends Command {

  // private double loffset, roffset;
  // private int lspeed, rspeed, diff;
  private boolean quickTurn;

  public DrivetrainCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.DRIVETRAIN); 
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    quickTurn = false;
    Robot.DRIVETRAIN.tankDrive(0, 0);
    // loffset = 0;
    // roffset = 0;
    // lspeed = 0;
    // rspeed = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if(Robot.oi.DRIVER.getRawAxis(RobotMap.DRIVE_TURN_AXIS)>-.002 && 
    //       Robot.oi.DRIVER.getRawAxis(RobotMap.DRIVE_TURN_AXIS)<.002 && 
    //       Math.abs(Robot.oi.DRIVER.getRawAxis(RobotMap.DRIVE_FORWARD_AXIS))>.03){
    //   lspeed = Robot.DRIVETRAIN.getLeftEncoderSpeed();
    //   rspeed = -Robot.DRIVETRAIN.getRightEncoderSpeed();
    //   if(lspeed > rspeed){
    //     diff = lspeed-rspeed;
    //     diff /= lspeed;
    //     loffset -= diff/20;
    //     roffset += diff/20;
    //   } else {
    //     diff = rspeed-lspeed;
    //     diff /= rspeed;
    //     loffset += diff/20;
    //     roffset -= diff/20;
    //   }
    // } else {
    //   loffset = 0;
    //   roffset = 0;
    // }

    // notForward = Math.abs(Robot.oi.DRIVER.getRawAxis(RobotMap.DRIVE_FORWARD_AXIS)) < .01;
    
    // double[] speeds = DriveControl.calculateDrive(loffset-Robot.oi.DRIVER.getRawAxis(RobotMap.DRIVE_FORWARD_AXIS), roffset+Robot.oi.DRIVER.getRawAxis(RobotMap.DRIVE_TURN_AXIS), quickTurn);
    // Robot.DRIVETRAIN.tankDrive(speeds[0], speeds[1]);

    quickTurn = Robot.oi.DRIVER.getRawButton(RobotMap.QUICK_TURN_BUTTON_PORT_1)
                || Robot.oi.DRIVER.getRawButton(RobotMap.QUICK_TURN_BUTTON_PORT_2);

    Robot.DRIVETRAIN.arcadeDrive(Robot.oi.DRIVER.getRawAxis(RobotMap.DRIVE_FORWARD_AXIS),
                                  Robot.oi.DRIVER.getRawAxis(RobotMap.DRIVE_TURN_AXIS), quickTurn);
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
}
