// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.HooperSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class HooperCommand extends Command {

  private final HooperSubsystem m_HooperSubsystem;
  private final double m_voltage;

  public HooperCommand(HooperSubsystem HooperSubsystem, double voltage) {

    m_HooperSubsystem = HooperSubsystem;
    m_voltage = voltage;
    addRequirements(m_HooperSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_HooperSubsystem.setHooperVoltage(m_voltage);
  }

  @Override
  public void end(boolean interrupted) {
    m_HooperSubsystem.stopHooper();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
