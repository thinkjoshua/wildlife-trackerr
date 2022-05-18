package models;

import models.DatabaseRule;
import models.Sightings;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sightings testSighting = new Sightings(1, "Mike", "Zone A");
        assertEquals(true, testSighting instanceof Sightings);
    }

    @Test
    public void getRangerName_sightingInstantiatesWithrangerName_true() {
        Sightings testSighting = new Sightings(1, "Mike", "Zone A");
        assertEquals("Mike", testSighting.getRangerName());
    }
    @Test
    public void getAnimalId_sightingInstantiatesWithanimalId_true() {
        Sightings testSighting = new Sightings(1, "Mike", "Zone A");
        assertEquals(1, testSighting.getAnimalId());
    }
    @Test
    public void getLocation_sightingInstantiatesWithlocation_true() {
        Sightings testSighting = new Sightings(1, "Mike", "Zone A");
        assertEquals("Zone A", testSighting.getLocation());
    }
    @Test
    public void equals_returnsTrueIfRangerNameAreSame_true() {
        Sightings testSighting = new Sightings(1, "Mike", "Zone A");
        Sightings anotherSighting = new Sightings(1, "Mike","Zone A");
        assertTrue(testSighting.equals(anotherSighting));
    }
    @Test
    public void save_successfullyAddsSightingToDatabase_List() {
        Sightings testSighting = new Sightings(1, "Mike", "Zone A");
        testSighting.save();
        assertTrue(Sightings.all().get(0).equals(testSighting));
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Sightings firstSighting = new Sightings(1, "Mike", "Zone A");
        firstSighting.save();
        Sightings secondSighting = new Sightings(2, "Paul","Near the river");
        secondSighting.save();
        assertEquals(true, Sightings.all().get(0).equals(firstSighting));
        assertEquals(true, Sightings.all().get(1).equals(secondSighting));

    }
    @Test
    public void save_assignsIdToSighting() {
        Sightings testSighting = new Sightings(1, "Mike", "Zone A");
        testSighting.save();
        Sightings savedSighting = Sightings.all().get(0);
        assertEquals(savedSighting.getId(), testSighting.getId());
    }
    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Sightings firstSighting = new Sightings(1, "Mike", "Zone A");
        firstSighting.save();
        Sightings secondSighting =  Sightings.all().get(0);
        secondSighting.save();
        assertEquals(Sightings.find(secondSighting.getId()), secondSighting);
    }


}