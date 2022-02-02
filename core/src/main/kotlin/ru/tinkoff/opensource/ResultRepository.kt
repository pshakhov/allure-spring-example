package ru.tinkoff.opensource

import org.springframework.data.repository.CrudRepository
import ru.tinkoff.opensource.ResultEntity

interface ResultRepository: CrudRepository<ResultEntity, Long>{

    fun getFirstByPersonIdOrderByIdDesc(id:String): ResultEntity

}