import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.*;
class tictactoe{
    static ArrayList<Integer> userpos = new ArrayList<Integer>();
    static ArrayList<Integer> cpupos = new ArrayList<Integer>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);        
        char[][]  gameboard= {
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '}
        };
        printBoard(gameboard);
        

        while(true){
            
            //taking input from user
            System.out.println("Enter your placement (1-9):");
            int position = sc.nextInt();
            while(userpos.contains(position) || cpupos.contains(position)){
                System.out.print("Enter another position: ");
                position = sc.nextInt(); 
            }
            //method to place user's mark
            placemark(gameboard,position,"user");

            String result = iswinner();
            if(result.length()>0){
                System.out.println(result);
                break;
            }

            //taking input from computer
            Random rand = new Random();
            int cpuPosition = rand.nextInt(9)+1;
            while(cpupos.contains(cpuPosition) || userpos.contains(cpuPosition)){
                cpuPosition = rand.nextInt(9)+1;
            }

            System.out.println("\nComputer's marker: "+cpuPosition);
            //method to place CPU's mark
            placemark(gameboard,cpuPosition,"cpu"); 

            result = iswinner();
            if(result.length()>0){
                System.out.println(result);
                break;
            }         
            
        }
    }

    public static void placemark(char[][] gameboard, int pos, String place){
        char symbol = ' ';
        if(place.equals("user")){
            symbol = 'x';
            userpos.add(pos);
        }
        else if(place.equals("cpu")){
            symbol = 'O';
            cpupos.add(pos);
        }

        switch (pos) {
            case 1: gameboard[0][0] = symbol;
                
                break;
            case 2: gameboard[0][2] = symbol;
                
                break;
            case 3: gameboard[0][4] = symbol;
                
                break;
            case 4: gameboard[2][0] = symbol;
                
                break;
            case 5: gameboard[2][2] = symbol;
                
                break;
            case 6: gameboard[2][4] = symbol;
                
                break;
            case 7: gameboard[4][0] = symbol;
                
                break;
            case 8: gameboard[4][2] = symbol;
                
                break;
            case 9: gameboard[4][4] = symbol;
                
                break;
            default: System.out.println("Invalid choice");
                break;
        }
        printBoard(gameboard);
    }

    private static String iswinner(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();

        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);
        
        for(List l : winning){
            if(userpos.containsAll(l)){
                return "Congratulations ! you won";

            }
            else if(cpupos.containsAll(l)){
                return "Sorry ! you lost (: ";
            }
            
        }
        if(userpos.size() + cpupos.size() == 9){
            return "It's a TIE ";
        }
        else{
            return "";
        }
    }

    private static void printBoard(char[][] gameboard) {        
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                System.out.print(gameboard[i][j]);
            }
            System.out.println();
        }
    }    
}
