/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;


import java.util.List;
import java.util.Set;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author cristian
 */
@RestController("/")
public class CinemaAPIController {
    @Autowired
    private CinemaServices cinemaServices;
    @RequestMapping(value = "/cinemas",method = RequestMethod.GET)
    public ResponseEntity<?> controllerGetResourceCinema() throws ResourceNotFoundException {
        try {
            Set<Cinema> data = cinemaServices.getAllCinemas();
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException e) {
            throw  new ResourceNotFoundException(e.getMessage(),e);
        }
    }
    @RequestMapping( value = "/cinemas/{name}",method = RequestMethod.GET)
    public ResponseEntity<?> getCinemaByName(@PathVariable String name) throws ResourceNotFoundException {
        try {
            Cinema data = cinemaServices.getCinemaByName(name);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (CinemaException | CinemaPersistenceException e) {
            throw  new ResourceNotFoundException(e.getMessage(),e);
        }
    }
    @RequestMapping( value = "/cinemas/{name}/{date}",method = RequestMethod.GET)
    public ResponseEntity<?> getFunctionsByNameAndDate(@PathVariable String name,@PathVariable String date) throws ResourceNotFoundException {
        try {
            List<CinemaFunction> data = cinemaServices.getFunctionsByCinemaAndDate(name,date);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException e) {
            throw  new ResourceNotFoundException(e.getMessage(),e);
        }
    }
    @RequestMapping( value = "/cinemas/{name}/{date}/{moviename}",method = RequestMethod.GET)
    public ResponseEntity<?> getFunctionsByNameAndDateAndMovie(@PathVariable String name,@PathVariable String date,@PathVariable String moviename) throws ResourceNotFoundException {
        try {
            CinemaFunction data = cinemaServices.getFunctionByCinemaAndDateAndMovie(name,date,moviename);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException e) {
            throw  new ResourceNotFoundException(e.getMessage(),e);
        }
    }
    @RequestMapping(value = "/cinemas/{name}",method = RequestMethod.POST )
    public ResponseEntity<?> addFunctionToCinema(@PathVariable String name,@RequestBody CinemaFunction function) throws ResourceNotFoundException{
        try{
            CinemaFunction data = cinemaServices.addFunctionToCinema(name,function);
            return  new ResponseEntity<>(data,HttpStatus.CREATED);
        }catch (CinemaPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value = "/cinemas/{name}",method = RequestMethod.PUT )
    public ResponseEntity<?> updateFunctionByCinema(@PathVariable String name,@RequestBody CinemaFunction function) throws ResourceNotFoundException{
        try{
            CinemaFunction data = cinemaServices.updateFunctionByCinema(name,function);
            return new ResponseEntity<>(data,HttpStatus.CREATED);
        }catch (CinemaPersistenceException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }

}
