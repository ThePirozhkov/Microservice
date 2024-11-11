package by.baby.mockwebservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MockWebServiceApplication.class)
@AutoConfigureMockMvc
class MockWebServiceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void mockControllerIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/mock"))
                .andExpect(status().isOk());
    }

}
