package br.com.fm.investment.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "clientCurrency", url = "${currency.quotes.api}")
public interface FeignClientCurrencyQuote {

    @GetMapping(value = "/all",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> currencyCuotesList();

}
