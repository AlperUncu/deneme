// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeCommand extends Command {

  private final IntakeSubsystem m_IntakeSubsystem;
  private final double m_voltage;

  public IntakeCommand(IntakeSubsystem IntakeSubsystem, double voltage) {

    m_IntakeSubsystem = IntakeSubsystem;
    m_voltage = voltage;
    addRequirements(m_IntakeSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_IntakeSubsystem.setIntakeVoltage(m_voltage);
  }

  @Override
  public void end(boolean interrupted) {
    m_IntakeSubsystem.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
