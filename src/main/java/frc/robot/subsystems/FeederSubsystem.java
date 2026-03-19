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

public class FeederSubsystem extends SubsystemBase {
  private final TalonFX motor_feeder;
  private final String canBusName = "Canivore";

  
  public FeederSubsystem() {
    motor_feeder = new TalonFX(4,canBusName);
  }

  

  public void setFeederVoltage(double voltage) {
        motor_feeder.setVoltage(voltage);
    }

  public void stopFeeder() {
        motor_feeder.setVoltage(0);
    }

 
  

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
