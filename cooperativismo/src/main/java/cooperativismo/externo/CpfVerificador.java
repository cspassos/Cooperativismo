package cooperativismo.externo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import cooperativismo.dto.StatusCpfDTO;

@Component
public class CpfVerificador {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${verificador-cpf-url}")
	private String urlVerificador;
	
	public String getStatus(String cpf) {
		try {
			
			String url = urlVerificador + cpf;
			
			StatusCpfDTO resp = restTemplate.getForObject(url, StatusCpfDTO.class);
			
			if(resp == null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao consultar cpf");
			}
			
			return resp.getStatus();

		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cpf inválido, " + e.getMessage());
		}
	}
}
