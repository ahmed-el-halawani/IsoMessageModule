package IsoFieldConverter

class IntegerFieldConverter:BaseIsoFieldConverter<String>() {
    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars * 2
    }

    override fun inHexLength(numberOfChars: Int): Int {
        return numberOfChars
    }

    override fun fromHex(hexValue: String): String {
        return Integer.parseInt(hexValue,16).toString()
    }

    override fun toHex(value: String, fieldLength: Int): String {
        return Integer.toHexString(Integer.parseInt(value))
    }
}