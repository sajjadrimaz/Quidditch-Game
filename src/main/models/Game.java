package src.main.models;


import java.util.Arrays;
import java.util.HashMap;

public class Game {

    private HashMap<Team, Integer> scoreBoard;
    private static int gameCount;

    private static final Integer QUAFFLE_POINTS = 10;
    private static final Integer SNITCH_POINTS = 150;

    public Game(Team home, Team away) {
        this.scoreBoard = new HashMap<Team, Integer>();
        this.scoreBoard.put(new Team(home), 0);
        this.scoreBoard.put(new Team(away), 0);
        gameCount++;
    }

    public Integer getScore(Team team) {
        return this.scoreBoard.get(team);
    }

    public void setScore(Team team, Integer score) {
        if (team == null){
            throw new IllegalArgumentException("Cannot add null to scoreboard!");
        }
        this.scoreBoard.put(team, score);
    }

    public Team getTeam(String name) {

        return this.scoreBoard.keySet().stream()
                .filter((key) -> key.getHouse().equals(name))
                .findFirst()
                .orElse(null);

    }

    public static int getGameCount() {
        return gameCount;
    }

    public String getPlaceholder(String play) {
        return play.substring(play.indexOf("<") + 1, play.indexOf(">"));
    }

    public String replacePlaceholder(String play, String placeholder, String value){  
        return play.replace("<"+placeholder+">", value);
    }

    public static Integer getQuafflePoints() {
        return QUAFFLE_POINTS;
    }

    public static Integer getSnitchPoints() {
        return SNITCH_POINTS;
    }

    public void quaffleScore(Team team){
        this.setScore(team, getScore(team) + QUAFFLE_POINTS);
    }

    public void catchSnitch(Team team){
        this.setScore(team, getScore(team) + SNITCH_POINTS);
    }

    public String simulate(String play){
        String placeholder = getPlaceholder(play);
        Team team = getRandomTeam();

        if (placeholder.equals(Team.getPositionChaser())){
            quaffleScore(team);
            String chaser = team.getChasers()[random(team.getChasers().length)];
            return replacePlaceholder(play, placeholder, chaser);
        }else if (placeholder.equals(Team.getPositionSeeker())){
            catchSnitch(team);
            return replacePlaceholder(play, placeholder, team.getSeeker());
        }else{
            return replacePlaceholder(play, placeholder, team.getKeeper());
        }
    }

    public Team getRandomTeam(){
        Object[] teams = this.scoreBoard.keySet().toArray();
        return (Team) teams[random(teams.length)];
    }

    public int random(int range){
        return (int) (Math.random() * range);
    }

}
