/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author varun
 */
public class Assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Reader.init(System.in);
        System.out.println("Welcome to Mafia");
        
        int numOfPlayers=0;
        while(numOfPlayers<6){
            System.out.print("Enter Number of players: ");
            numOfPlayers = Reader.nextInt();
            System.out.println();
        }
        
        mafiaGame game = new mafiaGame(numOfPlayers);
        game.generateRandomPlayers();
        
        System.out.println("Choose a Character ");
        System.out.println("1) Mafia");
        System.out.println("2) Detective");
        System.out.println("3) Healer");
        System.out.println("4) Commoner");
        System.out.println("5) Assign Randomly");
        System.out.print("select option ");
        
        int playerType = Reader.nextInt();
        Player user = null;
        if(playerType==1){
            Object tempUser = new Mafia();
            user = game.userAssign(tempUser);
        }
        else if(playerType==2){
            Object tempUser = new Detective();
            user = game.userAssign(tempUser);
        }
        else if(playerType==3){
            Object tempUser = new Healer();
            user = game.userAssign(tempUser);
        }
        else if(playerType==4){
            Object tempUser = new Commoner();
            user = game.userAssign(tempUser);
        }
        else{
            user = game.randomAssignUser();
        }
        System.out.println();
        
        System.out.println("You are Player" + user.getPlayerID() + ".");
        System.out.print("You are a " + user.getPlayerType() + ". ");
        game.otherPlayers(user, user.getPlayerID());

        //TO BE COMMENTED
        /*
        for(int i=0;i<game.getRandomList().size();i++){
            int j = i+1;
            System.out.println("player "+j + " " +game.getRandomList().get(i));
        }
        */
        
        int round = 1;
        boolean checkGameEnd = game.gameEnd();
        while(checkGameEnd==false){
            Player target = null;
            Player detectedPlayer = null;
            Player healedPlayer = null;
            System.out.println("Round " + round + ": ");
            game.remainingPlayers();
            if(user.getPlayerType().equals("Mafia")){
                if(user.getIsAlive()){
                    System.out.print("Choose a target: ");
                    int targetID = Reader.nextInt();
                    System.out.println();
                    while(user.checkPlayer(targetID, game)){
                        System.out.println("You cannot choose a mafia");
                        System.out.print("Choose a target: ");
                        targetID = Reader.nextInt();
                        System.out.println();
                    }
                    target = user.userChoosePlayer(targetID, game);
                }
                else{
                    System.out.println("Mafias have chosen their target.");
                    target = user.choosePlayer(game);
                }
                
                game.damageCalc(target);
                
                System.out.println("Detectives have chosen a player to test.");
                detectedPlayer = game.detectiveChoose();
                
                System.out.println("Healers have chosen someone to heal.");
                healedPlayer = game.HealerChoose();
                
                System.out.println("--End of actions--");
                
            }
            else if(user.getPlayerType().equals("Detective")){
                System.out.println("Mafias have chosen their target.");
                target = game.targetChoose();
                game.damageCalc(target);
                if(user.getIsAlive()){
                    System.out.print("Choose a player to test: ");
                    int testPlayer = Reader.nextInt();
                    System.out.println();
                    while(user.checkPlayer(testPlayer, game)){
                        System.out.println("You cannot test a detective.");
                        System.out.print("Choose a player to test: ");
                        testPlayer = Reader.nextInt();
                        System.out.println();
                    }
                    detectedPlayer = user.userChoosePlayer(testPlayer, game);
                    if(detectedPlayer.getPlayerType().equals("Mafia")){
                        System.out.println("Player"+detectedPlayer.getPlayerID()+" is a mafia.");
                    }
                    else{
                        System.out.println("Player"+detectedPlayer.getPlayerID()+" is not a mafia.");
                    }
                }
                else{
                    System.out.println("Detectives have chosen a player to test.");
                    detectedPlayer = game.detectiveChoose();
                }
                System.out.println("Healers have chosen someone to heal.");
                healedPlayer = game.HealerChoose();
                
                System.out.println("--End of actions--");
                
            }
            else if(user.getPlayerType().equals("Healer")){
                
                System.out.println("Mafias have chosen their target.");
                target = game.targetChoose();
                //System.out.println("target " + target + " target HP " + target.getPlayerHP());
                game.damageCalc(target);
                
                System.out.println("Detectives have chosen a player to test.");
                detectedPlayer = game.detectiveChoose();
                
                if(user.getIsAlive()){
                    System.out.print("Choose a player to heal: ");
                    int healedID = Reader.nextInt();
                    healedPlayer = user.userChoosePlayer(healedID, game);
                }
                else{
                    System.out.println("Healers have chosen someone to heal.");
                    healedPlayer = game.HealerChoose();
                }
                
                System.out.println("--End of actions--");
                
                
            }
            else if(user.getPlayerType().equals("Commoner")){
                
                System.out.println("Mafias have chosen their target.");
                target = game.targetChoose();
                game.damageCalc(target);
                
                System.out.println("Detectives have chosen a player to test.");
                detectedPlayer = game.detectiveChoose();
                
                System.out.println("Healers have chosen someone to heal.");
                healedPlayer = game.HealerChoose();
                
                System.out.println("--End of actions--");
                
                
            }
            
            //TO BE COMMENTED
            
            //System.out.println("detective " + detectedPlayer);
            //System.out.println("healer " + healedPlayer);
            
            game.healPlayer(healedPlayer);
            game.targetPlayerStatus(target);
            
            //System.out.println("target " + target + " target HP " + target.getPlayerHP());
            checkGameEnd = game.gameEnd();

            if(checkGameEnd==false){
                boolean isVoteOutPossible = game.isVoteOutPossible(detectedPlayer);
                if(isVoteOutPossible){
                    if(user.getIsAlive()){
                        System.out.print("Select a person to vote out: ");
                        int voteOutID = Reader.nextInt();
                        System.out.println();
                        game.playerVote(voteOutID);
                        game.voteRandomly(user.getPlayerID());
                        boolean voteOutPossible = game.voteOut();
                        while(voteOutPossible==false){
                            System.out.print("Select a person to vote out: ");
                            voteOutID = Reader.nextInt();
                            game.playerVote(voteOutID);
                            game.voteRandomly(user.getPlayerID());
                            voteOutPossible = game.voteOut();
                        }
                    }
                    else{
                        game.voteRandomly(user.getPlayerID());
                        boolean voteOutPossible = game.voteOut();
                        while(voteOutPossible==false){
                            game.voteRandomly(user.getPlayerID());
                            voteOutPossible = game.voteOut();
                        }
                    }

                }
            }
            
            System.out.println("--End of round " +  round + "--");
            round++;
            
            checkGameEnd = game.gameEnd();
            if(checkGameEnd==true){
                game.gameOver(user);
            }
   
        }
        
        
        
        
        
        
        
    }
    
}


class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
    static float nextFloat() throws IOException {
        return Float.parseFloat( next() );
    }
}











