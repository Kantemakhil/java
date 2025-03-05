package net.syscon.s4.triggers;

public interface StockLocationsTjnRepository {
	StockLocations getStockLocations(StockLocations stockLocations);

	Integer insert(StockLocations stockLocations);

	Integer update(StockLocations stockLocations);

	Integer delete(StockLocations stockLocations);
}
