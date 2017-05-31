package edu.stanford.nlp.ie.machinereading;

import java.util.Map;
import java.util.List;
import java.util.Arrays;

public class NerTagNormalizer {
  // Internal list of NER tags
  List<String> tags;

  public NerTagNormalizer(String possibleEntities) {
    this.tags = Arrays.asList(possibleEntities.split("\\s*,\\s*"));
  }

  // Returns normalized NER Tag
  public String getNormalisedTag(String tag) {
    for (String current : this.tags) {
      if (current.equalsIgnoreCase(tag)) {
          return current;
      }
    }

    return "";
  }

  // Populates Hashmap
  public void populateHashmap(Map<String, String> entityTagForNer) {
    for (String current : this.tags) {
      entityTagForNer.put(current.toLowerCase(), current.toUpperCase());
    }
  }
}