package implementation;


public interface Callback<K, V> {
	public void process(K key, V value);
}
