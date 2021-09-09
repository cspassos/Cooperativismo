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

import cooperativismo.dto.ContagemResultadoDTO;
import cooperativismo.externo.CpfVerificador;
import cooperativismo.model.entity.Sessao;
import cooperativismo.model.entity.Voto;
import cooperativismo.model.repository.SessaoRepository;
import cooperativismo.model.repository.VotoRepository;

@Service
public class BonusCpfVotoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BonusCpfVotoService.class);

	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private SessaoRepository sessaoRepository;
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private CpfVerificador cpfVerificador;
	
	private static String unableCpf = "UNABLE_TO_VOTE";
	
	public Voto salvarVotoBonusCpf(Long idPauta, Long idSessao, Voto voto) {
		Sessao sessao = sessaoService.buscarSessaoPautaPorId(idSessao, idPauta);
		
		if (!idPauta.equals(sessao.getPauta().getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessão incorreta para a pauta requerida!");
		}
		voto.setPauta(sessao.getPauta());
		return validarSalvar(sessao, voto);
	}

	private Voto validarSalvar(final Sessao sessao, final Voto voto) {
		LOGGER.debug("Validar e salvar o voto!");
		
		if(unableCpf.equals(cpfVerificador.getStatus(voto.getCpf()))) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido para a votação!");
			
		} else {
			
			//plusMinutes -> usado para retornar data e hora com os minutos especificados
			if (LocalDateTime.now().isAfter(sessao.getDataInicio().plusMinutes(sessao.getTempoSessao()))) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possivel realizar o seu voto. Votação encerrada!");
			}
			
			if(votoRepository.findByCpfAndPautaId(voto.getCpf(), voto.getPauta().getId()).isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um voto desse associado para essa Pauta.");
			}
			
			return votoRepository.save(voto);
		}
	}

	public List<Voto> buscarTodosVotos() {
		return votoRepository.findAll();
	}

	public Voto buscarPorId(Long id) {
		return votoRepository.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voto não encontrado!"));
	}

	public ContagemResultadoDTO buscarResultadoVotos(Long idPauta) {
		
		ContagemResultadoDTO contagemResultadoDTO = new ContagemResultadoDTO();
		
		Optional<List<Voto>> resultadoVotos = votoRepository.findByPautaId(idPauta);
		if (!resultadoVotos.isPresent() || resultadoVotos.get().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A Pauta selecionada está sem votos");
		}
		
		contagemResultadoDTO.setPauta(resultadoVotos.get().iterator().next().getPauta());
		contagemResultadoDTO.setQtdVotos(resultadoVotos.get().size());
		contagemResultadoDTO.setQtdSessoes(sessaoRepository.countByPautaId(idPauta));
		
		Integer totalSim = 0;
		for(Voto voto : resultadoVotos.get()) {
			if(Boolean.TRUE.equals(voto.getEscolha())) {
				totalSim++;
			}
		}
		contagemResultadoDTO.setQtdSim(totalSim);
		contagemResultadoDTO.setQtdNao(contagemResultadoDTO.getQtdVotos() - totalSim);

		return contagemResultadoDTO;
	}

}
