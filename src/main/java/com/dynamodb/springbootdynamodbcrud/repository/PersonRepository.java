package com.dynamodb.springbootdynamodbcrud.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.dynamodb.springbootdynamodbcrud.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    @Autowired
    private DynamoDBMapper dynamoDbMapper;


    public Person save(Person person) {
        dynamoDbMapper.save(person);
        return person;
    }

    public Person findById(String id) {
        return dynamoDbMapper.load(Person.class, id);
    }

    public List<Person> findAll() {
        return dynamoDbMapper.scan(Person.class, new DynamoDBScanExpression());
    }

    public String update(String id, Person person) {
        dynamoDbMapper.save(person,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                     new AttributeValue().withS(id)
                                )));
        return id;
    }

    public String delete(String id) {
        Person person = dynamoDbMapper.load(Person.class, id);
        dynamoDbMapper.delete(person);
        return "Person deleted successfully:: "+id;
    }
}
