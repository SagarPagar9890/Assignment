import java.util.Arrays;
import java.util.List;

public class Average {
	
	
	 private static double getAverage(List<Integer> list) {
	        return list.stream().mapToInt(a -> a).average().orElse(0);
	    }

	 
	public static void main(String[] args) {
	
		        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		 
		        double avg = getAverage(list);
		        System.out.println(avg);      
		    }
		

	}


