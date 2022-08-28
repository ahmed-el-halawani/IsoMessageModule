package tlvIsoField

import IsoFieldConverter.AsciiFieldConverter
import IsoFieldConverter.BcdFieldConverter
import IsoFieldConverter.IntegerFieldConverter

class MyTlvConfiguration: TlvConfiguration() {
    override fun tagLength(): Int = 2

    override fun setConfigurationFieldsList(): List<TlvConfigurationField> = listOf(
//        TlvListField("0005"),
//        TlvListField("0006"),
//        TlvValueField("007",BcdFieldConverter()),
//        TlvValueField("008",AsciiFieldConverter()),

//        FileID(0001);
        TlvValueField("0001"),

//        Name(0002"),
        TlvValueField("0002"),

//        Package_Name(0003"),
        TlvValueField("0003"),

//        Total_Size(0004"),
        TlvValueField("0004"),

//        version(0005"),
        TlvValueField("0005"),

//        File_Type(0006"),
        TlvValueField("0006"),

//        Chunks(0007"),
        TlvListField("0007"),

//        Chunk_size(0008"),
        TlvValueField("0008"),

//        Chunk_Id(0009"),
        TlvValueField("0009"),

//        time(001f"),
        TlvValueField("001f"),

//        date(0020"),
        TlvValueField("0020"),

        //    enc_Data(0x002a),
        TlvValueField("002a", lengthConversion = IntegerFieldConverter()),

    )
}