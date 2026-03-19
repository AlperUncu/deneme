// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.FeederSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class FeederCommand extends Command {

  private final FeederSubsystem m_FeederSubsystem;
  private final double m_voltage;

  public FeederCommand(FeederSubsystem FeederSubsystem, double voltage) {

    m_FeederSubsystem = FeederSubsystem;
    m_voltage = voltage;
    addRequirements(m_FeederSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_FeederSubsystem.setFeederVoltage(m_voltage);
  }

  @Override
  public void end(boolean interrupted) {
    m_FeederSubsystem.stopFeeder();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
