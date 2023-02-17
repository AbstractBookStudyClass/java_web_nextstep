package chapter6.webapp.web.storoage;

import java.util.List;

public interface Storage <V> {
	 Integer save(V data);
	 V load(Integer key);
	 Integer getAtomicKey();
	 Integer getKeyValue(V data);
	 List<V> getAllValues();
	void delete(V user);
}
