package com.example.mini_projeto.Controllers;


import com.example.mini_projeto.DTOs.ReservaDTO;
import com.example.mini_projeto.Services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaService reservaService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<String> reserveBook(@RequestBody ReservaDTO data) {
        reservaService.reserveBook(data);
        return ResponseEntity.ok("Livro reservado com sucesso");
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/cancel")
    public ResponseEntity<String> cancelBookReservation(@RequestBody ReservaDTO data) {
        reservaService.cancelBookReservation(data);
        return ResponseEntity.ok("Reserva cancelada com sucesso");
    }
}
