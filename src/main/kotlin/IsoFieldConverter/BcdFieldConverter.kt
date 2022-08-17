package IsoFieldConverter

import IsoFieldPaddingType
import IsoHelpers.paddingBCDLeft
import IsoHelpers.paddingBinaryLeft


class BcdFieldConverter : BaseIsoFieldConverter {

    override fun getLength(numberOfChars: Int): Int {
        return numberOfChars
    }

    override fun fromHex(hexValue: String): String {
        var binaryString = ""
        for (i in 2..hexValue.length step 2) {
            val intValue = Integer.parseInt(hexValue.substring(i - 2, i), 16)
            binaryString += paddingBCDLeft(intValue.toString())
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