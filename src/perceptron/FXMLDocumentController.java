/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Bayu
 */
public class FXMLDocumentController implements Initializable {
    
    float tetha=(float) 0.3;
    float weight1,weight2,weight3,bias;
    int epoch=20;
    float a=(float) 0.7; //learning rate
    float data [][]= {{16,18,20,1},{16,20,25,1},{17,25,26,1},{16,18,20,1},{20,18,16,1},{20,16,18,1},
                      {23,17,18,1},{23,18,17,1},{20,21,23,1},{20,28,29,-1},{25,30,23,-1},{30,23,26,-1},
                      {23,30,25,-1},{25,23,30,-1},{30,25,23,-1},
                      };
     
    @FXML
    private Button button;
    @FXML
    private TextField out;
    @FXML
    private TextField in1;
    @FXML
    private TextField in2;
    @FXML
    private TextField in3;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        float hitung;
        hitung= (convert(Float.parseFloat(in1.getText()))*weight1)+(convert(Float.parseFloat(in2.getText()))*weight2)+(convert(Float.parseFloat(in3.getText()))*weight3)+bias;
        if(hitung>tetha){
           out.setText("Dinaikkan");        
        }else if(hitung>=-tetha&&hitung<=tetha){
           out.setText("Tetap");            
        }else if(hitung<-tetha){
           out.setText("Diturunkan");
        }
    }
    public float convert(float a){
        float b=0;
        if(a<16){
            b=(float)0;
        }else if(a>=16&&a<=23){
            b=(float)0.5;
        }else if(a>23){
            b=(float)1;
        }
        return b;
    }
    public void latih(){
        float hitung;
            float count=0;

        int hasilaktifasi=0;
        for (int i = 0; i < epoch; i++) {
            System.out.println("\n"+"Epoch ke "+(i+1)+"\n");
                for (int j = 0; j < 15; j++) {
                    System.out.println("Data ke : "+(j+1));
                    hitung=(convert(data[j][0])*(float) weight1)+(convert(data[j][1])*(float)weight2)+(convert(data[j][2])*(float)weight3)+(float)bias;
                    if(hitung>tetha){
                        hasilaktifasi=1;
                    }else if(hitung>=-tetha&&hitung<=tetha){
                        hasilaktifasi=0;
                    }else if(hitung<-tetha){
                        hasilaktifasi=-1;
                    }
                    System.out.println("y_in : "+(String.format("%.2f", hitung))+"  Hasil Aktivasi  "+(float)hasilaktifasi+"   target : "+data[j][3]);

                    if((int) data[j][3]!=hasilaktifasi){
                        weight1=(float)weight1+((float)a*(float)data[j][3]*convert(data[j][0]));
                        weight2=(float)weight2+((float)a*(float)data[j][3]*convert(data[j][1]));
                        weight3=(float)weight3+((float)a*(float)data[j][3]*convert(data[j][2]));
                        bias=bias+(a*data[j][3]);
                    }
                System.out.println("Bobot sensor 1 : "+(String.format("%.2f", weight1))+"   Bobot sensor 2 : "+(String.format("%.2f", weight2))+"   Bobot sensor 3 : "+(String.format("%.2f", weight3))+"    Bias : "+(String.format("%.2f", bias)));
                }
        }
        for (int j = 0; j < 15; j++) {
                    //System.out.println("Data ke : "+(j+1));
                    hitung=(convert(data[j][0])*(float) weight1)+(convert(data[j][1])*(float)weight2)+(convert(data[j][2])*(float)weight3)+(float)bias;
                    if(hitung>tetha){
                        hasilaktifasi=1;
                    }else if(hitung>=-tetha&&hitung<=tetha){
                        hasilaktifasi=0;
                    }else if(hitung<-tetha){
                        hasilaktifasi=-1;
                    }
                    
                    if(hasilaktifasi==data[j][3]){
                        count=count+1;
                    }
                    
    }float akurat=count/15;
                    System.out.println("Keakuratan dari learning sebanyak "+epoch+" epoch yaitu "+akurat+" %");
    }
    //Override
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        weight1=weight2=weight3=bias=0;
            latih();
    }    
    
    }
