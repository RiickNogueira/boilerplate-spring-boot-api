package br.com.boilerplate.controller;

import br.com.boilerplate.model.Test;
import br.com.boilerplate.model.dto.TestFormDTO;
import br.com.boilerplate.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Test>> findAll(){
        List<Test> list = testService.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Test> findById(@PathVariable Long id){
        Test test = testService.findById(id);

        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Test> save(@RequestBody @Valid final TestFormDTO formDTO){
        Test test = testService.convertToModel(formDTO);

        Test savedTest = testService.save(test);

        return new ResponseEntity<>(savedTest, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        testService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
