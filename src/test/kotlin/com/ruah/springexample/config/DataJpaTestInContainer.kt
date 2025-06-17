package com.ruah.springexample.config

import org.junit.jupiter.api.Disabled
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.lang.annotation.Inherited


@Inherited
@Retention(AnnotationRetention.RUNTIME)
@Disabled
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestQueryDslConfig::class)
annotation class DataJpaTestInContainer()
