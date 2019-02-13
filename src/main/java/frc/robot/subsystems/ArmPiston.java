/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 * 
 * @param <DoubleSolenoid>
 */
public class ArmPiston extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid arm;

  public ArmPiston() {
    arm = new DoubleSolenoid(RobotMap.INTAKE_LIFT_PISTON_1, RobotMap.INTAKE_LIFT_PISTON_2);
  }

  public void setForward(){
    arm.set(DoubleSolenoid.Value.kForward);
  }
  public void setReverse(){
    arm.set(DoubleSolenoid.Value.kReverse);
  }
  public void setOff(){
    arm.set(DoubleSolenoid.Value.kOff);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
