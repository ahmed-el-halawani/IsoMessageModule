package converters;

class AsciiValueConverter(private val value: String) : BaseValueConverter {
    override fun toHex(): String {
        TODO("Not yet implemented")
    }

    override fun toBcd(): String {
        TODO("Not yet implemented")
    }

    override fun toBase64(): String {
        TODO("Not yet implemented")
    }

    override fun toAscii(): String {
        return value;
    }

    override fun toBinary(): String {
        TODO("Not yet implemented")
    }

}

