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


  // private final JoystickButton LINE_FOLLOW_LEFT = new JoystickButton(DRIVER, RobotMap.LINE_FOLLOW_LEFT_BUTTON_PORT);
  // private final JoystickButton LINE_FOLLOW_RIGHT = new JoystickButton(DRIVER, RobotMap.LINE_FOLLOW_RIGHT_BUTTON_PORT);
  private final JoystickButton VISION_BUTTON = new JoystickButton(DRIVER, RobotMap.VISION_BUTTON_PORT);


  private final JoystickButton CARGO_BUTTON = new JoystickButton(OPERATOR, RobotMap.CARGO_BUTTON_PORT);

  private final JoystickButton CARGO_DEPLOY_BUTTON = new JoystickButton(OPERATOR, RobotMap.DEPLOY_CARGO_BUTTON_PORT);

  private final JoystickButton HATCH_BUTTON = new JoystickButton(OPERATOR, RobotMap.HATCH_BUTTON_PORT);

  private final JoystickButton ARM_UP_BUTTON = new JoystickButton(OPERATOR, RobotMap.ARM_UP_BUTTON_PORT);

  private final JoystickButton ELEVATOR_1 = new JoystickButton(OPERATOR, RobotMap.ELEVATOR_1_BUTTON_PORT);
  private final JoystickButton ELEVATOR_2 = new JoystickButton(OPERATOR, RobotMap.ELEVATOR_2_BUTTON_PORT);
  private final JoystickButton ELEVATOR_3 = new JoystickButton(OPERATOR, RobotMap.ELEVATOR_3_BUTTON_PORT);
  private final JoystickButton ELEVATOR_MANUAL = new JoystickButton(OPERATOR, RobotMap.ELEVATOR_MANUAL_BUTTON_PORT);

  public OI() {
    CARGO_BUTTON.whileHeld(new StartWheels());
    CARGO_BUTTON.whileHeld(new AcquireCargo());
    CARGO_BUTTON.whenPressed(new ArmUp());
    CARGO_BUTTON.whenReleased(new ArmDown());

    ARM_UP_BUTTON.whenPressed(new ArmUp());

    CARGO_DEPLOY_BUTTON.whileHeld(new ReleaseCargo());

    HATCH_BUTTON.whenPressed(new HatchCommand());

    ELEVATOR_MANUAL.toggleWhenPressed(new ElevatorManualCommand());
    ELEVATOR_1.whenPressed(new ElevatorSetStage(1));
    ELEVATOR_2.whenPressed(new ElevatorSetStage(2));
    ELEVATOR_3.whenPressed(new ElevatorSetStage(3));

    VISION_BUTTON.whileHeld(new Visiontime());

    // ELEVATOR_1.whileHeld(new ElevatorAutoCommand());
    // ELEVATOR_2.whileHeld(new ElevatorAutoCommand());
    // ELEVATOR_3.whileHeld(new ElevatorAutoCommand());

    // LINE_FOLLOW_LEFT.whileHeld(new LineFollow(LineFollow.Direction.LEFT));
    // LINE_FOLLOW_RIGHT.whileHeld(new LineFollow(LineFollow.Direction.RIGHT));



  }


}
