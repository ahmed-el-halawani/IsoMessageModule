import IsoFieldConverter.AsciiFieldConverter
import IsoFieldConverter.BcdFieldConverter
import IsoFieldConverter.BinaryFieldConverter
import IsoFieldConverter.HexFieldConverter

class BaseIso {

    private val bitmap = FixedIsoField(64, BinaryFieldConverter())

    /** secondary bitmap */
    val field1 = FixedIsoField(64, BcdFieldConverter())

    /** primary account number `PAN` */
    val field2 = FixedIsoField(19, BcdFieldConverter())

    /** process code */
    val field3 = FixedIsoField(6, BcdFieldConverter())
    val field4 = FixedIsoField(6, BcdFieldConverter())
    val field5 = FixedIsoField(6, BcdFieldConverter())
    val field6 = FixedIsoField(6, BcdFieldConverter())
    val field7 = FixedIsoField(6, BcdFieldConverter())
    val field8 = FixedIsoField(6, BcdFieldConverter())
    val field9 = FixedIsoField(6, BcdFieldConverter())
    val field10 = FixedIsoField(6, BcdFieldConverter())

    /** System trace audit number (STAN) */
    val field11 = FixedIsoField(6, BcdFieldConverter())
    val field12 = FixedIsoField(6, BcdFieldConverter())
    val field13 = FixedIsoField(4, BcdFieldConverter())
    val field14 = FixedIsoField(6, BcdFieldConverter())
    val field15 = FixedIsoField(6, BcdFieldConverter())
    val field16 = FixedIsoField(6, BcdFieldConverter())
    val field17 = FixedIsoField(6, BcdFieldConverter())
    val field18 = FixedIsoField(6, BcdFieldConverter())
    val field19 = FixedIsoField(6, BcdFieldConverter())
    val field20 = FixedIsoField(6, BcdFieldConverter())
    val field21 = FixedIsoField(6, BcdFieldConverter())
    val field22 = FixedIsoField(6, BcdFieldConverter())
    val field23 = FixedIsoField(6, BcdFieldConverter())
    val field24 = FixedIsoField(6, BcdFieldConverter())
    val field25 = FixedIsoField(6, BcdFieldConverter())
    val field26 = FixedIsoField(6, BcdFieldConverter())
    val field27 = FixedIsoField(6, BcdFieldConverter())
    val field28 = FixedIsoField(6, BcdFieldConverter())
    val field29 = FixedIsoField(6, BcdFieldConverter())
    val field30 = FixedIsoField(6, BcdFieldConverter())
    val field31 = FixedIsoField(6, BcdFieldConverter())
    val field32 = DynamicIsoField(6, BcdFieldConverter())
    val field33 = DynamicIsoField(6, BcdFieldConverter())
    val field34 = DynamicIsoField(6, BcdFieldConverter())
    val field35 = DynamicIsoField(6, BcdFieldConverter())
    val field36 = DynamicIsoField(6, BcdFieldConverter())
    val field37 = FixedIsoField(6, BcdFieldConverter())
    val field38 = FixedIsoField(6, BcdFieldConverter())
    val field39 = FixedIsoField(4, BcdFieldConverter())
    val field40 = FixedIsoField(6, BcdFieldConverter())

    /** Card acceptor terminal identification (TID) */
    val field41 = FixedIsoField(8, AsciiFieldConverter())
    val field42 = FixedIsoField(15, AsciiFieldConverter())
    val field43 = FixedIsoField(6, BcdFieldConverter())
    val field44 = DynamicIsoField(6, BcdFieldConverter())
    val field45 = DynamicIsoField(6, BcdFieldConverter())
    val field46 = DynamicIsoField(6, BcdFieldConverter())
    val field47 = DynamicIsoField(6, BcdFieldConverter())
    val field48 = DynamicIsoField(6, BcdFieldConverter())
    val field49 = FixedIsoField(6, BcdFieldConverter())
    val field50 = FixedIsoField(6, BcdFieldConverter())
    val field51 = FixedIsoField(6, BcdFieldConverter())
    val field52 = FixedIsoField(6, BcdFieldConverter())
    val field53 = FixedIsoField(6, BcdFieldConverter())
    val field54 = DynamicIsoField(6, BcdFieldConverter())
    val field55 = DynamicIsoField(6, BcdFieldConverter())
    val field56 = DynamicIsoField(6, BcdFieldConverter())
    val field57 = DynamicIsoField(6, BcdFieldConverter())
    val field58 = DynamicIsoField(6, BcdFieldConverter())
    val field59 = DynamicIsoField(6, BcdFieldConverter())
    val field60 = DynamicIsoField(6, BcdFieldConverter())
    val field61 = DynamicIsoField(6, BcdFieldConverter())
    val field62 = DynamicIsoField(999, HexFieldConverter())
    val field63 = DynamicIsoField(6, BcdFieldConverter())
    val field64 = FixedIsoField(6, BcdFieldConverter())

