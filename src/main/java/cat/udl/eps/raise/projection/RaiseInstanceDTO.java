package cat.udl.eps.raise.projection;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RaiseInstanceDTO {
    private Long id;

    private Long datasetId;

    private Long repositoryId;

    private String datasetName;

    private String repositoryName;

    private boolean isAgreeToRaise;

    private LocalDate nextActionDate;

    private short feedFrequencyInDays;

    public RaiseInstanceDTO(Long id,
                            Long datasetId,
                            Long repositoryId,
                            String datasetName,
                            String repositoryName,
                            boolean isAgreeToRaise,
                            LocalDate nextActionDate,
                            short feedFrequencyInDays) {
        this.id = id;
        this.datasetId = datasetId;
        this.repositoryId = repositoryId;
        this.datasetName = datasetName;
        this.repositoryName = repositoryName;
        this.isAgreeToRaise = isAgreeToRaise;
        this.nextActionDate = nextActionDate;
        this.feedFrequencyInDays = feedFrequencyInDays;
    }
}
