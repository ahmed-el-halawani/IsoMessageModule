fun main(args: Array<String>) {
//    for (i in 1..64) {
////        println("val field${i} = BaseIsoField(3, IsoFieldConversionType.Bcd);")
//        println("field${i},")
//    }

    val baseIsoField = BaseIso();

    baseIsoField.parseIsoMessage("080020200000008000000000000000013239313130303031")
    baseIsoField.printData()

}