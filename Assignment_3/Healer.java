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
public class Healer extends Player {
    
    public Healer(){
        super();
        super.setPlayerType("Healer");
        super.setPlayerHP(800);
    }

    @Override
    public boolean checkPlayer(int playerID, mafiaGame game) {
        Player target = null;
        for(int i=0;i<game.getRandomList().size();i++){
            if(game.getRandomList().get(i).getPlayerID()==playerID){
                target = game.getRandomList().get(i);
            }
        }
        if(target.getPlayerType().equals("Healer")){
            return true;
        }
        return false;
    }
    
    @Override
    public Player choosePlayer(mafiaGame game) {
        Random random = new Random();
        Player target = null;
        int randomVal = random.nextInt(game.getRandomList().size());
        target = game.getRandomList().get(randomVal);
            
        return target;
    }

    
}
