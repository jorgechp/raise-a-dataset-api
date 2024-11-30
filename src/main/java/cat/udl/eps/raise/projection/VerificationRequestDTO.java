package cat.udl.eps.raise.projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VerificationRequestDTO {
    private Long instanceId;
    private Long indicatorId;
    private String uniqueIdentifier;

    public VerificationRequestDTO(@JsonProperty("instanceId") Long instanceId,
                                  @JsonProperty("uniqueIdentifier")  String uniqueIdentifier) {
        this.instanceId = instanceId;
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public VerificationRequestDTO(Long instanceId, Long indicatorId, String uniqueIdentifier) {
        this.instanceId = instanceId;
        this.indicatorId = indicatorId;
        this.uniqueIdentifier = uniqueIdentifier;
    }
}
