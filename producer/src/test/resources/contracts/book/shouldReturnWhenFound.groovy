package contracts.book

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return book when found")
    request {
        method 'GET'
        url '/api/book/1'
    }
    response {
        status 200
        body(
            name: "ABC",
            author: "tabiul"
        )
        headers {
            contentType(applicationJson())
        }
    }
}
