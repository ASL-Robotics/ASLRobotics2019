/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorAutoCommand;

/**
 * Elevator Subsystem Code
 */
public class Elevator extends Subsystem {
  
  // 30 60 90- hatch 40 70 100- ball
  private final double[] HATCH_ENCODER_VALUES = {0};// {offset, gnd, hatch2, hatch3}; // CHANGE THESE
  private final double[] BALL_ENCODER_VALUES = {0};// {offset, ball1, ball2, ball3}; // CHANGE THESE
  public int stage = 1; // 0-offset, 1-stg 1, 2-stg 2, 3-stg3

  private final double MAX_HEIGHT = 300; // CHANGE THIS 

  // Hardware Inits
  private WPI_TalonSRX pulleyMotor;
  private DigitalInput ballSensor;

  public Elevator() {
    this.pulleyMotor = new WPI_TalonSRX(RobotMap.ELEVATOR_MOTOR);
    this.ballSensor = new DigitalInput(RobotMap.ELEVATOR_BALL_SWITCH);

    pulleyMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		pulleyMotor.setSensorPhase(true);
    pulleyMotor.setSelectedSensorPosition(0, 0, 10);
  }

  public double[] getArray(){
    return (RobotMap.hasBall) ? BALL_ENCODER_VALUES : HATCH_ENCODER_VALUES;
  }

  // public void setStage(int stage) { //0 - botton, 1- 1, 2 - 2, etc.
  //   //increments the distance by either 30 or 10, depending on even or odd.
  //   double encoderValue = ((RobotMap.hasBall) ? BALL_ENCODER_VALUES[stage] : HATCH_ENCODER_VALUES[stage]);
  //   if(encoderValue > MAX_HEIGHT) {
  //     encoderValue = MAX_HEIGHT;
  //   }

  //   setMotorPosition(encoderValue);
  // }

  public void updateBallSensor() {
    RobotMap.hasBall = ballSensor.get();
  }

  // public double getTargetDistance(int stage) { 
  //   return RobotMap.hasBall ? BALL_ENCODER_VALUES[stage] : HATCH_ENCODER_VALUES[stage];
  // }

  public double getEncoderDistance() { return pulleyMotor.getSelectedSensorPosition(); }

  public void setMotorPosition(double position) { pulleyMotor.set(ControlMode.Position, position); }

  public void setMotorSpeed(double speed) { 
    pulleyMotor.set(speed);
  }

  public void stopMotor() { pulleyMotor.set(ControlMode.Current, 0); }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ElevatorAutoCommand());
  }
}
