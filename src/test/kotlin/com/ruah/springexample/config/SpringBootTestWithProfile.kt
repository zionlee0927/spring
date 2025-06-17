package com.ruah.springexample.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.lang.annotation.Inherited

@Inherited
@ActiveProfiles("test")
@SpringBootTest(properties = ["spring.profiles.active=test"], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Retention(AnnotationRetention.RUNTIME)
annotation class SpringBootTestWithProfile()
