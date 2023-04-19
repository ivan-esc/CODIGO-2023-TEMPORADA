package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;


// --------------------------------------------------
// --------------------------------------------------

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Auto 1";
  private static final String kCustomAuto2 = "Auto 2";
  private static final String kCustomAuto3 = "Auto 3";
  private static final String kCustomAuto4 = "Auto 4";
  private static final String kCustomAuto5 = "Auto 5";
  
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public RelativeEncoder m_Encoder;
  
  public final WPI_TalonFX rightmotor1 = new WPI_TalonFX(4);
  public final WPI_TalonFX rightmotor2 = new WPI_TalonFX(2);
  public final WPI_TalonFX leftmotor1 = new WPI_TalonFX(3);
  public final WPI_TalonFX leftmotor2 = new WPI_TalonFX(1); 
   // //SpeedControllerGroups
   public final MotorControllerGroup rightSpeedGroup = new MotorControllerGroup(rightmotor1, rightmotor2);
   public final MotorControllerGroup leftSpeedGroup = new MotorControllerGroup(leftmotor1, leftmotor2);
  //
  DifferentialDrive drivetrain = new DifferentialDrive(rightSpeedGroup, leftSpeedGroup);

  public final CANSparkMax brazo = new CANSparkMax(28,MotorType.kBrushless);
  public final CANSparkMax brazo2 = new CANSparkMax(32,MotorType.kBrushless);
  public final CANSparkMax garradechavez = new CANSparkMax(23,MotorType.kBrushless);
 
  // Controles
  XboxController stick = new XboxController(1);
  XboxController extra = new XboxController(2);

  AHRS gyro = new AHRS(I2C.Port.kOnboard);
  int autoS = 1;

  //Subsystems Autonomous
  // private final auto1 defaultcode = new auto1();
  // private final auto2 custom2 = new auto2();
  // private final auto3 custom3 = new auto3();

