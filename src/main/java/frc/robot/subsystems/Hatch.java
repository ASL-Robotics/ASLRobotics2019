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
 */
public class Hatch extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid hatchPiston;
public Hatch(){
  hatchPiston = new DoubleSolenoid(RobotMap.HATCH_PISTON_1,RobotMap.HATCH_PISTON_2);
}
public void setForward(){
  hatchPiston.set(DoubleSolenoid.Value.kForward);
}
public void setReverse(){
  hatchPiston.set(DoubleSolenoid.Value.kForward);
}
public void setOff(){
  hatchPiston.set(DoubleSolenoid.Value.kForward);
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
