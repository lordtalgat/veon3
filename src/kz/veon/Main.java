package kz.veon;

import java.util.Scanner;

public class Main {

    // wall size
    private static final byte Size = 10;
    
    private static String[][] wall = new String[Size][Size];
   
    private static byte bPlayerNum = 0;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int iTmp =0;
        int xTmp=0;
        int yTmp=0;

        for (int y=0;y<Size;y++) {
            for (int x = 0; x < Size; x++) {
                wall[x][y] = ".";
            }
        }

        // Play until one of players win
        while (!isNextTurnGameEnd()) {
            nextPlayer();
            while (true) {
                System.out.println("\nTurn of Player " + bPlayerNum);
                showWall();
                System.out.print("Enter place " + (1 == bPlayerNum ? "X" : "O") + ": ");
                if (sc.hasNextInt()) {
                    iTmp = sc.nextInt();
                    xTmp=iTmp%10;
                    yTmp=iTmp/10;
                    if (isValidInput(xTmp,yTmp))
                        break;
                }
                System.out.println("Invalid number. ReEnter");
                sc.next();
            }
            try {
                putXY(xTmp, yTmp);
            } catch (Exception e) {
                System.out.println("Error ;(");
            }
        }
        showWall();
    }

    //proverka tolko po X dlya reshenia po Y nado pomenyat wall[x][y] na wall[y][x]
    private static Boolean isNextTurnGameEnd(){
        int option1=0; //X.XXX
        int option2=0; //XX.XX
        int option3=0; //XXX.X
        int option4=0; //XXXX.
        int option5=0; //.XXXX
        String sw = 1 == bPlayerNum ? "X" : "O";
        String s = 1 == bPlayerNum ? "O" : "X";
        for (int y=0;y<Size; y++){
            option1=0;
            option2=0;
            option3=0;
            option4=0;
            option5=0;
            for (int x=0;x<Size;x++){
               if (wall[x][y]==s){
                   //5
                   if (option1==4){
                       option1=5;
                   }
                   if (option2==4){
                       option2=5;
                   }
                   if (option3==4){
                       option3=5;
                   }
                   if (option5==4){
                       option5=5;
                   }
                   //4
                   if (option1==3){
                       option1=4;
                   }
                   if (option2==3){
                       option2=4;
                   }
                   if (option4==3){
                       option4=4;
                   }
                   if (option5==3){
                       option5=4;
                   }
                   //3
                   if (option1==2){
                       option1=3;
                   }
                   if (option3==2){
                       option3=3;
                   }
                   if (option4==2){
                       option4=3;
                   }
                   if (option5==2){
                       option5=3;
                   }
                   //2
                   if (option2==1){
                       option2=2;
                   }
                   if (option3==1){
                       option3=2;
                   }
                   if (option4==1){
                       option4=2;
                   }
                   if (option5==1){
                       option5=2;
                   }

                   //1
                   if (option1==0){
                       option1=1;
                   }
                   if (option2==0){
                       option2=1;
                   }
                   if (option3==0){
                       option3=1;
                   }
                   if (option4==0){
                       option4=1;
                   }

               }else{
                   if (wall[x][y]=="."){
                       //5
                       if (option4==4){
                           option4 = 5;
                       }
                       //4
                       if (option3==3){
                           option3=4;
                       }
                       //3
                       if (option2==2){
                           option2=3;
                       }
                       //2
                       if (option1==1){
                           option1=2;
                       }
                       if (option2==1){
                           option2=0;
                       }
                       if (option3==1){
                           option3=0;
                       }
                       if (option4==1){
                           option4=0;
                       }
                       if (option5==1){
                           option5=0;
                       }
                       //1
                       if (option5==0){
                           option5=1;
                       }

                   }else{
                       option1=0;
                       option2=0;
                       option3=0;
                       option4=0;
                   }
               }
            }
            System.out.format("\n player=%s col=%d o1=%d o2=%d o3=%d o4=%d 05=%d", s, y, option1, option2, option3, option4,option5);
            if (option1==5 || option2==5 || option3==5 || option4==5 || option5==5){
                System.out.format("\n player=%s will win next turn",s);
                return true;
            }
        }
        return false;
    }


    private static boolean isValidInput(int x,int y) {
        if (x > Size-1 || x<0 || y>Size-1 ||y<0) return false;
        switch (getXY(x, y)) {
            case "O":
            case "X":
                return false;
        }
        return true;
    }

    //next player set
    private static void nextPlayer() {
        bPlayerNum = (byte) (1 == bPlayerNum ? 2 : 1);
    }


    private static String getXY(int x, int y) {
        return wall[x][y];
    }


    private static void putXY(int x,int y) {
        wall[x][y] = 1 == bPlayerNum ? "X" : "O";
    }


    private static void showWall() {
        for (int y=0;y<Size;y++) {
            for (int x = 0; x < Size; x++) {
                System.out.printf("%4s", getXY(x, y));
            }
            System.out.print("\n");
        }
    }
}
