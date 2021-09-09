package cooperativismo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cooperativismo.model.entity.Pauta;
import cooperativismo.model.entity.Sessao;
import cooperativismo.model.repository.PautaRepository;
import cooperativismo.model.repository.SessaoRepository;

@Service
public class SessaoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessaoService.class);

	@Autowired
	private SessaoRepository sessaoRepository;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public Sessao criarSessao(Long idPauta, Sessao sessao) {
        Optional<Pauta> findById = pautaRepository.findById(idPauta);
        if(!findById.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada!");
        }
        sessao.setPauta(findById.get());
        
        return salvarSessao(sessao);
	}

	private Sessao salvarSessao(Sessao sessao) {
		LOGGER.debug("Salvar Sessão!");
		if (sessao.getDataInicio() == null) {
            sessao.setDataInicio(LocalDateTime.now());
        }
        if (sessao.getTempoSessao() == null) {
            sessao.setTempoSessao(1L);
        }

        return sessaoRepository.save(sessao);
	}

	public List<Sessao> buscarTodasSessoes() {
		return sessaoRepository.findAll();
	}
	
	public Sessao buscarPorId(Long id) {
		return sessaoRepository.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada!"));
	}

	public void excluirSessao(Long id) {
		LOGGER.debug("Excluir Sessão!");
		Optional<Sessao> sessao = sessaoRepository.findById(id);
        if (!sessao.isPresent()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrado!");
        }
        sessaoRepository.delete(sessao.get());
	}

	public Sessao buscarSessaoPautaPorId(Long idSessao, Long idPauta) {
		Optional<Sessao> sessao = sessaoRepository.findByIdAndPautaId(idSessao, idPauta);
        if(!sessao.isPresent()){
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrado!");
        }
        return sessao.get();
	}

	public void deletarSessaoPeloIdPauta(Long id) {
		Optional<List<Sessao>> sessoes = sessaoRepository.findByPautaId(id);
		sessoes.ifPresent(sessao -> sessao.forEach(sessaoRepository::delete));
	}

}
