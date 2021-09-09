package cooperativismo.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cooperativismo.dto.ContagemResultadoDTO;
import cooperativismo.model.entity.Voto;
import cooperativismo.service.VotoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/votos")
@RequiredArgsConstructor//Cria um construtor com os argumentos obrigatorios
public class VotoController {

	@Autowired
	private VotoService votoService;
	
	@PostMapping("pauta/{idPauta}/sessao/{idSessao}")
	@ResponseStatus(HttpStatus.CREATED)
	public Voto salvarVoto(@PathVariable Long idPauta, @PathVariable Long idSessao, @Valid @RequestBody Voto voto) {
		return votoService.salvarVoto(idPauta, idSessao, voto);
	}
	
	@GetMapping
	public List<Voto> buscarTodosVotos() {
		return votoService.buscarTodosVotos();
	}
	
	@GetMapping("{id}")
	public Voto buscarPorId(@PathVariable Long id) {
		return votoService.buscarPorId(id);
	}
	
	@GetMapping("pauta/{idPauta}/resultado-votos")
	public ContagemResultadoDTO buscarResultadoVotos(@PathVariable Long idPauta) {
		return votoService.buscarResultadoVotos(idPauta);
	}
}
