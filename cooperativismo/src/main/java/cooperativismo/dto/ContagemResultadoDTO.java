package cooperativismo.dto;

import java.io.Serializable;

import cooperativismo.model.entity.Pauta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContagemResultadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer qtdVotos;
	private Integer qtdSessoes;

	private Integer qtdSim;
	private Integer qtdNao;
	
	private Pauta pauta;
}
