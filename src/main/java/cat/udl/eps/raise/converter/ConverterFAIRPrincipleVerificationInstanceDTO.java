package cat.udl.eps.raise.converter;

import cat.udl.eps.raise.domain.FAIRPrincipleVerificationInstance;
import cat.udl.eps.raise.projection.FAIRPrincipleVerificationInstanceDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConverterFAIRPrincipleVerificationInstanceDTO implements Converter<FAIRPrincipleVerificationInstance, FAIRPrincipleVerificationInstanceDTO> {

    @Override
    public FAIRPrincipleVerificationInstanceDTO convert(@NotNull FAIRPrincipleVerificationInstance source) {
        FAIRPrincipleVerificationInstanceDTO dto = new FAIRPrincipleVerificationInstanceDTO(
                source.getId(),
                source.getFairPrinciple().getId(),
                source.getAuthor().getId(),
                source.getInstance().getId(),
                source.getInstance().getDataset().getId()
        );
        return dto;
    }
}