package IsoFieldConverter

import IsoFieldPaddingType
import IsoHelpers.paddingBinaryLeft

class BinaryFieldConverter : BaseIsoFieldConverter {

    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars
    }

    override fun fromHex(hexValue: String): String {
        var binaryString = ""
        for (i in 2..hexValue.length step 2) {
            val intValue = Integer.parseInt(hexValue.substring(i - 2, i), 16)
            binaryString += paddingBinaryLeft(Integer.toBinaryString(intValue))
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