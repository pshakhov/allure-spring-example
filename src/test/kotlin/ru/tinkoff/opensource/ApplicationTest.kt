package ru.tinkoff.opensource

import mu.KLogging
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.tinkoff.opensource.component.Props


@Tag("service")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTest : KLogging() {

    @Autowired
    lateinit var props: Props

    @Test
    fun `applicationCanStartUp`() {
        logger.info { props.applicationName }
        logger.info { props.url }
    }
}