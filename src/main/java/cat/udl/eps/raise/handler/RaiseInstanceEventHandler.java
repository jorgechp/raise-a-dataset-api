package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.repository.RaiseInstanceRepository;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class RaiseInstanceEventHandler {


    final RaiseInstanceRepository raiseInstanceRepository;

    public RaiseInstanceEventHandler(RaiseInstanceRepository raiseInstanceRepository) {
        this.raiseInstanceRepository = raiseInstanceRepository;
    }}
