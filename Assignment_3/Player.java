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
public abstract class Player {
    
    private String playerType = "Player";
    private double playerHP;
    private boolean isAlive;
    private int votesReceived=0;
    private final int playerID;
    private static int ID;
    
    public Player(){
        playerHP = 800;
        isAlive = true;
        this.playerID=ID+1;
        ID+=1;
    }
    
    protected String getPlayerType(){
        return this.playerType;
    }
    
    protected double getPlayerHP(){
        return this.playerHP;
    }
    
    protected boolean getIsAlive(){
        return this.isAlive;
    }
    
    protected int getVotes(){
        return this.votesReceived;
    }
    
    protected int getPlayerID(){
        return this.playerID;
    }
    
    protected void setPlayerType(String newPlayerType){
        this.playerType = newPlayerType;
    }
    
    protected void setPlayerHP(double newHP){
        this.playerHP = newHP;
    }
    
    protected void setIsAlive(boolean aliveStatus){
        this.isAlive = aliveStatus;
    }
    
    protected void setVotes(int votesReceived){
        this.votesReceived = votesReceived;
    }
    
    @Override
    public boolean equals(Object player){
        if(player!=null && getClass()==player.getClass()){
            Player userPlayer = (Player)player;
            return (playerHP==userPlayer.playerHP && (playerType.compareTo(userPlayer.playerType)==0));
        }
        else{
            return false;
        }
    }
    
    public abstract boolean checkPlayer(int playerID, mafiaGame game);
    
    public abstract Player choosePlayer(mafiaGame game);

    public Player userChoosePlayer(int targetID, mafiaGame game) {
        Player target = null;
        for(int i=0;i<game.getRandomList().size();i++){
            if(game.getRandomList().get(i).getPlayerID()==targetID){
                target = game.getRandomList().get(i);
            }
        }
        return target;
    }
    
    
}
