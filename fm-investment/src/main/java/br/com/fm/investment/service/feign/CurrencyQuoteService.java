package br.com.fm.investment.service.feign;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CurrencyQuoteService {

    @Autowired
    private FeignClientCurrencyQuote clientCurrencyQuote;


    public ResponseEntity<?> currencyListAll(){

        try{
            ResponseEntity<?> listResponseEntity = clientCurrencyQuote.currencyCuotesList();
            return listResponseEntity;
        }catch (Exception e){
            return null;
        }

    }

}
