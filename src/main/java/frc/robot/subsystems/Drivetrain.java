/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DrivetrainCommand;
/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX frontLeft;
  private WPI_TalonSRX backLeft;
  private WPI_TalonSRX frontRight;
  private WPI_TalonSRX backRight;

  private SpeedControllerGroup left;
  private SpeedControllerGroup right;

  private DigitalInput l, c, r;


  public Drivetrain(){
    frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_DRIVE_MOTOR);
    backLeft = new WPI_TalonSRX(RobotMap.BACK_LEFT_DRIVE_MOTOR);
    frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_DRIVE_MOTOR);
    backRight = new WPI_TalonSRX(RobotMap.BACK_RIGHT_DRIVE_MOTOR);

    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);

    l = new DigitalInput(RobotMap.PHOTO_SWITCH_LEFT);
    c = new DigitalInput(RobotMap.PHOTO_SWITCH_CENTER);
    r = new DigitalInput(RobotMap.PHOTO_SWITCH_RIGHT);

    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    frontLeft.setSensorPhase(true);

    frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    frontRight.setSensorPhase(true);


  }
  public void tankDrive(double leftSpeed, double rightSpeed){
    left.set(-leftSpeed);
    right.set(rightSpeed);
  }
  public void arcadeDrive(double x, double z, boolean quickTurn){
    // x *= x*x;
    // z *= Math.abs(z);
    // if(Math.abs(x)<=.01 && !quickTurn) z *= .5;
    // else if(!quickTurn) z *= x;

    x *= Math.abs(x);
    z *= Math.abs(z);

    tankDrive(x-z, x+z);
  }


  public String getPESensors(){ 
    String ret = "";

    ret += l.get()? "1" : "0";
    ret += c.get()? "1" : "0";
    ret += r.get()? "1" : "0";

    return ret;
  }

  public void arcadeDriveVision(double x, double z){
    tankDrive(x+z, x-z);
  }

  public int getLeftEncoderSpeed() { return frontLeft.getSelectedSensorVelocity(); }
  public int getRightEncoderSpeed() { return frontRight.getSelectedSensorVelocity(); }

  public boolean getLeftSensor(){ return l.get(); }
  public boolean getCenterSensor(){ return c.get(); }
  public boolean getRightSensor(){ return r.get(); }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DrivetrainCommand());
  }
}
