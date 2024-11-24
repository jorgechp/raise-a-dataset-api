package cat.udl.eps.raise.projection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VerificationIndicatorResponseDTO {
    private String indicatorId;
    private String commentValue;
    private boolean isValid;
    @JsonCreator
    public VerificationIndicatorResponseDTO(
            @JsonProperty("indicatorId") String indicatorId,
            @JsonProperty("commentValue") String commentValue,
            @JsonProperty("isValid") boolean isValid) {
        this.indicatorId = indicatorId;
        this.commentValue = commentValue;
        this.isValid = isValid;
    }
}
