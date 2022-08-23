import IsoFieldConverter.BinaryFieldConverter

class BaseIso : BaseIsoFields() {
    var mti: String = "0000"
    var isoMessage: String = ""
    private var bitmapList: List<Int> = emptyList()

    fun parseIsoMessage(isoMessage: String) {
        this.isoMessage = isoMessage
        mti = extractMti(isoMessage)
        bitmapList = extractBitmapToList(isoMessage)
        val dataField = extractDataField(isoMessage)
        parseDataField(bitmapList, dataField)
    }

    fun parseIsoMessage() {
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
            val hex = dataField.substring(position)
            field.setHexValue(hex)
            position += field.fieldLength
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
        return Array(64) { if (bitmapList.contains(it)) 1 else 0 }.joinToString("")
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
                bitmap.fieldLength,
                bitmap.hex,
                bitmap.value,
            )
        )
        bitmapList.forEachIndexed { i, fieldIndex ->
            val field = fields[fieldIndex]
            println(
                String.format(
                    "[field%s] : fieldLength:%s ||hexValueLength:%s ||  value:%s || hexValue:%s ",
                    fieldIndex + 1,
                    field.fieldLength,
                    field.valueLength,
                    field.value,
                    field.hex,
                )
            )
        }

        println("End print Iso message\n\n")

    }
}