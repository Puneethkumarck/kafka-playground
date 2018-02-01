package com.home.work.kafkaplay.config
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.deser.std.DateDeserializers
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.DateSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MainConfig {

    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper=new ObjectMapper()
        objectMapper.with {
            configure(SerializationFeature.WRITE_NULL_MAP_VALUES,false)
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false)
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)
            configure(SerializationFeature.INDENT_OUTPUT,false)
            configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true)
            enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
            setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        }
        objectMapper.registerModule(simpleModule())
        objectMapper
    }


    private SimpleModule simpleModule(){
        SimpleModule module=new SimpleModule()
        module.with {
            addSerializer(Date,new DateSerializer())
            addDeserializer(Date,new DateDeserializers.DateDeserializer())
        }
    }
}
