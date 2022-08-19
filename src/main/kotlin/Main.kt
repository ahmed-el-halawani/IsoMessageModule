fun main(args: Array<String>) {
//    for (i in 1..64) {
////        println("val field${i} = BaseIsoField(3, IsoFieldConversionType.Bcd);")
//        println("field${i},")
//    }

    // 3, 11, 12, 13, 39, 41, 42, 62,

    setDataToIso()
    parseIsoMessage()
//


//    baseIsoField.field3.setValue("120")
//    baseIsoField.field3.getHexValue()

}

fun parseIsoMessage() {
    val baseIsoField = BaseIso();
//    baseIsoField.parseIsoMessage("080020200000008000000000000000013239313130303031")
//    baseIsoField.parseIsoMessage("08102038000002C00004920000000039133901062330304E5041593536373835303836393820202020202020202000340032CB5B2AD3CF39CD29BEDDA0A368A0345AFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF")
    baseIsoField.parseIsoMessage("08002020000000C000009200000000394E50415935363738353038363938202020202020202020")

    baseIsoField.printSimpleData()
}


fun setDataToIso() {
    val baseIsoField = BaseIso()
    //3, 11, 41, 42,
    baseIsoField.mti = "0800"
    baseIsoField.field3.setFieldValue("920000")
    baseIsoField.field11.setFieldValue("39")
    baseIsoField.field41.setFieldValue("NPAY5678")
    baseIsoField.field42.setFieldValue("508698")
    baseIsoField.parseIsoMessage()
    baseIsoField.printSimpleData()
}
