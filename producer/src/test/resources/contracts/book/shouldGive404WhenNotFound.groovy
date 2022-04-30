package contracts.book

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return 404 when book is not found")
    request {
        method 'GET'
        url '/api/book/2'
    }
    response {
        status 404
    }
}