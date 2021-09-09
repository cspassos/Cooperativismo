package cooperativismo.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cooperativismo.model.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{

	Optional<Voto> findByCpfAndPautaId(String cpf, Long id);

	Optional<List<Voto>> findByPautaId(Long id);

}
