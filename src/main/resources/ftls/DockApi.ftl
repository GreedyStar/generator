package  ${Configuration.packageName};


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
<#if Configuration.knif4jEnable>
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
</#if>

@Configuration
<#if Configuration.knif4jEnable>
@EnableKnife4j
</#if>
public class SwaggerConfig {
    <#noparse>
    @Value("${swagger.enabled:false}")
    </#noparse>
    private boolean enabled;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                 .enable(enabled)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .groupName("dev")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("接口说明文档")
                .contact(new Contact("greedyStar", "github.com/GreedyStar/generator", ""))
                .version("1.0.0")
                .build();
    }

}
