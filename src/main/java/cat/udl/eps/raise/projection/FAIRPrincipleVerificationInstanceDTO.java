package cat.udl.eps.raise.projection;


import lombok.Data;

import java.time.LocalDate;


@Data
public class FAIRPrincipleVerificationInstanceDTO {
    private Long id;

    private Long fairPrincipleId;

    private Long authorId;

    private String authorName;

    private Long instanceID;

    private Long datasetId;

    private String datasetName;

    private String datasetPrefix;

    private LocalDate verificationDate;


    public FAIRPrincipleVerificationInstanceDTO(Long id, Long fairPrincipleId, Long authorId, Long instanceID, Long datasetId) {
        this.id = id;
        this.fairPrincipleId = fairPrincipleId;
        this.authorId = authorId;
        this.instanceID = instanceID;
        this.datasetId = datasetId;
    }


}
