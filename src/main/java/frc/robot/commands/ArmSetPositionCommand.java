package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSetPositionCommand extends Command {
    
    private final ArmSubsystem m_ArmSubsystem;
    private final double m_targetDegrees;

    public ArmSetPositionCommand(ArmSubsystem ArmSubsystem, double targetDegrees) {
        m_ArmSubsystem = ArmSubsystem;
        m_targetDegrees = targetDegrees;
        addRequirements(m_ArmSubsystem);
    }

  
    @Override
    public void initialize() {
        m_ArmSubsystem.setArmPositionByDegrees(m_targetDegrees);
    }

    @Override
    public void execute() {
    }

    // Komut bittiğinde çalışır.
    @Override
    public void end(boolean interrupted) {
        // Hedefe ulaştıktan sonra kolun ağırlıkla aşağı düşmemesi için
        // motoru durdurmuyoruz (stop() YAPMIYORUZ). 
        // kG (Yerçekimi FeedForward'ı) sayesinde motor o açıda gücünü koruyarak kolu havada tutar.
    }

    // "Görevi tamamladım" deme şartımız.
    @Override
    public boolean isFinished() {
        double currentDegrees = m_ArmSubsystem.getArmDegrees();
        double error = Math.abs(m_targetDegrees - currentDegrees);
        
        // Hedef açıya 2 dereceden fazla yaklaştıysak komutu başarılı sayıp bitir.
        return error < 2.0; 
    }
}