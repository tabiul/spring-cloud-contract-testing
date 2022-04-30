package contracts.book

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should save book successfully")
    request {
        method 'POST'
        url '/api/book'
        body(
            name: "Spring Cloud Contract",
            author: "Spring"
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
            name: "Spring Cloud Contract",
            author: "Spring"
        )
        headers {
            contentType(applicationJson())
        }
    }
}
