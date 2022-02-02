package ru.tinkoff.opensource.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import ru.tinkoff.opensource.Application

import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger.web.UiConfigurationBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Configuration
    @EnableWebSecurity
    class SecurityConfiguration : WebSecurityConfigurerAdapter() {
        override fun configure(http: HttpSecurity) {
            http.csrf().disable().authorizeRequests().antMatchers("/").permitAll()
        }
    }

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(
            RequestHandlerSelectors.basePackage(
                Application::class.java.getPackage().name
            )
        )
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(true)

    @Bean
    fun uiConfiguration(): UiConfiguration {
        return UiConfigurationBuilder.builder()
            .displayRequestDuration(true)
            .validatorUrl("") // workaround for swagger bug (empty string instead of null disabling validation)
            .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfo(
            "One module example application",
            "",
            "1.0",
            "",
            null,
            "",
            "",
            emptyList()
        )
    }
}
