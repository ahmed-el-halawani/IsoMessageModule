package IsoFieldConverter


class Base64FieldConverter(paddingWith: Char? = ' ') : BaseIsoFieldConverter(paddingWith) {
    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars
    }

    override fun fromHex(hexValue: String): String {
        return ""
    }

    override fun toHex(value: String, fieldLength: Int): String {
        TODO("Not yet implemented")
    }

    override fun inHexLength(numberOfChars: Int): Int {
        return numberOfChars * 2
    }

}