/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ArmDown;
import frc.robot.commands.BeginIntake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public final Joystick stick = new Joystick(0);
  public Joystick operator = new Joystick(1);

  public JoystickButton deployArmButton = new JoystickButton(operator, 1);
  public JoystickButton retractArmButton = new JoystickButton(operator, 2);
  public OI() {
    deployArmButton.whileHeld(new BeginIntake());
    retractArmButton.whenPressed(new ArmDown());
  }

}
