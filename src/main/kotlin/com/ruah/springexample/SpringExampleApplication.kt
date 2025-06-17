package com.ruah.springexample

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SpringExampleApplication

inline fun <reified T> T.logger() = LoggerFactory.getLogger("CUSTOM_JSON_LAYOUT_LOGGER")!!

fun main(args: Array<String>) {
	runApplication<SpringExampleApplication>(*args)
}

@RestController
class ActuatorController {
	@GetMapping("/actuator/health")
	fun healthCheck() = ResponseEntity.ok("OK")
}
