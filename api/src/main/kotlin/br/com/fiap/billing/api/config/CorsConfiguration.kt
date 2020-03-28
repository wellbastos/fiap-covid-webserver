package br.com.fiap.billing.api.config

import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.http.HttpMethod.OPTIONS


class CorsConfiguration : Filter {


    private val ORIGIN_NAME = "Access-Control-Allow-Origin"
    private val EXPOSE = "Access-Control-Expose-Headers"
    private val METHODS_NAME = "Access-Control-Allow-Methods"
    private val HEADERS_NAME = "Access-Control-Allow-Headers"
    private val MAX_AGE_NAME = "Access-Control-Max-Age"

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, resp: ServletResponse,
                          chain: FilterChain) {

        val response = resp as HttpServletResponse
        val request = req as HttpServletRequest
        response.setHeader(ORIGIN_NAME, "*")
        response.setHeader(METHODS_NAME, "POST, GET, PUT, PATCH, OPTIONS, DELETE")
        response.setHeader(MAX_AGE_NAME, "3600")
        response.setHeader(HEADERS_NAME, "x-requested-with, authorization, Content-Type, Authorization, Token")
        response.setHeader(EXPOSE, "Content-Disposition")

        if (OPTIONS.name.equals(request.method, ignoreCase = true)) {
            response.status = HttpServletResponse.SC_OK
        } else {
            chain.doFilter(request, response)
        }

    }

    @Throws(ServletException::class)
    override fun init(config: FilterConfig?) {
    }

    override fun destroy() {}

}