// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HooperSubsystem extends SubsystemBase {
  private final TalonFX motor_hooper;
  private final String canBusName = "Canivore";

  
  public HooperSubsystem() {
    motor_hooper = new TalonFX(3,canBusName);
  }

  

  public void setHooperVoltage(double voltage) {
        motor_hooper.setVoltage(voltage);
    }

  public void stopHooper() {
        motor_hooper.setVoltage(0);
    }

 
  

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
