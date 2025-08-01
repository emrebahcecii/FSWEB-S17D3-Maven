package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/kangaroos")
public class KangrooController {
    private final Map<Integer, Kangaroo> kangaroos = new HashMap<>();

    @GetMapping
    public List<Kangaroo> getKangaroos(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable int id){
        Kangaroo kangaroo = kangaroos.get(id);
        if(kangaroo == null){
            throw new ZooException("Kangaroo not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroo;
    }

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo){
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable int id, @RequestBody Kangaroo kangaroo){
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id){
        Kangaroo removed = kangaroos.remove(id);
        if (removed == null) {
            throw new ZooException("Kangaroo to delete not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return removed;
    }
}
