package demo.chiwoo.lotto.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import java.util.Collections.shuffle
import java.util.stream.Collectors

@Configuration
class LottoRouterFunction {

    @Bean
    fun health() = router {
        GET("/health") { _ -> ServerResponse.ok().bodyValue("OK") }
    }

    @Bean
    fun userRouter(lottoHandler: LottoHandler): RouterFunction<ServerResponse> {
        return router {
            GET("/api/lotto/lucky", lottoHandler::getLucky)
        }
    }

    @Component
    class LottoHandler {

        companion object {
            @Suppress("JAVA_CLASS_ON_COMPANION")
            val log: Logger = LoggerFactory.getLogger(javaClass)
        }

        val numbers = (1..45).toList()
        fun getLucky(req: ServerRequest): Mono<ServerResponse> {
            shuffle(numbers)
            shuffle(numbers)
            val result = numbers.stream().limit(6).sorted().collect(Collectors.toList())
            log.info("result: {}", result)
            return ok().bodyValue(result)
        }
    }
}
