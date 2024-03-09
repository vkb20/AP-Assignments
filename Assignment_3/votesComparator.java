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
public class votesComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        if(o1.getVotes()>o2.getVotes()){
            return -1;
        }
        else if(o1.getVotes()<o2.getVotes()){
            return 1;
        }
        else{
            return 0;
        }
    }
    
}
