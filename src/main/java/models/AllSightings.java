package models;

import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class AllSightings {
    public int id;
    public String name;
    public String health;
    public String type;
    public String age;
    private String location;
    private String rangerName;
    private int animalId;
    private Timestamp lastSeen;

    public AllSightings(String name, String health, String type, String age, String location, String rangerName, Timestamp lastSeen) {
        this.name = name;
        this.health = health;
        this.type = type;
        this.age = age;
        this.location = location;
        this.rangerName = rangerName;
        this.lastSeen = lastSeen;
    }
    public int getId() {
        return id;
    }
    public int getAnimalId() {
        return animalId;
    }
    public String getName() {
        return name;
    }
    public String getHealth() {
        return health;
    }
    public String getType() {
        return type;
    }
    public String getAge() {
        return age;
    }
    public String getLocation() {
        return location;
    }
    public String getRangerName() {
        return rangerName;
    }
    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public static List<AllSightings> getAll() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT animals.id,name,health,age,location,rangerName,type,lastSeen FROM animals INNER JOIN sightings ON sightings.animalId = animals.id ORDER BY lastSeen";
            return con.createQuery(sql)
//                    .addParameter("id", this.id)
                    .executeAndFetch(AllSightings.class);
        }
    }
}
