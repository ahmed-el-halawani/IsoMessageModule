package IsoFieldConverter


class AsciiFieldConverter(paddingWith: Char? = ' ') : BaseIsoFieldConverter<String>(paddingWith) {

    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars
    }

    override fun inHexLength(numberOfChars: Int): Int {
        return numberOfChars * 2
    }

    override fun fromHex(hexValue: String): String {
        var binaryString = ""
        for (i in 2..hexValue.length step 2) {
            binaryString += Integer.parseInt(hexValue.substring(i - 2, i), 16).toChar()
        }
        return binaryString.trim()
    }

    override fun toHex(value: String, fieldLength: Int): String {
        var binaryString = ""

        if (paddingWith != null) {
            val returnValue = paddingRight(value, fieldLength)
            for (element in returnValue.toCharArray()) {
                binaryString += Integer.toHexString(element.code)
            }
        }
        return binaryString.uppercase()
    }

}