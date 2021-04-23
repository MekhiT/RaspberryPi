import framboos.InPin;
import framboos.OutPin;
import framboos.actor.SerialPortActor;
import framboos.actor.SerialPortActor.SendMessage;
import java.io.*;
import java.util.Scanner;

public class MainController {
    TrackController main = new TrackController(5);
    boolean manual_mode = false;
    boolean trackswitch1default = false;
    boolean trackswitch2default = false;
    boolean trackswitch3default = false;
    boolean trackswitch4default = false;
    boolean trackswitch5default = false;
    public static void main(String[] args) {
  // reading from an in pin
  Reader inner_input;
  Writer inner_output;
  SendMessage writtenmessage = new SendMessage("Hello");
  InPin manual_button = new InPin(8);
  boolean isButtonPressed = manual_button.getValue();
  manual_button.close();

  // writing to an out pin
  SerialPortActor serial = new SerialPortActor("/dev/tty/ACM0");
  serial.connect();
  writtenmessage.message();
}

  public void toggle_manual_mode(){
      OutPin manual_led = new OutPin(0);
      if(manual_mode){
        manual_led.setValue(true);
        manual_mode = false;
      }
      else{
          manual_led.setValue(false);
          manual_mode = true;
      }
  }

  private void ReadPLC(){
        File PLC = new File("PLC.txt");
        Scanner sc;
        String [] lines = new String[25];
        try {
            sc = new Scanner(PLC);
            int i = 0;
            while(sc.hasNextLine()){
                lines[i] = sc.nextLine();
                i++;
            }
        }catch(FileNotFoundException e){
            System.out.println("Cannot find file names PLC.txt");
            e.printStackTrace();
        }
        if(lines[0].contains("1")){
            trackswitch1default = true;
        }else{
            trackswitch1default = false;
        }
      if(lines[1].contains("1")){
          trackswitch2default = true;
      }else{
          trackswitch2default = false;
      }
      if(lines[2].contains("1")){
          trackswitch3default = true;
      }else{
          trackswitch3default = false;
      }
      if(lines[3].contains("1")){
          trackswitch4default = true;
      }else{
          trackswitch4default = false;
      }
      if(lines[4].contains("1")){
          trackswitch5default = true;
      }else{
          trackswitch5default = false;
      }
  }

  public void toggleSwitch(int number){
       trackModel.switch(number);
  }
}
