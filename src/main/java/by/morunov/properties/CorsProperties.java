package by.morunov.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author Alex Morunov
 */
@Configuration
@ConfigurationProperties("cors")
@Validated
public class CorsProperties {

    @NotBlank
    private String uiUrl;

    public String getUiUrl() {
        return uiUrl;
    }

    public void setUiUrl(String uiUrl) {
        this.uiUrl = uiUrl;
    }
}
