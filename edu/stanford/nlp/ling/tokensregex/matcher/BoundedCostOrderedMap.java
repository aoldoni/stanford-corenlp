package edu.stanford.nlp.ling.tokensregex.matcher;

import edu.stanford.nlp.util.BinaryHeapPriorityQueue;
import java.util.function.Function;
import edu.stanford.nlp.util.PriorityQueue;

import java.util.*;

/**
 * Map that is sorted by cost - keep lowest scores
 *  When deciding what item to keep with the same cost, ties are arbitrarily broken
 * @author Angel Chang
 */
public class BoundedCostOrderedMap<K,V> extends AbstractMap<K,V> {
  /**
   * Limit on the size of the map
   */
  final int maxSize;
  /**
   * Limit on the maximum allowed cost
   */
  final double maxCost;
  /**
   * Priority queue on the keys - note that the priority queue only orders on the cost,
   * We can't control the ordering of keys with the same cost
   */
  PriorityQueue<K> priorityQueue = new BinaryHeapPriorityQueue<>();
  /** Map of keys to their values */
  Map<K,V> valueMap = new HashMap<>();
  /** Cost function on the values */
  Function<V,Double> costFunction;

  public BoundedCostOrderedMap(Function<V,Double> costFunction, int maxSize, double maxCost) {
    this.costFunction = costFunction;
    this.maxSize = maxSize;
    this.maxCost = maxCost;
  }

  @Override
  public int size() {
    return valueMap.size();
  }

  @Override
  public boolean isEmpty() {
    return valueMap.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return valueMap.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return valueMap.containsValue(value);
  }

  @Override
  public V get(Object key) {
    return valueMap.get(key);
  }

  public double getCost(V value) {
    return costFunction.apply(value);
  }

  @Override
  public V put(K key, V value) {
    double cost = getCost(value);
    if (cost >= maxCost) return null;
    V v = valueMap.get(key);
    if (v != null && getCost(v) < cost) return null;
    if (maxSize > 0 && priorityQueue.size() >= maxSize ) {
      if (priorityQueue.getPriority() > cost) {
        K k = priorityQueue.removeFirst();
        valueMap.remove(k);
        // keep maxSize lowest scores
        priorityQueue.changePriority(key, cost);
        return valueMap.put(key, value);
      }
    } else {
      priorityQueue.changePriority(key, cost);
      return valueMap.put(key, value);
    }
    return null;
  }

  @Override
  public V remove(Object key) {
    priorityQueue.remove(key);
    return valueMap.remove(key);
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    for (K key:m.keySet()) {
      put(key, m.get(key));
    }
  }

  @Override
  public void clear() {
    valueMap.clear();
    priorityQueue.clear();
  }

  @Override
  public Set<K> keySet() {
    return valueMap.keySet();
  }

  @Override
  public Collection<V> values() {
    return valueMap.values();
  }

  public List<V> valuesList() {
    List<V> list = new ArrayList<>();
    for (K k: priorityQueue.toSortedList()) {
      list.add(valueMap.get(k));
    }
    Collections.reverse(list);
    return list;
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    return valueMap.entrySet();
  }

  public double topCost() { return priorityQueue.getPriority(); }
  public K topKey() { return priorityQueue.getFirst(); }
}
