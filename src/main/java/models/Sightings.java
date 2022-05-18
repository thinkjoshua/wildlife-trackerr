package models;

import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Sightings {

    private int id;

    private int animalId;
    private String rangerName;
    private String location;
    private Timestamp lastSeen;


    public Sightings(int animalId, String rangerName, String location ){
        this.rangerName = rangerName;
        this.animalId = animalId;
        this.location = location;



    }



    public String getRangerName() {
        return rangerName;
    }
    public int getAnimalId() {
        return animalId;
    }

    public String getLocation() {
        return location;
    }
    public Timestamp getLastSeen() {
        return lastSeen;
    }
    public int getId() {
        return id;
    }
    @Override
    public boolean equals(Object otherSighting){
        if (!(otherSighting instanceof Sightings)) {
            return false;
        } else {
            Sightings newSighting = (Sightings) otherSighting;
            return this.getRangerName().equals(newSighting.getRangerName()) &&
                    this.getAnimalId() == newSighting.getAnimalId() &&
                    this.getLocation().equals(newSighting.getLocation());


        }
    }

    //    @Override
//    public int hashCode() {
//        return Objects.hash(getRangerName(),getAnimalId(), getLocation());


    //    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalId, location, rangerName, lastSeen) VALUES (:animalId, :location, :rangerName, now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("rangerName", this.rangerName)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }


    public static Sightings find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sightings sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sightings.class);
            return sighting;
        }
    }

}
