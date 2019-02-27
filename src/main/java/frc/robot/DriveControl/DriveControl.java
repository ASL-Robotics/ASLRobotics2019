package frc.robot.DriveControl;

public class DriveControl {
	
	static double oldWheel, quickStopAccumulator;
	
	// "Deadband" is the dead zone of the joysticks, for throttle and steering
    private static double throttleDeadband = 0.04;
    private static double wheelDeadband = 0.02;
	
	public static double[] calculateDrive(double throttle, double wheel, boolean isQuickTurn){
		
		
		double wheelNonLinearity;
		wheel = handleDeadband(wheel, wheelDeadband);
        throttle = handleDeadband(throttle, throttleDeadband);
        
		//throttle = throttle / 0.6;
		
		if(throttle < 0)
        	wheel = -wheel;
        
        //InputOutputComm.putDouble(InputOutputComm.LogTable.kDriveLog,"Teleop/Throttle", throttle);		
        //InputOutputComm.putDouble(InputOutputComm.LogTable.kDriveLog,"Teleop/Wheel", wheel);
		
        
        
		double negInertia = wheel - oldWheel;
        oldWheel = wheel;
        
        wheelNonLinearity = 0.5;
        // Apply a sin function that's scaled to make it feel better.
        wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		
        double sensitivity;
        double angularPower;
        double linearPower;
        
        double negInertiaAccumulator = 0.0;
        double negInertiaScalar;
        
        
     	if (wheel * negInertia > 0) {
            negInertiaScalar = 2.5;
        } else {
            if (Math.abs(wheel) > 0.65) {
                negInertiaScalar = 5.0;
            } else {
                negInertiaScalar = 3.0;
            }
        }
        
	  
        double negInertiaPower = negInertia * negInertiaScalar;
        negInertiaAccumulator += negInertiaPower;

        wheel = wheel + negInertiaAccumulator;
        if (negInertiaAccumulator > 1) {
            negInertiaAccumulator -= 1;
        } else if (negInertiaAccumulator < -1) {
            negInertiaAccumulator += 1;
        } else {
            negInertiaAccumulator = 0;
        }
        linearPower = throttle;
        
        // linearPower += .01;
        
        double rightPower,leftPower,overPower;
        
        sensitivity = .85;        
        
        // Calculates quick turn (top right swtich)
        if (isQuickTurn) {
            if (Math.abs(linearPower) < 0.2) {
                double alpha = 0.1;
                quickStopAccumulator = (1 - alpha) * quickStopAccumulator
                        + alpha * 5 * -1 * limit(true, wheel, 1);
            }
            overPower = 1.0;
            sensitivity = 1.0;
            angularPower = -wheel; // for old controller
        } else {
            overPower = 0.0;
            angularPower = -1*(Math.abs(throttle) * wheel * sensitivity
                    - quickStopAccumulator);
            if (quickStopAccumulator > 1) {
                quickStopAccumulator -= 1;
            } else if (quickStopAccumulator < -1) {
                quickStopAccumulator += 1;
            } else {
                quickStopAccumulator = 0.0;
            }
        }    
        
        rightPower = leftPower = linearPower;
        leftPower += angularPower;
        rightPower -= angularPower;
        
        if (leftPower > 1.0) {
            rightPower -= overPower * (leftPower - 1.0);
            leftPower = 1.0;
        } else if (rightPower > 1.0) {
            leftPower -= overPower * (rightPower - 1.0);
            rightPower = 1.0;
        } else if (leftPower < -1.0) {
            rightPower += overPower * (-1.0 - leftPower);
            leftPower = -1.0;
        } else if (rightPower < -1.0) {
            leftPower += overPower * (-1.0 - rightPower);
            rightPower = -1.0;
        }	       
        return new double[] {leftPower, rightPower};
		// sends final values to drive train
		//DriveAssembly.drive(leftPower, rightPower);
		
		//InputOutputComm.putDouble(InputOutputComm.LogTable.kDriveLog,"Teleop/leftPower", leftPower);		
		//InputOutputComm.putDouble(InputOutputComm.LogTable.kDriveLog,"Teleop/rightPower", rightPower);

	}
    
    // calculates the limit

    public static double limit(boolean limitHigh, double input, double limit){
		if(limitHigh)
			if(input > limit)
				return limit;
			else
				return input;
		else
			if(input < limit)
				return limit;
			else
				return input;
	}

	// calculates the deadband of the value
	
	public static double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	}
}
