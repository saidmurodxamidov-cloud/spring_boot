package org.example.spring_boot.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CharArrayToStringConverter implements AttributeConverter<char[], String> {

    @Override
    public String convertToDatabaseColumn(char[] attribute) {
        if (attribute == null) return null;
        return new String(attribute);
    }

    @Override
    public char[] convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return dbData.toCharArray();
    }
}
