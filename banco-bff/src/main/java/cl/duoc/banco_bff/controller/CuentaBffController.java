package cl.duoc.banco_bff.controller;

import cl.duoc.banco_bff.dto.CuentaDTO;
import cl.duoc.banco_bff.service.CuentaBffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaBffController {

    private final CuentaBffService cuentaBffService;

    public CuentaBffController(CuentaBffService CuentaBffService) {
        this.cuentaBffService = CuentaBffService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> listarCuentas() {
        return ResponseEntity.ok(
                cuentaBffService.listarCuentas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> buscarCuenta(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                cuentaBffService.buscarCuenta(id)
        );
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity<BigDecimal> consultarSaldo(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                cuentaBffService.consultarSaldo(id)
        );
    }
}
