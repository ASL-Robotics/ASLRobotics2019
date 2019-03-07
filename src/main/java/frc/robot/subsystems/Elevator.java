/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorAutoCommand;
import frc.robot.commands.ElevatorManualCommand;

/**
 * Elevator Subsystem Code
 */
public class Elevator extends Subsystem {
  
  // 30 60 90- hatch 40 70 100- ball
  public final double[] HATCH_ENCODER_VALUES = {RobotMap.HATCH_STAGE_1, RobotMap.HATCH_STAGE_2, RobotMap.HATCH_STAGE_3};
  public final double[] BALL_ENCODER_VALUES = {RobotMap.BALL_STAGE_1, RobotMap.BALL_STAGE_2, RobotMap.BALL_STAGE_3};
  public int stage = 1, positionOffset = 0;

  // Hardware Inits
  private TalonSRX pulleyMotor;
  private DigitalInput ballSensor, isDownLimit;

  public Elevator() {
    this.pulleyMotor = new TalonSRX(RobotMap.ELEVATOR_MOTOR);
    this.ballSensor = new DigitalInput(RobotMap.HAS_BALL_SWITCH);
    this.isDownLimit = new DigitalInput(RobotMap.ELEVATOR_DOWN_SWITCH);

    pulleyMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
		pulleyMotor.setSensorPhase(true);
    pulleyMotor.setSelectedSensorPosition(0, 0, 10);

  }

  public double[] getTargetArray(){
    return (RobotMap.hasBall) ? BALL_ENCODER_VALUES : HATCH_ENCODER_VALUES;
  }

  public void checkForBall() {
    if(!ballSensor.get()) RobotMap.hasBall = true;
  }
  public boolean getBallSensor(){
    return ballSensor.get();
  }

  public void resetArrays(){
    HATCH_ENCODER_VALUES[0] = RobotMap.HATCH_STAGE_1;
    HATCH_ENCODER_VALUES[1] = RobotMap.HATCH_STAGE_2;
    HATCH_ENCODER_VALUES[2] = RobotMap.HATCH_STAGE_3;

    BALL_ENCODER_VALUES[0] = RobotMap.BALL_STAGE_1;
    BALL_ENCODER_VALUES[1] = RobotMap.BALL_STAGE_2;
    BALL_ENCODER_VALUES[2] = RobotMap.BALL_STAGE_3;
  }

  public double getEncoderDistance() { 
    return -pulleyMotor.getSelectedSensorPosition();
  }

  public void setMotorPosition(double position) { 
    double speed = position-getEncoderDistance();
    speed /= 20000;
    if(speed>=0) speed += .15;
    setMotorSpeed(speed);
  }

  public void setMotorSpeed(double speed) { pulleyMotor.set(ControlMode.PercentOutput, speed);}

  public void stopMotor() { pulleyMotor.set(ControlMode.PercentOutput, 0);}

  public boolean isDown(){ return !isDownLimit.get(); }

  public void resetEncoder(){ pulleyMotor.setSelectedSensorPosition(0); }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new ElevatorAutoCommand());
    setDefaultCommand(new ElevatorAutoCommand());
  }
}
