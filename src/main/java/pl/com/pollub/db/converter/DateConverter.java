package pl.com.pollub.db.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Created by mmaciasz on 2016-06-10.
 */
@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime != null ? Date.valueOf(localDateTime.toLocalDate()) : null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date date) {
        return date != null ? date.toLocalDate().atStartOfDay() : null;
    }
}
