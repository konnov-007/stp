package apps.stp.converter.main

import org.junit.Assert
import org.junit.Test

class FloatingNumberTest {
    @Test
    fun main() {
        val model = ConvertNumberModel();
        var expectedValue : String
        var actualValue : String

        expectedValue = "-11.E"
        actualValue = model.convertNumber("-17.875", "10", "16")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "-17.875"
        actualValue = model.convertNumber("-11.E", "16", "10")
        Assert.assertEquals(expectedValue, actualValue)


        expectedValue = "170.25"
        actualValue = model.convertNumber("AA.4", "16", "10")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "AA.4"
        actualValue = model.convertNumber("170.25", "10", "16")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "85.6875"
        actualValue = model.convertNumber("1010101.1011", "2", "10")
        Assert.assertEquals(expectedValue, actualValue)


        expectedValue = "1010101.1011"
        actualValue = model.convertNumber("85.6875", "10", "2")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "55.B"
        actualValue = model.convertNumber("1010101.1011", "2", "16")
        Assert.assertEquals(expectedValue, actualValue)


        expectedValue = "1010101.1011"
        actualValue = model.convertNumber("55.B", "16", "2")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "A.CCCCCCC"
        actualValue = model.convertNumber("10.8", "10", "16")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "10.796875"
        actualValue = model.convertNumber("A.CCCCCCC", "16", "10", "6")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "0.1101"
        actualValue = model.convertNumber("0.847", "10", "2", "4")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "0.6615"
        actualValue = model.convertNumber("0.847", "10", "8", "4")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "9A7.44"
        actualValue = model.convertNumber("4647.214", "8", "16", "4")
        Assert.assertEquals(expectedValue, actualValue)

        expectedValue = "4647.214"
        actualValue = model.convertNumber("9A7.46", "16", "8", "4")
        Assert.assertEquals(expectedValue, actualValue)
    }
}
