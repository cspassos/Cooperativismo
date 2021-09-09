package cooperativismo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cooperativismo.model.entity.Pauta;
import cooperativismo.model.repository.PautaRepository;

import java.util.Optional;

@Service
public class PautaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PautaService.class);
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private SessaoService sessaoService; 
	
	@Autowired
	private VotoService votoService; 
	
	public Pauta salvar(Pauta pauta) {
		LOGGER.debug("Salvar a Pauta!");
		return pautaRepository.save(pauta);
	}

	public Pauta buscarPorId(Long id) {
		return pautaRepository.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada!"));
	}

	public List<Pauta> buscarTodasPautas() {
		return pautaRepository.findAll();
	}

	public void excluirPauta(Long id) {
		LOGGER.debug("Excluir Pauta!");
		Optional<Pauta> pauta = pautaRepository.findById(id);
        if (!pauta.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrado!");
        }
        
        votoService.deletarVotoPeloIdPauta(id);
        sessaoService.deletarSessaoPeloIdPauta(id);
        pautaRepository.delete(pauta.get());
	}

}
