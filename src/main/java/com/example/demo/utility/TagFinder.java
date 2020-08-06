package com.example.demo.utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagFinder {
    public static List<String> findTags(String content) {
        List<String> words = Arrays.asList(content.split(" "));
        List<String> tags = words.stream().filter(word -> word.startsWith("@")).collect(Collectors.toList());
        tags.replaceAll(tag -> tag.replaceFirst("@", ""));

        return tags;
    }
}
