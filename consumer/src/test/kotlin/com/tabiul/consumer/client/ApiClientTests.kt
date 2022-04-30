package com.tabiul.consumer.client

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ExtendWith(SpringExtension::class)
@AutoConfigureStubRunner(ids = ["contract-testing:producer:+:stubs:8081"], stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@ContextConfiguration(classes = [TestClientConfiguration::class])
class ApiClientTests {

    @Autowired
    private lateinit var apiClient: ApiClient

    @Test
    fun testGetShouldReturn() {
        val book = apiClient.get(1)
        assert(book?.name == "ABC")
        assert(book?.author == "tabiul")
    }

    @Test
    fun testNotFoundShouldReturn404() {
        val e = assertThrows<HttpStatusCodeException> { apiClient.get(2) }
        assert(e.statusCode == HttpStatus.NOT_FOUND)
    }

    @Test
    fun testSaveShouldSave() {
        val book = apiClient.save(Book(name = "Spring Cloud Contract", author = "Spring"))
        assert(book?.name == "Spring Cloud Contract")
        assert(book?.author == "Spring")
    }
}


@TestConfiguration
class TestClientConfiguration {
    @Bean
    fun restTemplate(): RestTemplate {
        val builder = RestTemplateBuilder()
        return builder.rootUri("http://localhost:8081/api").build()
    }
}