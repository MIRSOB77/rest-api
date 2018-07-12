package de.bringmeister;

import de.bringmeister.common.JsonPriceUnit;
import de.bringmeister.web.utils.PriceUnitConverter;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public static CustomEditorConfigurer customEditorConfigurer(){
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        configurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[] {
                (registry) -> registry.registerCustomEditor(JsonPriceUnit.class,    new PriceUnitConverter()) });
        return configurer;
    }
}
