package com.application.controller;

import com.application.model.Person;
import com.application.service.SocialMediaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/pairs")
@AllArgsConstructor
public class SMSController {

    private SocialMediaService socialMediaService;

    @GetMapping
    public ResponseEntity getPairs(){
        return new ResponseEntity<>(socialMediaService.getPairs(), OK);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody List<Person> persons) {
        socialMediaService.save(persons);
        return new ResponseEntity(CREATED);
    }
}
