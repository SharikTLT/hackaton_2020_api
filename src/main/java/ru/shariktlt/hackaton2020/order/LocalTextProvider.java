package ru.shariktlt.hackaton2020.order;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.shariktlt.hackaton2020.core.service.YamlPropertySourceFactory;

import java.util.Map;

@Configuration
@ConfigurationProperties
@PropertySource(value = "classpath:textVars.yml", factory = YamlPropertySourceFactory.class)
public class LocalTextProvider implements TextProvider {

    @Getter
    @Setter
    private Map<String, Map<String, String>> localDictionary;


    @Override
    public String getTextForKey(TextTypes type, String key) {
        if (!localDictionary.containsKey(type.getKey())) {
            throw new IllegalArgumentException("Invalid dictionary type");
        }
        return localDictionary.get(type.getKey()).getOrDefault(key, "#MissedKey:" + type.name() + "/" + key + "#");
    }

}
