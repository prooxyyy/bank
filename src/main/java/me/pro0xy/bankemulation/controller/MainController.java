package me.pro0xy.bankemulation.controller;

import lombok.extern.slf4j.Slf4j;
import me.pro0xy.bankemulation.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 01:03</p>
 */

@RestController
@Slf4j
public class MainController {

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<Object>> root(){
        return new ApiResponse.ResponseBuilder()
                .setStatus(HttpStatus.OK)
                .addKeyValue("up", true)
                .build();
    }
}
