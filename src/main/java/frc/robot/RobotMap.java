/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // MOTORS
  public static final int FRONT_LEFT_DRIVE_MOTOR = 0;
  public static final int BACK_LEFT_DRIVE_MOTOR = 1;
  public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
  public static final int BACK_RIGHT_DRIVE_MOTOR = 3;

  public static final int ELEVATOR_MOTOR_1 = 4;
  public static final int ELEVATOR_MOTOR_2 = 5;

  public static final int INTAKE_MOTOR = 6;
  public static final int INTAKE_LIFT_MOTOR = 7;

  public static final int LEFT_CARRIAGE_MOTOR = 8;
  public static final int RIGHT_CARRIAGE_MOTOR = 9;


  // PISTONS
  public static final int HATCH_PISTON_1 = 0;
  public static final int HATCH_PISTON_2 = 1;
  
}
