package trungitnt95.springboot001;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import trungitnt95.springboot001.controllers.products.AbstractProductController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AbstractProductController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ProductTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username="spring")
	public void generateApiDocument() throws Exception {
		this.mockMvc.perform(get("/api/products/v1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("[\"v1\",\"v2\"]")))
				.andDo(document("products"));
	}
}
