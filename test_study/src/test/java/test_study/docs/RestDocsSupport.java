package test_study.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();



    @BeforeEach
    void setup( RestDocumentationContextProvider provider){

        //webAppContextSetup의 경우 webApplicationCaontext를 파라미터로 받는다.
//        this.mockMvc = MockMvcBuilders.webAppContextSetup( webApplicationContext)
//                .apply(documentationConfiguration(provider))
//                .build();
//

        //standaloneSetup의 경우 우리가 api를 명시할 컨트롤러를 파라미터로 넣을 수 있어 webAppContextSetup를 요구하지 않는다.
           this.mockMvc = MockMvcBuilders.standaloneSetup( initController())
                .apply(documentationConfiguration(provider))
                .build();
    }

    protected abstract Object initController();

}
