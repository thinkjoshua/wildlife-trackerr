package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animals testAnimals = new Animals("Jaguar");
        assertEquals(true, testAnimals instanceof Animals);
    }
    @Test
    public void getName_animalInstantiatesWithName_Jaguar() {
        Animals testAnimals = new Animals("Jaguar");
        assertEquals("Jaguar", testAnimals.getName());
    }
    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Animals firstAnimals = new Animals("Jaguar");
        Animals anotherAnimals = new Animals("Jaguar");
        assertTrue(firstAnimals.equals(anotherAnimals));
    }
    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        Animals testAnimal = new Animals("Jaguar");
        testAnimal.save();
        assertTrue(Animals.all().get(0).equals(testAnimal));
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animals firstAnimals = new Animals("Jaguar");
        firstAnimals.save();
        Animals secondAnimals = new Animals("Giraffe");
        secondAnimals.save();
        assertEquals(true, Animals.all().get(0).equals(firstAnimals));
        assertEquals(true, Animals.all().get(1).equals(secondAnimals));
    }
    @Test
    public void save_assignsIdToAnimal() {
        Animals testAnimals = new Animals("Jaguar");
        testAnimals.save();
        Animals savedAnimals = Animals.all().get(0);
        assertEquals(testAnimals.getId(), savedAnimals.getId());
    }
    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animals firstAnimals = new Animals("Jaguar");
        firstAnimals.save();
        Animals secondAnimals = new Animals("Giraffe");
        secondAnimals.save();
        assertEquals(Animals.find(secondAnimals.getId()), secondAnimals);
    }

//    @Test
//    public void getAnimals_retrievesAllAnimalssFromDatabase_animalsList() {
//        Animal testAnimal = new Animal("Jaguar");
//        testAnimal.save();
//        Sighting firstSighting  = new Sighting("Limuru", testAnimal.getId());
//        testAnimal.save();
//        Sighting secondSighting = new Sighting("Nairobi", testAnimal.getId());
//        testAnimal.save();
//        Object[] monsters = new Object[] { firstSighting, secondSighting };
//        assertTrue(testAnimal.getSightings().containsAll(Arrays.asList(monsters)));
//    }
//    @Test
//    public void getSighting_retrievesAllSightingFromDatabase_sightingList() {
//        Animal lucky = new Animal("Lucky");
//        lucky.save();
//        Sighting nairobi = new Sighting();
//        nairobi.save();
//        Sighting kisumu = new Sighting();
//        kisumu.save();
//        Sighting[] sightings = {nairobi, kisumu};
//        assertTrue(lucky.getSighting().containsAll(Arrays.asList(sighting)));
//    }


}