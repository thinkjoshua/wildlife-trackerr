import models.AllSightings;
import models.Animals;
import models.EndangeredAnimal;
import models.Sightings;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals-form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "animals-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal-sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("sightingS", AllSightings.getAll());
            model.put("animal", EndangeredAnimal.all());
            return new ModelAndView(model, "animal-sighting.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal-sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String animalName = request.queryParams("animal");
            String rangerName = request.queryParams("ranger");
            String location = request.queryParams("location");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String type = request.queryParams("type");

            if(type.equals("animal")){
                Animals animal = new Animals(animalName);
                animal.save();
                Sightings newSighting = new Sightings(animal.getId(),rangerName,location);
                newSighting.save();
            } else if(type.equals("endangered")){
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(animalName,health,age);
                endangeredAnimal.save();
                Sightings anotherSighting = new Sightings(endangeredAnimal.getId(), rangerName,location);
                anotherSighting.save();
            }


//            EndangeredAnimal endangeredAnimal = new EndangeredAnimal(animalName, type, health, age, location, ranger);
//            endangeredAnimal.save();
//            Sightings  sighting = new Sightings (endangeredAnimal.getId(), location, ranger);
//            sighting.save();



            List<AllSightings> allSightings = AllSightings.getAll();
            List<EndangeredAnimal> animals = EndangeredAnimal.all();
            model.put("sightings", allSightings);
            model.put("animals", animals);

            return new ModelAndView(model, "animal-sighting.hbs");
        }, new HandlebarsTemplateEngine());
    }
}