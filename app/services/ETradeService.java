package services;


import javax.inject.Singleton;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
public class ETradeService {

    public CompletionStage<String> getStockData() {
        return CompletableFuture.supplyAsync(() -> talk2etrade());
    }


    public String talk2etrade() {
        return new Date() + "    AAPL 152";
    }
}
