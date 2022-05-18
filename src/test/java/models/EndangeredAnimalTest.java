package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Endangered_instantiatesCorrectly_true() {
        EndangeredAnimal testEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        assertEquals(true, testEndangered instanceof EndangeredAnimal);
    }
    @Test
    public void Endangered_InstantiatesWithName_Elephant() {
        EndangeredAnimal testEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        assertEquals("Elephant", testEndangered.getName());
    }
    @Test
    public void Endangered_InstantiatesWithHealth_Ill() {
        EndangeredAnimal testEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        assertEquals("Ill", testEndangered.getHealth());
    }

    @Test
    public void Endangered_InstantiatesWithAge_Young() {
        EndangeredAnimal testEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        assertEquals("Young", testEndangered.getAge());
    }
    //
    @Test
    public void equals_returnsTrueIfRangerNameAreSame_true() {
        EndangeredAnimal testEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        EndangeredAnimal anotherEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        assertTrue(testEndangered.equals(anotherEndangered));
    }
    @Test
    public void save_insertsObjectIntoDatabase_Endangered() {
        EndangeredAnimal testEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        testEndangered.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(testEndangered));
    }
    @Test
    public void all_returnsAllInstancesOfEndangered_true() {
        EndangeredAnimal firstEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        firstEndangered.save();
        EndangeredAnimal secondEndangered = EndangeredAnimal.all().get(0);
        secondEndangered.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangered));
        assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangered));
    }
    @Test
    public void save_assignsIdToEndangered() {
        EndangeredAnimal unsavedEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        unsavedEndangered.save();
        EndangeredAnimal savedEndangered = EndangeredAnimal.all().get(0);
        assertEquals(savedEndangered.getId(), unsavedEndangered.getId());
    }
    @Test
    public void find_returnsEndangeredWithSameId_secondEndangered() {
        EndangeredAnimal firstEndangered = new EndangeredAnimal("Elephant", "Ill",  "Young");
        firstEndangered.save();
        EndangeredAnimal secondEndangered =  EndangeredAnimal.all().get(0);
        secondEndangered.save();
        assertEquals(EndangeredAnimal.find(secondEndangered.getId()), secondEndangered);
    }


}

