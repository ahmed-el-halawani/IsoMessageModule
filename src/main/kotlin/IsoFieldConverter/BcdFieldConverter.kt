package IsoFieldConverter

import utils.IsoHelpers.paddingLeft


class BcdFieldConverter(paddingWith: Char? = '0') : BaseIsoFieldConverter<String>(paddingWith) {

    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars * 2
    }

    override fun fromHex(hexValue: String): String {
        return hexValue.toLong().toString()
    }

    override fun toHex(value: String, fieldLength: Int): String {
        return paddingLeft(value, fieldLength, "0")
    }

    override fun inHexLength(numberOfChars: Int): Int {
        return numberOfChars
    }

}