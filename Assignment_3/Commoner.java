/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;
import java.util.*;

/**
 *
 * @author varun
 */
public class Commoner extends Player {
    
    public Commoner(){
        super();
        super.setPlayerType("Commoner");
        super.setPlayerHP(1000);
    }

    @Override
    public boolean checkPlayer(int playerID, mafiaGame game) {
        Player target = null;
        for(int i=0;i<game.getRandomList().size();i++){
            if(game.getRandomList().get(i).getPlayerID()==playerID){
                target = game.getRandomList().get(i);
            }
        }
        if(target.getPlayerType().equals("Commoner")){
            return true;
        }
        return false;
    }
    
    @Override
    public Player choosePlayer(mafiaGame game) {
        boolean flag = true;
        Random random = new Random();
        Player target = null;
        while(flag){
            int randomVal = random.nextInt(game.getRandomList().size());
            target = game.getRandomList().get(randomVal);
            if(target.getClass()==this.getClass()){
                flag=true;
            }
            else{
                flag=false;
            }
        }
        return target;
    }

    
}
