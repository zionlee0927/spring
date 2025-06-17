import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.asciidoctor.jvm.convert") version "3.3.2"

	val kotlinVersion = "1.9.22"

	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
	kotlin("kapt") version kotlinVersion

	kotlin("plugin.allopen") version kotlinVersion
	kotlin("plugin.noarg") version kotlinVersion
	idea
}

group = "com.ruah"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

configurations.forEach {
	it.exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	it.exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
}

repositories {
	mavenCentral()
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

val asciidoctorExt by configurations.creating

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.bouncycastle:bcprov-jdk18on:1.77")
	implementation("io.jsonwebtoken:jjwt:0.12.5")
	implementation("com.mysql:mysql-connector-j:8.4.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.infobip:infobip-spring-data-jpa-querydsl-boot-starter:9.0.2")
	implementation("com.querydsl:querydsl-guava:5.1.0")

	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
	kaptTest("org.mapstruct:mapstruct-processor:1.5.5.Final")
	implementation("com.aallam.ulid:ulid-kotlin:1.3.0")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
	implementation("org.json:json:20240303")
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
	implementation("io.github.oshai:kotlin-logging:6.0.9")

	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
	annotationProcessor("jakarta.annotation:jakarta.annotation-api")
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.13.9")
	testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
	runtimeOnly("io.kotest:kotest-assertions-core:5.8.0")
	testImplementation("org.testcontainers:testcontainers:1.19.6")
	testImplementation("org.testcontainers:junit-jupiter:1.19.6")
	testImplementation("org.testcontainers:mysql:1.19.6")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
	asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
}


val snippetsDir by extra {
	file("build/generated-snippets")
}

tasks {
	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "21"
		}
	}
	withType<Test> {
		// 테스트 실행 시 JVM 메모리 설정 추가 및 업데이트
		maxHeapSize = "4g"
		jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off", "-Xmx4g", "-Xms1g")
		testLogging {
			showStandardStreams = true
			showCauses = true
			showExceptions = true
			showStackTraces = true
		}
		useJUnitPlatform()
	}

	// Gradle 자체 JVM 설정 추가
	withType<JavaCompile> {
		options.isFork = true
		options.forkOptions.jvmArgs = listOf("-Xmx4g")
	}

	asciidoctor {
		dependsOn(test)
		configurations("asciidoctorExt")
		baseDirFollowsSourceFile()
		inputs.dir(snippetsDir)
	}
	bootJar {
		if (System.getProperty("env") == null || System.getProperty("env") == "dev") {
			dependsOn(asciidoctor)
			from("build/docs/asciidoc") {
				into("static/docs")
			}
		}
	}
}

idea {
	module {
		val kaptMain = file("${layout.buildDirectory}/generated/querydsl")
		sourceDirs.add(kaptMain)
		generatedSourceDirs.add(kaptMain)
	}
}