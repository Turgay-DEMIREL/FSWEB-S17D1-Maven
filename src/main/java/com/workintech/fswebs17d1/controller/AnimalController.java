package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/workintech/animal")

public class AnimalController {
    private Map<Integer, Animal> animals;

    @Value("${project.developer.fullname}")
    private String courseName;

    @Value("${course.name}")
    private String developerName;

    @PostConstruct
    public void loadAll(){
        System.out.println("PostConstruct çalıştı.");
        this.animals=new HashMap<>();
        this.animals.put(1,new Animal(1,"maymun"));
    }

    @GetMapping("/config")
    public String getCustomConfigValues(){
        return developerName + " -- "+courseName;
    }

    @GetMapping
    public List<Animal> getAnimals(){
        System.out.println("----animals get all triggered!");
        return new ArrayList<>(animals.values());
    }
    //http://localhost:8585/workintech/animal/99
    @GetMapping("{id}")
    public Animal getAnimal(@PathVariable("id") int id){
       if(id<0) {
           System.out.println("id cannnot be less then zero!!");
           return null;
       }
        return this.animals.get(id);
    }
    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        this.animals.put(animal.getId(),animal);
    }
    @PutMapping("{id}")
    public Animal updateAnimal(@PathVariable("id") int id,@RequestBody Animal newAnimal){
        this.animals.replace(id,newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable("id") int id){
        this.animals.remove(id);
    }
}
