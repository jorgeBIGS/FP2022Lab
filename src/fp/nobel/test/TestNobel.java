package fp.nobel.test;

import fp.nobel.Genero;
import fp.nobel.Premio;

public class TestNobel {

	public static void main(String[] args) {
		Premio p = new Premio(1050, "Informática", "Jorge", "Garcia", Genero.MALE, 1981);
		System.out.println(p);

	}

}
