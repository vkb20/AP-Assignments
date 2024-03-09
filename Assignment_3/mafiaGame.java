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
public class mafiaGame {
    
    private ArrayList<Player> playersList;
    private ArrayList<Player> randomList;
    private int numOfPlayers;
    private int numOfMafias;
    private int numOfDetectives;
    private int numOfCommoners;
    private int numOfHealers;
    
    public mafiaGame(int numOfPlayers){
        this.numOfPlayers = numOfPlayers;
        this.playersList = new ArrayList<>();
        this.randomList = new ArrayList<>();
        this.numOfMafias = (int)(numOfPlayers/5);
        this.numOfDetectives = (int)(numOfPlayers/5);
        this.numOfHealers = Math.max(1, (int)(numOfPlayers/10));
        this.numOfCommoners = numOfPlayers - (numOfMafias+numOfDetectives+numOfHealers);
    }
    
    public ArrayList<Player> getRandomList(){
        return this.randomList;
    }
    
    public int getNumOfPlayers(){
        return this.numOfPlayers;
    }
    
    public int getNumOfMafias(){
        return this.numOfMafias;
    }
    
    public int getNumOfDetectives(){
        return this.numOfDetectives;
    }
    
    public int getNumOfCommoners(){
        return this.numOfCommoners;
    }
    
    public int getNumOfHealers(){
        return this.numOfHealers;
    }
    
    public void setRandomList(ArrayList<Player> randomList){
        this.randomList = randomList;
    }
    
    public void setNumOfMafias(int numOfMafias){
        this.numOfMafias = numOfMafias;
    }
    
    public void setNumOfDetectives(int numOfDetectives){
        this.numOfDetectives = numOfDetectives;
    }
    
    public void setNumOfCommoners(int numOfCommoners){
        this.numOfCommoners = numOfCommoners;
    }
    
    public void setNumOfHealers(int numOfHealers){
        this.numOfHealers = numOfHealers;
    }
    
    public void generateRandomPlayers(){
        int i = this.numOfPlayers;
        Random random = new Random();
        int c1 = this.numOfCommoners;
        int c2 = this.numOfDetectives;
        int c3 = this.numOfHealers;
        int c4 = this.numOfMafias;
        while(i!=0){
            int low = 1;
            int high = 5;
            int randomVal = random.nextInt(high-low) + low;
            if(randomVal==1){
                if(c4>0){
                    Mafia mafia = new Mafia();
                    randomList.add(mafia);
                    playersList.add(mafia);
                    c4--;
                    i--;
                }
            }
            else if(randomVal==2){
                if(c2>0){
                    Detective detective = new Detective();
                    randomList.add(detective);
                    playersList.add(detective);
                    c2--;
                    i--;
                }
            }
            else if(randomVal==3){
                if(c3>0){
                    Healer healer = new Healer();
                    randomList.add(healer);
                    playersList.add(healer);
                    c3--;
                    i--;
                }
            }
            else if(randomVal==4){
                if(c1>0){
                    Commoner commoner = new Commoner();
                    randomList.add(commoner);
                    playersList.add(commoner);
                    c1--;
                    i--;
                }
            }
        }
    }
    
    public Player userAssign(Object tempUser){
        boolean notFound = true;
        int i = 0;
        Player user = null;
        while(notFound && i<randomList.size()){
            if(randomList.get(i).equals(tempUser)){
                user=randomList.get(i);
                notFound=false;
            }
            i++;
        }
        return user;
    }
    
    public Player randomAssignUser(){
        Random random = new Random();
        Player userPlayer = null;
        int randomIndex = random.nextInt(randomList.size()); 
        userPlayer = randomList.get(randomIndex);
        return userPlayer;
    }
    
    
    
