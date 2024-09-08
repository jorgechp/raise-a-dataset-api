package cat.udl.eps.raise.projection;


import cat.udl.eps.raise.domain.FAIRCategories;
import lombok.Getter;

import java.time.LocalDate;

@Getter

public class ComplianceValidationDTO extends ComplianceDTO {
    private long upVotes;
    private long downVotes;

    public ComplianceValidationDTO(Long id,
                                   Long fairPrincipleId,
                                   Long authorId,
                                   Long instanceId,
                                   Long repositoryId,
                                   Long datasetId,
                                   String authorName,
                                   String repositoryName,
                                   String datasetName,
                                   String fairPrinciplePrefix,
                                   String fairPrincipleName,
                                   FAIRCategories fairCategory,
                                   LocalDate complianceDate,
                                   long upVotes,
                                   long downVotes) {
        super(id, fairPrincipleId, authorId, authorName, instanceId, repositoryId, repositoryName, datasetId, datasetName, fairPrinciplePrefix, fairPrincipleName, fairCategory, complianceDate);
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }
}
