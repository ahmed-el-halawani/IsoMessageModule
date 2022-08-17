package IsoFieldConverter

import IsoFieldPaddingType

class Base64FieldConverter : BaseIsoFieldConverter {
    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars * 2
    }

    override fun fromHex(hexValue: String): String {
        return ""
    }

    override fun toHex(value: String, paddingType: IsoFieldPaddingType): String {
        TODO("Not yet implemented")
    }

    override fun toHex(value: Int, paddingType: IsoFieldPaddingType): String {
        TODO("Not yet implemented")
    }
}