    public void otherPlayers(Player user, int playerNum){
        String[] otherSamePlayers = null;
        if(user.getPlayerType().compareTo("Mafia")==0){
            otherSamePlayers = new String[numOfMafias-1];
            System.out.print("Other mafias are ");
            int k = 0;
            for(int i=0;i<randomList.size();i++){
                int j = i+1;
                if(j!=playerNum && randomList.get(i).getPlayerType().compareTo("Mafia")==0){
                    otherSamePlayers[k] = "Player"+j;
                    k++;
                }
            }
            System.out.println(Arrays.toString(otherSamePlayers));
        }
        if(user.getPlayerType().compareTo("Detective")==0){
            otherSamePlayers = new String[numOfDetectives-1];
            System.out.print("Other detectives are ");
            int k = 0;
            for(int i=0;i<randomList.size();i++){
                int j = i+1;
                if(j!=playerNum && randomList.get(i).getPlayerType().compareTo("Detective")==0){
                    otherSamePlayers[k] = "Player"+j;
                    k++;
                }
            }
            System.out.println(Arrays.toString(otherSamePlayers));
        }
        if(user.getPlayerType().compareTo("Healer")==0){
            otherSamePlayers = new String[numOfHealers-1];
            System.out.print("Other healers are ");
            int k = 0;
            for(int i=0;i<randomList.size();i++){
                int j = i+1;
                if(j!=playerNum && randomList.get(i).getPlayerType().compareTo("Healer")==0){
                    otherSamePlayers[k] = "Player"+j;
                    k++;
                }
            }
            System.out.println(Arrays.toString(otherSamePlayers));
        }
        if(user.getPlayerType().compareTo("Commoner")==0){
            otherSamePlayers = new String[0];
            System.out.print("Other Commoners are ");
            System.out.println(Arrays.toString(otherSamePlayers));
        }
    }
    
    
    
    
    public boolean gameEnd(){
        int numOfAliveMafias = 0;
        int numOfAliveNonMafias = 0;
        for(int i=0;i<randomList.size();i++){
            if(randomList.get(i).getPlayerType().compareTo("Mafia")==0){
                numOfAliveMafias+=1;
                
            }
            else{
                numOfAliveNonMafias+=1;
            }
        }
        
        if(numOfAliveMafias==numOfAliveNonMafias || numOfAliveMafias==0 || numOfAliveNonMafias ==0){
            return true;
        }
        return false;
        
    }
    
    public void remainingPlayers(){
        System.out.print(randomList.size() + " players are remaining: ");
        for(int i=0;i<randomList.size();i++){
            if(i==randomList.size()-1){
                System.out.println("Player" + randomList.get(i).getPlayerID() + " are alive.");
            }
            else{
                System.out.print("Player" + randomList.get(i).getPlayerID()+", ");
            }
        }
    }
    
    public Player detectiveChoose(){
        Player detectedPlayer = null;
        if(this.getNumOfDetectives()>0){
            int i=0;
            boolean foundDetective = false;
            while(i<this.getRandomList().size() && foundDetective==false){
                if(this.getRandomList().get(i).getPlayerType().equals("Detective")){
                   detectedPlayer = this.getRandomList().get(i).choosePlayer(this);
                   foundDetective=true;
                }
                i++;
            }
        }
        return detectedPlayer;
    }
    
    public Player targetChoose(){
        Player target = null;
        int i=0;
        boolean foundMafia = false;
        while(i<this.getRandomList().size() && foundMafia==false){
            if(this.getRandomList().get(i).getPlayerType().equals("Mafia")){
               target = this.getRandomList().get(i).choosePlayer(this);
               foundMafia=true;
            }
            i++;
        }
        return target;
        
    }
    
    public Player HealerChoose(){
        Player healedPlayer = null;
        if(this.getNumOfHealers()>0){
            int i=0;
            boolean foundHealer = false;
            while(i<this.getRandomList().size() && foundHealer==false){
                if(this.getRandomList().get(i).getPlayerType().equals("Healer")){
                   healedPlayer = this.getRandomList().get(i).choosePlayer(this);
                   foundHealer=true;
                }
                i++;
            }
        }
        return healedPlayer;
    }
    