    var mti: String = ""
    var dataField: String = ""
    var isoMessage: String = ""
    private var bitmapList: List<Int> = emptyList()

    public fun parseIsoMessage(isoMessage: String) {
        this.isoMessage = isoMessage
        mti = extractMti(isoMessage)
        bitmapList = extractBitmapToList(isoMessage)
        dataField = extractDataField(isoMessage)
        parseDataField(bitmapList, dataField)
    }

    public fun parseIsoMessage() {
        this.isoMessage = mti
        bitmapList = extractBitmapFromFieldsToList()
        bitmap.setFieldValue(bitmapToBinary(bitmapList))
        this.isoMessage += bitmapToHex(bitmapList)
        this.isoMessage += parseDataField(bitmapList)
    }

    fun parseDataField(bitmapList: List<Int>, dataField: String) {
        var position = 0
        bitmapList.forEach { fieldIndex ->
            val field = fields[fieldIndex]
            field.setHexValue(dataField.substring(position))
            position += field.length
        }
    }

    fun parseDataField(bitmapList: List<Int>): String {
        var dataField = ""
        bitmapList.forEach { fieldIndex ->
            val field = fields[fieldIndex]
            dataField += field.hex
        }
        return dataField
    }

    fun extractMti(isoMessage: String): String {
        return isoMessage.substring(0, 4)
    }

    fun extractBitmapToList(isoMessage: String): List<Int> {
        bitmap.setHexValue(isoMessage.substring(4))
        return bitmap.value?.toCharArray()
            ?.mapIndexed { i, it -> if (it == '1') i else null }?.filterNotNull() ?: emptyList()
    }

    fun extractBitmapFromFieldsToList(): List<Int> {
        return fields.mapIndexed { index, field ->
            if (field.value != null) index else null
        }.filterNotNull()
    }

    fun bitmapToBinary(bitmapList: List<Int>): String {
        return Array(63) { if (bitmapList.contains(it)) 1 else 0 }.joinToString("")
    }

    fun bitmapToHex(bitmapList: List<Int>): String {
        return BinaryFieldConverter().toHex(bitmapToBinary(bitmapList), 64)
    }

    fun extractDataField(isoMessage: String): String {
        return isoMessage.substring(20)
    }

    fun printDataFullData() {
        bitmapList.forEachIndexed { i, fieldIndex ->
            val field = fields[fieldIndex]
            println(String.format("[field%s] : %s", i + 1, field.toString()))
        }
    }

    fun printSimpleData() {
        println("print Iso message")

        println(
            String.format(
                "[iso Message] : %s",
                isoMessage,
            )
        )

        println(
            String.format(
                "[mti] : %s",
                mti,
            )
        )

        println(
            String.format(
                "[bitmap] : %s",
                bitmapList.joinToString("") { "%s, ".format((it + 1)) },
            )
        )

        println(
            String.format(
                "[bitmap] : %s",
                bitmapToBinary(bitmapList),
            )
        )


        println(
            String.format(
                "[field%s] : length:%s || hexValue:%s || value:%s",
                " bitmap ",
                bitmap.length,
                bitmap.hex,
                bitmap.value,
            )
        )
        bitmapList.forEachIndexed { i, fieldIndex ->
            val field = fields[fieldIndex]
            println(
                String.format(
                    "[field%s] : length:%s || hexValue:%s || value:%s",
                    fieldIndex + 1,
                    field.length,
                    field.hex,
                    field.value,
                )
            )
        }

        println("End print Iso message\n\n")

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