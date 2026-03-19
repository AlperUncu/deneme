// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ArmCommand extends Command {

  private final ArmSubsystem m_ArmSubsystem;
  private final double m_voltage;

  public ArmCommand(ArmSubsystem ArmSubsystem, double voltage) {

    m_ArmSubsystem = ArmSubsystem;
    m_voltage = voltage;
    addRequirements(m_ArmSubsystem);

  }
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_ArmSubsystem.setArmVoltage(m_voltage);
  }

  @Override
  public void end(boolean interrupted) {
    m_ArmSubsystem.stopArm();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
