package models;

import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimal extends Wildlife implements DatabaseManagement {
    public static final String ANIMAL_TYPE = "endangered-animal";


    public EndangeredAnimal(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.type = ANIMAL_TYPE;
        this.age = age;


    }



    @Override
    public boolean equals(Object otherAnimal ){
        if (!(otherAnimal instanceof EndangeredAnimal)) {
            return false;
        } else {
            EndangeredAnimal newAnimal = (EndangeredAnimal) otherAnimal;
            return this.getAge().equals( newAnimal.getAge()) &&
                    this.getHealth().equals(newAnimal.getHealth()) &&
                    this.getAge().equals(newAnimal.getAge());
        }
    }

    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM animals WHERE type='endangered-animal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }
    public static EndangeredAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            EndangeredAnimal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return animal;
        }
    }

}