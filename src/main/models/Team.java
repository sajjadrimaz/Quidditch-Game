package src.main.models;

import java.util.Arrays;
import java.util.Objects;

public class Team {

    private static final String POSITION_CHASER = "chaser";
    private static final String POSITION_SEEKER = "seeker";
    private static final String POSITION_KEEPER = "keeper";

    private String house;
    private String keeper;
    private String seeker;
    private String[] chasers;

    public Team(String house, String keeper, String seeker, String[] chasers) {
        if (house == null || house.isBlank()) {
            throw new IllegalArgumentException("Invalid house!");
        }
        if (keeper == null || keeper.isBlank()) {
            throw new IllegalArgumentException("Invalid keeper");
        }
        if (seeker == null || seeker.isBlank()) {
            throw new IllegalArgumentException("Invalid seeker");
        }
        if (chasers.length != 3) {
            throw new IllegalArgumentException("Invalid chasers");
        }
        if (hasNull(chasers) || hasBlank(chasers)){
            throw new IllegalArgumentException("Illegal elements");
        }
        this.house = house;
        this.keeper = keeper;
        this.seeker = seeker;
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    public Team(Team source) {
        this.house = source.house;
        this.keeper = source.keeper;
        this.seeker = source.seeker;
        this.chasers = Arrays.copyOf(source.chasers, source.chasers.length);
        ;
    }

    public String getHouse() {
        return this.house;
    }

    public void setHouse(String house) {
        checkParam(house);
        this.house = house;
    }

    public String getKeeper() {
        return this.keeper;
    }

    public void setKeeper(String keeper) {
        checkParam(keeper);
        this.keeper = keeper;
    }

    public String getSeeker() {
        return this.seeker;
    }

    public void setSeeker(String seeker) {
        checkParam(seeker);
        this.seeker = seeker;
    }

    public String[] getChasers() {
        return Arrays.copyOf(this.chasers, this.chasers.length);
    }

    public void setChasers(String[] chasers) {
        if (chasers.length != 3 || hasNull(chasers) || hasBlank(chasers)) {
            throw new IllegalArgumentException("Invalid chasers");
        }
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    public static boolean hasNull(String[] chasers) {
        return Arrays.stream(chasers)
                .anyMatch((chaser) -> chaser == null);
    }

    public static boolean hasBlank(String[] chasers) {
        return Arrays.stream(chasers)
                .anyMatch((chaser) -> chaser.isBlank());
    }

    public static String getPositionChaser() {
        return POSITION_CHASER;
    }

    public static String getPositionSeeker() {
        return POSITION_SEEKER;
    }

    public static String getPositionKeeper() {
        return POSITION_KEEPER;
    }

    public void checkParam(String param) {
        if (param == null || param.isBlank()) {
            throw new IllegalArgumentException(param + " cannot be null or blank");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Team)) {
            return false;
        }
        Team team = (Team) o;
        return Objects.equals(house, team.house) && 
                Objects.equals(keeper, team.keeper) && 
                Objects.equals(seeker, team.seeker) && 
                Objects.equals(Arrays.toString(chasers), Arrays.toString(team.chasers));
    }

    @Override
    public int hashCode() {
        return Objects.hash(house, keeper, seeker, Arrays.toString(chasers));
    }

    @Override
    public String toString() {
        return "House: " + this.house + "\n" +
                "Keeper: " + this.keeper + "\n" +
                "Seeker: " + this.seeker + "\n" +
                "Chasers: " + Arrays.toString(this.chasers) + "\n";
    }

}
