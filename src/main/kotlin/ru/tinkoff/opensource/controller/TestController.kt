package ru.tinkoff.opensource.controller

import mu.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.tinkoff.opensource.ResultEntity
import ru.tinkoff.opensource.ResultRepository
import ru.tinkoff.opensource.configuration.LogContext
import ru.tinkoff.opensource.model.Info

private val logger = KotlinLogging.logger { }

@RestController
class TestController(
    private val errorTypeRepository: ResultRepository
) {

    @PostMapping("people")
    fun addInfo(@RequestBody info: Info): Info =
        LogContext.use(entityId = info.id) {
            logger.info { info }
            info
        }

    @PostMapping("entity")
    fun addEntity(@RequestBody entity: ResultEntity): ResultEntity? =
        LogContext.use(entityId = entity.id!!.toString()) {
            errorTypeRepository.save(entity)
            errorTypeRepository.getFirstByPersonIdOrderByIdDesc(entity.personId)
        }
}
