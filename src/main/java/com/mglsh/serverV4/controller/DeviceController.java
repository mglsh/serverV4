package com.mglsh.serverV4.controller;

import com.mglsh.serverV4.model.Device;
import com.mglsh.serverV4.repository.DeviceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/devices"})

public class DeviceController {
    private DeviceRepository repository;


    DeviceController(DeviceRepository contactRepository) {

        this.repository = contactRepository;
    }


    @GetMapping
    public List findAll() {

        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Device> findById(@PathVariable long id){

        return repository.findById(id)

                .map(record -> ResponseEntity.ok().body(record))

                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Device create(@RequestBody Device device){

        return repository.save(device);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Device> update(@PathVariable("id") long id,

                                         @RequestBody Device device){

        return repository.findById(id)

                .map(record -> {

                    record.setName(device.getName());

                    record.setEmail(device.getEmail());

                    record.setPhone(device.getPhone());

                    Device updated = repository.save(record);

                    return ResponseEntity.ok().body(updated);

                }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        return repository.findById(id)

                .map(record -> {

                    repository.deleteById(id);

                    return ResponseEntity.ok().build();

                }).orElse(ResponseEntity.notFound().build());

    }
}

