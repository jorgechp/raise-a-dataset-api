package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Dataset;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class NewDatasetStepDefs {

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  private StepDefs stepDefs;

  @When("I register a new Dataset with the name {string} and the description {string} and the creation date {string} and the registration date {string} and the author {string}")
  public void iRegisterANewDatasetWithTheNameAndTheDescriptionAndTheCreationDateAndTheRegistrationDateAndTheAuthor(
    String name,
    String description,
    String creationDate,
    String registrationDate,
    String author) throws Exception {

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      formatter = formatter.withLocale(Locale.ENGLISH);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
      LocalDate formattedCreationDate = LocalDate.parse(creationDate, formatter);
      LocalDate formattedRegistrationDate = LocalDate.parse(registrationDate, formatter);

      Dataset newDataset = new Dataset();
      newDataset.setName(name);
      newDataset.setDescription(description);
      newDataset.setCreationDate(formattedCreationDate);
      newDataset.setRegistrationDate(formattedRegistrationDate);
      newDataset.setAuthor(author);

      stepDefs.result = stepDefs.mockMvc.perform(
                      post("/datasets")
                              .contentType(MediaType.APPLICATION_JSON_VALUE)
                              .characterEncoding("utf-8")
                              .content(stepDefs.mapper.writeValueAsString(newDataset))
                              .accept(MediaType.APPLICATION_JSON)
                              .with(AuthenticationStepDefs.authenticate()))
              .andDo(print());
  }

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
}
