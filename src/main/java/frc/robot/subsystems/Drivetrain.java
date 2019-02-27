/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.DriveControl.DriveControl;
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
  // private DifferentialDrive driveTrain;

  private DigitalInput l, c, r;


  public Drivetrain(){
    frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_DRIVE_MOTOR);
    backLeft = new WPI_TalonSRX(RobotMap.BACK_LEFT_DRIVE_MOTOR);
    frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_DRIVE_MOTOR);
    backRight = new WPI_TalonSRX(RobotMap.BACK_RIGHT_DRIVE_MOTOR);

    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);
    // driveTrain = new DifferentialDrive(left, right);

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
  // public void arcadeDrive(double x, double z){
  //   // driveTrain.arcadeDrive(x*.85, z*.6);
  //   x *= .85;
  //   z *= .6;
  //   tankDrive(x+z, x-z);
  // }

  // // -1 to 1 are passed
  // public void freezyDrive(double leftSpeed, double rightSpeed) {
  //   left.set(-leftSpeed);
  //   right.set(rightSpeed);
  // }

  public String getPESensors(){ // REVERSE THIS AT COMPETITION
    String ret = "";

    ret += l.get()? "1" : "0";
    ret += c.get()? "1" : "0";
    ret += r.get()? "1" : "0";
    // ret += l.get()? "0" : "1";
    // ret += c.get()? "0" : "1";
    // ret += r.get()? "0" : "1";

    return ret;
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
