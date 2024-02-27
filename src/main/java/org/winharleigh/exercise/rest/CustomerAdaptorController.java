package org.winharleigh.exercise.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerAdaptorController {

    @Operation(summary = "getCustomerContactDetail")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account balance set to zero"),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))})*/
    @GetMapping(value = "/contacts/{accountNumber}"/*, produces = APPLICATION_JSON_VALUE*/)
    public void getCustomerContactDetail(@PathVariable final String accountNumber) {
        System.out.println("HERE!!" + accountNumber);
    }
}
