package fr.nil.spotify.spotifyAPI.api.controller;

import fr.nil.spotify.spotifyAPI.api.response.TokenResponse;
import fr.nil.spotify.spotifyAPI.api.type.RefreshTokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;


@RestController
public class SpotifyAPIController {

    @Value("#{systemEnvironment['SPOTIFY_CID']}")
    private final String CLIENT_ID = "b07e0f0337ea4efd81ee9f34c663cc80";
    @Value("#{systemEnvironment['SPOTIFY_CSRT']}")
    private final String CLIENT_SECRET = "7254025957b644058fe56929a10fedf0";

    /* @GetMapping("/callback")
     public RefreshTokenType getRefreshToken(@RequestParam(value = "code") String token) {

         System.out.println(token);

         return RefreshTokenType(token);
     }*/
    @Autowired
    private WebClient webClient;

    @PostMapping("/token")
    public Mono<TokenResponse> refreshToken(@RequestBody RefreshTokenType refreshTokenRequest) {
        return webClient
                .post()
                .uri("https://accounts.spotify.com/api/token")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "refresh_token")
                        .with("refresh_token", refreshTokenRequest.getRefreshToken()))
                .retrieve()
                .bodyToMono(TokenResponse.class);

    }

}
