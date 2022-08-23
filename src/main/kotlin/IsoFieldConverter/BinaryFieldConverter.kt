package IsoFieldConverter


class BinaryFieldConverter(paddingWith: Char? = '0') : BaseIsoFieldConverter<String>(paddingWith) {

    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars * 8
    }

    override fun fromHex(hexValue: String): String {
        var binaryString = ""
        for (i in 2..hexValue.length step 2) {
            val intValue = Integer.parseInt(hexValue.substring(i - 2, i), 16)
            binaryString += paddingLeft(Integer.toBinaryString(intValue), 8)
        }
        return binaryString
    }

    override fun toHex(value: String, fieldLength: Int): String {
        val length = value.length
        var binaryString = ""
        for (i in 0 until fieldLength step 4) {
            val splitedBinary = value.substring(i, i + 4)
            val intValue = Integer.parseInt(splitedBinary, 2)
            binaryString += Integer.toHexString(intValue)
        }
        return binaryString.uppercase()
    }

    override fun inHexLength(numberOfChars: Int): Int {
        return numberOfChars / 4
    }

}