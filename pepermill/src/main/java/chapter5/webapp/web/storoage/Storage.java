package chapter5.webapp.web.storoage;

import java.util.List;

public interface Storage <V> {
	 Integer save(V data);
	 V load(Integer key);
	 Integer getAtomicKey();
	 Integer getKeyValue(V data);
	 List<V> getAllValues();
}
