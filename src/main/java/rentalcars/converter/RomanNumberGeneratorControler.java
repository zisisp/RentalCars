package rentalcars.converter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zais on 8/27/2015.
 */
@RestController
public class RomanNumberGeneratorControler {

    public static final String RESULT = "result=";
    public static final String OK = "OK";
    private RomanNumeralGenerator romanNumeralGenerator=new RomanNumeralGeneratorImpl();

    @RequestMapping("/parse")
    public NumberResult parse(@RequestParam("roman") String roman) {
        int parsedNumber= romanNumeralGenerator.parse(roman);
        if (parsedNumber==-1) {
            return new NumberResult( -1, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        } else {
            return new NumberResult(parsedNumber, "OK");
        }
    }

    @RequestMapping("/generate")
    public RomanResult generate(@RequestParam("number") String numberStr) {

        int number= 0;
        try {
            number = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            return new RomanResult(null, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        }
        String generateRoman = romanNumeralGenerator.generate(number);
        if (RomanNumeralGeneratorImpl.NOT_SUPPORTED.equals(generateRoman)) {
            return new RomanResult(null, RomanNumeralGeneratorImpl.NOT_SUPPORTED);
        } else {
            return new RomanResult(generateRoman, OK);

        }
    }

}
