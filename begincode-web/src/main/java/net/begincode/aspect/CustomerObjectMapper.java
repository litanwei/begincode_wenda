package net.begincode.aspect;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by saber on 2016/9/17.
 */
@Component
public class CustomerObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public CustomerObjectMapper() {

        super();

        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
            @Override
            public void serialize(Object value, JsonGenerator generator,
                                  SerializerProvider provider) throws IOException,
                    JsonProcessingException {
                generator.writeString("");
            }
        });
    }


}
