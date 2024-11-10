package cat.udl.eps.raise.projection;

public class VerificationRequestDTO {
    private Long instanceId;
    private Long indicatorId;


    public VerificationRequestDTO(Long instanceId, Long indicatorId) {
        this.instanceId = instanceId;
        this.indicatorId = indicatorId;
    }
}
