/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public final Joystick DRIVER = new Joystick(RobotMap.DRIVER_CONTROL_PORT);
  public final Joystick OPERATOR = new Joystick(RobotMap.OPERATOR_CONTROL_PORT);


  private final JoystickButton LINE_FOLLOW_LEFT = new JoystickButton(DRIVER, RobotMap.LINE_FOLLOW_LEFT_BUTTON_PORT);
  private final JoystickButton LINE_FOLLOW_RIGHT = new JoystickButton(DRIVER, RobotMap.LINE_FOLLOW_RIGHT_BUTTON_PORT);

  private final JoystickButton CARGO_BUTTON = new JoystickButton(OPERATOR, RobotMap.CARGO_BUTTON_PORT);

  private final JoystickButton CARGO_ARM_DOWN_BUTTON = new JoystickButton(OPERATOR, RobotMap.CARGO_ARM_DOWN_BUTTON_PORT);

  private final JoystickButton CARGO_DEPLOY_BUTTON = new JoystickButton(OPERATOR, RobotMap.DEPLOY_CARGO_BUTTON_PORT);

  private final JoystickButton HATCH_BUTTON = new JoystickButton(OPERATOR, RobotMap.HATCH_BUTTON_PORT);

  // private final JoystickButton ELEVATOR_1 = new JoystickButton(OPERATOR, RobotMap.ELEVATOR_1_BUTTON_PORT);
  // private final JoystickButton ELEVATOR_2 = new JoystickButton(OPERATOR, RobotMap.ELEVATOR_2_BUTTON_PORT);
  // private final JoystickButton ELEVATOR_3 = new JoystickButton(OPERATOR, RobotMap.ELEVATOR_3_BUTTON_PORT);
  // private final JoystickButton ELEVATOR_INTERRUPT = new JoystickButton(OPERATOR, RobotMap.ELEVATOR_INTERRUPT_BUTTON_PORT);

  public OI() {
    CARGO_BUTTON.whileHeld(new StartWheels());
    CARGO_BUTTON.whileHeld(new AcquireCargo());
    CARGO_BUTTON.whenPressed(new ArmUp());
    CARGO_ARM_DOWN_BUTTON.whenPressed(new ArmDown());

    CARGO_DEPLOY_BUTTON.whileHeld(new ReleaseCargo());

    HATCH_BUTTON.whenPressed(new HatchCommand());

    LINE_FOLLOW_LEFT.whenPressed(new LineFollow(LineFollow.Direction.LEFT));
    LINE_FOLLOW_RIGHT.whenPressed(new LineFollow(LineFollow.Direction.RIGHT));

    //ELEVATOR_1.whileHeld(new ElevatorStage1());
    //ELEVATOR_2.whileHeld(new ElevatorStage2());
    //ELEVATOR_3.whileHeld(new ElevatorStage3());
    //ELEVATOR_INTERRUPT.whenPressed(new ElevatorInterrupt());

  }


}
