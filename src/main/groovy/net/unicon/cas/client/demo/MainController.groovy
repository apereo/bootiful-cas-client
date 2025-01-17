package net.unicon.cas.client.demo


import org.apereo.cas.client.authentication.AttributePrincipal
import org.apereo.cas.client.boot.configuration.CasClientConfigurer
import org.apereo.cas.client.boot.configuration.EnableCasClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Controller
@EnableCasClient
class MainController implements CasClientConfigurer {

    @Value('${casLogoutUrl}')
    private String casLogoutUrl;

    String getCasLogoutUrl() {
        return casLogoutUrl
    }

    @RequestMapping(value = '/', method = RequestMethod.GET)
    def index(HttpServletRequest request, HttpServletResponse response, Model model) {
        'index'
    }

    @RequestMapping(value = '/protected', method = RequestMethod.GET)
    def protected1(HttpServletRequest request, Model model) {
        AttributePrincipal principal = request.userPrincipal
        model.addAttribute('principal', principal)
        'protected'
    }


    /**
     * Example of customizing the filter config for any 'exotic' properties that are not exposed via properties file
     */
    @Override
    void configureValidationFilter(FilterRegistrationBean validationFilter) {
        //This is Groovy. Below this, is the example (commented out) on how to do it in Java lang.
        validationFilter.initParameters.millisBetweenCleanUps = '120000'
        //validationFilter.getInitParameters().put("millisBetweenCleanUps", "120000");
    }

    /**
     * Example of customizing the filter config for any 'exotic' properties that are not exposed via properties file
     */
    /*@Override
    void configureAuthenticationFilter(FilterRegistrationBean authenticationFilter) {
        //This is Groovy. Below this, is the example (commented out) on how to do it in Java lang.
        authenticationFilter.initParameters.artifactParameterName = 'casTicket'
        authenticationFilter.initParameters.serviceParameterName = 'targetService'

        //authenticationFilter.getInitParameters().put("artifactParameterName", "casTicket");
        //authenticationFilter.getInitParameters().put("serviceParameterName", "targetService");
    }*/
}