// --------------------------------------------------
// --------------------------------------------------

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Center (1 cube / mobility / balance)", kDefaultAuto);
    m_chooser.addOption("Left (2 cube / mobility)", kCustomAuto2);
    m_chooser.addOption("Left (1 cube / mobility / balance)", kCustomAuto3);
     m_chooser.addOption("Right (2 cube / mobility)", kCustomAuto4);
    m_chooser.addOption("Right (1 cube / mobility / balance)", kCustomAuto5);
  

    rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
    rightmotor2.getSensorCollection().setIntegratedSensorPosition(0,0);
    leftmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
    m_Encoder = garradechavez.getEncoder();

    SmartDashboard.putData("Auto choices", m_chooser);
    rightSpeedGroup.setInverted(true);
    //CameraServer.startAutomaticCapture(1);  
    UsbCamera camera = CameraServer.startAutomaticCapture();
    camera.setResolution(18, 14);
    
    rightmotor1.setNeutralMode(NeutralMode.Brake);
    rightmotor2.setNeutralMode(NeutralMode.Brake);   
    leftmotor1.setNeutralMode(NeutralMode.Brake);
    leftmotor2.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void robotPeriodic() {}


  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
    rightmotor2.getSensorCollection().setIntegratedSensorPosition(0,0);
    leftmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
    m_Encoder.setPosition(0);
    gyro.zeroYaw();
    autoS = 1;
     
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("CM", (((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048));
    SmartDashboard.putNumber("Yaw", gyro.getYaw());
    SmartDashboard.putNumber("Pitch", gyro.getPitch());
    SmartDashboard.putNumber("Roll", gyro.getRoll());
    SmartDashboard.putNumber("Brazo", m_Encoder.getPosition());
    switch (m_autoSelected) {
      case kCustomAuto2:
      autocode2();
        break;
      case kCustomAuto3:
      autocode3();
        break;
        case kCustomAuto4:
      autocode4();
        break;
        case kCustomAuto5:
      autocode5();
        break;
      case kDefaultAuto:
      default:
      autocode1();
        break;
    }
  }
 
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {    
    gyro.zeroYaw();

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    drivetrain.arcadeDrive(stick.getLeftY()*(-0.75), stick.getRightX()*(0.8));
   
    brazoChavez();
    brazo.set(extra.getLeftY()*-1);
    brazo2.set(extra.getLeftY());  
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    rightmotor1.setNeutralMode(NeutralMode.Brake);
    rightmotor2.setNeutralMode(NeutralMode.Brake);   
    leftmotor1.setNeutralMode(NeutralMode.Brake);
    leftmotor2.setNeutralMode(NeutralMode.Brake);
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
  
  public void brazoChavez(){
    
  double axisBrazo = extra.getRightY();
  if(axisBrazo >= 0.2){
    garradechavez.set(0.2);
  }
  else if(axisBrazo <= -0.2){
    garradechavez.set(-0.2);
  }
  else if(axisBrazo >= -0.2 && axisBrazo<=0.2){
    garradechavez.set(0);
  }
  }

  // "Center (1 cube / mobility / balance)"
public void autocode1(){
    if(autoS== 1){
      if(m_Encoder.getPosition() <= 24){
        garradechavez.set(0.2);
      }
      if(m_Encoder .getPosition() > 24){
        garradechavez.set(0);
        autoS++;
      }
    }

    if(autoS == 2){
          if((((rightmotor2.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) > -70){
            rightSpeedGroup.set(0.2);
            leftSpeedGroup.set(0.2);
          }
          
          if((((rightmotor2.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) <= -70){
            rightSpeedGroup.set(0);
            leftSpeedGroup.set(0);
            autoS++;
          }
        }
      if(autoS == 3){
          try{
            brazo.set(0.3);
            brazo2.set(-0.3);
            Thread.sleep(500);
            brazo.set(0);
            brazo2.set(0);
            rightmotor2.getSensorCollection().setIntegratedSensorPosition(0,0);
            autoS++;
          }
          catch(InterruptedException e){
          }
        }
        if(autoS == 4){
          if((((rightmotor2.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) < 480){
            rightSpeedGroup.set(-0.25);
            leftSpeedGroup.set(-0.25);
          }
          
          if((((rightmotor2.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) >= 480){
            rightSpeedGroup.set(0);
            leftSpeedGroup.set(0);
          }
        }
        // if(autoS == 5){
        //   if((((rightmotor2.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) < 400){
        //     rightSpeedGroup.set(-0.15);
        //     leftSpeedGroup.set(-0.15);
        //   }
          
        //   if((((rightmotor2.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) >= 400){
        //     rightSpeedGroup.set(0);
        //     leftSpeedGroup.set(0);
        //     autoS++;
        //   }
        // }
        // if(autoS == 6){
        //   if((((rightmotor2.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) > 200){
        //     rightSpeedGroup.set(0.2);
        //     leftSpeedGroup.set(0.2);
        //   }
          
        //   if((((rightmotor2.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) <= 200){
        //     rightSpeedGroup.set(0);
        //     leftSpeedGroup.set(0);
        //     autoS++;
        //   }
        // }
        // if(autoS == 7){
        //   if(gyro.getPitch() < 2 && gyro.getPitch() > -2){
        //     rightSpeedGroup.set(0);
        //     leftSpeedGroup.set(0);
        //   }
        //   if(gyro.getPitch() >= 2){
        //     rightSpeedGroup.set(0.1);
        //     leftSpeedGroup.set(0.1);
        //   }
        //   if(gyro.getPitch() <= -2){
        //     rightSpeedGroup.set(-0.1);
        //     leftSpeedGroup.set(-0.1);
        //   }
        // }
}

// "Left (2 cube / mobility)"
public void autocode2(){
  if(autoS== 1){
    if(m_Encoder.getPosition() <= 24){
      garradechavez.set(0.2);
    }
    if(m_Encoder.getPosition() > 24){
      garradechavez.set(0);
      autoS++;
    }
  }

  if(autoS == 2){
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) > -70){
          rightSpeedGroup.set(0.2);
          leftSpeedGroup.set(0.2);
        }
        
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) <= -70){
          rightSpeedGroup.set(0);
          leftSpeedGroup.set(0);
          rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
          autoS++;
        }
      }
    if(autoS == 3){
        try{
          brazo.set(0.5);
          brazo2.set(-0.5);
          Thread.sleep(500);
          brazo.set(0);
          brazo2.set(0);
          autoS++;
        }
        catch(InterruptedException e){
        }
      }
      if(autoS == 4){
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) < 400){
          rightSpeedGroup.set(-0.3);
          leftSpeedGroup.set(-0.3);
        }
        
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) >= 400){
          rightSpeedGroup.set(0);
          leftSpeedGroup.set(0);
          autoS++;
          gyro.zeroYaw();
        }
        if(m_Encoder.getPosition() >= 8){
          garradechavez.set(-0.2);
        }
        if(m_Encoder.getPosition() < 8){
          garradechavez.set(0);
      }
      }
      if(autoS == 5){
        if(gyro.getYaw() < 179 && gyro.getYaw() >= -10){
          rightSpeedGroup.set(-0.14);
          leftSpeedGroup.set(0.14);
        }
        if(gyro.getYaw() < -150){
          rightSpeedGroup.set(0);
          leftSpeedGroup.set(0);
          rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
          autoS++;
        }
      }
      if(autoS == 6){
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) > -20){
          rightSpeedGroup.set(0.1);
          leftSpeedGroup.set(0.1);
          brazo.set(-0.5);
          brazo2.set(0.5);
        }
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) <= -20){
          rightSpeedGroup.set(0);
          leftSpeedGroup.set(0);
          brazo.set(0);
          brazo2.set(0);
          autoS++;
        }
      }
}

