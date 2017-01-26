/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Keinan
 */

public class KnightsTourApp {
    public static void main(String[] args){
        KnightsTourApp run = new KnightsTourApp();
        run.runSimulation();
    }
    
    Board board;
    private int usedMoves8 = -1;
    private int N ;             //Board size initialize on startup
    private int position;       //Initial position scaned on startup
    private float NumMoves = 0; //Keep track of the number of moves
    private Stack stack = new Stack();
    private static int[][] moves8 =
        { {+1,-2}, {+2,-1}, {+2,+1}, {+1,+2},       //8 possible moves
        {-1,+2}, {-2,+1}, {-2,-1}, {-1,-2} };
    
    public void runSimulation() //Begins path finding
    {
        System.out.print("Enter board size (8 for 8x8 board): ");
        Scanner scanInput = new Scanner(System.in);
        N = scanInput.nextInt();    //Get board size
        System.out.print("Enter the beginning square (1 to "+(N*N)+"): ");
        position = scanInput.nextInt();     //Get starting position

        board = new Board(N);
        
        stack.push(new Knight(position));   //Put start position into stack
        board.setVisited(position);
        while(!board.boardFull())
        {
            //If all possibilities have been tried end simulation
            int nextSpace = getNextPos();
            if(nextSpace > 0)
            {
                Knight newKnight = new Knight(nextSpace);
                board.setVisited(nextSpace);
                stack.push(newKnight);
                position = nextSpace;
                NumMoves++;
            }
            else
            {
                Knight holder = (Knight)stack.pop();
                position = (((Knight)stack.peek()).Jvalue);
                System.out.println("Tried: "+holder.Jvalue+" From: "+position);
//                board.setVisited(holder.Jvalue);
                board.setTried(holder.Jvalue, ((Knight)stack.peek()).Jvalue);
            }
        }
    }
    
    public int getNextPos() // picks next
    { // possible
        int cycle = 0;      //Move index for move8, start at 0
        while(cycle < 8) // move
        {
            int dx = moves8[cycle][0]; // get move in
            int dy = moves8[cycle][1]; // (x,y) format
            int x = (position-1)%N; // translate from j
            int y = (position-1)/N; // to (x,y) format
            x = x + dx; // add move to
            y = y + dy; // position
            cycle++; // used this move
            if(x>=0 && x<N && y>=0 && y<N) // on the board?
            { // yes
                int nextPos = x + y*N + 1; // (x,y) to j
                if(board.get(nextPos, position)==false) // unoccupied cell?
                { // yes
                    System.out.println(nextPos);
//                    usedMoves8=cycle;
                    return nextPos; // found a move
                } // end if(x>=0...)
            }
        } // end while // no possible move
        cycle = 0; // reset move index
        return -1; // failure
    } // end getNextPos()
}