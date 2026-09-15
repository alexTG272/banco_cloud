package cl.duoc.banco_bff.controller;

import cl.duoc.banco_bff.dto.SolicitudDTO;
import cl.duoc.banco_bff.service.SolicitudBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudBffController {

    private final SolicitudBffService solicitudBffService;

    public SolicitudBffController(SolicitudBffService solicitudBffService) {
        this.solicitudBffService = solicitudBffService;
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> listarSolicitudes() {
        return ResponseEntity.ok(
                solicitudBffService.listarSolicitudes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> buscarSolicitud(
            @PathVariable Long id) {

        SolicitudDTO solicitud = solicitudBffService.buscarSolicitud(id);

        return ResponseEntity.ok(solicitud);
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO> crearSolicitud(
            @RequestBody SolicitudDTO solicitud) {

        return ResponseEntity.ok(
                solicitudBffService.crearSolicitud(solicitud)
        );
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<SolicitudDTO> aprobarSolicitud(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                solicitudBffService.aprobarSolicitud(id)
        );
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<SolicitudDTO> rechazarSolicitud(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                solicitudBffService.rechazarSolicitud(id)
        );
    }
}
