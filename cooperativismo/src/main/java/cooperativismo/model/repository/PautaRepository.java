package cooperativismo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cooperativismo.model.entity.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>{

}
