package cooperativismo.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "voto_seq", sequenceName = "voto_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voto_seq")
    private Long id;

	@Column(nullable = false, length = 11)
    @NotNull(message = "{voto.campo.cpf.obrigatorio}")
    @CPF(message = "{voto.campo.cpf.invalido}")
    private String cpf;

	@NotNull(message = "{voto.campo.escolha.obrigatorio}")
    private Boolean escolha;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pauta pauta;

}
