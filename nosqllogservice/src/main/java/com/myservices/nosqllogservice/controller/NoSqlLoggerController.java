package com.myservices.nosqllogservice.controller;

import com.myservices.nosqllogservice.exceptions.ApiException;
import com.myservices.nosqllogservice.model.Logs;
import com.myservices.nosqllogservice.repository.CassandraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/nosqllogging")
@Validated
public class NoSqlLoggerController {

    @Autowired
    CassandraRepo cassandraRepo;

    @GetMapping("/getlogs")
    @ResponseStatus(HttpStatus.OK)
    public List<Logs> getlogs() {
        try {
            List<Logs> resultlog = cassandraRepo.findAll();
            return resultlog;
        }
        catch (Exception ex){
            throw new ApiException("Cannot retrieve result from storage");
        }
    }

    @GetMapping("/getlog/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Logs getlogs(@PathVariable Integer id) {

        Logs log = cassandraRepo.findById(id)
                .orElseThrow(()->new ApiException("Item not found on storage"));
        return log;
    }

    @PostMapping("/writelog")
    public ResponseEntity<String> writelog(@Valid @RequestBody List<Logs> log) {
        try {
            List<Logs> resultlog = cassandraRepo.saveAll(log);
            return new ResponseEntity<>("Logs inserted", HttpStatus.CREATED);
        }
        catch (Exception ex){
            throw new ApiException("Save Failed: " + ex.getLocalizedMessage());
        }
    }
}
