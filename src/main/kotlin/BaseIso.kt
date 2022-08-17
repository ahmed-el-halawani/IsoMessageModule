import IsoFieldConverter.AsciiFieldConverter
import IsoFieldConverter.BcdFieldConverter
import IsoFieldConverter.BinaryFieldConverter

class BaseIso {

    /** secondary bitmap */
    val field1 = BaseIsoField(64, BcdFieldConverter(), defaultValue = "0")

    /** primary account number `PAN` */
    val field2 = BaseIsoField(19, BcdFieldConverter())

    /** process code */
    val field3 = BaseIsoField(6, BcdFieldConverter())
    val field4 = BaseIsoField(3, BcdFieldConverter())
    val field5 = BaseIsoField(3, BcdFieldConverter())
    val field6 = BaseIsoField(3, BcdFieldConverter())
    val field7 = BaseIsoField(3, BcdFieldConverter())
    val field8 = BaseIsoField(3, BcdFieldConverter())
    val field9 = BaseIsoField(3, BcdFieldConverter())
    val field10 = BaseIsoField(3, BcdFieldConverter())

    /** System trace audit number (STAN) */
    val field11 = BaseIsoField(6, BcdFieldConverter())
    val field12 = BaseIsoField(3, BcdFieldConverter())
    val field13 = BaseIsoField(3, BcdFieldConverter())
    val field14 = BaseIsoField(3, BcdFieldConverter())
    val field15 = BaseIsoField(3, BcdFieldConverter())
    val field16 = BaseIsoField(3, BcdFieldConverter())
    val field17 = BaseIsoField(3, BcdFieldConverter())
    val field18 = BaseIsoField(3, BcdFieldConverter())
    val field19 = BaseIsoField(3, BcdFieldConverter())
    val field20 = BaseIsoField(3, BcdFieldConverter())
    val field21 = BaseIsoField(3, BcdFieldConverter())
    val field22 = BaseIsoField(3, BcdFieldConverter())
    val field23 = BaseIsoField(3, BcdFieldConverter())
    val field24 = BaseIsoField(3, BcdFieldConverter())
    val field25 = BaseIsoField(3, BcdFieldConverter())
    val field26 = BaseIsoField(3, BcdFieldConverter())
    val field27 = BaseIsoField(3, BcdFieldConverter())
    val field28 = BaseIsoField(3, BcdFieldConverter())
    val field29 = BaseIsoField(3, BcdFieldConverter())
    val field30 = BaseIsoField(3, BcdFieldConverter())
    val field31 = BaseIsoField(3, BcdFieldConverter())
    val field32 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field33 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field34 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field35 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field36 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field37 = BaseIsoField(3, BcdFieldConverter())
    val field38 = BaseIsoField(3, BcdFieldConverter())
    val field39 = BaseIsoField(3, BcdFieldConverter())
    val field40 = BaseIsoField(3, BcdFieldConverter())

    /** Card acceptor terminal identification (TID) */
    val field41 = BaseIsoField(16, AsciiFieldConverter())
    val field42 = BaseIsoField(3, BcdFieldConverter())
    val field43 = BaseIsoField(3, BcdFieldConverter())
    val field44 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field45 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field46 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field47 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field48 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field49 = BaseIsoField(3, BcdFieldConverter())
    val field50 = BaseIsoField(3, BcdFieldConverter())
    val field51 = BaseIsoField(3, BcdFieldConverter())
    val field52 = BaseIsoField(3, BcdFieldConverter())
    val field53 = BaseIsoField(3, BcdFieldConverter())
    val field54 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field55 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field56 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field57 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field58 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field59 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field60 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field61 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field62 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field63 = BaseIsoField(3, BcdFieldConverter(), isFixed = false)
    val field64 = BaseIsoField(3, BcdFieldConverter())

    var mti: String = ""
    var dataField: String = ""
    var isoMessage: String = ""
    private var bitmapList: List<Boolean> = emptyList()

    public fun parseIsoMessage(isoMessage: String) {
        this.isoMessage = isoMessage
        mti = extractMti(isoMessage)
        bitmapList = extractBitmapToList(isoMessage)
        dataField = extractDataField(isoMessage)
        parseDataField(bitmapList, dataField)
    }

    fun parseDataField(bitmapList: List<Boolean>, dataField: String) {
        var position = 0
        bitmapList.forEachIndexed { i, isParsable ->
            if (isParsable) {
                val field = fields[i]
                field.setHexValue(dataField.substring(position, position + field.length))
                position += field.length
            }
        }
    }

    fun extractMti(isoMessage: String): String {
        return isoMessage.substring(0, 4)
    }

    fun extractBitmapToList(isoMessage: String): List<Boolean> {
        return BinaryFieldConverter().fromHex(isoMessage.substring(4, 20)).toCharArray().map { it == '1' }
    }

    fun extractDataField(isoMessage: String): String {
        return isoMessage.substring(20)
    }

    fun printData() {
        bitmapList.forEachIndexed { i, isParsable ->
            if (isParsable) {
                val field = fields[i]
                println(String.format("[field%s] : %s", i + 1, field.toString()))
            }
        }
    }

    private val fields: List<BaseIsoField> = listOf(
        field1,
        field2,
        field3,
        field4,
        field5,
        field6,
        field7,
        field8,
        field9,
        field10,
        field11,
        field12,
        field13,
        field14,
        field15,
        field16,
        field17,
        field18,
        field19,
        field20,
        field21,
        field22,
        field23,
        field24,
        field25,
        field26,
        field27,
        field28,
        field29,
        field30,
        field31,
        field32,
        field33,
        field34,
        field35,
        field36,
        field37,
        field38,
        field39,
        field40,
        field41,
        field42,
        field43,
        field44,
        field45,
        field46,
        field47,
        field48,
        field49,
        field50,
        field51,
        field52,
        field53,
        field54,
        field55,
        field56,
        field57,
        field58,
        field59,
        field60,
        field61,
        field62,
        field63,
        field64,
    )
}