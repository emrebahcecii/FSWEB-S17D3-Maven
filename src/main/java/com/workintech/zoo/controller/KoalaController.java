package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KoalaController {
    private Map<Integer, Koala> koalas;

    public KoalaController(){
        koalas = new HashMap<>();
    }

    @GetMapping("/workintech/koalas")
    public List<Koala> getKoala(){
        return koalas.values().stream().toList();
    }
    @GetMapping("/workintech/koalas/{id}")
    public Koala getKoalaById(@PathVariable int id){
        Koala koala = koalas.get(id);
        if (koala == null) {
            throw new ZooException("Koala not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return koala;
    }
    @PostMapping("/workintech/koalas")
    public Koala addKoalas(@RequestBody Koala koala){
        koalas.put(koala.getId(),koala);
        return koala;
    }
    @PutMapping("/workintech/koalas/{id}")
    public Koala updateKoala(@PathVariable int id, @RequestBody Koala koala){
        koalas.put(id,koala);
        return koala;
    }
    @DeleteMapping("/workintech/koalas/{id}")
    public Koala deleteKoala(@PathVariable int id){
        return koalas.remove(id);
    }

}
