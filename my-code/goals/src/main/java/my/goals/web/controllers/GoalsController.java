package my.goals.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.goals.web.dtos.CreateGoalForm;

@RestController
@RequestMapping("api/v1/goals")
public class GoalsController {


    @PostMapping("create")
    public void create(@RequestBody CreateGoalForm form) {
    }

    @GetMapping("find")
    public ResponseEntity<Object> findOne() {
        return null;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable("id") String id) {
        return null;
    }

}
