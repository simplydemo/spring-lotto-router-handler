package demo.chiwoo.lotto.api

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(
    excludeAutoConfiguration = [ReactiveUserDetailsServiceAutoConfiguration::class, ReactiveSecurityAutoConfiguration::class]
)
@RunWith(SpringRunner::class)
@AutoConfigureWebTestClient
@ContextConfiguration(classes = [LottoRouterFunction::class])
class LottoRouterFunctionTest {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        val log: Logger = LoggerFactory.getLogger(javaClass)
    }

    @Autowired
    private lateinit var webClient: WebTestClient

    @Test
    @Throws(Exception::class)
    fun testLottoGetLucky() {
        val responseBody: MutableList<Integer>? = webClient.get().uri("/api/lotto/lucky")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk
            .expectBodyList(Integer::class.java)
            .hasSize(6)
            .returnResult().responseBody

        assertThat(responseBody?.get(0)?.toInt()).isGreaterThanOrEqualTo(1)
        assertThat(responseBody?.get(5)?.toInt()).isLessThanOrEqualTo(45)
        log.info("responseBody: {}", responseBody)
    }
}
