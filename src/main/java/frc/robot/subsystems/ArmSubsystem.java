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

public class ArmSubsystem extends SubsystemBase {
  private final TalonFX motor_arm;
  private final String canBusName = "Canivore";

  private final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
  private final double gear_arm = 50.0;


  public ArmSubsystem() {
    motor_arm = new TalonFX(1,canBusName);
    double startingMotorRotations = (90.0 / 360.0) * gear_arm;
    motor_arm.setPosition(startingMotorRotations);
    TalonFXConfiguration configs = new TalonFXConfiguration();
    configs.Slot0.kP = 2.0;  
    configs.Slot0.kI = 0.0;  
    configs.Slot0.kD = 0.1; 
    configs.Slot0.kS = 0.25; 
    configs.Slot0.kV = 0.12; 
    configs.Slot0.kG = 0.4;  
    configs.Slot0.GravityType = GravityTypeValue.Arm_Cosine;

    configs.Slot1.kP = 0.5;  
    configs.Slot1.kI = 0.0;
    configs.Slot1.kD = 0.2;  // Aşağı inerken frenleme gücünü (kD) biraz daha artırabiliriz
    configs.Slot1.kS = 0.1;  // Aşağı inerken sürtünmeyi yenmek daha kolaydır
    configs.Slot1.kV = 0.12; 
    configs.Slot1.kG = 0.4; 
    configs.Slot1.GravityType = GravityTypeValue.Arm_Cosine;

    configs.MotionMagic.MotionMagicCruiseVelocity = 5.0; // Saniyede kaç tur atacak (Maks Hız)
    configs.MotionMagic.MotionMagicAcceleration = 10.0;  // O hıza ne kadar sürede çıkacak (İvme)
    configs.MotionMagic.MotionMagicJerk = 50.0;          // İvmeyi nasıl yumuşatacak (S-Curve Sarsıntı Önleyici)
    motor_arm.getConfigurator().apply(configs);
  }

  public void setArmPositionByDegrees(double targetDegrees) {
    double targetRotations = (targetDegrees / 360.0) * gear_arm;
    double currentRotations = motor_arm.getPosition().getValueAsDouble();
        if (targetRotations > currentRotations) {
            motor_arm.setControl(m_request.withPosition(targetRotations).withSlot(0));
        } 
        else {
            motor_arm.setControl(m_request.withPosition(targetRotations).withSlot(1));
        }
    }

  public double getArmDegrees() {
        double currentMotorRotations = motor_arm.getPosition().getValueAsDouble();
        return (currentMotorRotations / gear_arm) * 360.0;
    }

  public void setArmVoltage(double voltage) {
        motor_arm.setVoltage(voltage);
    }

  public void stopArm() {
        motor_arm.setVoltage(0);
    }

 
  

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
