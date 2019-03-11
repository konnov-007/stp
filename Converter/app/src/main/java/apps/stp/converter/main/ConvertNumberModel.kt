package apps.stp.converter.main


class ConvertNumberModel {
   fun convertNumber(inputNumber: String, inputNumberSystem: String, resultNumberSystem: String) =
      Integer.toString(Integer.parseInt(inputNumber, inputNumberSystem.toInt()), resultNumberSystem.toInt())
}