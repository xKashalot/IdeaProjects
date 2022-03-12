package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;




public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        //попытка1
        /*Stream<String> text = br.lines();
        text.flatMap(x -> Arrays.stream(x.split("[\\p{Punct}\\s]+")))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .limit(10)
                .collect(Collectors.toList());
        text.forEach(System.out::println);*/

        //попытка3
                 br.lines().flatMap(x -> Stream.of(x.split("[\\p{Punct}\\s]+")))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .limit(10)
                .forEach(System.out::println);

        //попытка2
        /*List<String> text = Arrays.asList(br.readLine().split("[\\p{Punct}\\s]+"));
        //ArrayList<String> text2 = new ArrayList(List.of(br.readLine().split("[\\p{Punct}\\s]+")));
        List<String> result = text.stream()
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .limit(10)
                .collect(Collectors.toList());
        result.forEach(System.out::println);*/
    }
}
