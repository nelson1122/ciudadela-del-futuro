package com.constructoressas.ciudadeladelfuturo.controllers;

import com.constructoressas.ciudadeladelfuturo.entities.CompraMaterial;
import com.constructoressas.ciudadeladelfuturo.services.CompraMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping(value = "/compraMaterial")
public class CompraMaterialController {

    @Autowired
    CompraMaterialService service;

    @PostMapping("/ingresar")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> ingresarMaterial(@RequestBody CompraMaterial compraMaterial) {
        return new ResponseEntity<>(service.ingresarMaterial(compraMaterial), OK);
    }
}
