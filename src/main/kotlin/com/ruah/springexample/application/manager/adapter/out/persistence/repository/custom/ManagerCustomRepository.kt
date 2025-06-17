package com.ruah.springexample.application.manager.adapter.out.persistence.repository.custom

import com.ruah.springexample.application.manager.adapter.out.persistence.entity.ManagerEntity
import com.ruah.springexample.application.manager.service.port.`in`.SearchManagerCommand

interface ManagerCustomRepository {

    fun search(command: SearchManagerCommand): List<ManagerEntity>
}