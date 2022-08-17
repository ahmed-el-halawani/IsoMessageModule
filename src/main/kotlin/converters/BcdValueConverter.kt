package converters;

class BcdValueConverter(private val value: String) : BaseValueConverter {
    override fun toHex(): String {
        TODO("Not yet implemented")
    }

    override fun toBcd(): String {
        return value;
    }

    override fun toBase64(): String {
        TODO("Not yet implemented")
    }

    override fun toAscii(): String {
        TODO("Not yet implemented")
    }

    override fun toBinary(): String {
        TODO("Not yet implemented")
    }
}