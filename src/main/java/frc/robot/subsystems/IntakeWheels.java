/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class IntakeWheels extends Subsystem {
  //private WPI_TalonSRX armMotor;
  private WPI_TalonSRX wheelMotor;
  // private WPI_VictorSPX wheelMotor;
  //private DigitalInput armDown;
  //private DigitalInput armUp;

  public IntakeWheels(){
    //armMotor = new WPI_TalonSRX(1);
    // wheelMotor = new WPI_VictorSPX(RobotMap.INTAKE_MOTOR); 
    wheelMotor = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR);
    //armDown = new DigitalInput(3);
    //armUp = new DigitalInput(4);
  }

  //public void setArmMotor(Double speed){
    //armMotor.set(speed);
  //}

  public void setWheelMotor(Double speed){
    wheelMotor.set(speed);
  }

  //public boolean up(){
    //boolean hit = armUp.get();
    //return hit;
  //}

  //public boolean down(){
    //boolean hit = armDown.get();
    //return hit;
  //}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
