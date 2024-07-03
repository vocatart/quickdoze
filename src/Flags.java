import java.util.Map;
import java.util.HashMap;

public class Flags {
    enum GenericFlags {
        GENDER_FACTOR,
        AMPLITUDE_FLUX,
        BREATHINESS,
        COMPRESSOR,
        NORMALIZATION,
        APERIODICITY,

    }

    public static final Map<String, Enum<?>> FLAGS = new HashMap<String, Enum<?>>() {
        {
            put("g", GenericFlags.GENDER_FACTOR);
        }
    };
}
