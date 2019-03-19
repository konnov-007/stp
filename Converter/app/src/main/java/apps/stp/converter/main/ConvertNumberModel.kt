package apps.stp.converter.main

import apps.stp.converter.util.Misc


class ConvertNumberModel {

   fun numberIsCorrect(inputNumberString : String) : Boolean{
      if(inputNumberString.length > Misc.MAX_NUMBER_LENGTH) {
         return false
      }

      val numberOfDots = inputNumberString.length - inputNumberString.replace(Misc.DOT_SYMBOL, "").length
      if(numberOfDots > 1) {
         return false
      }

      if(inputNumberString.contains("-") && inputNumberString.indexOf("-") != 0) {
         return false
      }

      val numberOfMinuses = inputNumberString.length - inputNumberString.replace("-", "").length
      if(numberOfMinuses > 1) {
         return false
      }

      return true
   }


   fun convertNumber(inputNumber: String,
                     inputNumberSystem: String,
                     resultNumberSystem: String,
                     accuracy: String
   ): String { //converting number with accuracy
      var inputNumberSystem = inputNumberSystem
      if (!inputNumber.contains(Misc.DOT_SYMBOL)) { //if it's an integer
         val convertedNumber = convertInteger(inputNumber, inputNumberSystem, resultNumberSystem)
         return convertedNumber.toUpperCase()
      } else { // if it's a number with a fraction
         val splitNumber =
            inputNumber.split(("\\" + Misc.DOT_SYMBOL).toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
         val leftPart = convertInteger(splitNumber[0], inputNumberSystem, resultNumberSystem) //converted integer
         var rightPart = splitNumber[1] //fraction
         if (Integer.valueOf(inputNumberSystem) != 10 && Integer.valueOf(resultNumberSystem) != 10) {
            rightPart = getFraction(rightPart, inputNumberSystem, "10")
            inputNumberSystem = "10"
            rightPart = getFraction(rightPart, inputNumberSystem, resultNumberSystem)
         } else {
            rightPart = getFraction(splitNumber[1], inputNumberSystem, resultNumberSystem)
         }
         if(rightPart.length > Integer.valueOf(accuracy)) {
            rightPart = rightPart.substring(0, Integer.valueOf(accuracy))
         }
         return leftPart.toUpperCase() + "." + rightPart.toUpperCase()
      }
   }


   fun convertNumber(inputNumber: String, inputNumberSystem: String, resultNumberSystem: String): String {
      var inputNumberSystem = inputNumberSystem
      if (!inputNumber.contains(Misc.DOT_SYMBOL)) { //if it's an integer
         val convertedNumber = convertInteger(inputNumber, inputNumberSystem, resultNumberSystem)
         return convertedNumber.toUpperCase()
      } else { // if it's a number with a fraction
         val splitNumber =
            inputNumber.split(("\\" + Misc.DOT_SYMBOL).toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
         val leftPart = convertInteger(splitNumber[0], inputNumberSystem, resultNumberSystem) //converted integer
         var rightPart = splitNumber[1] //fraction
         if (Integer.valueOf(inputNumberSystem) != 10 && Integer.valueOf(resultNumberSystem) != 10) {
            rightPart = getFraction(rightPart, inputNumberSystem, "10")
            inputNumberSystem = "10"
            rightPart = getFraction(rightPart, inputNumberSystem, resultNumberSystem)
         } else {
            rightPart = getFraction(splitNumber[1], inputNumberSystem, resultNumberSystem)
         }
         return leftPart.toUpperCase() + "." + rightPart.toUpperCase()
      }
   }

   private fun convertInteger(inputNumber: String, inputNumberSystem: String, resultNumberSystem: String): String {
      return Integer.toString(
         Integer.parseInt(inputNumber, Integer.parseInt(inputNumberSystem)),
         Integer.parseInt(resultNumberSystem)
      )
   }

   private fun getFraction(numberAfterDot: String, inputNumberSystem: String, resultNumberSystem: String): String {
      when (Integer.valueOf(inputNumberSystem)) {
         10 -> {
            run {
               //if converting from a number with base 10
               val resultBuilder = StringBuilder()
               var nextMultiplier = numberAfterDot
               while (true) {
                  if (resultBuilder.length >= Misc.DEFAULT_ACCURACY || nextMultiplier.toDouble() == 0.toDouble()) {
                     return resultBuilder.toString()
                  }
                  val multipliedNumber =
                     java.lang.Double.parseDouble(resultNumberSystem) * java.lang.Double.parseDouble("0" + Misc.DOT_SYMBOL + nextMultiplier)
                  val resultDigit =
                     multipliedNumber.toString().split(("\\" + Misc.DOT_SYMBOL).toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                  nextMultiplier =
                     multipliedNumber.toString().split(("\\" + Misc.DOT_SYMBOL).toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                  resultBuilder.append(convertInteger(resultDigit, inputNumberSystem, resultNumberSystem))
               }
            }
         }
         else -> {
            var resultString = convertFractionToBase10(numberAfterDot, inputNumberSystem, resultNumberSystem)
            if (resultString.length > 7) {
               resultString = resultString.substring(0, 7)
            }
            return resultString
         }
      }
   }

   private fun convertFractionToBase10(
      numberAfterDot: String,
      inputNumberSystem: String,
      resultNumberSystem: String
   ): String {
      var resultNumber = 0.0
      for (i in 0 until numberAfterDot.length) {
         val sourceInt = Integer.parseInt(numberAfterDot[i].toString(), Integer.valueOf(inputNumberSystem))
         val resultDigit =
            sourceInt * Math.pow(java.lang.Double.parseDouble(inputNumberSystem), (-1 * (i + 1)).toDouble())
         resultNumber += resultDigit
         if(resultNumber.toString().length > 6) { //if the fraction is too long
            break
         }
      }
      val numberWithoutDot = Integer.parseInt(
         resultNumber.toString().split(("\\" + Misc.DOT_SYMBOL).toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1],
         Integer.valueOf(resultNumberSystem)
      )
      return numberWithoutDot.toString()
   }
}

/////https://calculatori.ru/perevod-chisel.html
////https://moodle.ivkhk.ee/mod/page/view.php?id=2830