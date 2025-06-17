package com.ruah.springexample.config.container

import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class MysqlTestContainer {

    companion object {
        private const val DATABASE_NAME = "trainer_test"
        private const val USERNAME = "user"
        private const val PASSWORD = "pass"

        @Container
        @JvmStatic
        val MYSQL_CONTAINER: MySQLContainer<*> = MySQLContainer<Nothing>("mysql:8")
                .apply { withDatabaseName(DATABASE_NAME) }
                .apply { withUsername(USERNAME) }
                .apply { withPassword(PASSWORD) }
                .apply { withReuse(true) }
                .apply { start() }
    }
}