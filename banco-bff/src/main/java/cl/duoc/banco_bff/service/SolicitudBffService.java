package cl.duoc.banco_bff.service;

import cl.duoc.banco_bff.dto.SolicitudDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Service
public class SolicitudBffService {

    private final RestClient restClient;

    @Value("${solicitudes-service.url}")
    private String solicitudesServiceUrl;

    public SolicitudBffService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<SolicitudDTO> listarSolicitudes() {
        SolicitudDTO[] solicitudes = restClient.get()
                .uri(solicitudesServiceUrl + "/solicitudes")
                .retrieve()
                .body(SolicitudDTO[].class);

        return solicitudes != null
                ? Arrays.asList(solicitudes)
                : List.of();
    }

    public SolicitudDTO buscarSolicitud(Long id) {
        return restClient.get()
                .uri(solicitudesServiceUrl + "/solicitudes/" + id)
                .retrieve()
                .body(SolicitudDTO.class);
    }

    public SolicitudDTO crearSolicitud(SolicitudDTO solicitud) {
        return restClient.post()
                .uri(solicitudesServiceUrl + "/solicitudes")
                .body(solicitud)
                .retrieve()
                .body(SolicitudDTO.class);
    }

    public SolicitudDTO aprobarSolicitud(Long id) {
        return restClient.put()
                .uri(solicitudesServiceUrl + "/solicitudes/" + id + "/aprobar")
                .retrieve()
                .body(SolicitudDTO.class);
    }

    public SolicitudDTO rechazarSolicitud(Long id) {
        return restClient.put()
                .uri(solicitudesServiceUrl + "/solicitudes/" + id + "/rechazar")
                .retrieve()
                .body(SolicitudDTO.class);
    }
}
