// package frc.robot;

// public class auto3 extends Robot{
//     public void autocode3(){
//         // if(autoS== 1){
//         //     if(m_Encoder.getPosition() <= 10){
//         // //       garradechavez.set(0.2);
//         //     }
//         //     if(m_Encoder.getPosition() > 10){
//         //       garradechavez.set(0);
//         //       autoS++;
//         //     }
//         //  } 
//           if(autoS == 1){
//             double cm3 = (((rightmotor1.getSelectedSensorPosition())* 0.25 * 0.625 * 3.141592 * 6 * 2.54) / 2048);
//                 if(cm3 > 68){
//                   rightSpeedGroup.set(-0.1);
//                   leftSpeedGroup.set(0.1);
//                 }
                
//                 if(cm3 <= 68){
//                   rightSpeedGroup.set(0);
//                   leftSpeedGroup.set(0);
//                   autoS++;
//                 } 
//             }  
//     }
// }
