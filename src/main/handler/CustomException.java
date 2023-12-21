package main.handler;

import main.HardCodedValue;

public class CustomException extends Exception{
    private String msg = "";

    public CustomException(){
        super();
        msg = "error2";
    }
    public CustomException(String message){
        super(message);
        msg = message;
    }

    public void processError(int value){
        System.out.println("#########################");
        if(value == 0) msg = "error2";
        switch (msg){
            case "error1":
                System.out.println(HardCodedValue.ERROR1+ value+ "!");
                break;
            case "error2":
                System.out.println(HardCodedValue.ERROR2);
                break;
            case "error3":
                System.out.println(HardCodedValue.ERROR3);
                break;
            case "error4":
                System.out.println(HardCodedValue.ERROR4);
                break;
            case "error5":
                System.out.println(HardCodedValue.ERROR5);
                break;
            case "error6":
                System.out.println(HardCodedValue.ERROR6);
                break;
            case "error7":
                System.out.println(HardCodedValue.ERROR7);
                break;
            case "error8":
                System.out.println(HardCodedValue.ERROR8);
                break;
            case "error9":
                System.out.println(HardCodedValue.ERROR9);
                break;
            case "error10":
                System.out.println(HardCodedValue.ERROR10);
                break;
            case "error11":
                System.out.println(HardCodedValue.ERROR11);
                break;


        }
        System.out.println("#########################");
    }
}