    public void damageCalc(Player target){
        int mafiaHpGreaterThanZero = 0;
        for(int i=0;i<randomList.size();i++){
            if(randomList.get(i).getPlayerType().equals("Mafia")){
                if(randomList.get(i).getPlayerHP()>0){
                    mafiaHpGreaterThanZero+=1;
                }
            }
        }
        double initialHP = target.getPlayerHP();
        if(mafiaHpGreaterThanZero==0){
            target.setPlayerHP(initialHP);
        }
        else{
            double ratio = initialHP/mafiaHpGreaterThanZero;
            int mafiaWithLessHP = 0;
            for(int i=0;i<randomList.size();i++){
                if(randomList.get(i).getPlayerType().equals("Mafia")){
                    if(randomList.get(i).getPlayerHP()>0 && randomList.get(i).getPlayerHP()<ratio){
                        initialHP = initialHP - randomList.get(i).getPlayerHP();
                        randomList.get(i).setPlayerHP(0);
                        mafiaWithLessHP+=1;
                    }      
                }
            }
            if(mafiaWithLessHP==0){
                for(int i=0;i<randomList.size();i++){
                    if(randomList.get(i).getPlayerType().equals("Mafia")){
                        if(randomList.get(i).getPlayerHP()>0){
                            initialHP = initialHP - ratio;
                            randomList.get(i).setPlayerHP(randomList.get(i).getPlayerHP()-ratio);
                        }      
                    }
                }
            }
            else{
                double newRatio = initialHP/mafiaWithLessHP;
                for(int i=0;i<randomList.size();i++){
                    if(randomList.get(i).getPlayerType().equals("Mafia")){
                        if(randomList.get(i).getPlayerHP()>0){
                            initialHP = initialHP - newRatio;
                            randomList.get(i).setPlayerHP(randomList.get(i).getPlayerHP()-newRatio);
                        }      
                    }
                }
            }
            if(initialHP<=0){
                target.setPlayerHP(0);
                target.setIsAlive(false);
                if(target.getPlayerType().compareTo("Commoner")==0){
                    this.numOfCommoners = this.numOfCommoners - 1;
                }
                else if(target.getPlayerType().compareTo("Healer")==0){
                    this.numOfHealers = this.numOfHealers - 1;
                }
                else{
                    this.numOfDetectives = this.numOfDetectives - 1;
                }
                this.numOfPlayers = this.numOfPlayers - 1;
            }
            else{
                target.setPlayerHP(initialHP);
            }
        }
        /*
        if(healedPlayer!=null){
            healedPlayer.setPlayerHP(healedPlayer.getPlayerHP()+500);
        }
        if(target.getPlayerHP()==0){
            target.setIsAlive(false);
            int index = 0;
            for(int i=0;i<randomList.size();i++){
                if(target.getPlayerID()==randomList.get(i).getPlayerID()){
                    index = i;
                }
            }
            System.out.println("Player"+target.getPlayerID()+ " has died.");
            if(target.getPlayerType().compareTo("Commoner")==0){
                this.numOfCommoners = this.numOfCommoners - 1;
            }
            else if(target.getPlayerType().compareTo("Healer")==0){
                this.numOfHealers = this.numOfHealers - 1;
            }
            else{
                this.numOfDetectives = this.numOfDetectives - 1;
            }
            this.numOfPlayers = this.numOfPlayers - 1;
            randomList.remove(index);
        }
        else{
            System.out.println("No one died.");
        }
        */
        
    }
    
    public void healPlayer(Player healedPlayer){
        if(healedPlayer!=null){
            healedPlayer.setPlayerHP(healedPlayer.getPlayerHP()+500);
        }
    }
    
