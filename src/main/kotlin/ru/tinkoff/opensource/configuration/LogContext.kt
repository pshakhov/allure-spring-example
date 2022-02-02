package ru.tinkoff.opensource.configuration

import org.slf4j.MDC
import java.lang.IllegalStateException
import java.util.UUID

@Suppress("TooGenericExceptionCaught", "RethrowCaughtException")
object LogContext {

    const val TRACE_ID = "traceId"
    const val ENTITY_ID = "entityId"

    val traceId: String
        get() = MDC.get(TRACE_ID)
            ?: throw IllegalStateException("MDC key $TRACE_ID must be not null")

    val entityId: String
        get() = MDC.get(ENTITY_ID)
            ?: throw IllegalStateException("MDC key $ENTITY_ID must be not null")

    fun addTraceId(traceId: String) =
        addField(TRACE_ID, traceId)

    fun addEntityId(entityId: String) =
        addField(ENTITY_ID, entityId)

    private fun addField(key: String, value: String?) = MDC.put(key, value)

    fun clear() = MDC.clear()

    inline fun <reified T> use(entityId: String, traceId: String = UUID.randomUUID().toString(), block: () -> T): T {
        try {
            addTraceId(traceId)
            addEntityId(entityId)
            return block()
        } catch (e: Exception) {
            throw e
        } finally {
            clear()
        }
    }
}
