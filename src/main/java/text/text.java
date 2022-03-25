package text;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class text {
	public static void main(String[] args) {

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		System.out.println(datetime);

		String num1 = "1.5";
		String num2 = "60";

		double d1 = Double.valueOf(num1);
		double d2 = Double.valueOf(num2);
		System.out.println(d1 + "," + d2);

		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		double doubleValue1 = b1.multiply(b2).doubleValue();// *
		double doubleValue2 = b1.add(b2).doubleValue();// +

		System.out.println(doubleValue1 + ";" + doubleValue2);
	}
}
