package cat.udl.eps.raise.query;

import cat.udl.eps.raise.domain.FAIRCategories;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FAIRPrincipleVerificationInstanceDataTable {

    private Long id;
    private String instanceUri;
    private String datasetUri;
    private String fairPrincipleUri;
    private String datasetName;
    private String repositoryName;
    private String fairPrinciplePrefix;
    private String fairPrincipleName;
    private FAIRCategories fairPrincipleCategory;
    private LocalDate verificationDate;

    public FAIRPrincipleVerificationInstanceDataTable(Long id,
                                                      String instanceUri,
                                                      String datasetUri,
                                                      String fairPrincipleUri,
                                                      String datasetName,
                                                      String repositoryName,
                                                      String fairPrinciplePrefix,
                                                      String fairPrincipleName,
                                                      FAIRCategories fairPrincipleCategory,
                                                      LocalDate verificationDate) {
        this.id = id;
        this.instanceUri = instanceUri;
        this.datasetUri = datasetUri;
        this.fairPrincipleUri = fairPrincipleUri;
        this.datasetName = datasetName;
        this.repositoryName = repositoryName;
        this.fairPrinciplePrefix = fairPrinciplePrefix;
        this.fairPrincipleName = fairPrincipleName;
        this.fairPrincipleCategory = fairPrincipleCategory;
        this.verificationDate = verificationDate;
    }

}