    public void targetPlayerStatus(Player target){
        if(target.getPlayerHP()==0){
            int index = 0;
            for(int i=0;i<randomList.size();i++){
                if(target.getPlayerID()==randomList.get(i).getPlayerID()){
                    index = i;
                }
            }
            System.out.println("Player"+target.getPlayerID()+ " has died.");
            
            randomList.remove(index);
        }
        else{
            target.setIsAlive(true);
            System.out.println("No one died.");
            if(target.getPlayerType().compareTo("Commoner")==0){
                this.numOfCommoners = this.numOfCommoners + 1;
            }
            else if(target.getPlayerType().compareTo("Healer")==0){
                this.numOfHealers = this.numOfHealers + 1;
            }
            else{
                this.numOfDetectives = this.numOfDetectives + 1;
            }
            this.numOfPlayers = this.numOfPlayers + 1;
        }
    }
    
    public boolean isVoteOutPossible(Player detectedPlayer){
        if(detectedPlayer==null){
            return true;
        }
        else{
            if(detectedPlayer.getPlayerType().equals("Mafia")){
                this.numOfMafias = this.numOfMafias - 1;
                int playerID = detectedPlayer.getPlayerID();
                detectedPlayer.setIsAlive(false);
                int index = 0;
                for(int i=0;i<this.randomList.size();i++){
                    if(this.randomList.get(i).getPlayerID()==playerID){
                        index = i;
                    }
                }
                System.out.println("Player"+playerID+" has been voted out.");
                this.randomList.remove(index);
                this.numOfPlayers = this.numOfPlayers - 1;
                return false;
            }
            else{
                return true;
            }
        }
    }
    
    public void playerVote(int playerID){
        for(int i=0;i<randomList.size();i++){
            if(randomList.get(i).getPlayerID()==playerID){
                randomList.get(i).setVotes(randomList.get(i).getVotes()+1);
            }
        }
    }
    
    public void voteRandomly(int userID){ 
        Random random = new Random();
        for(int i=0;i<randomList.size();i++){
            if(randomList.get(i).getPlayerID()!=userID){
                int randomVal = random.nextInt(this.getRandomList().size());
                this.getRandomList().get(randomVal).setVotes(this.getRandomList().get(randomVal).getVotes()+1);
            }
        }
    }
    
    public boolean voteOut(){
        PriorityQueue<Player> votesInDescendingOrder = new PriorityQueue<>(new votesComparator());
        for(int i=0;i<randomList.size();i++){
            votesInDescendingOrder.add(randomList.get(i));
        }
        Player top = votesInDescendingOrder.poll();
        Player second = votesInDescendingOrder.poll();
        boolean isPossible = false;
        if(top.getVotes()==second.getVotes()){
            isPossible = false;
            System.out.println("voting tied");
        }
        else{
            top.setIsAlive(false);
            int playerID = top.getPlayerID();
            int index = 0;
            for(int i=0;i<randomList.size();i++){
                if(randomList.get(i).getPlayerID()==playerID){
                    index=i;
                }
            }
            System.out.println("Player"+playerID+" has been voted out.");
            
            if(top.getPlayerType().compareTo("Mafia")==0){
                this.numOfMafias = this.numOfMafias - 1;
            }
            
            else if(top.getPlayerType().compareTo("Commoner")==0){
                this.numOfCommoners = this.numOfCommoners - 1;
            }
            else if(top.getPlayerType().compareTo("Healer")==0){
                this.numOfHealers = this.numOfHealers - 1;
            }
            else{
                this.numOfDetectives = this.numOfDetectives - 1;
            }
            this.numOfPlayers = this.numOfPlayers - 1;
            randomList.remove(index);
            
            isPossible = true;            
        }
        this.refreshVotes();
        return isPossible;
    }
    
    public void refreshVotes(){
        for(int i=0;i<randomList.size();i++){
            randomList.get(i).setVotes(0);
        }
    }
    
