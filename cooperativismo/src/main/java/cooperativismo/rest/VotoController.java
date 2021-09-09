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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Voto")
@RestController
@RequestMapping("/votos")
public class VotoController {

	@Autowired
	private VotoService votoService;
	
	@ApiOperation(value = "Salvar Voto")
	@PostMapping("pauta/{idPauta}/sessao/{idSessao}")
	@ResponseStatus(HttpStatus.CREATED)
	public Voto salvarVoto(@PathVariable Long idPauta, @PathVariable Long idSessao, @Valid @RequestBody Voto voto) {
		return votoService.salvarVoto(idPauta, idSessao, voto);
	}
	
	@ApiOperation(value = "Buscar todas as votos")
	@GetMapping
	public List<Voto> buscarTodosVotos() {
		return votoService.buscarTodosVotos();
	}
	
	@ApiOperation(value = "Buscar voto por ID")
	@GetMapping("{id}")
	public Voto buscarPorId(@PathVariable Long id) {
		return votoService.buscarPorId(id);
	}
	
	@ApiOperation(value = "Buscar resultado da votação")
	@GetMapping("pauta/{idPauta}/resultado-votos")
	public ContagemResultadoDTO buscarResultadoVotos(@PathVariable Long idPauta) {
		return votoService.buscarResultadoVotos(idPauta);
	}
}
