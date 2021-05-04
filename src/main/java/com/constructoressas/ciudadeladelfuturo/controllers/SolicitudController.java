package com.constructoressas.ciudadeladelfuturo.controllers;


import com.constructoressas.ciudadeladelfuturo.entities.Solicitud;
import com.constructoressas.ciudadeladelfuturo.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping(value = "/solicitud")
public class SolicitudController {

    @Autowired
    SolicitudService service;

    @PostMapping("/crear")
    public @ResponseBody
    ResponseEntity<?> ingresarMaterial(@RequestBody Solicitud solicitud) {
        return new ResponseEntity<>(service.crearSolicitud(solicitud), OK);
    }

}