    public void gameOver(Player user){
        int totalNonMafias = this.numOfCommoners+this.numOfDetectives+this.numOfHealers;
        
        if(this.numOfMafias!=totalNonMafias){
            if(this.numOfMafias==0){
                System.out.println("The Mafias have lost.");
            }
            else if(this.numOfMafias!=0){
                System.out.println("The Mafias have won.");
            }
        }
        else{
            System.out.println("The Mafias have won.");
        }
        int c = 0;
        for(int i=0;i<playersList.size();i++){
            if(playersList.get(i).getPlayerType().equals("Mafia")){
                if(i==playersList.size()-1){
                    if(playersList.get(i).getPlayerID()==user.getPlayerID()){
                        System.out.print("Player" + playersList.get(i).getPlayerID() + "[USER]" + " ");
                    }
                    else{
                        System.out.print("Player" + playersList.get(i).getPlayerID() +" ");
                    }
                }
                else{
                    if(playersList.get(i).getPlayerID()==user.getPlayerID()){
                        System.out.print("Player" + playersList.get(i).getPlayerID() + "[USER]" +", ");
                    }
                    else{
                        System.out.print("Player" + playersList.get(i).getPlayerID() +", ");
                    }
                }
                c+=1;
                
            }
        }
        if(c==1){
            System.out.println(" was Mafia.");
        }
        else{
            System.out.println(" were Mafia.");
        }
        
        c=0;
        for(int i=0;i<playersList.size();i++){
            if(playersList.get(i).getPlayerType().equals("Detective")){
                if(i==playersList.size()-1){
                    if(playersList.get(i).getPlayerID()==user.getPlayerID()){
                        System.out.print("Player" + playersList.get(i).getPlayerID() + "[USER]" + " ");
                    }
                    else{
                        System.out.print("Player" + playersList.get(i).getPlayerID() +" ");
                    }
                }
                else{
                    if(playersList.get(i).getPlayerID()==user.getPlayerID()){
                        System.out.print("Player" + playersList.get(i).getPlayerID() + "[USER]" +", ");
                    }
                    else{
                        System.out.print("Player" + playersList.get(i).getPlayerID() +", ");
                    }
                }
                c+=1;
                
            }
        }
        if(c==1){
            System.out.println(" was Detective.");
        }
        else{
            System.out.println(" were Detective.");
        }
        
        c=0;
        for(int i=0;i<playersList.size();i++){
            if(playersList.get(i).getPlayerType().equals("Healer")){
                if(i==playersList.size()-1){
                    if(playersList.get(i).getPlayerID()==user.getPlayerID()){
                        System.out.print("Player" + playersList.get(i).getPlayerID() + "[USER]" + " ");
                    }
                    else{
                        System.out.print("Player" + playersList.get(i).getPlayerID() +" ");
                    }
                }
                else{
                    if(playersList.get(i).getPlayerID()==user.getPlayerID()){
                        System.out.print("Player" + playersList.get(i).getPlayerID() + "[USER]" +", ");
                    }
                    else{
                        System.out.print("Player" + playersList.get(i).getPlayerID() +", ");
                    }
                }
                c+=1;
                
            }
        }
        if(c==1){
            System.out.println(" was Healer.");
        }
        else{
            System.out.println(" were Healer.");
        }
        
        
        c=0;
        for(int i=0;i<playersList.size();i++){
            if(playersList.get(i).getPlayerType().equals("Commoner")){
                if(i==playersList.size()-1){
                    if(playersList.get(i).getPlayerID()==user.getPlayerID()){
                        System.out.print("Player" + playersList.get(i).getPlayerID() + "[USER]" + " ");
                    }
                    else{
                        System.out.print("Player" + playersList.get(i).getPlayerID() +" ");
                    }
                }
                else{
                    if(playersList.get(i).getPlayerID()==user.getPlayerID()){
                        System.out.print("Player" + playersList.get(i).getPlayerID() + "[USER]" +", ");
                    }
                    else{
                        System.out.print("Player" + playersList.get(i).getPlayerID() +", ");
                    }
                }
                c+=1;
                
            }
        }
        if(c==1){
            System.out.println(" was Commoner.");
        }
        else{
            System.out.println(" were Commoner.");
        }
        
    }
}
