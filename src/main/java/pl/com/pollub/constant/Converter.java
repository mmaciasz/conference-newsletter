package pl.com.pollub.constant;

/**
 * Created by mmaciasz on 2016-06-14.
 */
public interface Converter<From, To> {

    To convert(From from);
}
