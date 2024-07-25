package ru.stroev.testTaskAxiomatika.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.stroev.testTaskAxiomatika.models.enums.FamilyStatus;

/**
 * Family status converter
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Component
public class StringToFamilyStatusConverter implements Converter<String, FamilyStatus> {

    @Override
    public FamilyStatus convert(String source) {
        try {
            return FamilyStatus.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
