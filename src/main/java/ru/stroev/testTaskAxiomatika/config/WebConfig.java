package ru.stroev.testTaskAxiomatika.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.stroev.testTaskAxiomatika.converter.StringToFamilyStatusConverter;

/**
 * Web configuration
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToFamilyStatusConverter stringToFamilyStatusConverter;

    public WebConfig(StringToFamilyStatusConverter stringToFamilyStatusConverter) {
        this.stringToFamilyStatusConverter = stringToFamilyStatusConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToFamilyStatusConverter);
    }
}
