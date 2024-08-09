package cat.udl.eps.raise.converter;

import cat.udl.eps.raise.domain.Verification;
import cat.udl.eps.raise.projection.VerificationDTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConverterFAIRPrincipleVerificationInstanceDTO implements Converter<Verification, VerificationDTO> {

    @Override
    public VerificationDTO convert(@NotNull Verification source) {
        VerificationDTO dto = null;
        return dto;
    }
}