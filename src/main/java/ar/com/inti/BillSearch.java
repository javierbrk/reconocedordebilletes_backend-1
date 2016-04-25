package ar.com.inti;

import java.util.List;

public interface BillSearch {

	String search(BillImage needle, List<Bill> haystack);
}
