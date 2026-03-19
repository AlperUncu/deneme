// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ShooterCommand extends Command {

  private final ShooterSubsystem m_ShooterSubsystem;
  private final double m_voltage;

  public ShooterCommand(ShooterSubsystem ShooterSubsystem, double voltage) {

    m_ShooterSubsystem = ShooterSubsystem;
    m_voltage = voltage;
    addRequirements(m_ShooterSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_ShooterSubsystem.setShooterVoltage(m_voltage);
  }

  @Override
  public void end(boolean interrupted) {
    m_ShooterSubsystem.stopShooter();
  }
  

  @Override
  public boolean isFinished() {
    return false;
  }
}
