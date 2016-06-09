package pl.com.pollub.db.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by mmaciasz on 2016-06-08.
 */
@Converter
public class DateConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
    	if(localDateTime == null)
    		return null;
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
    	if(timestamp == null)
    		return null;
        return timestamp.toLocalDateTime();
    }
}
