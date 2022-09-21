package com.cricket;

//class PlayerNameComparator implements Comparator<Player>{
//
//    @Override
//    public int compare(Player player1, Player player2) {
//        return player1.getName().compareTo(player2.getName());
//    }
//}



//POJO class for player to be stored
public class Player implements Comparable<Player>
{
    private Integer jerseyNumber;
    private String name;
    private String type;
    private Double averageScore;
    private Integer runsScored;
    private Integer matchesPlayed;
    private Integer wicketsTaken;
    private Integer outsOnZero;

    public Player() {
    }

    public Player(Integer jerseyNumber, String name, String type, Double averageScore, Integer runsScored, Integer matchesPlayed, Integer wicketsTaken, Integer outsOnZero) {
        this.jerseyNumber = jerseyNumber;
        this.name = name;
        this.type = type;
        this.averageScore = averageScore;
        this.runsScored = runsScored;
        this.matchesPlayed = matchesPlayed;
        this.wicketsTaken = wicketsTaken;
        this.outsOnZero = outsOnZero;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(Integer runsScored) {
        this.runsScored = runsScored;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Integer getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(Integer wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public Integer getOutsOnZero() {
        return outsOnZero;
    }

    public void setOutsOnZero(Integer outsOnZero) {
        this.outsOnZero = outsOnZero;
    }

	@Override
	public int compareTo(Player o) {
		return (int)(this.averageScore - o.getAverageScore());
	}
}