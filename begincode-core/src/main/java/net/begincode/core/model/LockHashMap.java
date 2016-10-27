package net.begincode.core.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LockHashMap<K, V> extends HashMap<K, V> {

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return super.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return super.isEmpty();
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return super.get(key);
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return super.containsKey(key);
	}

	@Override
	public V put(K key, V value) {
		synchronized (this) {
			return super.put(key, value);
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		super.putAll(m);
	}

	@Override
	public V remove(Object key) {
		synchronized (this) {
			return super.remove(key);
		}
	}

	@Override
	public void clear() {
		synchronized (this) {
			super.clear();
		}
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return super.containsValue(value);
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return super.keySet();
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return super.values();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return super.entrySet();
	}

	@Override
	public V getOrDefault(Object key, V defaultValue) {
		// TODO Auto-generated method stub
		return super.getOrDefault(key, defaultValue);
	}

	@Override
	public V putIfAbsent(K key, V value) {
		// TODO Auto-generated method stub
		return super.putIfAbsent(key, value);
	}

	@Override
	public boolean remove(Object key, Object value) {
		// TODO Auto-generated method stub
		return super.remove(key, value);
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue) {
		// TODO Auto-generated method stub
		return super.replace(key, oldValue, newValue);
	}

	@Override
	public V replace(K key, V value) {
		// TODO Auto-generated method stub
		return super.replace(key, value);
	}

	@Override
	public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
		// TODO Auto-generated method stub
		return super.computeIfAbsent(key, mappingFunction);
	}

	@Override
	public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		// TODO Auto-generated method stub
		return super.computeIfPresent(key, remappingFunction);
	}

	@Override
	public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		// TODO Auto-generated method stub
		return super.compute(key, remappingFunction);
	}

	@Override
	public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		// TODO Auto-generated method stub
		return super.merge(key, value, remappingFunction);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) {
		// TODO Auto-generated method stub
		super.forEach(action);
	}

	@Override
	public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
		// TODO Auto-generated method stub
		super.replaceAll(function);
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
