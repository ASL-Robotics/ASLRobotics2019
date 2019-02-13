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

  // OI Ports
    // joysticks
  public static final int DRIVER_CONTROL_PORT = 1;
  public static final int OPERATOR_CONTROL_PORT = 0;

    // buttons
  public static final int CARGO_BUTTON_PORT = 2;
  public static final int CARGO_ARM_DOWN_BUTTON_PORT = 10;

  public static final int DEPLOY_CARGO_BUTTON_PORT = 6;

  public static final int HATCH_BUTTON_PORT = 5;

  public static final int ELEVATOR_1_BUTTON_PORT = 1;
  public static final int ELEVATOR_2_BUTTON_PORT = 3;
  public static final int ELEVATOR_3_BUTTON_PORT = 4;
  public static final int ELEVATOR_INTERRUPT_BUTTON_PORT = 9;

    // axes
  public static final int DRIVE_FORWARD_AXIS = 1;
  public static final int DRIVE_TURN_AXIS = 4;

  public static final int OPERATOR_ELEVATOR_AXIS = 1;


  // MOTORS
  public static final int FRONT_LEFT_DRIVE_MOTOR = 2;
  public static final int BACK_LEFT_DRIVE_MOTOR = 1;
  public static final int FRONT_RIGHT_DRIVE_MOTOR = 4;
  public static final int BACK_RIGHT_DRIVE_MOTOR = 3;

  public static final int ELEVATOR_MOTOR = 5;

  public static final int INTAKE_MOTOR = 6;
      // public static final int INTAKE_LIFT_MOTOR = 7;

  public static final int LEFT_CARRIAGE_MOTOR = 7;
  public static final int RIGHT_CARRIAGE_MOTOR = 8;


  // PISTONS
  public static final int HATCH_PISTON_1 = 0;
  public static final int HATCH_PISTON_2 = 1;

  public static final int INTAKE_LIFT_PISTON_1 = 2;
  public static final int INTAKE_LIFT_PISTON_2 = 3;


  // DIGITAL INPUTS
  public static final int PHOTO_SWITCH_LEFT = 0;
  public static final int PHOTO_SWITCH_CENTER = 1;
  public static final int PHOTO_SWITCH_RIGHT = 2;

  public static final int INTAKE_UP_LIMIT = 3;
  public static final int INTAKE_DOWN_LIMIT = 4;
  public static final int ELEVATOR_BALL_SWITCH = 5;


  // SPEEDS
    // cargo
  public static final double CARRIAGE_SPEED = .5;
  public static final double INTAKE_LIFT_SPEED = .5;

    // elevator
  public static final double ELEVATOR_LIFT_SPEED = .4;
  public static final double ELEVATOR_DOWN_SPEED = -.4;


  // OTHER
  public static final int PISTON_DELAY_TIME = 1000;
  public static boolean hasBall = false;
  
}
