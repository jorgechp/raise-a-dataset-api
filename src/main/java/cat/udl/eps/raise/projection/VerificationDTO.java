package cat.udl.eps.raise.projection;


import cat.udl.eps.raise.domain.FAIRCategories;
import lombok.Data;

import java.time.LocalDate;


@Data
public class VerificationDTO {
    private Long id;

    private Long fairPrincipleId;

    private Long authorId;

    private String authorName;

    private Long instanceID;

    private Long repositoryId;

    private String repositoryName;

    private Long datasetId;

    private String datasetName;

    private String fairPrinciplePrefix;

    private String fairPrincipleName;

    private FAIRCategories fairCategory;

    private LocalDate verificationDate;


    public VerificationDTO(Long id, Long fairPrincipleId, Long authorId, String authorName, Long instanceID, Long repositoryId, String repositoryName, Long datasetId, String datasetName, String fairPrinciplePrefix, String fairPrincipleName, FAIRCategories fairCategory, LocalDate verificationDate) {
        this.id = id;
        this.fairPrincipleId = fairPrincipleId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.instanceID = instanceID;
        this.repositoryId = repositoryId;
        this.repositoryName = repositoryName;
        this.datasetId = datasetId;
        this.datasetName = datasetName;
        this.fairPrinciplePrefix = fairPrinciplePrefix;
        this.fairPrincipleName = fairPrincipleName;
        this.fairCategory = fairCategory;
        this.verificationDate = verificationDate;
    }


}
