// package frc.robot;

// public class auto2 extends Robot{
//     public void autocode2(){
//         if(autoS== 1){
//             if(m_Encoder.getPosition() <= 6){
//               garradechavez.set(0.2);
//             }
//             if(m_Encoder.getPosition() > 6){
//               garradechavez.set(0);
//               autoS++;
//             }
//           }
    
//           if(autoS == 2){
//               try{
//                 brazo.set(-0.3);
//                 brazo2.set(0.3);
//                 Thread.sleep(300);
//                 brazo.set(0);
//                 autoS++;
    
//               }
//               catch(InterruptedException e){
//               }
//             }
//             if(autoS == 3){
              
//           double distanceR = rightmotor1.getSelectedSensorPosition();
//           double cm = ((distanceR* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048);
//               if(cm <= 410){
//                 rightSpeedGroup.set(-0.2);
//                 leftSpeedGroup.set(-0.2);
//               }
              
//               if(cm >= 410){
//                 rightSpeedGroup.set(0);
//                 leftSpeedGroup.set(0);
//                 autoS++;
//               }        
//           }
//           // 84.82 cm para media vuelta
//           if(autoS == 4){
              
//             double distanceR = rightmotor1.getSelectedSensorPosition();
//             double cm3 = ((distanceR* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048);
//                 if(cm3 >= 325.18){
//                   rightSpeedGroup.set(0.1);
//                   leftSpeedGroup.set(-0.1);
//                 }
                
//                 if(cm3 <= 325.18){
//                   rightSpeedGroup.set(0);
//                   leftSpeedGroup.set(0);
//                   autoS++;
//                 } 
              
//       // 84.82 cm para media vuelta right -1, left +1
//         }   
//     }
// }
