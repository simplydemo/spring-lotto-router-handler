package demo.chiwoo.lotto.api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Configuration
open class LottoRouterFunction {

    @Bean
    open fun health() = router {
        GET("/health") { _ -> ServerResponse.ok().bodyValue("OK") }
    }

    @Bean
    open fun userRouter(lottoHandler: LottoHandler): RouterFunction<ServerResponse> {
        return router {
            GET("/api/lotto/lucky", lottoHandler::generateLotto645)
        }
    }

    @Component
    class LottoHandler {

        companion object {
            @Suppress("JAVA_CLASS_ON_COMPANION")
            val log: Logger = LoggerFactory.getLogger(javaClass)
        }

        fun generateLotto645(req: ServerRequest): Mono<ServerResponse> {
            val count = req.queryParam("count").map {
                try {
                    val i = it.toInt()
                    if (i > 99) 99 else i
                } catch (e: NumberFormatException) {
                    1
                }
            }
            .orElse(1)

            if (count !in 1..1000) {
                return ServerResponse
                    .badRequest()
                    .bodyValue("Invalid count: must be between 1 and 1000")
            }
            val lottoSets = List(count) {
                (1..45).shuffled().take(6).sorted()
            }
            val result = if (count == 1) lottoSets[0] else lottoSets
            return ServerResponse.ok().bodyValue(result)
        }
    }
}
