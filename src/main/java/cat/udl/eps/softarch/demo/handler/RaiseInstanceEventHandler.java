package cat.udl.eps.softarch.demo.handler;


import cat.udl.eps.softarch.demo.repository.RaiseInstanceRepository;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class RaiseInstanceEventHandler {


    final RaiseInstanceRepository raiseInstanceRepository;

    public RaiseInstanceEventHandler(RaiseInstanceRepository raiseInstanceRepository) {
        this.raiseInstanceRepository = raiseInstanceRepository;
    }}
