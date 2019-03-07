/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /**
	 *
	 */
	
  public static final PowerDistributionPanel pdp = new PowerDistributionPanel();

  public static final Drivetrain DRIVETRAIN = new Drivetrain();
  public static final Hatch HATCH = new Hatch();
  public static final ArmPiston ARM_PISTON = new ArmPiston();
  public static final Elevator ELEVATOR = new Elevator();
  public static final Carriage CARRIAGE = new Carriage();
  public static final IntakeWheels INTAKE_WHEELS = new IntakeWheels();
  public static OI oi;
  Command m_autonomousCommand;
  // SendableChooser<Command> m_chooser = new SendableChooser<>();
  public static final SendableChooser<Boolean> hasBallOverrideChooser = new SendableChooser<>();
  public static final SendableChooser<Boolean> elevatorOverrideChooser = new SendableChooser<>();
  public static UsbCamera camera;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    // SmartDashboard.putData("Auto mode", m_chooser);
    camera = CameraServer.getInstance().startAutomaticCapture();

    SmartDashboard.putBoolean("l", DRIVETRAIN.getLeftSensor());
    SmartDashboard.putBoolean("c", DRIVETRAIN.getCenterSensor());
    SmartDashboard.putBoolean("r", DRIVETRAIN.getRightSensor());
    SmartDashboard.putBoolean("Elevator Down", ELEVATOR.isDown());
    SmartDashboard.putBoolean("Has Ball", RobotMap.hasBall);

    SmartDashboard.putNumber("Elevator Encoder", ELEVATOR.getEncoderDistance());
    SmartDashboard.putNumber("Left Speed", DRIVETRAIN.getLeftEncoderSpeed());
    SmartDashboard.putNumber("Right Speed", DRIVETRAIN.getRightEncoderSpeed());

    SmartDashboard.putString("Elevator Mode", "Auto");

    SmartDashboard.putNumberArray("Hatch Position Values", ELEVATOR.HATCH_ENCODER_VALUES);
    SmartDashboard.putNumberArray("Ball Position Values", ELEVATOR.BALL_ENCODER_VALUES);

    SmartDashboard.putData("Has Ball Override", hasBallOverrideChooser); // true if has ball
    hasBallOverrideChooser.addOption("True", true);
    hasBallOverrideChooser.setDefaultOption("False", false);

    SmartDashboard.putData("Elevator Manual Override", hasBallOverrideChooser); // true if manual
    hasBallOverrideChooser.addOption("Manual", true);
    hasBallOverrideChooser.setDefaultOption("Auto / Controller Override", false);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    ELEVATOR.checkForBall();
    if(hasBallOverrideChooser.getSelected()) RobotMap.hasBall = true;
    if(ELEVATOR.isDown()){
      ELEVATOR.resetEncoder();
      ELEVATOR.resetArrays();
    }

    SmartDashboard.getEntry("l").forceSetBoolean(DRIVETRAIN.getLeftSensor());
    SmartDashboard.getEntry("c").forceSetBoolean(DRIVETRAIN.getCenterSensor());
    SmartDashboard.getEntry("r").forceSetBoolean(DRIVETRAIN.getRightSensor());
    SmartDashboard.getEntry("Elevator Down").forceSetBoolean(ELEVATOR.isDown());
    SmartDashboard.getEntry("Has Ball").forceSetBoolean(RobotMap.hasBall);

    SmartDashboard.getEntry("Elevator Encoder").forceSetNumber(ELEVATOR.getEncoderDistance());
    SmartDashboard.getEntry("Left Speed").forceSetNumber(DRIVETRAIN.getLeftEncoderSpeed());
    SmartDashboard.getEntry("Right Speed").forceSetNumber(DRIVETRAIN.getRightEncoderSpeed());



    // System.out.println(pdp.getCurrent(10)); //ALSO USEFUL: motor speed, batery voltage, enc position, motor current, joystick signal
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    // System.out.println(pdp.getCurrent(10));
    // System.out.println(ELEVATOR.stage);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
