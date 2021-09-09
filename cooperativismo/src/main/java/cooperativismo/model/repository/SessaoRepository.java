package cooperativismo.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cooperativismo.model.entity.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long>{

	Optional<Sessao> findByIdAndPautaId(Long idSessao, Long idPauta);

	Integer countByPautaId(Long id);

	Optional<List<Sessao>> findByPautaId(Long id);
	
}
