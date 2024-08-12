package cat.udl.eps.raise.projection;


import cat.udl.eps.raise.domain.FAIRCategories;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ComplianceDTO {
    private Long id;

    private Long fairPrincipleId;

    private Long authorId;

    private String authorName;

    private Long instanceId;

    private Long repositoryId;

    private String repositoryName;

    private Long datasetId;

    private String datasetName;

    private String fairPrinciplePrefix;

    private String fairPrincipleName;

    private FAIRCategories fairCategory;

    private LocalDate verificationDate;


    public ComplianceDTO(Long id, Long fairPrincipleId, Long authorId, String authorName, Long instanceId, Long repositoryId, String repositoryName, Long datasetId, String datasetName, String fairPrinciplePrefix, String fairPrincipleName, FAIRCategories fairCategory, LocalDate verificationDate) {
        this.id = id;
        this.fairPrincipleId = fairPrincipleId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.instanceId = instanceId;
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
