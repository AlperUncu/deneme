// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private final TalonFX motor_shooter;
  private final TalonFX motor_shooter2;
  private final String canBusName = "Canivore";

  private final VelocityVoltage velocityRequest = new VelocityVoltage(0);

  
  public ShooterSubsystem() {
    
    motor_shooter = new TalonFX(5,canBusName);
    motor_shooter2 = new TalonFX(6,canBusName);
    motor_shooter.setControl(new Follower(motor_shooter2.getDeviceID(), MotorAlignmentValue.Opposed));

    var slot0 = new Slot0Configs();
    slot0.kV = 0.12; // Voltaj dengesi (Önemli!)
    slot0.kP = 0.11; // Hata düzeltme sertliği
    motor_shooter.getConfigurator().apply(slot0);

    CurrentLimitsConfigs currentLimits = new CurrentLimitsConfigs();
    
    // 1. Stator Akım Sınırı (Motorun kendi içindeki akım - Torku sınırlar)
    currentLimits.StatorCurrentLimit = 50; // 60 Amper sınırı
    currentLimits.StatorCurrentLimitEnable = true;
    
    // 2. Supply Akım Sınırı (Pilden çekilen akım - Sigortayı korur)
    currentLimits.SupplyCurrentLimit = 50.0; // Pilden max 40A çeksin
    currentLimits.SupplyCurrentLimitEnable = true;

    motor_shooter.getConfigurator().apply(currentLimits);
    motor_shooter2.getConfigurator().apply(currentLimits);
  }

  public void setRpm(double target_RPM) {
        double target_RPS = target_RPM / 60.0;
        motor_shooter2.setControl(velocityRequest.withVelocity(target_RPS));
    }

  public void setShooterVoltage(double voltage) {
        motor_shooter2.setVoltage(voltage);
    }

  public void stopShooter() {
        motor_shooter2.setVoltage(0);
    }

 
  

  @Override
  public void periodic() {
    double anlikRPS = motor_shooter.getVelocity().getValueAsDouble();
    double anlikRPM = anlikRPS * 60.0;
    SmartDashboard.putNumber("Shooter/Anlik RPM", anlikRPM);
    SmartDashboard.putNumber("Shooter/Stator Akimi", motor_shooter.getStatorCurrent().getValueAsDouble());
  }

  @Override
  public void simulationPeriodic() {
  }
}
