package cat.udl.eps.raise.projection;

import lombok.Data;

@Data
public class VerificationRequestDTO {
    private Long instanceId;
    private Long indicatorId;
    private String uri;

    public VerificationRequestDTO(Long instanceId, String uri) {
        this.instanceId = instanceId;
        this.uri = uri;
    }

    public VerificationRequestDTO(Long instanceId, Long indicatorId, String uri) {
        this.instanceId = instanceId;
        this.indicatorId = indicatorId;
        this.uri = uri;
    }
}