//"Left (1 cube / mobility / balance)"
public void autocode3(){
  if(autoS== 1){
    if(m_Encoder.getPosition() <= 24){
      garradechavez.set(0.2);
    }
    if(m_Encoder.getPosition() > 24){
      garradechavez.set(0);
      autoS++;
    }
  }

  if(autoS == 2){
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) > -70){
          rightSpeedGroup.set(0.2);
          leftSpeedGroup.set(0.2);
        }
        
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) <= -70){
          rightSpeedGroup.set(0);
          leftSpeedGroup.set(0);
          rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
          autoS++;
        }
      }
    if(autoS == 3){
        try{
          brazo.set(0.5);
          brazo2.set(-0.5);
          Thread.sleep(500);
          brazo.set(0);
          brazo2.set(0);
          autoS++;
        }
        catch(InterruptedException e){
        }
      }
      if(autoS == 4){
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) < 400){
          rightSpeedGroup.set(-0.2);
          leftSpeedGroup.set(-0.2);
        }
        
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) >= 400){
          rightSpeedGroup.set(0);
          leftSpeedGroup.set(0);
          autoS++;
          gyro.zeroYaw();
        }
        if(m_Encoder.getPosition() >= 8){
          garradechavez.set(-0.2);
        }
        if(m_Encoder.getPosition() < 8){
          garradechavez.set(0);
      }
      }
      if(autoS == 5){
          if(gyro.getYaw() > -89){
            rightSpeedGroup.set(0.15);
            leftSpeedGroup.set(-0.15);
          }
          if(gyro.getYaw() <= -89){
            rightSpeedGroup.set(0);
            leftSpeedGroup.set(0);
            rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
            autoS++;
          }
      }
      if(autoS == 6){
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) > -200){
          rightSpeedGroup.set(0.2);
          leftSpeedGroup.set(0.2);
        }
        
        if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) <= -200){
          rightSpeedGroup.set(0);
          leftSpeedGroup.set(0);
          autoS++;
          gyro.zeroYaw();
        }
      }
      if(autoS == 7){
        if(gyro.getYaw() < 89){
          rightSpeedGroup.set(-0.2);
          leftSpeedGroup.set(0.2);
        }
        if(gyro.getYaw() >= 89){
          rightSpeedGroup.set(0);
          leftSpeedGroup.set(0);
          rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
          autoS++;
        }
    }
    if(autoS == 8){
      if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) > -200){
        rightSpeedGroup.set(0.2);
        leftSpeedGroup.set(0.2);
      }
      
      if((((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048) <= -200){
        rightSpeedGroup.set(0);
        leftSpeedGroup.set(0);
        autoS++;
        gyro.zeroYaw();
      }
    }
    if(autoS == 9){
      if(gyro.getPitch() < 2 && gyro.getPitch() > -2){
        rightSpeedGroup.set(0);
        leftSpeedGroup.set(0);
      }
      if(gyro.getPitch() >= 2){
        rightSpeedGroup.set(0.1);
        leftSpeedGroup.set(0.1);
      }
      if(gyro.getPitch() <= -2){
        rightSpeedGroup.set(-0.1);
        leftSpeedGroup.set(-0.1);
      }
    }
}

// "Right (2 cube / mobility)"
public void autocode4(){
  if(autoS == 1){
    // if(autoS == 5){
    //   if(gyro.getYaw() > -179 && gyro.getYaw() <= 10){
    //     rightSpeedGroup.set(0.2);
    //     leftSpeedGroup.set(-0.2);
    //   }
    //   if(gyro.getYaw() > 150){
    //     rightSpeedGroup.set(0);
    //     leftSpeedGroup.set(0);
    //     rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
    //     autoS++;
    //   }
    // }
  }
}

//"Right (1 cube / mobility / balance)"
public void autocode5(){
  if(autoS == 1){
    
  }
}
}
