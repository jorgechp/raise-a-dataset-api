package cat.udl.eps.raise.steps;


import cat.udl.eps.raise.domain.FAIRCategories;
import cat.udl.eps.raise.domain.FAIRPrinciple;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class NewFAIRPrincipleStepDefs {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private StepDefs stepDefs;

    @And("It has been created a dataset with the name {string} and the description {string} and the creation date {string} date {string}  and the author {string}")
    public void itHasNotBeenCreatedADatasetWithTheNameAndTheDescriptionAndTheCreationDateAndTheAuthor(String name,
                                                                                                      String description,
                                                                                                      String creationDate,
                                                                                                      String registrationDate,
                                                                                                      String author) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/datasets/search/findByName").param("name", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.description", is(description)))
                .andExpect(jsonPath("$.creationDate", is(creationDate)))
                .andExpect(jsonPath("$.registrationDate", is(registrationDate)))
                .andExpect(jsonPath("$.author", is(author)));

    }

    @When("I register a new FAIR Principle with the name {string} and the name prefix {string} and the description {string} and the url {string} and the category {string}")
    public void iRegisterANewFAIRPrincipleWithTheNameAndTheNamePrefixAndTheDescriptionAndTheUrlAndTheCategory(
            String name, String prefix, String description, String url, String category) throws Exception {

        FAIRPrinciple fairPrinciple = new FAIRPrinciple();
        fairPrinciple.setNamePrefix(prefix);
        fairPrinciple.setName(name);
        fairPrinciple.setDescription(description);
        fairPrinciple.setCategory(FAIRCategories.FINDABILITY);
        fairPrinciple.setUrl(url);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/fAIRPrinciples")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .characterEncoding("utf-8")
                                .content(stepDefs.mapper.writeValueAsString(fairPrinciple))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("It has been created a new FAIR Principle with the name {string} and the name prefix {string} and the description {string} and the url {string} and the category {string}")
    public void itHasBeenCreatedANewFAIRPrincipleWithTheNameAndTheNamePrefixAndTheDescriptionAndTheUrlAndTheCategory(
            String name, String prefix, String description, String url, String category) throws Exception {

        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/fAIRPrinciples/search/findByName").param("name", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.namePrefix", is(prefix)))
                .andExpect(jsonPath("$.description", is(description)))
                .andExpect(jsonPath("$.category", is(category)))
                .andExpect(jsonPath("$.url", is(url)));

    }

}
