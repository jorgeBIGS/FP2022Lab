package fp.tipos.hotel.test;

import fp.tipos.hoteles.CategoriaHotel;
import fp.tipos.hoteles.Hotel;

public class TestHotel {

	public static void main(String[] args) {
		Hotel h =new Hotel("Hotel Alfonso XIII", "Alfonsino",CategoriaHotel.CINCO);
		System.out.println(h.getCadenaConFormato());

	}

}
