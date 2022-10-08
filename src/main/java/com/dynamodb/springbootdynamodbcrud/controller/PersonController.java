package com.dynamodb.springbootdynamodbcrud.controller;

import com.dynamodb.springbootdynamodbcrud.entity.Person;
import com.dynamodb.springbootdynamodbcrud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @PostMapping
    public Person save(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable String id) {
        return repository.findById(id);
    }

    @GetMapping
    public List<Person> findAll() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable(value = "id") String id, @RequestBody Person person) {
        return repository.update(id, person);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") String id) {
        return repository.delete(id);
    }

}
