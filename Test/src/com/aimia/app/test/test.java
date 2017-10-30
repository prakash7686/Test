package com.aimia.app.test;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class test {
	@Test
	public void testReadFile() {

		try (BufferedReader br = Files.newBufferedReader(Paths.get(getClass().getResource("/Data.txt").toURI()))) {
			assertNotNull(br.readLine());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void testGender() {

		try (BufferedReader br = Files.newBufferedReader(Paths.get(getClass().getResource("/Data.txt").toURI()))) {
			assertNotNull(br.readLine());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
