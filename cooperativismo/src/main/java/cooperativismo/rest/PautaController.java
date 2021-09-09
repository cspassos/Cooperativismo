package cooperativismo.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cooperativismo.model.entity.Pauta;
import cooperativismo.service.PautaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pauta")
@RequiredArgsConstructor//Cria um construtor com os argumentos obrigatorios
public class PautaController {
	
	@Autowired
	private PautaService pautaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pauta salvar(@Valid @RequestBody Pauta pauta) {
		return pautaService.salvar(pauta);
	}
	
	@GetMapping("{id}")
	public Pauta buscarPorId(@PathVariable Long id) {
		return pautaService.buscarPorId(id);
	}
	
	@GetMapping
	public List<Pauta> buscarTodasPautas() {
		return pautaService.buscarTodasPautas();
	}
	
	@DeleteMapping("{id}")
	public void excluirPauta(@PathVariable Long id) {
		pautaService.excluirPauta(id);
	}
	
}
