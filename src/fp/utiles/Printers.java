package fp.utiles;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Printers {
	
	public static <T, E> void mostrarMapConColeccion(Map<T, List<E>> m, Integer n) {
		for(Entry<T, List<E>> entry: m.entrySet()) {
			System.out.println(entry.getKey());
			List<E> lv = entry.getValue();
			if (n!= -1 && lv.size() >n){
				lv = lv.subList(0, n);
			}
			imprimeColeccion(lv);
		}	
	}
	
	public static <E> void imprimeColeccion(Collection<E> coleccion) {
		coleccion.stream().forEach(x -> System.out.println(x));
	}
	
	public static <K, V> void imprimeMap(Map<K, V> map) {
	    for (Map.Entry<K, V> entry : map.entrySet()) {          
	         System.out.println( entry.getKey() + " --> " + entry.getValue() );
	    }
	}
	
	public static <T, E> void imprimeMapConSet(Map<T, Set<E>> m, Integer n) {
		for(Entry<T, Set<E>> entry: m.entrySet()) {
			System.out.println(entry.getKey());
			Set<E> lv = entry.getValue();
			if (n!= -1 && lv.size() > n){
				lv = lv.stream().limit(n).collect(Collectors.toSet());
			}
			imprimeColeccion(lv);
		}	
	}
}
