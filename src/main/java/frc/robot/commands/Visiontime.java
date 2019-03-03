/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import java.lang.reflect.Executable;
import java.util.ArrayList;

import frc.robot.DriveControl.GripPipCont;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Visiontime extends Command {
  private static VisionThread visionThread;
  private final Object imgLock = new Object();
  private double distance = 0;
  private double turn = 0;
  public static final int IMG_WIDTH = 320;
  public static final int IMG_HEIGHT = 240;
  private static double centerX = 0.0;

  // private long startTime, timeSinceStart;

  public Visiontime() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.DRIVETRAIN);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    // startTime = System.currentTimeMillis();
    // timeSinceStart = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    for(int i = 0; i< 5; i++) {
      try {
          Thread.sleep(100);
      } catch(InterruptedException ie) {}
      visionThread = new VisionThread(Robot.camera, new GripPipCont(), pipeline -> {
        if (!pipeline.filterContoursOutput().isEmpty()) {
          Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
          synchronized (imgLock) {
            centerX = r.x + (r.width / 2);
          }
        }
      });

      
    }
    visionThread.start();

    double centerX;
    synchronized (imgLock) {
      centerX = this.centerX;
    }
    double turn = centerX - (IMG_WIDTH / 2);
    Robot.DRIVETRAIN.arcadeDriveVision(0.3, turn * 0.0008);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.DRIVETRAIN.tankDrive(0, 0);
    try {
      Thread.sleep(0);
    } catch (InterruptedException e) {
      this.interrupted();
      return;
    }
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
