
package models;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;


public class Animals extends Wildlife implements DatabaseManagement{

    public static final String ANIMAL_TYPE = "animal";

//    private String name;
//    private int id;

    public Animals(String name){
        this.type = ANIMAL_TYPE;
        this.name = name;

    }
    //    public String getName() {
//        return name;
//    }
//    public int getId() {
//        return id;
//    }
    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animals)) {
            return false;
        } else {
            Animals newAnimal = (Animals) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }
    //    @Override
//    public int hashCode() {
//        return Objects.hash(getName());
//    }
//
//    @Override
//    public void save() {
//        try(Connection con = DB.sql2o.open()) {
//            String sql = "INSERT INTO animal (name) VALUES (:name)";
//            this.id = (int) con.createQuery(sql, true)
//                    .addParameter("name", this.name)
//                    .executeUpdate()
//                    .getKey();
//        }
//    }
    public static List<Animals> all() {
        String sql = "SELECT * FROM animals WHERE type = 'animal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
        }
    }
    public static Animals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animals animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animals.class);
            return animal;
        }
    }
    public static List<Object> getAnimals() {
        List<Object> allAnimals = new ArrayList<Object>();

        try(Connection con = DB.sql2o.open()) {
            String sqlFire = "SELECT * FROM animals WHERE id=:id AND type='animal';";
            List<Animals> animals = con.createQuery(sqlFire)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
            allAnimals.addAll(animals);

            String sqlWater = "SELECT * FROM animals WHERE id=:id AND type='endangered-animal';";
            List<EndangeredAnimal> endangered = con.createQuery(sqlWater)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
            allAnimals.addAll(endangered);
        }

        return allAnimals;
    }


}