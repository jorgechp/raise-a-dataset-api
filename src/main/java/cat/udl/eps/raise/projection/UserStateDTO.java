package cat.udl.eps.raise.projection;

import cat.udl.eps.raise.domain.User;
import lombok.Data;

@Data
public class UserStateDTO {
    private User user;
    private int numberOfVerifications;
    private int numberOfIndicators;
    private int numberOfDatasetInstances;
}
