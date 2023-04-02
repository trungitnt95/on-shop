package trungitnt95.springboot001;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import trungitnt95.springboot001.controllers.products.TestRestApiController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestRestApiController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class GenerateApiDocumentTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username="spring")
	public void generateApiDocument() throws Exception {
		this.mockMvc.perform(get("/api/v1/test-generate-api-document")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("[1,2,3,4,5]")))
				.andDo(document("test-api"));
	}
}
