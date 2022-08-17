package IsoFieldConverter

import IsoFieldPaddingType

interface BaseIsoFieldConverter {
    public fun getLength(numberOfChars: Int): Int
    public fun fromHex(hexValue: String): String
    public fun toHex(value: String, paddingType: IsoFieldPaddingType): String
    public fun toHex(value: Int, paddingType: IsoFieldPaddingType): String
}