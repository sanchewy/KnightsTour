/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Keinan
 */
public class Board {
    int boardArray[];
    
    public Board (int size) {
        boardArray = new int[size*size];
    }

    public boolean get(int nextPos, int number) //Checks if input position is open on board
    {
        if(boardArray[nextPos-1] == number){
            return true;        //This spot has already been checked with this number
        }
        if(boardArray[nextPos-1] == -1){
            return true;    //True, the spot is occupied
        }
        else
        {
            return false;   //False, spot is not occupied
        }
    }
    
    public boolean boardFull(){
        for(int i = 0; i < boardArray.length; i++){
            if(boardArray[i]==0){
                return false;
            }
        }
        return true;
    }
    
    public void setVisited(int index) 
    {
        boardArray[index-1] = -1;      //Set index as visited
    }
    
    public void setUnVisited(int index)
    {
        boardArray[index-1] = 0;      //Set index as not visited
    }
    
    public void setTried(int index, int number)
    {
        boardArray[index-1] = number;      //Set index as tried
    }
    
    
}
