package com.ddam40.chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.*;

public class Chpater11 {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futerPrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        try {
            double price = futerPrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrieveTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returened after " + retrieveTime + "msecs");

        start = System.nanoTime();
        findPrices2("Hello World").forEach(System.out::println);
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"), new Shop("LetsSave"), new Shop("MyFavorite"), new Shop("ByNow"),
            new Shop("AVC Mart"), new Shop("Corona19"), new Shop("Coupang"));

    public static List<String> findPrices2(String product) {
        List<CompletableFuture<String>> priceFeatures =
                shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(()-> shop.getPrice(product)))
                    .map(future -> future.thenApply(Quote::parse))
                    .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))))
                    .collect(toList());
        return priceFeatures.stream()
                    .map(CompletableFuture::join)
                    .collect(toList());
/*
        return shops.parallelStream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
*/
    }
    public static List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(() -> {
                        return shop.getName() + " price is " + shop.getPrice(product);
                    }))
                    .collect(toList());
        return priceFutures.stream()
                    .map(CompletableFuture::join)
                    .collect(toList());
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Shop {
    private String name;

    public Shop(String shopName) {
        this.name = shopName;
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%,2f:%s", name, price, code);
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        Chpater11.delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getName() {
        return name;
    }
}
