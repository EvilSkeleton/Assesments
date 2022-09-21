package com.cricket;

import java.util.*;
import java.util.stream.Collectors;

public class TeamMaker {
    Map<String,Player> allPlayers;
    Map<String,Player> selectedTeam;

    public TeamMaker(Map<String, Player> allPlayers, Map<String, Player> selectedTeam) {
        this.allPlayers = allPlayers;
        this.selectedTeam = selectedTeam;
    }

    public void displaySinglePlayer(Player player){
        System.out.println(player.getJerseyNumber() + "\t" + player.getName() + "\t" + player.getMatchesPlayed() + "\t" + player.getRunsScored() + "\t" + player.getWicketsTaken() +"\t" + player.getOutsOnZero() + "\t" + player.getType());
    }

    public void displayAllPlayers(){
        System.out.println("All Players: ");
        System.out.println("Jersey-Number\tName\tMatches-Played\tRuns-Scored\tWickets-Taken\tDucks\tPlayer-type");
//        allPlayers.values().stream().collect(Collectors.toList()).stream().sorted(new PlayerAverageScoreComparator()).forEach(player -> displaySinglePlayer(player));
    }

    public boolean acceptAllPlayerDetails(){

        allPlayers.clear();
        selectedTeam.clear();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 20 players information ensuring at least 3 bowlers and 1 wicketkeeper.");
        int numberOfBowlers = 0;
        int numberOfWicketkeeper = 0;
        for(int i =0 ; i < 2; i++)
        {
            Player player = new Player();
            System.out.println("Enter player jersey number.");
            player.setJerseyNumber(sc.nextInt());
            sc.nextLine();
            System.out.println("Enter player name.");
            player.setName(sc.nextLine());
            System.out.println("Enter number of matches played.");
            player.setMatchesPlayed(sc.nextInt());
            System.out.println("Enter number of runs scored.");
            player.setRunsScored(sc.nextInt());
            player.setAverageScore((double) (player.getRunsScored()/ player.getMatchesPlayed()));
            System.out.println("Enter number of wickets taken.");
            player.setWicketsTaken(sc.nextInt());
            System.out.println("Enter number of outs on zero.");
            player.setOutsOnZero(sc.nextInt());
            System.out.println("Enter type of player-  1: Bowler , 2: Batsman , 3:Wicket-Keeper , 4: All-Rounder");
            switch (sc.nextInt()){
                case 1:
                    player.setType("Bowler");
                    numberOfBowlers++;
                    break;
                case 2:
                    player.setType("Batsman");
                    break;
                case 3:
                    player.setType("Wicket-Keeper");
                    numberOfWicketkeeper++;
                    break;
                case 4:
                    player.setType("All-Rounder");
                    break;
                default:
                    System.out.println("Player is set as batsman.");
                    player.setType("Batsman");
                    break;

            }
            allPlayers.put(player.getName(),player);
        }

        sc.close();
        if(numberOfBowlers>=3 && numberOfWicketkeeper>=1)
            return true;
        else
            return false;
    }

