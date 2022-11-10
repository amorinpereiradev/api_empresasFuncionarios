package br.com.cotiinformatica;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.EmpresasPostDto;

@SpringBootTest
@AutoConfigureMockMvc
class Projetoapi01ApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJ1c3Vhcmlvc2FwaSIsInN1YiI6IkZlcm5hbmRvQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NjgwNDE3MzQsImV4cCI6MTY2ODA0NzczNH0.o2z0d3tXeQknN58NcgH3PagvJ-fHZs3EQ0c8_MKNF6qRz_MznrUsOrbmCvJa-netdMbiJjb6nziPgep6FeMqVQ";
	
	@Test
	public void postEmpresasTest() throws Exception {
	
		Faker faker = new Faker(new Locale("pt-BR"));
		
		EmpresasPostDto dto = new EmpresasPostDto();
		dto.setNomeFantasia(faker.company().name());
		dto.setRazaoSocial(faker.company().name());
		dto.setCnpj(String.valueOf(faker.random().nextInt(999999999)));
		
		mockMvc.perform(
				post("/api/empresas")	
				.header("authorization", "Bearer " + token)
				.contentType("application/json")
				.content
					(objectMapper.writeValueAsString(dto)))
			.andExpect(status().isCreated());	
	}
}


