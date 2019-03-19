package apps.stp.converter.main

import org.junit.Assert.*
import org.junit.Test

class ConvertNumberModelTest {
    @Test
    fun main(){
        val model = ConvertNumberModel();
        var expectedValue : Int
        var actualValue : Int

        //base 2 to base 10
        expectedValue = 167
        actualValue = model.convertNumber("10100111", "2", "10").toInt()
        assertEquals(expectedValue, actualValue)

        //base 10 to base 2
        expectedValue = Integer.parseInt("10100111", 2);
        actualValue = Integer.parseInt(model.convertNumber("167", "10", "2"), 2)
        assertEquals(expectedValue, actualValue)

        //base 16 to base 8
        expectedValue = 2536337
        actualValue = model.convertNumber("ABCDF", "16", "8").toInt()
        assertEquals(expectedValue, actualValue)

        //base 8 to base 16
        expectedValue = 0xABCDF
        actualValue = Integer.parseInt(model.convertNumber("2536337", "8", "16"), 16)
        assertEquals(expectedValue, actualValue)

        //base 16 to base 10
        expectedValue = 15
        actualValue = model.convertNumber("F", "16", "10").toInt()
        assertEquals(expectedValue, actualValue)

        //base 2 to base 16
        expectedValue = Integer.parseInt("7F", 16);
        actualValue = Integer.parseInt(model.convertNumber("1111111", "2", "16"), 16)
        assertEquals(expectedValue, actualValue)

        //base 16 to base 2
        expectedValue = Integer.parseInt("1111111", 2);
        actualValue = Integer.parseInt(model.convertNumber("7F", "16", "2"), 2)
        assertEquals(expectedValue, actualValue)
    }
}