    public void setAllPlayers(){
        Player player1 = new Player(1,"Shikhar Dhawan","Batsman",0.0,70500,400,25,32);
        Player player2 = new Player(2,"Virat Kohli","Batsman",0.0,59500,400,25,32);
        Player player3 = new Player(3,"Ravindra Jadeja","All-Rounder",0.0,10500,400,25,32);
        Player player4 = new Player(4,"Mohammed Shami","Bowler",0.0,15500,400,25,32);
        Player player5 = new Player(5,"Manish Pandey","Bowler",0.0,13500,400,25,32);
        Player player6 = new Player(6,"Jasprit Bumrah","Bowler",0.0,38500,400,25,32);
        Player player7 = new Player(19,"Mayank Agarwal","Batsman",0.0,29500,400,25,32);
        Player player8 = new Player(8,"Shreyas Iyer","Batsman",0.0,91500,400,25,32);
        Player player9 = new Player(9,"Yuzvendra Chahal","Bowler",0.0,85500,400,25,32);
        Player player10 = new Player(10,"KL Rahul","Wicket-Keeper",0.0,75500,400,25,32);
        Player player11 = new Player(11,"Hardik Pandya","Batsman",0.0,92500,400,25,32);
        Player player12 = new Player(12,"Kuldeep Yadav","Bowler",0.0,16500,400,25,32);
        Player player13 = new Player(13,"Shardul Thakur","Bowler",0.0,7500,400,25,32);
        Player player14 = new Player(14,"Navdeep Saini","Bowler",0.0,8500,400,25,32);
        Player player15 = new Player(15,"Shubman Gill","Batsman",0.0,72500,400,25,32);
        Player player16 = new Player(16,"Ajinkya Rahane","Batsman",0.0,62500,400,25,32);
        Player player17 = new Player(17,"Ravichandran Ashwin","Bowler",0.0,22500,400,25,32);
        Player player18 = new Player(18,"Umesh Yadav","Bowler",0.0,32500,400,25,32);
        Player player19 = new Player(7,"MS Dhoni","Wicket-Keeper",0.0,98500,400,25,32);
        Player player20 = new Player(20,"Hanuma Vihari","Batsman",0.0,52500,400,25,32);

        allPlayers.put(player1.getName(),player1);
        allPlayers.put(player2.getName(),player2);
        allPlayers.put(player3.getName(),player3);
        allPlayers.put(player4.getName(),player4);
        allPlayers.put(player5.getName(),player5);
        allPlayers.put(player6.getName(),player6);
        allPlayers.put(player7.getName(),player7);
        allPlayers.put(player8.getName(),player8);
        allPlayers.put(player9.getName(),player9);
        allPlayers.put(player10.getName(),player10);
        allPlayers.put(player11.getName(),player11);
        allPlayers.put(player12.getName(),player12);
        allPlayers.put(player13.getName(),player13);
        allPlayers.put(player14.getName(),player14);
        allPlayers.put(player15.getName(),player15);
        allPlayers.put(player16.getName(),player16);
        allPlayers.put(player17.getName(),player17);
        allPlayers.put(player18.getName(),player18);
        allPlayers.put(player19.getName(),player19);
        allPlayers.put(player20.getName(),player20);

        Iterator<Map.Entry<String,Player>> iterator = allPlayers.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Player> playerEntry = iterator.next();
            Player player = playerEntry.getValue();
            player.setAverageScore((double) (player.getRunsScored()/player.getMatchesPlayed()));
        }

    }

    public void updatePlayerByName() throws PlayerNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Player name to be updated.");
        String playerName = sc.nextLine();


        Player player = allPlayers.get(playerName);
        if(player == null){
            PlayerNotFoundException playerNotFoundException = new PlayerNotFoundException("Player with same name not found.");
            sc.close();
            throw (playerNotFoundException);
        }

        allPlayers.remove(playerName);

        System.out.println("Enter updated player jersey number.");
        player.setJerseyNumber(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter updated player name.");
        player.setName(sc.nextLine());
        System.out.println("Enter updated number of matches played.");
        player.setMatchesPlayed(sc.nextInt());
        System.out.println("Enter updated number of runs scored.");
        player.setRunsScored(sc.nextInt());
        System.out.println("Enter updated number of wickets taken.");
        player.setWicketsTaken(sc.nextInt());
        System.out.println("Enter updated number of outs on zero.");
        player.setOutsOnZero(sc.nextInt());
        System.out.println("Enter updated type of player-  1: Bowler , 2: Batsman , 3:Wicket-Keeper , 4: All-Rounder");
        switch (sc.nextInt()){
            case 1:
                player.setType("Bowler");
                break;
            case 2:
                player.setType("Batsman");
                break;
            case 3:
                player.setType("Wicket-Keeper");
                break;
            case 4:
                player.setType("All-Rounder");
                break;
            default:
                System.out.println("Player is set as batsman.");
                player.setType("Batsman");
                break;
        }

        allPlayers.put(player.getName(),player);
        sc.close();
    }

    public void selectTeam(){
        int numberOfBowlers =0;
        int numberOfWicketkeeper = 0;
        int selectedPlayers = 0;
//        List<Player> playerList = allPlayers.values().stream().collect(Collectors.toList()).stream().sorted(new PlayerAverageScoreComparator()).collect(Collectors.toList());

//        Iterator<Player> iterator = playerList.listIterator();
//        while(iterator.hasNext() && selectedPlayers<=11){
//            Player player = iterator.next();
//            if(selectedPlayers<7){
//                if(player.getType().equals("Wicket-Keeper"))
//                    numberOfWicketkeeper++;
//                if(player.getType().equals("Bowler"))
//                    numberOfBowlers++;
//                selectedTeam.put(player.getName(),player);
//                selectedPlayers++;
//            }
//            else{
//                if(numberOfBowlers>=3 && numberOfWicketkeeper>=1){
//                    selectedTeam.put(player.getName(),player);
//                    selectedPlayers++;
//                }
//                else if(numberOfWicketkeeper==0 && player.getType().equals("Wicket-Keeper")){
//                    numberOfWicketkeeper++;
//                    selectedPlayers++;
//                    selectedTeam.put(player.getName(),player);
//                }
//                else if(numberOfBowlers<3 && player.getType().equals("Bowler")){
//                    numberOfBowlers++;
//                    selectedPlayers++;
//                    selectedTeam.put(player.getName(),player);
//                }
//                else if(numberOfBowlers<3 && numberOfWicketkeeper==0 && (player.getType().equals("Wicket-Keeper") || player.getType().equals("Bowler")))
//                {
//                    selectedPlayers++;
//                    selectedTeam.put(player.getName(),player);
//                    if(player.getType().equals("Wicket-Keeper"))
//                        numberOfWicketkeeper++;
//                    if(player.getType().equals("Bowler"))
//                        numberOfBowlers++;
//                }
//            }
//        }

        displayFinalTeam();
    }

    public void displayFinalTeam(){
        System.out.println("Selected Players: ");
        System.out.println("Jersey-Number\tName\tMatches-Played\tRuns-Scored\tWickets-Taken\tDucks\tPlayer-type");
//        selectedTeam.values().stream().collect(Collectors.toList()).stream().sorted(new PlayerNameComparator()).forEach(player -> displaySinglePlayer(player));
    }
}



/*


Shikhar Dhawan	Batsman
Virat Kohli	Batsman
Ravindra Jadeja	All Rounder
Mohammed Shami	Bowler
Manish Pandey	Batsman
Jasprit Bumrah	Bowler
Mayank Agarwal	Batsman
Shreyas Iyer	Batsman
Yuzvendra Chahal	Bowler
KL Rahul	Wicketkeeper
Hardik Pandya	Batsman
Kuldeep Yadav	Bowler
Shardul Thakur	Bowler
Navdeep Saini	Bowler
Shubman Gill	Batsman
Ajinkya Rahane	Batsman
Ravichandran Ashwin	Bowler
Umesh Yadav	Bowler
MS Dhoni   Wicketkeeper
Hanuma Vihari	Batsman
 */