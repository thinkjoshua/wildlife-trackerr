package models;

import org.sql2o.Connection;

public abstract class Wildlife {
    public String name;
    public int id;
    public String health;
    public String age;
    public String type;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public String getType() {
        return type;
    }



    public void save(){
        try(Connection connect = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, health, age, type) VALUES (:name, :health, :age, :type)";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age",this.age)
                    .addParameter("type", this.type)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }
}
