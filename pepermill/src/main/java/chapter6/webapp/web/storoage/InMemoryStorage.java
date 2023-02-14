package chapter6.webapp.web.storoage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryStorage<V> implements Storage<V> {

	private static final Logger log = LoggerFactory.getLogger(InMemoryStorage.class);

	private AtomicInteger atomicKey = new AtomicInteger(0);
	private Map<Integer, V> storage = new ConcurrentHashMap<>();

	@Override
	public Integer save(final V data) {
		storage.put(atomicKey.get(), data);
		log.debug("InMemory storage Key : {}, Value : {}", atomicKey.get(), data);
		return atomicKey.getAndIncrement();
	}

	@Override
	public V load(final Integer key) {
		return storage.get(key);
	}

	@Override
	public Integer getAtomicKey() {
		return atomicKey.get();
	}

	@Override
	public Integer getKeyValue(final V data) {
		return storage.entrySet().stream()
			.filter(e -> data.equals(e.getValue()))
			.findFirst()
			.map(e -> e.getKey())
			.get();
	}

	@Override
	public List<V> getAllValues() {
		return storage.entrySet().stream()
			.map(e -> e.getValue())
			.collect(Collectors.toList());
	}
}