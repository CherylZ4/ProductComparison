package com.example.ProductComparison.service;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;


@Service
public class DataProcessingService {
    public List<String> getIngredientsInCommon(List<String>list1, List<String>list2){
        List<String> similarItems = new ArrayList<>(list1);

        // Retain only the common items between list1 and list2
        similarItems.retainAll(list2);
        return similarItems;
    }

    public double calculateJaccardSimilarity(List<String> list1, List<String> list2) {


        // Intersection set
        List<String> intersection = new ArrayList<>();
        intersection.addAll(list1);
        intersection.retainAll(list2);
        System.out.print("inter" + intersection.size());


        // Union set
        Set<String> union = new HashSet<>(list1);
        union.addAll(list2);
        System.out.print("union" + union.size());

        // Calculate Jaccard similarity coefficient
        double jaccardSimilarity = (double) intersection.size() / union.size() * 100;

        return Math.round(jaccardSimilarity);
    }

}
