//package com.example.OrderService.external.intercept;
//
//import java.io.IOException;
//
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//
//import brave.http.HttpRequest;
//
//public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
//
//    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
//
//    public RestTemplateInterceptor(
//            OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
//        this.oAuth2AuthorizedClientManager
//                = oAuth2AuthorizedClientManager;
//    }
//
//   
//
//	@Override
//	public ClientHttpResponse intercept(org.springframework.http.HttpRequest request, byte[] body,
//			ClientHttpRequestExecution execution) throws IOException {
//		// TODO Auto-generated method stub
//		
//		
//		request.getHeaders().add("Authorization",
//                "Bearer " +
//                oAuth2AuthorizedClientManager
//                        .authorize(OAuth2AuthorizeRequest
//                                .withClientRegistrationId("internal-client")
//                                .principal("internal")
//                                .build())
//                        .getAccessToken().getTokenValue());
//
//        return execution.execute(request, body);
//	}
//}