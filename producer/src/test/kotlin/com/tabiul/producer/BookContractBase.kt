package com.tabiul.producer

import com.tabiul.producer.controller.BookController
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier


@SpringBootTest(classes = [Application::class])
@AutoConfigureMessageVerifier
class BookContractBase {

    @Autowired
    private lateinit var bookController: BookController

    @BeforeEach
    fun setup() {
        RestAssuredMockMvc.standaloneSetup(bookController)
    }
}