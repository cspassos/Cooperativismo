package cooperativismo.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cooperativismo.model.entity.Voto;
import cooperativismo.service.BonusCpfVotoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bonus-cpf-voto")
@RequiredArgsConstructor//Cria um construtor com os argumentos obrigatorios
public class BonusCpfVotoController {

	@Autowired
	private BonusCpfVotoService bonusCpfVotoService;
	
	@PostMapping("pauta/{idPauta}/sessao/{idSessao}")
	@ResponseStatus(HttpStatus.CREATED)
	public Voto salvarVotoBonusCpf(@PathVariable Long idPauta, @PathVariable Long idSessao, @Valid @RequestBody Voto voto) {
		return bonusCpfVotoService.salvarVotoBonusCpf(idPauta, idSessao, voto);
	}
}
