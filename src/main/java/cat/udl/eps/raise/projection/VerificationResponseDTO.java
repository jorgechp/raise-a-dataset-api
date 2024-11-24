package cat.udl.eps.raise.projection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class VerificationResponseDTO {
    private Long instanceId;
    private Map<String, List<VerificationIndicatorResponseDTO>> result;

    @JsonCreator
    public VerificationResponseDTO(@JsonProperty("instanceId") Long instanceId,
                                   @JsonProperty("result") Map<String,List<VerificationIndicatorResponseDTO>> result) {
        this.instanceId = instanceId;
        this.result = result;
    }
}
