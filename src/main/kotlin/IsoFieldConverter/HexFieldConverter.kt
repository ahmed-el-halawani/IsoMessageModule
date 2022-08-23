package IsoFieldConverter

class HexFieldConverter(paddingWith: Char? = ' ') : BaseIsoFieldConverter<String>(paddingWith) {

    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars
    }

    override fun fromHex(hexValue: String): String {
        return hexValue
    }

    override fun toHex(value: String, fieldLength: Int): String {
        return value.uppercase()
    }

    override fun inHexLength(numberOfChars: Int): Int {
        return numberOfChars
    }

}