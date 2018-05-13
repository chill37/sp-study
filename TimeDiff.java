import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeDiff {
	
	public static void main(String[] args) throws ParseException {
		String [][]hobbylist= {
				{"tabletennis","sports","seoul","15:30-17:15","10","4"},	
				{"booklove","book","suwon","16:00-18:00","3","5"},
				
		};
		
		timeDifferenceDays(hobbylist[0]);
		timeDifferenceMinutes(hobbylist[0]);
		timeDifferenceDuration(hobbylist[0]);
		
	}

	private static void timeDifferenceDuration(String[] list) {
		String t=list[3].replaceAll("-", ":");
		String[]time=t.split(":");
		String start=time[0];
		String end=time[1];
		
		LocalTime startTime= LocalTime.of(Integer.parseInt(time[0]),Integer.parseInt(time[1]));
		LocalTime endTime= LocalTime.of(Integer.parseInt(time[2]),Integer.parseInt(time[3]));
		
		long diff1=startTime.until(endTime, ChronoUnit.MINUTES); 
		long diff2= ChronoUnit.MINUTES.between(startTime, endTime);
		
		System.out.println(diff1);
		System.out.println(diff2);
	}

	private static void timeDifferenceMinutes(String[] list) throws ParseException {
		String[]time=list[3].split("-");
		String start=time[0];
		String end=time[1];
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = format.parse(start);
		Date date2 = format.parse(end);
		long diff = date2.getTime() - date1.getTime(); 
		
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000); 
		
		System.out.print(diffHours + " hours, ");
		System.out.print(diffMinutes + " minutes, ");
		System.out.println();
	}

	private static void timeDifferenceDays(String[] list) throws ParseException {
		String[]time=list[3].split("-");
		String start=time[0];
		String end=time[1];
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = format.parse(start);
		Date date2 = format.parse(end);
		long diff = date2.getTime() - date1.getTime(); 
		
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		System.out.print(diffDays + " days, ");
		System.out.print(diffHours + " hours, ");
		System.out.print(diffMinutes + " minutes, ");
		System.out.print(diffSeconds + " seconds.");
		System.out.println();
	}

}
