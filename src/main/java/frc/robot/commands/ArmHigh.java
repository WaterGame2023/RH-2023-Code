// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.subsystems.*;


public class ArmHigh extends CommandBase {

  private final ArmSubsystem m_armSubsystem;
  private final PIDController shoulderPIDController;
  private final PIDController elbowPIDController;
  private final PIDController wristPIDController;
  public final double shoulderHighAngle;
  public final double elbowHighAngle;
  public final double wristHighAngle;

    /** Creates a new ArmHigh. */
    public ArmHigh(ArmSubsystem m_armSubsystem) {
          this.m_armSubsystem = m_armSubsystem;
          addRequirements(m_armSubsystem);

          this.shoulderPIDController = new PIDController(0.1, 0, 0);
          shoulderPIDController.setTolerance(.1);
          shoulderPIDController.setSetpoint(shoulderHighAngle);

          this.elbowPIDController = new PIDController(0.1, 0, 0);
          elbowPIDController.setTolerance(.1);
          elbowPIDController.setSetpoint(elbowHighAngle);

          this.wristPIDController = new PIDController(0.1, 0, 0);
          wristPIDController.setTolerance(.1);
          wristPIDController.setSetpoint(wristHighAngle);
    }
    

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("ArmHigh command started");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double shoulderSpeed = shoulderPIDController.calculate(m_armSubsystem.getShoulderAngle());
    double elbowSpeed = elbowPIDController.calculate(m_armSubsystem.getElbowAngle());
    double wristSpeed = wristPIDController.calculate(m_armSubsystem.getWristAngle());

    m_armSubsystem.setSpeeds(shoulderSpeed, elbowSpeed, wristSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_armSubsystem.stopMotor();
    System.out.println("Arm Motors Stopped!!!!!");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
