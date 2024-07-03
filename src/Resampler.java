public class Resampler {
    /**
     * Begins resynthesis on sample given sanitized inputs from UTAU.
     * 
     * @param inputFile        input audio
     * @param outputFile       output audio
     * @param targetNote       note to resample to
     * @param diphoneVelocity  consonant velocity
     * @param flagArray        string array of given flags
     * @param offset           length of note
     * @param stationaryLength length of the engine-stretched portion
     * @param diphoneLength    length of the engine-preserved portion
     * @param cutoff           render offset from start of audio
     * @param volume           percentage of total final volume
     * @param modulation       percentage of true f0 displacement
     * @param tempo            beats per minute
     * @param pitchObject
     */
    public static void resample(String inputFile, String outputFile, int targetNote, float diphoneVelocity,
            String[] flagArray, int offset, int stationaryLength, float diphoneLength, float cutoff, float volume,
            float modulation,
            float tempo, String pitchObject) {
        // resample
    }
}
