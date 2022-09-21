package com.cricket;

import java.util.Comparator;

public class PlayerAverageScoreComparator implements Comparator<Player>{

    @Override
    public int compare(Player player1, Player player2) {
        return (int) (player1.getAverageScore() - player2.getAverageScore());
    }
}