package ar.com.inti;

import java.io.File;

public interface BillFactory {

	Bill createFromFile(File directory);

}
