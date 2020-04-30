package ru.linar.sem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.linar.sem.converters.EntityConverter;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.BookChapter;

import javax.validation.Validator;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(bookGenericConverter());
        registry.addConverter(bookChapterGenericConverter());
    }

    @Bean
    public EntityConverter bookGenericConverter(){
        return new EntityConverter(Book.class);
    }

    @Bean
    public EntityConverter bookChapterGenericConverter(){
        return new EntityConverter(BookChapter.class);
    }
}
