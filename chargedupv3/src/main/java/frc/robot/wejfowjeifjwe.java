// package frc.robot;

// public class auto1 extends Robot {

//   public void autocode1(){
//     if(autoS== 1){
//         if(m_Encoder.getPosition() <= 6){
//           garradechavez.set(0.2);
//         }
//         if(m_Encoder.getPosition() > 6){
//           garradechavez.set(0);
//           autoS++;
//         }
//       }
  
//       if(autoS == 2){
//           try{
//             brazo.set(-0.3);
//             brazo2.set(0.3);
//             Thread.sleep(500);
//             brazo.set(0);
//             brazo2.set(0);
//             autoS++;
  
//           }
//           catch(InterruptedException e){
//           }
//         }
//         if(autoS == 3){
          
//       double distanceR = rightmotor1.getSelectedSensorPosition();
//       double cm = ((distanceR* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048);
//           if(cm <= 380){
//             rightSpeedGroup.set(-0.2);
//             leftSpeedGroup.set(-0.2);
//           }
          
//           if(cm >= 380){
//             rightSpeedGroup.set(0);
//             leftSpeedGroup.set(0);
//             rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
//             autoS++;
//           } 
        
//   //68 cm para media vuelta right -1, left +1
//       }
  
//       if(autoS == 4){
          
//         double distanceR = rightmotor1.getSelectedSensorPosition();
//         double cm3 = ((distanceR* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048);
//             if(cm3 > -72){
//               rightSpeedGroup.set(0.1);
//               leftSpeedGroup.set(-0.1);
//             }
            
//             if(cm3 <= -72){
//               rightSpeedGroup.set(0);
//               leftSpeedGroup.set(0);
//               autoS++;
//             } 
//         }   
//       if(autoS == 5){
//         double distanceR2 = rightmotor1.getSelectedSensorPosition();
//         double cm2 = ((distanceR2* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048);
  
//         if(cm2 > -102){
//           rightSpeedGroup.set(0.2);
//           leftSpeedGroup.set(0.2);
//           brazo.set(0.3);
//           brazo2.set(-0.3);
//         }
//         if(cm2 <= -102){
//           rightSpeedGroup.set(0);
//           leftSpeedGroup.set(0);
//           brazo.set(0);
//           brazo2.set(0);
//           autoS++;
//         }
//       }
//       if(autoS == 6){
//         double distanceR3 = rightmotor1.getSelectedSensorPosition();
//         double cm3 = ((distanceR3* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048);
//         if(cm3 < -30){
//           rightSpeedGroup.set(-0.1);
//           leftSpeedGroup.set(0.1);
//         }
//         if(cm3 >= -30){
//           rightSpeedGroup.set(0);
//           leftSpeedGroup.set(0);
//           autoS++;
//         }
//       }
//       if(autoS == 7){
//         if(m_Encoder.getPosition() <= 13){
//           garradechavez.set(0.2);
//         }
//         if(m_Encoder.getPosition() > 13){
//           garradechavez.set(0);
//           rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
//           autoS++;
//         }
//       }
//       if(autoS == 8){
//         double cm4 = (((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048);
//         if(cm4 >-420){
//           rightSpeedGroup.set(0.2);
//           leftSpeedGroup.set(0.2);
//         }
//         if(cm4 <= -420){
//           rightSpeedGroup.set(0);
//           leftSpeedGroup.set(0);
//           autoS++;
//         }
//       }
  
//       if(autoS == 9){
//         try{
//           brazo.set(-0.3);
//           brazo2.set(0.3);
//           Thread.sleep(300);
//           brazo.set(0);
//           rightmotor1.getSensorCollection().setIntegratedSensorPosition(0,0);
//           autoS++;
//         }
//         catch(InterruptedException e){
//         }
//       }
  
//       if(autoS == 11){
//         double cm5 = (((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048);
//         if(cm5 < -62){
//           rightSpeedGroup.set(-0.1);
//           leftSpeedGroup.set(0.1);
//         }
//         if(cm5 >= -478 && cm5 < -420){
//           rightSpeedGroup.set(0.2);
//           leftSpeedGroup.set(0.2);
//         }
//         if(cm5 >= -420 && cm5 < -378){
//           rightSpeedGroup.set(-0.1);
//           leftSpeedGroup.set(0.1);
//         }
//         if(cm5 >= -378 && cm5 < -200){
//           rightSpeedGroup.set(0.2);
//           leftSpeedGroup.set(0.2);
//         }
//         if(cm5 >= -200){
//           rightSpeedGroup.set(0);
//           leftSpeedGroup.set(0);
//           autoS++;
//         }
//       }
  
//       if(autoS == 11){
//         double pitch = (gyro.getPitch()*1000);
//           if(pitch <= -200){
//             leftSpeedGroup.set(0.1);
//             rightSpeedGroup.set(0.1);
//           }
//           if(pitch >= 800){
//             leftSpeedGroup.set(-0.1);
//             rightSpeedGroup.set(-0.1);
//           }
//           if(pitch > -200 && pitch < 800){
//             leftSpeedGroup.set(0);
//             rightSpeedGroup.set(0);
//           autoS++;
//         }
//       }
//       if(autoS == 7){
//         rightSpeedGroup.set(0);
//         leftSpeedGroup.set(0);
//       }
//   }
  
// }