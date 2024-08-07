package cat.udl.eps.raise.converter;


import cat.udl.eps.raise.domain.FAIRPrincipleVerificationInstance;
import cat.udl.eps.raise.projection.FAIRPrincipleVerificationInstanceDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;

@Configuration
public class ConverterConfig {

    private final Converter<FAIRPrincipleVerificationInstance, FAIRPrincipleVerificationInstanceDTO> tupleBackedMapToDtoConverter;

    public ConverterConfig(Converter<FAIRPrincipleVerificationInstance, FAIRPrincipleVerificationInstanceDTO> tupleBackedMapToDtoConverter) {
        this.tupleBackedMapToDtoConverter = tupleBackedMapToDtoConverter;
    }

    @Bean
    public DefaultFormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(tupleBackedMapToDtoConverter);
        return conversionService;
    }
}

