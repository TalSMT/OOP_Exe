package Filter;

import java.io.Serializable;

import WiFi_data.WiFi_Scan;

/**
 * This interface represents a simple boolean filter
 * @author Boaz
 *
 */
public interface filter extends Serializable{
	/**
	 * test if the Record rec is pass the filter 
	 * @param rec
	 * @return true iff: the record pass the filter, else returns false 
	 */
	public boolean test(WiFi_Scan rec);
}
