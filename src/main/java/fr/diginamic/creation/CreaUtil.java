package fr.diginamic.creation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CreaUtil {

	public static List<String> lire(Path path) throws IOException {
		try {
			List<String> lignes = Files.readAllLines(path, StandardCharsets.UTF_8);
			lignes.remove(0);
			return lignes;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	public static String[] formate(String ligne) {

		return ligne.split(";");

	}

}
