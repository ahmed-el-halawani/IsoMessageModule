import IsoFieldConverter.AsciiFieldConverter
import IsoFieldConverter.BcdFieldConverter
import IsoFieldConverter.BinaryFieldConverter
import standerIsoFields.DynamicIsoField
import standerIsoFields.FixedIsoField

abstract class IsoFields {
    protected val bitmap = FixedIsoField(64, BinaryFieldConverter())

    /** secondary bitmap */
    val field1 = FixedIsoField(64, BcdFieldConverter())

    /** primary account number `PAN` */
    val field2 = DynamicIsoField(19, BcdFieldConverter(), lengthOfLengthInHex = 1)

    /** process code */
    val field3 = FixedIsoField(6, BcdFieldConverter())
    val field4 = FixedIsoField(12, BcdFieldConverter())
    val field5 = FixedIsoField(12, BcdFieldConverter())
    val field6 = FixedIsoField(12, BcdFieldConverter())
    val field7 = FixedIsoField(10, BcdFieldConverter())
    val field8 = FixedIsoField(8, BcdFieldConverter())
    val field9 = FixedIsoField(8, BcdFieldConverter())
    val field10 = FixedIsoField(8, BcdFieldConverter())

    /** System trace audit number (STAN) */
    val field11 = FixedIsoField(6, BcdFieldConverter())

    val field12 = FixedIsoField(6, BcdFieldConverter())
    val field13 = FixedIsoField(4, BcdFieldConverter())
    val field14 = FixedIsoField(4, BcdFieldConverter())
    val field15 = FixedIsoField(4, BcdFieldConverter())
    val field16 = FixedIsoField(4, BcdFieldConverter())
    val field17 = FixedIsoField(4, BcdFieldConverter())
    val field18 = FixedIsoField(4, BcdFieldConverter())
    val field19 = FixedIsoField(3, BcdFieldConverter())
    val field20 = FixedIsoField(3, BcdFieldConverter())
    val field21 = FixedIsoField(3, BcdFieldConverter())
    val field22 = FixedIsoField(3, BcdFieldConverter())
    val field23 = FixedIsoField(3, BcdFieldConverter())
    val field24 = FixedIsoField(3, BcdFieldConverter())
    val field25 = FixedIsoField(2, BcdFieldConverter())
    val field26 = FixedIsoField(2, BcdFieldConverter())
    val field27 = FixedIsoField(1, BcdFieldConverter())
    val field28 = FixedIsoField(28, AsciiFieldConverter())
    val field29 = FixedIsoField(28, AsciiFieldConverter())
    val field30 = FixedIsoField(28, AsciiFieldConverter())
    val field31 = FixedIsoField(28, AsciiFieldConverter())
    val field32 = DynamicIsoField(11, BcdFieldConverter())
    val field33 = DynamicIsoField(11, BcdFieldConverter())
    val field34 = DynamicIsoField(28, AsciiFieldConverter())
    val field35 = DynamicIsoField(37, AsciiFieldConverter())
    val field36 = DynamicIsoField(104, AsciiFieldConverter())
    val field37 = FixedIsoField(12, AsciiFieldConverter())
    val field38 = FixedIsoField(6, AsciiFieldConverter())
    val field39 = FixedIsoField(2, AsciiFieldConverter())
    val field40 = FixedIsoField(3, AsciiFieldConverter())

    /** Card acceptor terminal identification (TID) */
    val field41 = FixedIsoField(8, AsciiFieldConverter())
    val field42 = FixedIsoField(15, AsciiFieldConverter())
    val field43 = FixedIsoField(40, AsciiFieldConverter())
    val field44 = DynamicIsoField(25, AsciiFieldConverter())
    val field45 = DynamicIsoField(76, AsciiFieldConverter())
    val field46 = DynamicIsoField(999, AsciiFieldConverter())
    val field47 = DynamicIsoField(999, AsciiFieldConverter())
    val field48 = DynamicIsoField(999, AsciiFieldConverter())
    val field49 = FixedIsoField(3, AsciiFieldConverter())
    val field50 = FixedIsoField(3, AsciiFieldConverter())
    val field51 = FixedIsoField(3, AsciiFieldConverter())
    val field52 = FixedIsoField(64, BinaryFieldConverter())
    val field53 = FixedIsoField(16, BcdFieldConverter())
    val field54 = DynamicIsoField(120, AsciiFieldConverter())
    val field55 = DynamicIsoField(999, AsciiFieldConverter())
    val field56 = DynamicIsoField(999, AsciiFieldConverter())
    val field57 = DynamicIsoField(999, AsciiFieldConverter())
    val field58 = DynamicIsoField(999, AsciiFieldConverter())
    val field59 = DynamicIsoField(999, AsciiFieldConverter())
    val field60 = DynamicIsoField(999, AsciiFieldConverter())
    val field61 = DynamicIsoField(999, AsciiFieldConverter())
    val field62 = DynamicIsoField(999, AsciiFieldConverter())
    val field63 = DynamicIsoField(999, AsciiFieldConverter())
    val field64 = FixedIsoField(64, BinaryFieldConverter())

    protected val fields: List<BaseIsoField> = listOf(
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