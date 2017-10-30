package com.aimia.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookReader {
	private Scanner read=null,read1=null;
	public static void main(String args[]) {
		new AddressBookReader().readFile();
	}

	public void readFile() {
		List<String> list = new ArrayList<>();
		List<AddressBookInfo> detailList = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(getClass().getResource("/Data.txt").toURI()))) {
			list = br.lines().collect(Collectors.toList());
			for (String s : list) {
				AddressBookInfo detail = new AddressBookInfo();
				String[] record = s.split(",");
				detail.setName(record[0]);
				detail.setGender(record[1]);
				detail.setDob(record[2]);
				String calAge[] = record[2].split("/");
				detail.setAge(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt("19".concat(calAge[2])));
				detailList.add(detail);
			}
			System.out.println("============");
			System.out.println("Address Info");
			System.out.println("============");
			detailList.forEach(System.out::println);
			System.out.println("\nEnter the Gender(Male/Female) to find the count:");
			read = new Scanner(System.in);
			String gender=read.nextLine().trim();
			if(gender.equalsIgnoreCase("Male")||gender.equalsIgnoreCase("Female"))
			{
				getGenderCount(detailList,gender);
			}
			else
			{
				System.out.println("Invalid Gender");
			}
			read.close();
			getOldestPerson(detailList);
			getDaysDifference(detailList);

						
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void getGenderCount(List<AddressBookInfo> detailList,String gender)
	{
		System.out.println("\nQuestion 1: How many male/Female are in the address book ?\n" + detailList.stream()
		.filter(detail -> detail.getGender().trim().equalsIgnoreCase(gender)).collect(Collectors.toList()).size());
	}
	
	public void getOldestPerson(List<AddressBookInfo> detailList)
	{
		final Comparator<AddressBookInfo> comp = (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge());
		AddressBookInfo oldest = detailList.stream().max(comp).get();
		System.out.println("Question 2: Who is the oldest person in the address book? \n" + oldest.getName()+" : "+ oldest.getAge());
	}
	
	public void getDaysDifference(List<AddressBookInfo> detailList)
	{
		List<AddressBookInfo> dayValidationList = detailList.stream()
				.filter(p -> p.getName().trim().contains("Bill") || p.getName().trim().contains("Paul"))
				.collect(Collectors.toList());
		List<Calendar> cal1 = calculateDateDiff(dayValidationList);
		System.out.println("Question 3: How many days older is Bill than Paul? \n" + daysBetween(cal1.get(0).getTime(), cal1.get(1).getTime()));

	}
	public List<Calendar> calculateDateDiff(List<AddressBookInfo> dayValidationList) {

		List<Calendar> calList = new ArrayList<Calendar>();

		for (AddressBookInfo detail : dayValidationList) {
			Calendar cal1 = new GregorianCalendar();
			int year = Integer.parseInt("19".concat(detail.getDob().trim().split("/")[2]));
			int month = Integer.parseInt(detail.getDob().trim().split("/")[1]) - 1;
			int day = Integer.parseInt(detail.getDob().trim().split("/")[0]);
			cal1.set(year, month, day);
			calList.add(cal1);
		}
		return calList;

	}

	public int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
}