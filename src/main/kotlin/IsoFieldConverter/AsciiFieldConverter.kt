package IsoFieldConverter

import IsoFieldPaddingType

class AsciiFieldConverter : BaseIsoFieldConverter {

    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars * 2
    }

    override fun fromHex(hexValue: String): String {
        var binaryString = ""
        for (i in 2..hexValue.length step 2) {
            binaryString += Integer.parseInt(hexValue.substring(i - 2, i), 16).toChar()
        }
        return binaryString
    }

    override fun toHex(value: String, paddingType: IsoFieldPaddingType): String {
        TODO("Not yet implemented")
    }

    override fun toHex(value: Int, paddingType: IsoFieldPaddingType): String {
        TODO("Not yet implemented")
    }
}